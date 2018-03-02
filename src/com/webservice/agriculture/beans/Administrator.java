package com.webservice.agriculture.beans;

public class Administrator {
	private int AdminID;
	private String UserName;
	private String password;
	private String Email;
	private String Authority;
	private String Phone;
	private String Depict;
	private String RegisTime;
	public String getRegisTime() {
		return RegisTime;
	}
	public void setRegisTime(String regisTime) {
		this.RegisTime = regisTime;
	}
	public int getAdminID() {
		return AdminID;
	}
	public void setAdminID(int adminID) {
		this.AdminID = adminID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getDepict() {
		return Depict;
	}
	public void setDepict(String depict) {
		Depict = depict;
	}
	
	
	public Administrator(int adminID, String userName, String password,
			String email, String authority, String phone, String depict,
			String regisTime) {
		super();
		AdminID = adminID;
		UserName = userName;
		this.password = password;
		Email = email;
		Authority = authority;
		Phone = phone;
		Depict = depict;
		RegisTime = regisTime;
	}
	public String getAuthority() {
		return Authority;
	}
	public void setAuthority(String authority) {
		Authority = authority;
	}
	public Administrator() {
		
	}
	@Override
	public String toString() {
		return "Administrator [AdminID=" + AdminID + ", UserName=" + UserName
				+ ", password=" + password + ", Email=" + Email
				+ ", Authority=" + Authority + ", Phone=" + Phone + ", Depict="
				+ Depict + ", RegisTime=" + RegisTime + "]";
	}
	
}
