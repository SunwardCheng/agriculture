package com.webservice.agriculture.controller.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.Administrator;
import com.webservice.agriculture.service.administrator.AdministratorService;



public class LoginAndLogout  {
	
	private static AdministratorService administratorService = new AdministratorService();

	public static JSONObject login(String userName,String password,String userType ,HttpServletRequest request){
		String msg = "";
		//存放登录状态
		JSONObject dataJsonObject = new JSONObject();
		//获取session
		HttpSession session = request.getSession();
		//设置session有效时间30分钟
		session.setMaxInactiveInterval(30*60);
		String sessionId = session.getId();
		Administrator administrator = administratorService.getByName(userName);
		
		if(administrator==null){
			msg = "用户名不存在！";
			dataJsonObject.put("code", 0);
			dataJsonObject.put("message", msg);
		}else {
			if(password.equals(administrator.getPassword())){
				if(!administrator.getAuthority().equals(userType)){
					msg = "权限有误";
					dataJsonObject.put("code", 0);
					dataJsonObject.put("message", msg);
				}else {
					//登录成功
					dataJsonObject.put("code", 1);
					dataJsonObject.put("sessionId", sessionId);
					dataJsonObject.put("userID", administrator.getAdminID());
				}
			}else {
				msg = "密码错误";
				dataJsonObject.put("code", 0);
				dataJsonObject.put("message", msg);
			}
		}
		
		return dataJsonObject;
	}
	
	public static void logout(HttpServletRequest request){
		request.getSession().invalidate();
	}
}
