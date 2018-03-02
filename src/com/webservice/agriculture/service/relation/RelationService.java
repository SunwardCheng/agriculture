package com.webservice.agriculture.service.relation;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.Fruits;
import com.webservice.agriculture.beans.Relation;
import com.webservice.agriculture.dao.RelationDAO;
import com.webservice.agriculture.dao.impl.RelationDAOImpl;
/**
 * 
 * @author Sunward
 * 2017/05
 */
public class RelationService {
	
	private RelationDAO relationDAO = new RelationDAOImpl(); 
	
	/**
	 * 根据基地和对应水果id得到传感器id
	 * @param baseId
	 * @param fruitId
	 * @return
	 * 2017/05
	 */
	public Relation getByBaseIdAndFruitId(String baseId,String fruitId){
		return relationDAO.get(baseId, fruitId); 
	}
	/**
	 * 根据基地ID 和区域ID 获取Relation
	 * @param baseId
	 * @param areaID
	 * @return
	 * 2017/06/13
	 */
	public Relation getByBaseIdAndAreaId(String baseId,String areaID){
		return relationDAO.getByArea(baseId, areaID);
	}
	/**
	 * 获取传感器ID
	 * @param baseId
	 * @param fruitId
	 * @return
	 * 2017/05
	 */
	public String getSensorId(String baseId,String fruitId){
		Relation relation = getByBaseIdAndFruitId(baseId, fruitId);
		return relation.getSensorID();
	}
	
	/**
	 * 根据基地id获取
	 * @param baseId
	 * @return
	 * 2017/05
	 */
	public List<Relation> getByBaseId(String baseId) {
		return relationDAO.getByBaseId(baseId);
	}
	
	/**
	 * 获取一个基地水果的id
	 * @param baseId
	 * @return
	 * 2017/05
	 */
	public List<String> getFruitsIdOfBase(String baseId) {
		List<Relation> relations = getByBaseId(baseId);
		
		//用来存放一个基地拥有的水果种类
		List<String > fruits = new ArrayList<String>();
		for (int i = 0; i < relations.size(); i++) {
			fruits.add(relations.get(i).getFruitID());
		}
		return fruits;
	}
	/**
	 * 获取农场传感器ID最大的值
	 * @param baseID
	 * @return
	 */
	public int findMaxAreaID(String baseID){
		return relationDAO.getMaxAreaID(baseID);
	}
	/**
	 * 向农场添加作物
	 */
	public void addFruits(List<Relation> relationList){
		relationDAO.batch(relationList);
	}
	/**
	 * 删除农场的关系
	 * @param baseId
	 */
	public void deleteFarm(String baseId) {
		relationDAO.delete(baseId);
	}
	
	public void deleteFarmFruit(String farmID,String fruitID){
		relationDAO.deleteFruit(farmID, fruitID);
	}
}
