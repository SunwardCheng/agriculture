package com.webservice.agriculture.dao.impl;

import java.util.List;

import com.webservice.agriculture.beans.Fruits;
import com.webservice.agriculture.dao.CriteriaFruit;
import com.webservice.agriculture.dao.DAO;
import com.webservice.agriculture.dao.FruitsDAO;
/**
 * 
 * @author Sunward
 * 
 */
public class FruitsDAOjdbcImpl extends DAO<Fruits> implements FruitsDAO{

	@Override
	public List<Fruits> getAll() {
		String sql="select * from fruit " ;
				//"and b.Node=(select Node from a where Variety='�޺���֦' and Type='����׼�¶�')";
		return getForList(sql);
	}

	@Override
	public int update(Fruits fruit) {
		String sql = "update fruit set Name=?,Feature=?,maxAirTemp=?,minAirTemp=?,maxAirHumi=?,minAirHumi=?," +
				"maxSoilTemp=?,minSoilTemp=?,maxSoilHumi=?,minSoilHumi=?,maxLight=?,minLight=? where FruitID = ?";
		return update(sql, fruit.getName(),fruit.getFeature(),fruit.getMaxAirTemp(),fruit.getMinAirTemp(),
				fruit.getMaxAirHumi(),fruit.getMinAirHumi(),fruit.getMaxSoilTemp(),fruit.getMinSoilTemp(),
				fruit.getMaxSoilHumi(),fruit.getMinSoilHumi(),fruit.getMaxLight(),fruit.getMinLight(),fruit.getFruitID());
				
	}

	@Override
	public Fruits get(String id) {
		String sql="select * from fruit where FruitID=?";
		return super.get(sql, id);
	}

	@Override
	public void delete(String id) {
		String sql="delete from fruit where FruitID=?";
		super.update(sql,id);
	}

	@Override
	public List<Fruits> getListWithCriteriaFruit(CriteriaFruit cf) {
		String sql="select * from fruit where fruitsName like ?";
		return getForList(sql,cf.getFruitsName());
	}

	@Override
	public Fruits getByName(String name) {
		String sql="select * from fruit where Name=?";
		return super.get(sql, name);
	}

	@Override
	public String getIdByName(String fruitName) {
		String sql="select FruitID from fruit where Name=?";
		return getForValue(sql, fruitName);
	}

	@Override
	public int intsert(Fruits fruit) {
		String sql = "insert into fruit(FruitID,Name,Feature,maxAirTemp,minAirTemp,maxAirHumi,minAirHumi" +
				",maxSoilTemp,minSoilTemp,maxSoilHumi,minSoilHumi,maxLight,minLight) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int i = update(sql, fruit.getFruitID(),fruit.getName(),fruit.getFeature(),fruit.getMaxAirTemp(),fruit.getMinAirTemp(),
				fruit.getMaxAirHumi(),fruit.getMinAirHumi(),fruit.getMaxSoilTemp(),fruit.getMinSoilTemp(),fruit.getMaxSoilHumi(),
				fruit.getMinSoilHumi(),fruit.getMaxLight(),fruit.getMinLight());
		return i;
	}

	@Override
	public String getMaxID() {
		String sql = "select FruitID from fruit order by ID desc limit 1";
		return getForValue(sql);
	}


}
