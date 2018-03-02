package com.webservice.agriculture.beans;

public class AdminBase {
	private String AdminID;
	private String BaseID;
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}
	public String getBaseID() {
		return BaseID;
	}
	public void setBaseID(String baseID) {
		BaseID = baseID;
	}
	public AdminBase(String adminID, String baseID) {
		super();
		AdminID = adminID;
		BaseID = baseID;
	}
	public AdminBase() {
		
	}
	@Override
	public String toString() {
		return "AdminBase [AdminID=" + AdminID + ", BaseID=" + BaseID + "]";
	}
	
	
	
	
}
