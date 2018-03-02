package com.webservice.agriculture.service.admin_base;

import java.util.List;

import com.webservice.agriculture.beans.AdminBase;
import com.webservice.agriculture.dao.AdminBaseDAO;
import com.webservice.agriculture.dao.impl.AdminBaseDAOImpl;
/**
 * 
 * @author Sunward
 * 2017/5
 */
public class AdminBaseService {
	private AdminBaseDAO adminBaseDAO = new AdminBaseDAOImpl();
	
	public List<AdminBase> getAdminBasesByAdminID(int adminId){
		return adminBaseDAO.getAdminBaseList(adminId);
	}
	
	/**
	 * 给用户添加农场
	 * @param userId
	 * @param baseIds
	 */
	public void addBaseToUser(int userId,List<String>baseIds){
		adminBaseDAO.batch(userId, baseIds);
	}
	
	/**
	 * 删除用户农场
	 * @param userID
	 */
	public void deleteBase(int userID) {
		adminBaseDAO.delete(userID);
	}
	
	/**
	 * 删除一个农场与用户所有的关系
	 * @param baseId
	 */
	public void deleteAllBase(String baseId){
		adminBaseDAO.deleteAllBases(baseId);
	}
	
}
