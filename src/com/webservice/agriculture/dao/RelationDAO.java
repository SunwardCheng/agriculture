package com.webservice.agriculture.dao;

import java.util.List;

import com.webservice.agriculture.beans.Relation;
/**
 * 
 * @author Sunward
 * 
 */
public interface RelationDAO {
	
	public List<Relation> getAll();
	
	public void update(Relation base);
	
	public Relation get(String BaseId,String fruitId);
	
	public List<Relation> getByBaseId(String baseId);
	
	public Relation getByArea(String baseID,String areaID);
	
	public void delete(String baseID);
	
	public void deleteFruit(String baseID,String fruitID);
	
	public int getMaxAreaID(String baseID);
	
	public void batch(List<Relation> relation);
}
