package com.webservice.agriculture.db;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC操作工具类
 * @author Sunward
 *
 */
public class jdbcUtils {
	private static Connection con = null;  
	private static DataSource dataSource=null;
	/**
	 * 释放连接
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		try {
			if (connection!=null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static{
		//数据源只能被创建一次
		dataSource=new ComboPooledDataSource("agricultureMVCApp");
	}
	/**
	 * 返回Connection
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	// 开启事务  
    public static void beginTransaction() throws SQLException {  
        //判断con是否为空，如果不为空，则说明事务已经开启  
        if(con != null){  
            throw new SQLException("事务已经开启了,不能重复开启事务");  
        }  
        //如果不为空，则开启事务  
        con = getConnection();  
        //设置事务提交为手动  
        con.setAutoCommit(false);  
    }  
    
 // 提交事务  
    public static void commitTransaction() throws SQLException {  
        //判断con是否为空，如果为空，则说明没有开启事务  
        if(con == null){  
            throw new SQLException("没有开启事务,不能提交事务");  
        }  
        //如果con不为空,提交事务  
        con.commit();  
        //事务提交后，关闭连接  
        con.close();  
        //表示事务已经结束  
        con = null;  
    }  
    
 // 回滚事务  
    public static void rollbackTransaction() throws SQLException {  
        //判断con是否为空，如果为空，则说明没有开启事务，也就不能回滚事务  
        if(con == null){  
            throw new SQLException("没有开启事务,不能回滚事务");  
        }  
        //事务回滚  
        con.rollback();  
        //事务回滚后，关闭连接  
        con.close();  
        //把con置为null，表示事务已经结束  
        con = null;  
    }  
	
}
