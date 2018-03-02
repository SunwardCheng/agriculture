package com.webservice.agriculture.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.webservice.agriculture.beans.Administrator;
import com.webservice.agriculture.beans.BaseInfo;
import com.webservice.agriculture.controller.cropdata.CropData;
import com.webservice.agriculture.controller.desktop.CropsInfo;
import com.webservice.agriculture.controller.log.LoginAndLogout;
import com.webservice.agriculture.controller.set_info.SetInfo;
import com.webservice.agriculture.service.base.BaseService;

/**
 * 
 * @author Sunward(CXY)
 *2017/5-6
 */
public class MainServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String serlvet=request.getServletPath();//获取请求地址
		String methodName=serlvet.substring(1);//得到方法名
		
		//反射调用方法
		Utils.callingMethodByReflection(this, methodName, request, response);
		
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//获取页面传递参数
		String userName = request.getParameter("loginName");
		String password = request.getParameter("loginPassword");
		String userType = request.getParameter("userType");
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject data = LoginAndLogout.login(userName, password, userType,request);
		
		response.getWriter().write(data.toString());
		
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * CXY
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		LoginAndLogout.logout(request);
	}
	/**
	 * desktop.html传递的数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	
	public void desktop(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//获取页面传递参数
		String userID=request.getParameter("userID");
		response.setHeader("Content-type", "text/html;charset=UTF-8");

		//获取desktop.html页面数据
		JSONObject desktopData = CropsInfo.desktop(Integer.parseInt(userID));
		
		response.getWriter().write(desktopData.toString());
	}
	
	/**
	 * 获取实时数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void queryRealTimeData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject realTimeData = CropData.realTimeData(baseId, fruitName);
		JSONObject msg = new JSONObject();
		if (realTimeData.isEmpty()) {
			msg.put("msg", "数据暂未监测！");
			response.getWriter().write(msg.toString());
		}
		else {
			response.getWriter().write(realTimeData.toString());
		}
	}
	
	/**
	 * cropInfo.html初始化数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void initLineData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String baseId=request.getParameter("farmId");
		String date=request.getParameter("date");
		
		//日期格式化
		date = Utils.formateDate(date);
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//初始化数据
		JSONObject initData = CropData.initData(baseId,date);
		System.out.println(initData.toString());
		response.getWriter().write(initData.toString());
	}
	
	/**
	 * 获取单个农场的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public JSONObject getBaseInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String baseId = request.getParameter("farmId");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject baseInfo = CropData.getBaseInfo(baseId);
		//获取一个农场有几个视频区域
		int count = baseInfo.getInt("areaCount");
		JSONArray area = new JSONArray();
		JSONObject value = null;
		for (int i = 0; i < count; i++) {
			value = new JSONObject();
			value.put("value", "区域" + (i + 1));
			area.add(value);
		}
		
		baseInfo.put("areas", area);
		
		response.getWriter().write(baseInfo.toString());
		
		return baseInfo;
	}
	
	/**
	 * 查询某天的运行数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void queryDayData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");
		String date=request.getParameter("date");
		//日期格式化
		date = Utils.formateDate(date);
				
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject dayData = CropData.queryDayData(baseId, fruitName, date);
		
		JSONObject msg = new JSONObject();
		if (dayData.isEmpty()) {
			msg.put("msg", "数据暂未监测！");
			response.getWriter().write(msg.toString());
		}
		else {
			response.getWriter().write(dayData.toString());
		}
		
		
	}
	
	/**
	 * 查询一周的运行数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void queryWeekData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject weekData = CropData.queryWeekData(baseId, fruitName);
		
		System.out.println("weekData:   "+weekData);
		
		JSONObject msg = new JSONObject();
		if (weekData.isEmpty()) {
			msg.put("msg", "数据暂未监测！");
			response.getWriter().write(msg.toString());
		}
		else {
			response.getWriter().write(weekData.toString());
		}
		
	}
	
	/**
	 * 查询某天的运行数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void queryMonthData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String baseId=request.getParameter("farmId");
		String fruitName=request.getParameter("fruitName");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject monthData = CropData.queryMonthData(baseId, fruitName);
		System.out.println("monthData:   "+monthData);
		System.out.println(fruitName+"*************************");
		
		JSONObject msg = new JSONObject();
		if (monthData.isEmpty()) {
			msg.put("msg", "数据暂未监测！");
			response.getWriter().write(msg.toString());
		}
		else {
			response.getWriter().write(monthData.toString());
		}
		
	}
	
	/**
	 * 获取本
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void video(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject baseInfo = getBaseInfo(request, response);
		System.out.println(baseInfo+"==========================");
		
		response.getWriter().write(baseInfo.toString());
		
	}
	/**
	 * 管理员设置页面的初始化
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void  setInfoInit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		//获取所有农场名，以两种不同格式存储
		JSONArray farmsArray = SetInfo.getAllFarms();
		JSONArray farmsArray2 = SetInfo.getAllFarms2();
		//获取所有农场主用户
		JSONArray farmOwners = SetInfo.getFarmOwners();
		//获取所有作物
		JSONArray fruits = SetInfo.getAllFruits();
		
		JSONObject data = new JSONObject();
		data.put("farms1", farmsArray);
		data.put("farms2", farmsArray2);
		data.put("farmOwners", farmOwners);
		data.put("fruits", fruits);
		response.getWriter().write(data.toString());
	}

	/**
	 * 管理员添加用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userForm = request.getParameter("userForm");
		JSONObject userInfo = JSONObject.fromObject(userForm);
		String registTime = request.getParameter("registTime");
		//获取所拥有农场
		String farms = userInfo.getString("farmsChecked");
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//添加用户
		JSONObject msg = SetInfo.addUser(userInfo,registTime);
		//用户添加成功
		if (msg.getInt("state")==1) {
			
			//得到用户ID
			int userID = msg.getInt("userID");
			JSONArray farmArray =null;
			
			//如果添加的是农场主
			if (userInfo.get("authority")=="farmowner"||userInfo.get("authority").equals("farmowner")) {
				
				//给用户添加农场
				farmArray = JSONArray.fromObject(farms);
				if (farmArray.size()>=1) {//若用户有农场则添加
					//给用户添加农场
					SetInfo.addfarmToUser(userID, farms);
				}
				
			}else{//添加的是管理员，把所有农场都交给刚刚添加的管理员管理
				farmArray = SetInfo.getAllFarms2();
				if (farmArray.size()>0) {
					SetInfo.addfarmToUser(userID, farmArray.toString());
				}
				
			}
		}
		
		response.getWriter().write(msg.toString());
		
	}
	
	/**
	 * 管理员修改所有信息时获取该用户所有信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void  getUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		JSONObject userInfo = SetInfo.adminGetUserInfo(Integer.parseInt(userID));
		
		response.getWriter().write(userInfo.toString());
	}
	
	/**
	 * 管理员修改用户所有信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void  modifyAllInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userForm = request.getParameter("userForm");
		String date = request.getParameter("date");
		
		JSONObject userInfo = JSONObject.fromObject(userForm);
		//获取所拥有农场
		String farms = userInfo.getString("farmsChecked");
		String userID = userInfo.getString("userID");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//得到Administrator实体类，其中时间是无效的,因为注册时间不可修改
		//得到实体类
		Administrator user = SetInfo.getUserBean(userInfo, date);
		//给实体设置ID
		user.setAdminID(Integer.parseInt(userID));
		//修改用户信息
		JSONObject msg = SetInfo.modifyUserAll(user);

		//删除用户农场
		SetInfo.deleteFarmFromUser(Integer.parseInt(userID));
		//给用户添加农场
		JSONArray farmArray = JSONArray.fromObject(farms);
		if (farmArray.size()>=1) {//若用户有农场则添加
			//给用户添加农场
			SetInfo.addfarmToUser(Integer.parseInt(userID), farms);
		}
		
		response.getWriter().write(msg.toString());
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/06/12
	 * CXY
	 */
	public void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userID = request.getParameter("userID");
		SetInfo.deleteUser(Integer.parseInt(userID));
	
	}
	/**
	 * 获取农场的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/06/10
	 */
	/*public void getFarmInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String farmID = request.getParameter("farmID");
		//获取农场信息
		JSONObject farm = SetInfo.getFarmInfo(farmID);
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write(farm.toString());
	}*/
	/**
	 * 管理员添加农场
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * CXY
	 */
	public void  addFarm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		String farmForm = request.getParameter("farmForm");
		String image = request.getParameter("image");
		JSONObject farmInfo = JSONObject.fromObject(farmForm);
		String fruits = farmInfo.getString("fruitsChecked");
		JSONObject msg = null;
		//如果用户想要添加图片
		   BaseService baseService =new BaseService();
		   String baseid = baseService.findMaxFarmID();
			//上传图片
			String path = Utils.fileUpload(image,baseid,request,false);

			if(path!=""||!path.equals("")){
				//给农场添加图片路径
				farmInfo.put("imageUrl", path);
				
				//添加农场
				 msg = SetInfo.addFarm(farmInfo);
				//获得农场ID，给农场添加作物
				String farmID = msg.getString("farmID");
				
				JSONArray fruitArray = JSONArray.fromObject(fruits);

				//农场添加成功的情况下才执行以下操作
				if (msg.getInt("state")==1) {
				
					//如果管理员添加了作物就执行添加
					if (fruitArray.size()>0) {
						SetInfo.addfruitsToFarm(farmID, fruits);
						
					}
					//给所有管理员添加这个刚添加的农场
					//函数的输入参数是JSonArray的String格式
					JSONArray farmArray= new JSONArray();
					farmArray.add(farmInfo.getString("farmName"));
							
					List<Administrator> adminList = SetInfo.getAllAdmins("administrator");
					for (Administrator admin:adminList) {
						SetInfo.addfarmToUser(admin.getAdminID(),farmArray.toString());
					}
				
				}
				
			}else{
				msg = new JSONObject();
				msg.put("msg","图片上传失败!");
			}
		
		
		
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		response.getWriter().write(msg.toString());
		
		
	}
	/**
	 * 修改农场信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/06/12
	 * CXY
	 */
	public void modifyFarmInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String farmForm = request.getParameter("farmForm");
		String img = request.getParameter("image");
		JSONObject farmInfo = JSONObject.fromObject(farmForm);
		//获取所拥有农场
		String fruits = farmInfo.getString("fruitsChecked");
		String farmID = farmInfo.getString("baseId");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject msg =null;
		//上传图片
		String path = Utils.fileUpload(img,farmID,request,true);
		
		if(path!=""||!path.equals("")){
			//修改路径
			farmInfo.put("imageUrl", path);
			//修改ID
			farmInfo.put("baseId", farmID);
			
			//修改用户信息
			msg = SetInfo.modifyForm(farmInfo);
			
			//给用户添加农场
			JSONArray fruitArray = JSONArray.fromObject(fruits);
			if (fruitArray.size()>=1) {//若管理员添加作物就添加
				//给农场添加作物
				SetInfo.addfruitsToFarm(farmID, fruits);
			}
		}else {
			msg = new JSONObject();
			msg.put("msg", "图片上传失败");
		}

		
		
		response.getWriter().write(msg.toString());
	}
	/**
	 * 删除农场
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/06/12
	 * CXY
	 */
	public void deleteFarm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String farmID = request.getParameter("farmID");
		SetInfo.deleteFarm(farmID);
	
	}
	
	/**
	 * 添加作物
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/05/15
	 * CXY
	 */
	public void addFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fruitForm = request.getParameter("fruitForm");
		JSONObject fruitInfo = JSONObject.fromObject(fruitForm);
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		//添加作物
		JSONObject msg = SetInfo.addFruit(fruitInfo);
		
		response.getWriter().write(msg.toString());
	}
	/**
	 * 获取作物信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/05/15
	 * CXY
	 */
	public void getFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fruitID = request.getParameter("fruitID");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		JSONObject fruitInfo = SetInfo.getFruit(fruitID);
		response.getWriter().write(fruitInfo.toString());
	}
	/**
	 * 修改作物信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/05/15
	 * CXY
	 */
	public void modifyFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fruitForm = request.getParameter("fruitForm");
		JSONObject fruitInfo = JSONObject.fromObject(fruitForm);
		
		JSONObject msg = SetInfo.modifyFruit(fruitInfo);
		
		response.getWriter().write(msg.toString());
		
	}
	/**
	 * 删除作物信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
     * 2017/05/15
	 * CXY
	 */
	public void deleteFruit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fruitID = request.getParameter("fruitID");
		SetInfo.deleteFruit(fruitID);
	}
	
	
	
	
	
	
	/**
	 * 农场主设置页面初始化
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 2017/06/08
	 * CXY
	 */
	public void  userInfoInit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject user = SetInfo.farmerGetUserInfo(Integer.parseInt(userID));
		
		response.getWriter().write(user.toString());
		
	}
	
	
	/**
	 * 农场主设置页面
	 * 修改用户信息（不包括密码）
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * ChengGuiHao
	 */
	public void  modifyInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String depict = request.getParameter("depict");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		Administrator user = new Administrator();
		user.setAdminID(Integer.parseInt(userID));
		user.setEmail(email);
		user.setPhone(phone);
		user.setDepict(depict);
		
		JSONObject msg = SetInfo.modifyUserInfo(user);
 
		response.getWriter().write(msg.toString());
	}
	
	/**
	 * 农场主设置页面
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * ChengGuihao
	 */
	public void  modifyPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userID = request.getParameter("userID");
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		JSONObject msg  =  SetInfo.modifyPassword(Integer.parseInt(userID), oldpassword, newpassword);
		
		response.getWriter().write(msg.toString());
	}
	
	
		  
}
