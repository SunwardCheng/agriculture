package com.webservice.agriculture.controller.cropdata;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import com.webservice.agriculture.controller.Utils;
import com.webservice.agriculture.service.base.BaseService;
import com.webservice.agriculture.service.environment.EnvironmentService;
import com.webservice.agriculture.service.fruits.FruitsService;
import com.webservice.agriculture.service.relation.RelationService;

public class CropData  {
	
	
	private static BaseService baseService = new BaseService();
	private static EnvironmentService environmentService = new EnvironmentService();
	private static FruitsService fruitsService = new FruitsService();
	private static RelationService relationService = new RelationService();

	
	/**
	 *实时数据，页面每隔一分钟请求
	 * @param baseId
	 * @param fruitName
	 * @return
	 */
	public static JSONObject realTimeData(String baseId,String fruitName){
		//获得水果ID
		String fruitId = fruitsService.getFruitIdByName(fruitName);
		//获取传感器ID
		String sensorId = relationService.getSensorId(baseId, fruitId);
		JSONObject realTimeData = environmentService.getMostNewData(sensorId);
		
		return realTimeData;
	}
	/**
	 * cropInfo.html页面初始化数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static JSONObject initData(String baseId,String date) {

		String yesterday = Utils.yesterday();
		
		
		//获取单个农场信息
		//JSONObject baseJsonObject = baseService.getBaseToJsonObjectByID(baseId);

		//一个基地种植的水果id
		List<String> idList = relationService.getFruitsIdOfBase(baseId);
		//获取一个基地所有水果名(JSONObject对象格式)
		//JSONArray fruits = fruitsService.getFruitsToJsonArray(idList);
		
		//获取传感器ID,默认是idList中的第一个水果的id获得的传感器id
		String sensorId = relationService.getSensorId(baseId, idList.get(0));
		
		//实时数据(最新数据)
		JSONObject realTimeData = environmentService.getMostNewData(sensorId);
		
		//一天的数据
		JSONObject dayData = environmentService.getDayDataToJsonArray(sensorId, date);
		
		//昨天的数据
		JSONArray yesterdayData = environmentService.getYesterdayData(sensorId, yesterday);		
		
		//实时数据和阈值比较结果
		JSONObject compareToThreshold = fruitsService.compareToThreshold(realTimeData, idList.get(0));
		
		//初始化数据
		JSONObject initData = new JSONObject();
			
		
		
		//initData.put("farmInfo", baseJsonObject);
		//initData.put("fruits", fruits);
		
		if (!(realTimeData.get("airTemp") instanceof JSONNull)) {
			initData.put("realTimeData", realTimeData);
		}
		if (yesterdayData.getJSONObject(1).get("yesAirTemp") !=null) {
			initData.put("yesterdayData", yesterdayData);
		}
		initData.put("compareToThreshold", compareToThreshold);
		
		if (!(dayData.get("dayTime") instanceof JSONNull)) {
			initData.put("dayData", dayData);
		}
		System.out.println(yesterdayData);
		System.out.println(dayData);
		return initData;
	}
	
	/**
	 * cropInfo.html页面查询某一天的数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static JSONObject queryDayData(String baseId,String fruitName,String date)
			throws ServletException, IOException {
		
	/*	String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");
		String date=request.getParameter("date");*/
		
		
		//获取水果ID
		String fruitId = fruitsService.getFruitIdByName(fruitName);
		//获取传感器ID
		String sensorId = relationService.getSensorId(baseId, fruitId);
		//一天的数据
		JSONObject dayData = environmentService.getDayDataToJsonArray(sensorId, date);
		
		return dayData;
	}
	
	/**
	 * 获取单个农场信息
	 * @param baseId
	 * @return
	 */
	public static JSONObject getBaseInfo(String baseId) {
		return baseService.getBaseToJsonObjectByID(baseId);
	}
	
	/**
	 * cropInfo.html 页面查询一个星期的数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static JSONObject queryWeekData(String baseId,String fruitName)
			throws ServletException, IOException {
		/*String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");*/
		
		
		//获取水果ID
		String fruitId = fruitsService.getFruitIdByName(fruitName);
		//获取传感器ID
		String sensorId = relationService.getSensorId(baseId, fruitId);
		//获取一周前的日期
		String aWeekAgoDate = Utils.aWeekAgoDate();
		
		//一周的数据
		JSONObject weekDada = environmentService.getRangeTimeDataToJsonArray(sensorId, aWeekAgoDate, Utils.todayDate());
		
		return weekDada;
	}
	/**
	 * cropInfo.html 页面查询一个星期的数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static JSONObject queryMonthData(String baseId,String fruitName){
	/*	String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");
		*/
		//获取水果ID
		String fruitId = fruitsService.getFruitIdByName(fruitName);
		//获取传感器ID
		String sensorId = relationService.getSensorId(baseId, fruitId);
		//获取一个月前的日期
		String aMonthAgoDate = Utils.aMonthAgoDate();
		
		//一个月的数据
		JSONObject monthDada = environmentService.getRangeTimeDataToJsonArray(sensorId, aMonthAgoDate, Utils.todayDate());
		
		return monthDada;
	}

}
