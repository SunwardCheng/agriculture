package com.webservice.agriculture.dao;

import java.util.List;

import com.webservice.agriculture.beans.BaseInfo;

/**
 * 
 * @author Sunward
 * 
 */
public interface BaseInfoDAO {
	
	public List<BaseInfo> getAll();
	
	public int update(BaseInfo base);
	
	public BaseInfo getByName(String name);
	
	public BaseInfo get(String id);
	
	//添加农场
	public int insert(BaseInfo base);
	
	public String getIdByName(String name);
	
	public void delete(String id);
	
	public List<BaseInfo> getBaseNames();
	
	//获取最后一行值
	public String getMaxID();
}
