package com.webservice.agriculture.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.webservice.agriculture.beans.Administrator;
import com.webservice.agriculture.db.jdbcUtils;

/**
 * 基本的查询方法工具
 * @author Sunward
 *
 * @param <T>
 */
public class DAO<T> {
	
	private QueryRunner queryRunner=new QueryRunner();
	private Class<T> clazz;
	
	public DAO(){
		//获取带泛型的父类
		Type superClass=getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType=(ParameterizedType) superClass;
			//获取父类泛型参数
			Type [] typeArgs=parameterizedType.getActualTypeArguments();
			
			if(typeArgs!=null&&typeArgs.length >0){
				if (typeArgs[0]instanceof Class) {
					//得到泛型，强制转为Class对象，初始化clazz
					clazz=(Class<T>) typeArgs[0];
				}
			}
		}
	}
	/**
	 * 获取表中单行的某列数据
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object...args){
		Connection connection=null;
		
		try {
			connection=jdbcUtils.getConnection();
			return  queryRunner.query(connection,sql,new ScalarHandler<E>(),args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 获取实体类在表中多行数据，存放在List中
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql,Object...args){
		Connection connection=null;
		
		try {
			connection=jdbcUtils.getConnection();
			return  queryRunner.query(connection,sql,new BeanListHandler(clazz),args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	/**
	 * 获取实体类在表中一行数据
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object...args){
		Connection connection=null;
		
		try {
			connection=jdbcUtils.getConnection();
			return  queryRunner.query(connection,sql,new BeanHandler<T>(clazz),args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	/**
	 * 更新数据
	 * @param sql
	 * @param args
	 */
	public int update(String sql,Object ... args){
		Connection connection=null;
		
		try {
			connection=jdbcUtils.getConnection();
			int I = queryRunner.update(connection,sql,args);
			return I;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
	}
	
	/**
	 * 插入数据 
	 * @param sql
	 * @param args
	 */
	/*public int insert(String sql, Object ... args){
		Connection connection=null;
		
		try {
			connection = jdbcUtils.getConnection();
			int i  =(Integer) queryRunner.insert(connection, sql, new BeanHandler<T>(clazz),args);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
	}*/
	
	/**
	 * 批量操作
	 * @param sql
	 * @param params
	 */
	public void batch(String sql,Object[][] params){
		Connection connection=null;
		
		try {
			connection=jdbcUtils.getConnection();
			queryRunner.batch(connection,sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbcUtils.releaseConnection(connection);
		}
	}
}
