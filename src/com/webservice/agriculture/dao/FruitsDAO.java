package com.webservice.agriculture.dao;

import java.util.List;

import com.webservice.agriculture.beans.Fruits;
/**
 * 
 * @author Sunward
 * 
 */
public interface FruitsDAO {
	
	public List<Fruits> getListWithCriteriaFruit(CriteriaFruit cf);
	
	public List<Fruits> getAll();
	
	public int update(Fruits fruits);
	
	public Fruits get(String id);
	
	public Fruits getByName(String name);
	
	public void delete(String id);

	public String getIdByName(String fruitName);

	public int intsert(Fruits fruit);
	
	public String getMaxID();
}
