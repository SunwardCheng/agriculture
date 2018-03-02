package com.webservice.agriculture.test;


import java.util.List;

import org.junit.Test;

import com.webservice.agriculture.beans.Fruits;
import com.webservice.agriculture.dao.CriteriaFruit;
import com.webservice.agriculture.dao.FruitsDAO;
import com.webservice.agriculture.dao.impl.FruitsDAOjdbcImpl;

public class FruitsDAOjdbcImplTest {
	private FruitsDAO fruitsDAO=new FruitsDAOjdbcImpl();
	@Test
	public void testGetAll() {
		List<Fruits> fruits=fruitsDAO.getAll();
		System.out.println(fruits);
	}
	
	@Test
	public void testgetListWithCriteriaFruit(){
		CriteriaFruit cf = new CriteriaFruit("", "", "нч╨к");
		List<Fruits> fruits=fruitsDAO.getListWithCriteriaFruit(cf);
		System.out.println(fruits);
	}
	@Test
	public void testUpdate() {
		Fruits fruits=new Fruits();
		fruitsDAO.update(fruits);
	}



}
