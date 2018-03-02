package com.webservice.agriculture.controller.set_info;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.AdminBase;
import com.webservice.agriculture.beans.Administrator;
import com.webservice.agriculture.beans.BaseInfo;
import com.webservice.agriculture.beans.Fruits;
import com.webservice.agriculture.beans.Relation;
import com.webservice.agriculture.controller.Utils;
import com.webservice.agriculture.service.admin_base.AdminBaseService;
import com.webservice.agriculture.service.administrator.AdministratorService;
import com.webservice.agriculture.service.base.BaseService;
import com.webservice.agriculture.service.environment.EnvironmentService;
import com.webservice.agriculture.service.fruits.FruitsService;
import com.webservice.agriculture.service.relation.RelationService;

/**
 * 
 * @author Sunward
 * 2017/6
 */
public class SetInfo {
	private static AdminBaseService adminBaseService = new AdminBaseService();
	private static AdministratorService administratorService = new AdministratorService();
	private static BaseService baseService = new BaseService();
	private static FruitsService fruitsService = new FruitsService();
	private static RelationService relationService = new RelationService();
	private static EnvironmentService environmentService = new EnvironmentService();
	/**
	 * 
	 * @param userInfo
	 * @param registTime
	 * @return
	 * 2017/06/08
	 */
	public static Administrator getUserBean(JSONObject userInfo,String registTime){
		int userID = -1;
		String userName = userInfo.getString("name");
		String password = userInfo.getString("password");
		String authority = userInfo.getString("authority");
		String phone = userInfo.getString("phone");
		String email = userInfo.getString("email");
		String depict = userInfo.getString("depict");
		
		registTime = Utils.formateTime(registTime);
		//将userID设为-1加入实体类中是因为注册用户是还没有ID
		Administrator user = new Administrator();
		user.setAdminID(userID);
		user.setUserName(userName);
		user.setPassword(password);
		user.setAuthority(authority);
		user.setDepict(depict);
		user.setEmail(email);
		user.setPhone(phone);
		user.setRegisTime(registTime);
		return user;
	}
	/**
	 * 
	 * @param farmInfo
	 * @return
	 * 2017/06/09
	 */
	public static BaseInfo getFarmBean(JSONObject farmInfo){
		
		String farmID = "base_000";
		String farmName = farmInfo.getString("farmName");
		String farmArea = farmInfo.getString("baseArea");
		String country = farmInfo.getString("country");
		String province = farmInfo.getString("province");
		String imageUrl = "/agriculture/"+farmInfo.getString("imageUrl");
		String city = farmInfo.getString("city");
		String distinct = farmInfo.getString("district");//县或区
		String address = farmInfo.getString("baseAddr");
		String longitude = farmInfo.getString("longitude");
		String latitude = farmInfo.getString("latitude");
		
		BaseInfo farm = new BaseInfo();
		
		//将farmID设为 base_000 加入实体类中是因为刚注册农场还没有ID，只是临时ID
		farm.setBaseID(farmID);
		farm.setBaseName(farmName);
		farm.setCountry(country);
		farm.setProvince(province);
		farm.setCity(city);
		farm.setCounty(distinct);
		farm.setBaseAddre(address);
		farm.setBaseArea(farmArea);
		farm.setLongitude(longitude);
		farm.setDimension(latitude);
		farm.setBaseImage(imageUrl);
		
		return farm;
	}
	/**
	 * 
	 * @param fruitInfo
	 * @return
	 * 2017/06/13
	 */
	public static Fruits getFruitBean(JSONObject fruitInfo){	
		String fruitID = "fruit_000";
		String fruitName = fruitInfo.getString("fruitName");
		String feature = fruitInfo.getString("feature");
		String maxAirTemp = fruitInfo.getString("maxAirTemp");
		String minAirTemp = fruitInfo.getString("minAirTemp");
		String maxAirHumi = fruitInfo.getString("maxAirHumi");
		String minAirHumi = fruitInfo.getString("minAirHumi");
		String maxSoilTemp = fruitInfo.getString("maxSoilTemp");//县或区
		String minSoilTemp = fruitInfo.getString("minSoilTemp");
		String maxSoilHumi = fruitInfo.getString("maxSoilHumi");
		String minSoilHumi = fruitInfo.getString("minSoilHumi");
		String maxLight = fruitInfo.getString("maxLight");
		String minLight = fruitInfo.getString("minLight");
		
		Fruits fruit = new Fruits();
		
		//将fruitID设为 fruit_000 加入实体类中是因为刚添加还没有ID，只是临时ID
		fruit.setFruitID(fruitID);
		fruit.setName(fruitName);
		fruit.setFeature(feature);
		fruit.setMaxAirTemp(Float.parseFloat(maxAirTemp));
		fruit.setMinAirTemp(Float.parseFloat(minAirTemp));
		fruit.setMaxAirHumi(Float.parseFloat(maxAirHumi));
		fruit.setMinAirHumi(Float.parseFloat(minAirHumi));
		fruit.setMaxSoilTemp(Float.parseFloat(maxSoilTemp));
		fruit.setMinSoilTemp(Float.parseFloat(minSoilTemp));
		fruit.setMaxSoilHumi(Float.parseFloat(maxSoilHumi));
		fruit.setMinSoilHumi(Float.parseFloat(minSoilHumi));
		fruit.setMaxLight(Float.parseFloat(maxLight));
		fruit.setMinLight(Float.parseFloat(minLight));
		
		return fruit;
	}
	/**
	 * 管理员添加用户
	 * @param request
	 * @return
	 * 2017/06/08
	 */
	public static JSONObject addUser(JSONObject userInfo,String registTime){
		Administrator user = getUserBean(userInfo,registTime);
		JSONObject msg = administratorService.addUser(user);
		return msg;
		
	}
	/**
	 * 管理员修改用户信息
	 * @param user
	 */
	public static JSONObject  modifyUserAll(Administrator user){
			
			int i = administratorService.modifyAllInfo(user);
			JSONObject msg = new JSONObject();
			if (i>0) {
				msg.put("msg", "修改成功！");
			}else {
				msg.put("msg", "修改失败，请下次重试");
			}
			return msg;
	}
	/**
	 * 给用户添加农场
	 * @param farms
	 * @param request
	 * 2017/06/08
	 */
	public static void addfarmToUser(int userID ,String farms) {
		List<String> farmList = baseService.findIdByNames(farms);
		adminBaseService.addBaseToUser(userID, farmList);
	}
	
	/**
	 * 删除用户农场
	 */
	public static void deleteFarmFromUser(int userID) {
		adminBaseService.deleteBase(userID);
	}
	/**
	 * //获取所有的农场名
	 * 以[ {farmName:"荔枝园",farmID:"base_001"},
     *    {farmName:"广西大学农园",farmID:"base_002"},
     *    ]
     *    	形式存放
	 * @return
	 * 2017/06/08
	 */
	public static JSONArray getAllFarms(){
		JSONArray farmsArray = baseService.findBaseNames();
		
		return farmsArray;
	}
	
	
	
	/**
	 * 获取所有农场名，以
	 * 以[ "荔枝园","广西大学农园",西乡塘果园"]
	 * 形式存放
	 * @return
	 * 2017/06/08
	 */
	public static JSONArray getAllFarms2(){
		JSONArray farmsArray = baseService.findBaseNames2();
		
		return farmsArray;
	}
	
	/**
	 * 管理员修改页面
	 * @param userID
	 * @return
	 * 2016/06/09
	 */
	public static JSONObject adminGetUserInfo(int userID){
		//获取用户信息
		JSONObject userInfo = farmerGetUserInfo(userID);
		//获取用户所拥有的农场ID
		List<AdminBase> adminBaseList = adminBaseService.getAdminBasesByAdminID(userID);
		List<String> IDList = new ArrayList<String>();
		for(AdminBase adminbase : adminBaseList){
			IDList.add(adminbase.getBaseID());
		}
		//前端显示用户所拥有的农场名
		userInfo.put("farmsChecked", baseService.findNameByIDs(IDList));
		
		return userInfo;
	}
	
	
	/**
	 * 获取所有非管理员用户(农场主)
	 * 超级管理员修改时需要
	 * @return
	 * 2017/06/08
	 */
	public static JSONArray getFarmOwners(){
		JSONArray farmOwners = administratorService.findUsers();
		return farmOwners;
	}
	
	/**
	 * 获取用户信息(农场主修改信息页面)
	 * @param userID
	 * @return
	 * 2017/06/09
	 */
	public static JSONObject farmerGetUserInfo(int userID){
		JSONObject userObject = administratorService.getUserToJsonObject(userID);
		return userObject;
	}
	
	/**
	 * 农场主修改密码
	 * @param userID
	 * @param oldPass
	 * @param newPass
	 * @return
	 * 2017/06/09
	 */
	public static JSONObject modifyPassword(int userID,String oldPass,String newPass){
		return administratorService.modifyPass(userID, oldPass, newPass);
	}
	
	/**
	 * 农场主修改用信息
	 * @param user
	 * @return
	 * 2017/06/09
	 */
	public static JSONObject modifyUserInfo(Administrator user){
		
		int i = administratorService.modifyInfo(user);
		JSONObject msg = new JSONObject();
		if (i>0) {
			msg.put("msg", "修改成功！");
		}else {
			msg.put("msg", "修改失败，请下次重试");
		}
		return msg;
	}
	/**
	 * 获取所有的水果
	 * @return
	 *  2017/06/09
	 */
	public static JSONArray getAllFruits(){
		JSONArray fruitsArray = fruitsService.findFruits();
		return fruitsArray;
	}
	/**
	 * 获取基地(农场)信息
	 * @param baseID
	 * @return
	 *  2017/06/09
	 */
	public static JSONObject getFarmInfo(String baseID){
		JSONObject farmJsonObject = baseService.getBaseToJsonObjectByID(baseID);
		return farmJsonObject;
	}
	
	/**
	 * 添加农场
	 * @param base
	 * @return
	 *  2017/06/10
	 */
	public static JSONObject addFarm(JSONObject base){
		BaseInfo baseInfo = getFarmBean(base);
		String maxFarmID = baseService.findMaxFarmID();
		//给新农场创建一个ID 如base_005
		String farmID = "";
		int id = Integer.valueOf((maxFarmID.split("_")[1])).intValue();
		
		//得到新的农场ID
		id = id + 1;
		farmID = "base_"+id;
		
		baseInfo.setBaseID(farmID);
		int i = baseService.addFarm(baseInfo);
		
		JSONObject msg = new JSONObject();
		if (i==-1) {
			msg.put("msg", "添加失败，请更换用户名");
			msg.put("state", 0);
		}else {
			msg.put("msg", "添加成功");
			msg.put("state", 1);
			msg.put("farmID", farmID);
		}
		return msg;
	}
	/**
	 * 给一个农场添加作物
	 * @param farmID
	 * @param fruits
	 * 2017/06/10
	 */
	public static void addfruitsToFarm(String farmID,String fruits){
		//得到数据库中一个农场的关系列表
		List<Relation> relationList = relationService.getByBaseId(farmID);
		
		//得到要添加的水果ID
		List<String> fruitIDList = fruitsService.findIdByNames(fruits);
		
		//用来存放一个农场所有作物ID
		List<String> fruitIDs = new ArrayList<String>();
		
		//用来存放要加入农场的作物关系
		List<Relation> addRelations = new ArrayList<Relation>();
		
		/**
		 * 1.当农场以前中有作物时，需要判断现在添加的作物和农场的作物是否相同
		 * 相同就不需要再添加，不同就添加
		 */
		if (!relationList.isEmpty()) {
			//得到农场所有的作物ID集合
			for (Relation relation:relationList) {
				fruitIDs.add(relation.getFruitID());
			}
			/**
			 * 1.1 向农场添加作物
			 */
			//要添加的作物ID：fruitID，农场中拥有的作物：fruitIDs
			for (String fruitID:fruitIDList) {
				//若添加的作物农场中不存在，就加入，否则不加入
				if (!fruitIDs.contains(fruitID)) {
					
					//获取该农场最大的areaID
					int areaID = relationService.findMaxAreaID(farmID);
					//给新加入的作物添加areaID和SensorID
					
					Relation sensorRelation = relationService.getByBaseIdAndAreaId(farmID, String.valueOf(areaID));
					String sensorID = sensorRelation.getSensorID();
					
					areaID = areaID + 1;
					
					//取sensorID的最后两位，最后两位代表传感器编号，也是基地的区域编号，中间两位代表基地号
					String lastID = "";
					if (areaID<10) {
						lastID = "0"+areaID;
					}else {
						lastID = "" + areaID;
					}
					
					//新的SensorID
					String newSensorID = sensorID.substring(0, 4) + lastID;
					
					Relation relation = new Relation();
					relation.setBaseID(farmID);
					relation.setFruitID(fruitID);
					relation.setAreaID(areaID);
					relation.setSensorID(newSensorID);
					//一次可能添加多种作物
					addRelations.add(relation);
					
				}
			}
			/**
			 * 1.2 农场删除作物
			 */
			//农场所有的作物ID：fruitID，若在要添加的作物当中，则不操作，否则删除,同时还要删除该作物的五种数据信息
			for (String fruitID:fruitIDs) {
				if (!fruitIDList.contains(fruitID)) {
					//得到sensorID
					Relation relation = relationService.getByBaseIdAndFruitId(farmID, fruitID);
					
					//删除作物
					relationService.deleteFarmFruit(farmID, fruitID);
					
					//删除作物的五种数据信息
					String sensorID = relation.getSensorID();
					//删除数据
					environmentService.deleteSensor(sensorID);
				}
				
			}
			
			/**
			 * 如果要要添加的作物以前没有，则添加
			 */
			if (!addRelations.isEmpty()) {
				System.out.println(addRelations);
				relationService.addFruits(addRelations);
			}
			
		}else {
			/**
			 * 2.农场中以前没有作物,添加所有的作物
			 */
			for (int i = 0;i<fruitIDList.size();i++) {
				
				//SensorID的中间两位编号是农场ID后两位，SensorID 的最后两位编号是AreaID的编号
				String interID = farmID.substring(6, 8);//不包括8
				String sensorID = "";
				if (i+1<10) {
					sensorID = "01" + interID + "0" + (i + 1);
				}else {
					sensorID = "01" + interID + (i + 1);
				}
				
				Relation relation = new Relation();
				relation.setBaseID(farmID);
				relation.setFruitID(fruitIDList.get(i));
				relation.setAreaID(i + 1);
				relation.setSensorID(sensorID);
				addRelations.add(relation);
			}
			//批量添加
			relationService.addFruits(addRelations);
		}
		
	}
	
	/**
	 * 修改农场信息
	 * @param base
	 * @return
	 * 2017/06/11
	 */
	public static JSONObject modifyForm(JSONObject base){
		String farmID = base.getString("baseId");
		BaseInfo baseInfo = getFarmBean(base);
		baseInfo.setBaseID(farmID);
		
		int i = baseService.updateForm(baseInfo);
		JSONObject msg = new JSONObject();
		if (i==-1) {
			msg.put("msg", "修改失败，请更换用户名");
		}else {
			msg.put("msg", "修改成功！");
		}
		return msg;
	}
	/**
	 * 获取所有管理员
	 * @return
	 * 2017/06/11
	 */
	public static List<Administrator> getAllAdmins(String admin) {
		return administratorService.findAdmins(admin);
	}
	/**
	 * 删除农场
	 * @param farmID
	 */
	public static void deleteFarm(String farmID) {
		//删除农场
		baseService.deleteBase(farmID);
		//删除用户农场表中所有该农场
		adminBaseService.deleteAllBase(farmID);
		//得到农场的关系
		List<Relation> relationList = relationService.getByBaseId(farmID);
		//删除该农场所有作物的五种数据
		for (Relation relation:relationList) {
			environmentService.deleteSensor(relation.getSensorID());
		}
		//删除关系表(relation)中所有的该农场的关系
		relationService.deleteFarm(farmID);
		
	}

	/**
	 * 删除用户
	 * @param userID
	 * 2017/06/12
	 */
	public static void deleteUser(int userID) {
		//删除用户
		administratorService.deleteUser(userID);
		//删除用户农场关系表的该用户数据表(admin_base)
		adminBaseService.deleteBase(userID);
		
	}
	/**
	 * 添加作物
	 * @param fruitInfo
	 * @return
	 * 2017/06/13
	 */
	public static JSONObject addFruit(JSONObject fruitInfo) {
		
		Fruits fruit = getFruitBean(fruitInfo);
		
		//取得数据库中最大水果ID
		String fruitID =  fruitsService.findMaxID();
		int lastID = Integer.valueOf(fruitID.split("_")[1]).intValue();
		lastID = lastID + 1;
		
		//得到要添加的水果ID
		fruitID = "fruit_"+lastID;
		fruit.setFruitID(fruitID);
		
		int i = fruitsService.addFruit(fruit);
		JSONObject msg = new JSONObject();
		if (i==-1) {
			msg.put("msg", "添加失败，请更换作物名");
			msg.put("state", 0);
		}else {
			msg.put("msg", "添加成功");
			msg.put("state", 1);
			msg.put("fruitID", fruitID);
		}
		
		return msg;
	}
	
	public static JSONObject getFruit(String fruitID){
		
		Fruits fruit = fruitsService.getFruitById(fruitID);
		JSONObject fruitObject = new JSONObject();
		
		fruitObject.put("fruitID", fruitID);
		fruitObject.put("fruitName", fruit.getName());
		fruitObject.put("feature", fruit.getFeature());
		fruitObject.put("maxAirTemp", fruit.getMaxAirTemp());
		fruitObject.put("minAirTemp", fruit.getMinAirTemp());
		fruitObject.put("maxAirHumi", fruit.getMaxAirHumi());
		fruitObject.put("minAirHumi", fruit.getMinAirHumi());
		fruitObject.put("maxSoilTemp", fruit.getMaxSoilTemp());
		fruitObject.put("minSoilTemp", fruit.getMinSoilTemp());
		fruitObject.put("maxSoilHumi", fruit.getMaxSoilHumi());
		fruitObject.put("minSoilHumi", fruit.getMinSoilHumi());
		fruitObject.put("maxLight", fruit.getMaxLight());
		fruitObject.put("minLight", fruit.getMinLight());
		
		return fruitObject;
	}
	
	/**
	 * 修改水果信息
	 * @param fruitForm
	 * @return
	 * 2017/06/15
	 * CXY
	 */
	public static JSONObject modifyFruit(JSONObject fruitForm) {
		
		Fruits fruit = getFruitBean(fruitForm);
		String fruitID = fruitForm.getString("fruitID");
		fruit.setFruitID(fruitID);
		
		int i = fruitsService.updateFruit(fruit);
		JSONObject msg = new JSONObject();
		if (i>0) {
			msg.put("msg", "修改成功！");
		}else {
			msg.put("msg", "修改失败，请稍候重试!");
		}
		
		return msg;
		
	}
	
	/**
	 * 删除水果信息
	 * @param fruitID
	 * 2017/06/15
	 * CXY
	 */
	public static void deleteFruit(String fruitID){
		fruitsService.deleteFruit(fruitID);
	}
}
 