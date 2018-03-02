package com.webservice.agriculture.dao.impl;

import java.util.List;

import com.webservice.agriculture.beans.Environment;
import com.webservice.agriculture.dao.DAO;
import com.webservice.agriculture.dao.EnvironmentDAO;
/**
 * 
 * @author Sunward
 * 
 */
public class EnvironmentDAOImpl extends DAO<Environment> implements EnvironmentDAO{

	@Override
	public List<Environment> getAll() {
		String sql="select * from environment" ;
		return getForList(sql);
	}

	@Override
	public void update(Environment base) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Environment get(String id) {
		String sql="select * from environment where SensorID=? order by ID desc limit 1 ";
		return super.get(sql, id);
	}

	@Override
	public void delete(String id) {
		String sql="delete from environment where SensorID=?";
		super.update(sql,id);
		
	}

	@Override
	public List<Environment> getForList(String id) {
		String sql="select * from environment where SensorID =? order by RealTime" ;
		return getForList(sql,id);
	}

	@Override
	public List<Environment> getByIdAndTime(String id, String time) {
		time = "%"+time+"%";
		String sql = "select * from environment where SensorID=? and RealTime like ? order by RealTime";
		return super.getForList(sql, id,time);
	}

	@Override
	public List<Environment> getByIdAndRangeTime(String id, String startTime,
			String endTime) {
		startTime = startTime+" 00:00:00";
		endTime = endTime +" 23:59:59";
		String sql = "select * from environment where SensorID=? and RealTime between ? and ? order by RealTime";
		return super.getForList(sql,id, startTime,endTime);
	}

	@Override
	public Environment getYesterdayAvg(String id, String time) {
		String sql = "select avg(AirTemperature) AirTemperature,avg(AirHumidity) AirHumidity,avg(SoilTemperature) SoilTemperature," +
				"avg(SoilHumidity) SoilHumidity,avg(Illumination) Illumination from environment where SensorID=? and RealTime like '%?%'";
		return super.get(sql, time);
	}

	@Override
	public Environment getYesterdayMax(String id, String time) {
		String sql = "select max(AirTemperature) AirTemperature,max(AirHumidity) AirHumidity,max(SoilTemperature) SoilTemperature," +
				"max(SoilHumidity) SoilHumidity,max(Illumination) Illumination from environment where SensorID=? and RealTime like '%?%'";
		return super.get(sql, time);
	}

	@Override
	public Environment getYesterdayMin(String id, String time) {
		String sql = "select min(AirTemperature) AirTemperature,min(AirHumidity) AirHumidity,min(SoilTemperature) SoilTemperature," +
				"min(SoilHumidity) SoilHumidity,min(Illumination) Illumination from environment where SensorID=? and RealTime like '%?%'";
		return super.get(sql, time);
	}

}
