package com.webservice.agriculture.service.administrator;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.Administrator;
import com.webservice.agriculture.beans.BaseInfo;
import com.webservice.agriculture.dao.AdministratorDAO;
import com.webservice.agriculture.dao.BaseInfoDAO;
import com.webservice.agriculture.dao.impl.AdministratorDAOImpl;

/**
 * 
 * @author Sunward
 *2017-6
 */
public class AdministratorService {
	
	private AdministratorDAO administratorDAO = new AdministratorDAOImpl();
	
	/**
	 * 通过用户名获取用户信息
	 * @param name
	 * @return
	 *  2017/06/09
	 */
	public Administrator getByName(String name){
		int id;
		try {
			id = getID(name);
			return administratorDAO.get(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取用户的id
	 * @param name
	 * @return
	 */
	public int getID(String name){
		return administratorDAO.getID(name);
	}
	
	/**
	 * 将用户信息存放到JSONObject对象中
	 * @param userID
	 * @return
	 *  2017/06/09
	 */
	public JSONObject getUserToJsonObject(int userID){
		JSONObject userJsonObject = new JSONObject();
		Administrator administrator = administratorDAO.get(userID);
		
		userJsonObject.put("userID", administrator.getAdminID());
		userJsonObject.put("userName", administrator.getUserName());
		userJsonObject.put("password", administrator.getPassword());
		userJsonObject.put("email", administrator.getEmail());
		userJsonObject.put("phone", administrator.getPhone());
		userJsonObject.put("depict", administrator.getDepict());
		userJsonObject.put("authority", administrator.getAuthority());
		return userJsonObject;
		
	}
	
	/**
	 * 获取所有农场主
	 * 以[{userName:"NongXueYuan",userID:"1"},{
     *     userName:"GXU",userID:"2"}]形式存放
	 * @return
	 *  2017/06/09
	 */
	public JSONArray findUsers(){
		List<Administrator> listusers = administratorDAO.getUserNames();
		JSONArray users = new JSONArray();
		JSONObject userJsonObject = null;
		for (Administrator user:listusers) {
			userJsonObject = new JSONObject();
			userJsonObject.put("userName", user.getUserName());
			userJsonObject.put("userID", user.getAdminID());
			users.add(userJsonObject);
		}
		
		return users;
	}
	
	/**
	 * 添加用户
	 * @param user
	 *  2017/06/09
	 */
	public JSONObject addUser(Administrator user){
		
		int i = -1;
		int userID = -1;
		try {
			i = administratorDAO.insert(user);
			userID = administratorDAO.getID(user.getUserName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject msg = new JSONObject();
		if (i==-1) {
			msg.put("msg", "添加失败，请更换用户名");
			msg.put("state", 0);
		}else {
			msg.put("msg", "添加成功");
			msg.put("userID", userID);
			msg.put("state", 1);
		}
		
		return msg;
	}
	
	/**
	 * 农场主修改密码
	 * @param userID
	 * @param oldpass
	 * @param newpass
	 * @return
	 *  2017/06/09
	 */
	public JSONObject modifyPass(int userID,String oldpass,String newpass){
		String password = administratorDAO.get(userID).getPassword();
		int i = -1;
		JSONObject msg = new JSONObject();
		if (password.equals(oldpass)) {
		    try {
		    	i = administratorDAO.updatePassword(userID, newpass);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i>0) {
				msg.put("msg", "修改成功！");
			}else {
				msg.put("msg", "修改失败，请下次重试");
			}
		}else {
			msg.put("msg", "修改失败，密码错误");
		}
		return msg;
	}
	/**
	 * 农场主修改信息
	 * @param user
	 * @return
	 *  2017/06/09
	 */
	public int modifyInfo(Administrator user){
		int i = -1;
		try {
			i = administratorDAO.updateInfo(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 管理员修改所有信息
	 * @param user
	 * @return
	 * 2017/06/09
	 */
	public int modifyAllInfo(Administrator user){
		int i = -1;
		try {
			i = administratorDAO.updateAll(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 获取所有的管理员
	 * @return
	 * 2017/06/11
	 */
	public List<Administrator> findAdmins(String admin){
		return administratorDAO.getAllAdmins(admin);
	}
	/**
	 * 删除用户
	 * @param adminID
	 */
	public void deleteUser(int adminID){
		administratorDAO.delete(adminID);
	}
}
