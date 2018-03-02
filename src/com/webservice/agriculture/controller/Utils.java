package com.webservice.agriculture.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * 工具类
 * @author Sunward(CXY)
 *
 */
public class Utils {
	/**
	 * 通过反射调用方法
	 * @param request
	 * @param response
	 */
	public static void callingMethodByReflection(Object object,String methodName,HttpServletRequest request, HttpServletResponse response){
		//去除方法名的后缀.do
		methodName=methodName.substring(0,methodName.length()-3);
		try {
			Method	method = object.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(object,request,response);
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formateDate(String date){
		
		date = date.replace("GMT", "").replaceAll("\\(.*\\)", "");
		SimpleDateFormat format =  new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);  
		
		try {
			Date newDate = format.parse(date);
			String dateString = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
			return dateString;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "error";
	}
	
	public static String formateTime(String dateTime) {
		dateTime = dateTime.replace("GMT", "").replaceAll("\\(.*\\)", "");
		SimpleDateFormat format =  new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);  
		
		try {
			Date newDate = format.parse(dateTime);
			String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newDate);
			return dateString;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "error";
	}
	
	/**
	 * 获取今天日期
	 * @return
	 */
	public static String todayDate() {
		Date date=new Date();//取时间
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		
		return dateString;
	}
	
	/**
	 * 获取昨天日期
	 * @return
	 */
	public static String yesterday() {
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
		date=calendar.getTime(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		
		return dateString;
	}
	
	/**
	 * 获取一周前日期
	 * @return
	 */
	public static String aWeekAgoDate() {
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-7);//把日期往前减少7天，若想把日期向后推一天则将负数改为正数
		date=calendar.getTime(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		
		return dateString;
	}
	
	/**
	 * 获取一个月前日期
	 * @return
	 */
	public static String aMonthAgoDate() {
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-30);//把日期往前减少30天，若想把日期向后推一天则将负数改为正数
		date=calendar.getTime(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		
		return dateString;
	}
	/**
	 * *
	 * 上传文件
	 * @param image
	 * @param baseid
	 * @param request
	 * @param isModify
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 2017/06/12
	 */
	public static String fileUpload(String image,String baseid,HttpServletRequest request,boolean isModify)
			throws ServletException, IOException {
		String sufffix = "";//图片后缀名
		// 图片头部 ，判断图片格式
		String header ="";
		String path = "";  
		//image不为空就执行
		System.out.println(image);
		if(image!=""||image!=null){
			
			if (image.indexOf("jpeg;base64", 10)>0) {
				header ="data:image/jpeg;base64,";
				sufffix = ".jpg";
			}
			if (image.indexOf("png;base64", 10)>0) {
				header ="data:image/png;base64,";
				sufffix = ".png";
			}
			
			int maxid = 0;
			if (isModify) {//如果是修改图片，ID不需要+1，注册用户需要 + 1
				maxid = Integer.valueOf(baseid.split("_")[1]).intValue();
			}
			else {
				maxid = Integer.valueOf(baseid.split("_")[1]).intValue()+1;
			}
			
			baseid = "base_" + maxid;
			
			
			
			//if(image.indexOf(header) != 0){  
			//return false;  
			//}  
			// 去掉头部  
			image = image.substring(header.length());  
			
			// 写入磁盘  
			BASE64Decoder decoder = new BASE64Decoder();  
			
			String serverpath = request.getSession().getServletContext().getRealPath("/");
			path="vue/assets/img/farm/"+baseid+sufffix;
			String imgPath = serverpath + path;//获取照片存储路径和文件名
			//判断文件夹是否存在该图片，存在就删除
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}else {
				try{  
					byte[] decodedBytes = decoder.decodeBuffer(image);  
					
					FileOutputStream out = new FileOutputStream(imgPath);  
					out.write(decodedBytes);  
					out.flush();
					out.close();  
					
				}catch(Exception e){  
					path = "";  
					e.printStackTrace();  
				}  
			}
		}
		return path;
	}  
}
