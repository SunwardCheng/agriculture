package com.webservice.agriculture.controller.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.webservice.agriculture.service.administrator.AdministratorService;

public class TestAdmin {

	@Test
	public void testGetByName() {
		AdministratorService administratorService = new AdministratorService();
		System.out.println(administratorService.getByName("NongXueYuan"));
	}

	@Test
	public void testGetID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserToJsonObject() {
		fail("Not yet implemented");
	}

}
