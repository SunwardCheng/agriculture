package com.webservice.agriculture.test;

import java.sql.SQLException;

import org.junit.Test;

import java.sql.Connection;
import com.webservice.agriculture.db.jdbcUtils;

public class JdbcUtilsTest {


	@Test
	public void testGetConnection() throws SQLException {
		Connection connection=jdbcUtils.getConnection();
		System.out.println(connection);
	}

}
