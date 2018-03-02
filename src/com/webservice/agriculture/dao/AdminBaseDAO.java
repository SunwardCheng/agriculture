package com.webservice.agriculture.dao;

import java.util.List;

import com.webservice.agriculture.beans.AdminBase;

/**
 * 
 * @author Sunward
 * 
 */
public interface AdminBaseDAO {
	public List<AdminBase> getAll();
	
	public int update(AdminBase base);
	
	public AdminBase get(int id);
	
	public List<AdminBase> getAdminBaseList(int id);
	
	public void delete(int id);
	
	//删除所有农场
	public void deleteAllBases(String baseID);
	
	//批量更新
	public void batch(int userId,List<String>baseList);
	
}
