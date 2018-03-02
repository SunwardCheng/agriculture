package com.webservice.agriculture.service.environment;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.Environment;
import com.webservice.agriculture.dao.EnvironmentDAO;
import com.webservice.agriculture.dao.impl.EnvironmentDAOImpl;
/**
 * 
 * @author Sunward
 * 2017/5
 */
public class EnvironmentService {
	private EnvironmentDAO environmentDAO = new EnvironmentDAOImpl();
	
	//获取数据库最新值
	public JSONObject getMostNewData(String sensorId){

		Environment environment = environmentDAO.get(sensorId);
		JSONObject environmentjsonObject = new JSONObject();
		
		//将实时数据存到JSONObject对象中
		environmentjsonObject.put("airTemp",environment.getAirTemperature());
		environmentjsonObject.put("airHumi", environment.getAirHumidity());
		environmentjsonObject.put("soilTemp", environment.getSoilTemperature());
		environmentjsonObject.put("soilHumi", environment.getSoilHumidity());
		environmentjsonObject.put("light", environment.getIllumination());
		environmentjsonObject.put("time", environment.getRealTime());
		return environmentjsonObject;
	}
	
	
	//获取指定时间的数据转化为JSONArray对象
	public JSONObject getDayDataToJsonArray(String sensorId,String time){
		
		List<Environment> environment = environmentDAO .getByIdAndTime(sensorId, time);
		
		//将一天的数据存放在数组里
		float airTemperature[] = new float[environment.size()];
		float airHumidity[] = new float[environment.size()];
		float soilTemperature[] = new float[environment.size()];
		float soilHumidity[] = new float[environment.size()];
		float light[] = new float[environment.size()];
		String realTime[] = new String[environment.size()];
		
		//存放一天的数据
		JSONObject dayData = new JSONObject();
		
		for(int i =0;i<environment.size();i++){
			
			airTemperature[i] = environment.get(i).getAirTemperature();
			airHumidity[i] = environment.get(i).getAirHumidity();
			soilTemperature[i] = environment.get(i).getSoilTemperature();
			soilHumidity[i] = environment.get(i).getSoilHumidity();
			light[i] = environment.get(i).getIllumination();
			realTime[i] = environment.get(i).getRealTime();
		}
		
		dayData.put("dayAirTemp", airTemperature);
		dayData.put("dayAirHumi", airHumidity);
		dayData.put("dayLight", light);
		dayData.put("daySoilTemp", soilTemperature);
		dayData.put("daySoilHumi", soilHumidity);
		dayData.put("dayTime", realTime);
		
		return dayData;
	}
	
	//获取指定时间范围的数据转化为JSONArray对象
	public JSONObject getRangeTimeDataToJsonArray(String sensorId,String startTime,String endTime){
		
		List<Environment> environment = environmentDAO.getByIdAndRangeTime(sensorId, startTime,endTime);
		
		//将一天的数据存放在数组里
		float airTemperature[] = new float[environment.size()];
		float airHumidity[] = new float[environment.size()];
		float soilTemperature[] = new float[environment.size()];
		float soilHumidity[] = new float[environment.size()];
		float light[] = new float[environment.size()];
		String realTime[] = new String[environment.size()];
		
		//存放一个时间段的数据
		JSONObject rangeTimeData = new JSONObject();
		
		for(int i =0;i<environment.size();i++){
			airTemperature[i] = environment.get(i).getAirTemperature();
			airHumidity[i] = environment.get(i).getAirHumidity();
			soilTemperature[i] = environment.get(i).getSoilTemperature();
			soilHumidity[i] = environment.get(i).getSoilHumidity();
			light[i] = environment.get(i).getIllumination();
			realTime[i] = environment.get(i).getRealTime();
		}
		
		rangeTimeData.put("rangeAirTemp", airTemperature);
		rangeTimeData.put("rangeAirHumi", airHumidity);
		rangeTimeData.put("rangeLight", light);
		rangeTimeData.put("rangeSoilTemp", soilTemperature);
		rangeTimeData.put("rangeSoilHumi", soilHumidity);
		rangeTimeData.put("rangeTime", realTime);
		
		return rangeTimeData;
	}
	//昨日数据：最高值，最低值，平均值
	public JSONArray getYesterdayData(String sensorId,String time){
		Environment environment = environmentDAO.getYesterdayMax(sensorId, time);
		//存放昨日最大值
		JSONObject maxJsonObject = new JSONObject();
		maxJsonObject.put("yesterday", "昨日最高");
		maxJsonObject.put("yesAirTemp", environment.getAirTemperature());
		maxJsonObject.put("yesAirHumi", environment.getAirHumidity());
		maxJsonObject.put("yesLight", environment.getIllumination());
		maxJsonObject.put("yesSoilTemp", environment.getSoilTemperature());
		maxJsonObject.put("yesSoilHumi", environment.getSoilHumidity());
		
		environment = environmentDAO.getYesterdayMin(sensorId, time);
		//存放昨日最小值
		JSONObject minJsonObject = new JSONObject();
		minJsonObject.put("yesterday", "昨日最低");
		minJsonObject.put("yesAirTemp", environment.getAirTemperature());
		minJsonObject.put("yesAirHumi", environment.getAirHumidity());
		minJsonObject.put("yesLight", environment.getIllumination());
		minJsonObject.put("yesSoilTemp", environment.getSoilTemperature());
		minJsonObject.put("yesSoilHumi", environment.getSoilHumidity());
		
		environment = environmentDAO.getYesterdayAvg(sensorId, time);
		//存放昨日平均值
		JSONObject avgJsonObject = new JSONObject();
		avgJsonObject.put("yesterday", "昨日平均");
		avgJsonObject.put("yesAirTemp", environment.getAirTemperature());
		avgJsonObject.put("yesAirHumi", environment.getAirHumidity());
		avgJsonObject.put("yesLight", environment.getIllumination());
		avgJsonObject.put("yesSoilTemp", environment.getSoilTemperature());
		avgJsonObject.put("yesSoilHumi", environment.getSoilHumidity());
		
		//存放昨日数据
		JSONArray yesterdayData = new JSONArray();
		yesterdayData.add(avgJsonObject);
		yesterdayData.add(maxJsonObject);
		yesterdayData.add(minJsonObject);
		
		return yesterdayData;
	}
	/**
	 * 删除农场的一个作物数据
	 * @param sensorID
	 */
	public void deleteSensor(String sensorID){
		environmentDAO.delete(sensorID);
	}
	
}
