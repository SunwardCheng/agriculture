package com.webservice.agriculture.dao.impl;

import java.util.List;

import com.webservice.agriculture.beans.BaseInfo;
import com.webservice.agriculture.dao.BaseInfoDAO;
import com.webservice.agriculture.dao.DAO;
/**
 * 
 * @author Sunward
 * 
 */
public class BaseInfoDAOImpl extends DAO<BaseInfo> implements BaseInfoDAO{

	@Override
	public List<BaseInfo> getAll() {
		String sql="select * from base " ;
		return getForList(sql);
	}

	@Override
	public int update(BaseInfo base) {
		String sql = "update base set BaseName=?,BaseImage=?,BaseAddre=?,BaseArea=?,Longitude=?," +
				"Dimension=?,Country=?,Province=?,City=?,County=? where BaseID = ?";
		return update(sql,base.getBaseName(),base.getBaseImage(),base.getBaseAddre(),base.getBaseArea()
				,base.getLongitude(),base.getDimension(),base.getCountry(),base.getProvince(),
				base.getCity(),base.getCounty(),base.getBaseID());
		
	}

	@Override
	public BaseInfo get(String id) {
		String sql="select * from base where BaseID=?";
		return super.get(sql, id);
	}

	@Override
	public void delete(String id) {
		String sql="delete from base where BaseID=?";
		super.update(sql,id);
		
	}

	@Override
	public BaseInfo getByName(String name) {
		String sql="select * from base where BaseName=?";
		return get(sql,name);
	}

	@Override
	public List<BaseInfo> getBaseNames() {
		String sql="select * from base";
		return getForList(sql);
	}

	@Override
	public String getIdByName(String name) {
		String sql="select BaseID from base where BaseName=?";
		return getForValue(sql, name);
	}

	@Override
	public int insert(BaseInfo base) {
		String sql = "insert into base(BaseID,BaseName,BaseImage,BaseAddre,BaseArea,Longitude," +
				"Dimension,Country,Province,City,County) values(?,?,?,?,?,?,?,?,?,?,?)";
		return update(sql, base.getBaseID(),base.getBaseName(),base.getBaseImage(),base.getBaseAddre(),
				base.getBaseArea(),base.getLongitude(),base.getDimension(),base.getCountry(),base.getProvince(),
				base.getCity(),base.getCounty());
	}

	@Override
	public String getMaxID() {
		String sql="select BaseID from base order by ID desc limit 1 ";
		return getForValue(sql);
	}

}
