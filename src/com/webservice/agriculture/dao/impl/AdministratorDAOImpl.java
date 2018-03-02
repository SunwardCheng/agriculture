package com.webservice.agriculture.dao.impl;

import java.util.List;

import com.webservice.agriculture.beans.Administrator;
import com.webservice.agriculture.dao.AdministratorDAO;
import com.webservice.agriculture.dao.DAO;
/**
 * 
 * @author Sunward
 * 
 */
public class AdministratorDAOImpl extends DAO<Administrator> implements AdministratorDAO{

	@Override
	public List<Administrator> getAll() {
		String sql="select * from administrator" ;
		return getForList(sql);
	}

	@Override
	public int insert(Administrator user) {
		String sql = "insert into administrator(UserName,Password,Authority,Email,Phone,RegisTime,Depict)" +
				" values(?,?,?,?,?,?,?)";
		return update(sql, user.getUserName(),user.getPassword(),user.getAuthority(),
				user.getEmail(),user.getPhone(),user.getRegisTime(),user.getDepict());
		
	}

	@Override
	public Administrator get(int id) {
		String sql="select * from administrator where AdminID=?";
		return super.get(sql, id);
	}

	@Override
	public void delete(int id) {
		String sql="delete from administrator where AdminID=?";
		super.update(sql,id);
		
	}

	@Override
	public int getID(String name) {
		String sql = "select AdminID from administrator where UserName=?";
		return super.getForValue(sql, name);
	}

	@Override
	public List<Administrator> getUserNames() {
		String sql = "select * from administrator where Authority=?";
		return getForList(sql,"farmowner");
	}

	@Override
	public int updatePassword(int ID, String Password) {
		String sql = "update administrator set Password =? where AdminID=?";
		return update(sql, Password,ID);
		
	}

	@Override
	public int updateInfo(Administrator user) {
		String sql = "update administrator set Phone =? ,email=?,Depict=? where AdminID=?";
		return update(sql, user.getPhone(),user.getEmail(),user.getDepict(),user.getAdminID());
		
	}

	@Override
	public int updateAll(Administrator user) {
		String sql = "update  administrator set UserName=?,Password=?,Authority=?,Email=?,Phone=?,Depict=? where AdminID =?";
		return update(sql, user.getUserName(),user.getPassword(),user.getAuthority(),
				user.getEmail(),user.getPhone(),user.getDepict(),user.getAdminID());
	}

	@Override
	public List<Administrator> getAllAdmins(String authority) {
		String sql="select * from administrator where Authority = ?" ;
		return getForList(sql,"administrator");
	}

}
