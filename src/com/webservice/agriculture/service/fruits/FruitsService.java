package com.webservice.agriculture.service.fruits;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.Fruits;
import com.webservice.agriculture.dao.FruitsDAO;
import com.webservice.agriculture.dao.impl.FruitsDAOjdbcImpl;
/**
 * 
 * @author Sunward
 * 2017/05
 */
public class FruitsService {
	FruitsDAO fruitsDAO = new FruitsDAOjdbcImpl();
	
	/**
	 * 得到水果的ID
	 * @param name
	 * @return
	 * 2017/05
	 */
	public String  getFruitIdByName(String name) {
		Fruits fruits = fruitsDAO.getByName(name);
		String fruitId = fruits.getFruitID();
		return fruitId;
	}
	
	public  Fruits  getFruitById(String id) {
		return fruitsDAO.get(id);
	}
	
	/**
	 * 根据水果ID获取水果存放到JSONArray中
	 * @param idList
	 * @return
	 * 2017/05
	 */
	public JSONArray getFruitsToJsonArray(List<String> idList){
		Fruits fruit = null;
		//用来存放一个基地所有水果
		JSONArray fruitsArray = new JSONArray();
		JSONObject fruitObject = null;
		for (int i = 0; i < idList.size(); i++) {
			fruitObject = new JSONObject();
			fruit = getFruitById(idList.get(i));
			fruitObject.put("fruitID", fruit.getFruitID());
			fruitObject.put("fruitName", fruit.getName());
			fruitsArray.add(fruitObject);
		}
		return fruitsArray;
	}
	
	/**
	 * 根据水果ID获取水果存放到List中
	 * @param idList
	 * @return
	 * 2017/05
	 */
	public List<String> getFruitsToList(List<String> idList){
		Fruits fruit = null;
		List<String> fruitList = new ArrayList<String>();
		//用来存放一个基地所有水果
		for (int i = 0; i < idList.size(); i++) {
			fruit = getFruitById(idList.get(i));
			fruitList.add(fruit.getName());
		}
		return fruitList;
	}
	
	/**
	 * 获取实时值是否超过了阈值
	 * @param realTimeData
	 * @param fruitId
	 * @return
	 * 2017/05
	 */
	public JSONObject compareToThreshold(JSONObject realTimeData,String fruitId){
		//获取水果实体类
		Fruits fruit = fruitsDAO.get(fruitId);

		//存放实时数据和阈值比较结果
		boolean airTempHigh = false;//高于空气阈值
		boolean airTempLow = false;//低于空气阈值
		boolean airHumiHigh = false; 
		boolean airHumiLow = false; 
		boolean lightHigh = false;
		boolean lightLow = false;
		boolean soilTempHigh = false;
		boolean soilTempLow = false;
		boolean soilHumiHigh = false;
		boolean soilHumiLow = false;
		
		//存放比较结果
		JSONObject compareToThreshold = new JSONObject();
		if (Float.parseFloat(realTimeData.get("airTemp").toString())<fruit.getMinAirTemp()) {
			airTempLow = true ;
		}
		if (Float.parseFloat(realTimeData.get("airTemp").toString())>fruit.getMaxAirTemp()) {
			airTempHigh = true ;
		}
		if (Float.parseFloat(realTimeData.get("airHumi").toString())<fruit.getMinAirHumi()) {
			airHumiLow = true ;
		}
		if (Float.parseFloat(realTimeData.get("airHumi").toString())>fruit.getMaxAirHumi()) {
			airHumiHigh = true ;
		}
		if (Float.parseFloat(realTimeData.get("soilTemp").toString())<fruit.getMinSoilTemp()) {
			soilTempLow = true ;
		}
		if (Float.parseFloat(realTimeData.get("soilTemp").toString())>fruit.getMaxSoilTemp()) {
			soilTempHigh = true ;
		}
		if (Float.parseFloat(realTimeData.get("soilHumi").toString())<fruit.getMinSoilHumi()) {
			soilHumiLow = true ;
		}
		if (Float.parseFloat(realTimeData.get("soilHumi").toString())>fruit.getMaxSoilHumi()) {
			soilHumiHigh = true ;
		}
		if (Float.parseFloat(realTimeData.get("light").toString())<fruit.getMinLight()) {
			lightLow = true ;
		}
		if (Float.parseFloat(realTimeData.get("light").toString())>fruit.getMaxLight()) {
			lightHigh = true ;
		}
		
		compareToThreshold.put("airTempLow", airTempLow);
		compareToThreshold.put("airTempHigh", airTempHigh);
		compareToThreshold.put("airHumiLow", airHumiLow);
		compareToThreshold.put("airHumiHigh", airHumiHigh);
		compareToThreshold.put("lightLow", lightLow);
		compareToThreshold.put("lightHigh", lightHigh);
		compareToThreshold.put("soilTempLow", soilTempLow);
		compareToThreshold.put("soilTempHigh", soilTempHigh);
		compareToThreshold.put("soilHumiLow", soilHumiLow);
		compareToThreshold.put("soilHumiHigh", soilHumiHigh);
		
		return compareToThreshold;
	}
	/**
	 * 获得所有水果名
	 * @return
	 */
	public JSONArray findFruits(){
		List<Fruits> fruits = fruitsDAO.getAll();
		JSONArray fruitArray = new JSONArray();
		JSONObject fruitObject = null;
		for (Fruits fruit:fruits) {
			fruitObject = new JSONObject();
			fruitObject.put("fruitID", fruit.getFruitID());
			fruitObject.put("fruitName", fruit.getName());
			fruitArray.add(fruitObject);
		}
		
		return fruitArray;
	}
	
	/**
	 * 获取水果的信息
	 * @param fruitID
	 * @return
	 * 2017/06/10
	 */
	public JSONObject findFruit(String fruitID){
		Fruits fruits = fruitsDAO.get(fruitID);
		JSONObject fruitJsonObject = new JSONObject();
		fruitJsonObject.put("fruitID", fruits.getFruitID());
		fruitJsonObject.put("fruitName", fruits.getName());
		fruitJsonObject.put("feature", fruits.getFeature());
		fruitJsonObject.put("depict", fruits.getDepict());
		fruitJsonObject.put("minAirTemp", fruits.getMinAirTemp());
		fruitJsonObject.put("maxAirTemp", fruits.getMaxAirTemp());
		fruitJsonObject.put("minSoilTemp", fruits.getMinSoilTemp());
		fruitJsonObject.put("maxSoilTemp", fruits.getMaxSoilTemp());
		fruitJsonObject.put("minAirHumi", fruits.getMinAirHumi());
		fruitJsonObject.put("maxAirHumi", fruits.getMaxAirHumi());
		fruitJsonObject.put("minSoilHumi", fruits.getMinSoilHumi());
		fruitJsonObject.put("maxSoilHumi", fruits.getMaxSoilHumi());
		fruitJsonObject.put("minLight", fruits.getMinLight());
		fruitJsonObject.put("maxLight", fruits.getMaxLight());
		
		return fruitJsonObject;
		
	}

	/**
	 * 根据水果名获取水果ID
	 * @param fruits
	 * @return
	 * 2017/05
	 */
	public List<String> findIdByNames(String fruits) {
		List<String> fruitlList = new ArrayList<String>();
		JSONArray fruitArray = JSONArray.fromObject(fruits);
		String fruitName = "";
		for (int i = 0;i<fruitArray.size();i++) {
			fruitName = fruitArray.getString(i);
			fruitlList.add(fruitsDAO.getIdByName(fruitName));
		}
		
		return fruitlList;
	}
	/**
	 * 添加水果
	 * @param fruit
	 * @return
	 * 2017/06/15
	 */
	public int addFruit(Fruits fruit) {
		int i = -1;
		try {
			i = fruitsDAO.intsert(fruit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 获取水果表fruit 的最大水果ID
	 * 2017/06/15
	 */
	public String findMaxID(){
		return fruitsDAO.getMaxID();
	}
	
	public int updateFruit(Fruits fruits){
		int i = -1;
		i = fruitsDAO.update(fruits);
		return i;
	}
	
	public void deleteFruit(String fruitID){
		fruitsDAO.delete(fruitID);
	}
}
