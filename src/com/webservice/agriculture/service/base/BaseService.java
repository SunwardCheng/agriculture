package com.webservice.agriculture.service.base;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.AdminBase;
import com.webservice.agriculture.beans.BaseInfo;
import com.webservice.agriculture.dao.BaseInfoDAO;
import com.webservice.agriculture.dao.impl.BaseInfoDAOImpl;
import com.webservice.agriculture.service.fruits.FruitsService;
import com.webservice.agriculture.service.relation.RelationService;
/**
 * 
 * @author Sunward
 * 2017/5
 */
public class BaseService {
	private BaseInfoDAO baseInfoDAO = new BaseInfoDAOImpl();
	
	/**
	 * 获取BaseInfo实体类
	 * @param id
	 * @return
	 * 2017/05
	 */
	public BaseInfo getBaseById(String id){
		return baseInfoDAO.get(id);
	}
	
	/**
	 * 将BaseInfo实体类转化为JSONObject对象
	 * @param id
	 * @return
	 * 2017/05
	 */
	public JSONObject getBaseToJsonObjectByID(String id){
		JSONObject jsonObject = new JSONObject();
		BaseInfo baseInfo = getBaseById(id);
		
		FruitsService fruitsService = new FruitsService();
		RelationService relationService = new RelationService();
		//获取一个基地所拥有的水果ID
		List<String> idList = relationService.getFruitsIdOfBase(id);
		//获取一个基地所拥有的水果名
		List<String> fruitList = fruitsService.getFruitsToList(idList);
		JSONArray fruitArray = new JSONArray();
		for (String fruit:fruitList) {
			fruitArray.add(fruit);
		}
		
		jsonObject.put("baseId", baseInfo.getBaseID());
		jsonObject.put("baseName", baseInfo.getBaseName());
		jsonObject.put("baseAddr", baseInfo.getBaseAddre());
		jsonObject.put("longitude",baseInfo.getLongitude());
		jsonObject.put("latitude", baseInfo.getDimension());
		jsonObject.put("areaCount", baseInfo.getAreaCount());
		jsonObject.put("baseArea", baseInfo.getBaseArea());
		jsonObject.put("country", baseInfo.getCountry());
		jsonObject.put("province", baseInfo.getProvince());
		jsonObject.put("city", baseInfo.getCity());
		jsonObject.put("district", baseInfo.getCounty());
		jsonObject.put("imageUrl", baseInfo.getBaseImage());
		//每个基地所拥有的水果
		jsonObject.put("fruitsChecked", fruitArray);
		return jsonObject;
	}
	
	//将多个农场信息存放到JSONArray对象中
	public JSONArray getBasesToJsonArray(List<AdminBase> adminBases){
		//用来存放用户所拥有的农场信息
		JSONArray basesJsonArray = new JSONArray();
		//农场id
		String baseId = "";
		//用来存放单个农场的JSONOject对象
		JSONObject baseJsonObject = null;
		System.out.println(adminBases.size()+"########################");
		for (int i = 0; i < adminBases.size(); i++) {
			baseId = adminBases.get(i).getBaseID();
			baseJsonObject = getBaseToJsonObjectByID(baseId);
			basesJsonArray.add(baseJsonObject);
		}
		
		System.out.println(basesJsonArray.toString());
		return basesJsonArray;
	}
	
	
	/**
	 * //获取所有的农场名
	 * 以[ [ {farmName:"荔枝园",farmID:"base_001"},
     *    {farmName:"广西大学农园",farmID:"base_002"},
     *    ]
     *    	形式存放
	 * @return
	 * 2017/06/09
	 */
	public JSONArray findBaseNames(){
		List<BaseInfo> listbases = baseInfoDAO.getBaseNames();
		JSONArray bases = new JSONArray();
		JSONObject baseJsonObject = null;
		for (BaseInfo base:listbases) {
			baseJsonObject = new JSONObject();
			baseJsonObject.put("farmName", base.getBaseName());
			baseJsonObject.put("farmID", base.getBaseID());
			bases.add(baseJsonObject);
		}
		
		return bases;
	}
	
	/**
	 * 获取所有农场名，以
	 * 以[ "荔枝园","广西大学农园",西乡塘果园"]
	 * 形式存放
	 * @return
	 * 2017/06/09
	 */
	public JSONArray findBaseNames2(){
		List<BaseInfo> listbases = baseInfoDAO.getBaseNames();
		JSONArray bases = new JSONArray();
		for (BaseInfo base:listbases) {
			bases.add(base.getBaseName());
		}
		
		return bases;
	}
	/**
	 * 根据用户所拥有的农场名获取农场ID
	 * @param farms
	 * @return
	 * 2017/06/09
	 */
	public List<String> findIdByNames(String farms){
		
		List<String> baseList = new ArrayList<String>();
		JSONArray bases = JSONArray.fromObject(farms);
		String baseName = "";
		for (int i = 0;i<bases.size();i++) {
			baseName = bases.getString(i);
			baseList.add(baseInfoDAO.getIdByName(baseName));
		}
		
		return baseList;
	}
	/**
	 * 根据用户所拥有的农场的ID来获取农场名
	 * @param farms
	 * @return
	 * 2017/05
	 */
	public List<String> findNameByIDs(List<String> IDList){
		
		List<String> baseList = new ArrayList<String>();
		for (String ID : IDList) {
			baseList.add(baseInfoDAO.get(ID).getBaseName());
		}
		
		return baseList;
	}
	
	/**
	 * 添加农场
	 * @param user
	 * 2017/06/10
	 */
	public int addFarm(BaseInfo base){
		
		int i = -1;
		try {
			i = baseInfoDAO.insert(base);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return i;
	}
	/**
	 * 获取数据库中农场ID最大的
	 * @return
	 * 2017/06/10
	 */
	public String findMaxFarmID(){
		return baseInfoDAO.getMaxID();
	}
	/**
	 * 更新农场信息
	 * @param base
	 * @return
	 * 2017/06/11
	 */
	public int updateForm(BaseInfo base){
		int i = -1;
		try {
			i =  baseInfoDAO.update(base);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return i;
	}
	
	/**
	 * 删除农场
	 * @param baseId
	 */
	public void deleteBase(String baseId) {
		baseInfoDAO.delete(baseId);
	}
}
