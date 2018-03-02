package com.webservice.agriculture.controller.desktop;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.AdminBase;
import com.webservice.agriculture.service.admin_base.AdminBaseService;
import com.webservice.agriculture.service.administrator.AdministratorService;
import com.webservice.agriculture.service.base.BaseService;

public class CropsInfo {
	
	/**
	 * 
	 */
	
	private static BaseService baseService = new BaseService();
	private static AdministratorService administratorService = new AdministratorService();
	private static AdminBaseService adminBaseService = new AdminBaseService();
	
	
	/*@SuppressWarnings("unused")
	private JSONObject farmData(String baseId){
		
		JSONObject baseInfojsonObject = baseService.getBaseToJsonObjectByID(baseId);
		
		return baseInfojsonObject;
	}
	*/
	private static  JSONArray farmsData(int userID) {

		//获取一个用户对应的农场ID
		List<AdminBase> adminBaseList = adminBaseService.getAdminBasesByAdminID(userID);
		
		//将一个用户所拥有的农场转化为JSONArray对象
		JSONArray basesJsonArray = baseService.getBasesToJsonArray(adminBaseList);
		
		return basesJsonArray;
	}
	
	
	public static JSONObject desktop(int userID){
		
		//获取农场信息
		JSONArray farmsData = farmsData(userID);
		
		//获取用户信息
		JSONObject userJsonObject = administratorService.getUserToJsonObject(userID);
		
		farmsData.add(userJsonObject);
		
		//桌面desktop.html传递的数据
		JSONObject desktopData = new JSONObject();
		desktopData.put("farmsData", farmsData);//农场信息
		desktopData.put("userInfo", userJsonObject);//用户信息
		
		return desktopData;
	}
	
	


}
