package com.webservice.agriculture.dao.impl;

import java.util.List;

import com.webservice.agriculture.beans.Relation;
import com.webservice.agriculture.dao.DAO;
import com.webservice.agriculture.dao.RelationDAO;
/**
 * 
 * @author Sunward
 * 
 */
public class RelationDAOImpl extends DAO<Relation> implements RelationDAO {

	@Override
	public List<Relation> getAll() {
		String sql="select * from relation " ;
		return getForList(sql);
	}

	@Override
	public void update(Relation base) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Relation get(String baseId,String fruitId) {
		String sql="select * from relation where BaseID=? and FruitID=?";
		return super.get(sql, baseId,fruitId);
	}

	@Override
	public void delete(String id) {
		String sql="delete from relation where BaseID=?";
		super.update(sql,id);
		
	}

	@Override
	public List<Relation> getByBaseId(String baseId) {
		String sql="select * from relation where BaseID=?";
		return super.getForList(sql, baseId);
	}

	@Override
	public int getMaxAreaID(String baseID) {
		String sql="select AreaID from relation where BaseID=? order by AreaID desc limit 1";
		return getForValue(sql, baseID);
	}

	@Override
	public void batch(List<Relation> relation) {
		Object[][] params = new Object[relation.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{relation.get(i).getBaseID(),relation.get(i).getFruitID(),
					relation.get(i).getAreaID(),relation.get(i).getSensorID()};
		}
		String sql="insert into relation(BaseID,FruitID,AreaID,SensorID) values(?,?,?,?)";
		
		batch(sql, params);
	}

	@Override
	public Relation getByArea(String baseID, String areaID) {
		String sql="select * from relation where BaseID=? and AreaID=?";
		return super.get(sql, baseID,areaID);
	}

	@Override
	public void deleteFruit(String baseID, String fruitID) {
		String sql="delete from relation where BaseID=? and FruitID = ?";
		super.update(sql,baseID,fruitID);
		
	}
}
