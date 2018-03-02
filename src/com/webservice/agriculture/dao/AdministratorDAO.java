package com.webservice.agriculture.dao;

import java.util.List;

import com.webservice.agriculture.beans.Administrator;

/**
 * 
 * @author Sunward
 * 
 */
public interface AdministratorDAO {
	
	public List<Administrator> getAll();
	
	//添加用户
	public int insert(Administrator user);
	
	//农场主修改密码
	public int updatePassword(int ID,String Password);
	
	//农场主修改信息
	public int updateInfo(Administrator user);
	
	public int updateAll(Administrator user);
	
	public Administrator get(int id);
	
	public int getID(String name);
	
	public void delete(int id);

	public List<Administrator> getUserNames();

	public List<Administrator> getAllAdmins(String authority);
}
