package com.webservice.agriculture.dao.impl;

import java.util.List;

import org.junit.Test;

import com.webservice.agriculture.beans.AdminBase;
import com.webservice.agriculture.dao.AdminBaseDAO;
import com.webservice.agriculture.dao.DAO;
/**
 * 
 * @author Sunward
 * 
 */
public class AdminBaseDAOImpl extends DAO<AdminBase> implements AdminBaseDAO {
	
	@Override
	public List<AdminBase> getAll() {
		String sql="select * from admin_base " ;
		return getForList(sql);
	}

	@Override
	public int update(AdminBase base) {
		String sql="insert into admin_base(AdminID,BaseID) values(?,?)";
		return update(sql,base.getAdminID(),base.getBaseID());
		
	}

	@Override
	public AdminBase get(int id) {
		String sql="select * from admin_base where AdminID=?";
		return super.get(sql, id);
	}

	@Override
	public void delete(int id) {
		String sql="delete from admin_base where AdminID=?";
		super.update(sql,id);
		
	}

	@Override
	public List<AdminBase> getAdminBaseList(int id) {
		String sql="select * from admin_base where AdminID=?";
		return super.getForList(sql, id);
	}

	@Override
	public void batch(int userId, List<String> baseList) {
		Object[][] params = new Object[baseList.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{userId,baseList.get(i)};
		}
		String sql="insert into admin_base(AdminID,BaseID) values(?,?)";
		
		batch(sql, params);
		
	}

	@Override
	public void deleteAllBases(String baseID) {
		String sql="delete from admin_base where BaseID=?";
		super.update(sql,baseID);
		
	}

}
