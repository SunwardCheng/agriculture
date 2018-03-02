package com.webservice.agriculture.dao;

import java.util.List;

import com.webservice.agriculture.beans.Environment;

/**
 * 
 * @author Sunward
 * 
 */
public interface EnvironmentDAO {
	
	public List<Environment> getAll();
	
	public List<Environment> getForList(String id);
	
	public void update(Environment base);
	
	//获取数据库最大一行值
	public Environment get(String id);
	
	//根据传感器ID和时间获取数据
	public List<Environment> getByIdAndTime(String id,String time);
	
	//根据传感器ID和时间范围获取数据
	public List<Environment> getByIdAndRangeTime(String id,String startTime,String endTime);
	
	public void delete(String id);
	
	//获得昨日平均数据
	public Environment getYesterdayAvg(String id,String time);
	//获得昨日最高数据
	public Environment getYesterdayMax(String id,String time);
	//获得昨日最低数据
	public Environment getYesterdayMin(String id,String time);
}
