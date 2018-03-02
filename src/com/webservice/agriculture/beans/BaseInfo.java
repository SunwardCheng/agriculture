package com.webservice.agriculture.beans;

public class BaseInfo {
	private String BaseID;
	private String BaseName;
	private String BaseImage;
	private String BaseAddre;
	private int AreaCount;
	private String baseArea;
	private String Longitude;
	private String Dimension;
	private String Country;
	private String Province;
	private String City;
	private String County;
	
	public String getBaseImage() {
		return BaseImage;
	}
	public void setBaseImage(String baseImage) {
		BaseImage = baseImage;
	}
	public String getBaseID() {
		return BaseID;
	}
	
	public void setBaseID(String baseID) {
		BaseID = baseID;
	}
	public String getBaseName() {
		return BaseName;
	}
	public void setBaseName(String BaseName) {
		this.BaseName = BaseName;
	}
	public String getBaseAddre() {
		return BaseAddre;
	}
	public void setBaseAddre(String baseAddre) {
		BaseAddre = baseAddre;
	}

	
	public int getAreaCount() {
		return AreaCount;
	}
	public void setAreaCount(int areaCount) {
		AreaCount = areaCount;
	}
	public String getBaseArea() {
		return baseArea;
	}
	public void setBaseArea(String baseArea) {
		this.baseArea = baseArea;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String Longitude) {
		this.Longitude = Longitude;
	}
	public String getDimension() {
		return Dimension;
	}
	public void setDimension(String Dimension) {
		this.Dimension = Dimension;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCounty() {
		return County;
	}
	public void setCounty(String county) {
		County = county;
	}
	
	public BaseInfo(String baseID, String baseName, String baseImage,
			String baseAddre, String baseArea, String longitude,
			String dimension, String country, String province, String city,
			String county) {
		super();
		BaseID = baseID;
		BaseName = baseName;
		BaseImage = baseImage;
		BaseAddre = baseAddre;
		this.baseArea = baseArea;
		Longitude = longitude;
		Dimension = dimension;
		Country = country;
		Province = province;
		City = city;
		County = county;
	}
	public BaseInfo() {
	
	}
	@Override
	public String toString() {
		return "BaseInfo [BaseID=" + BaseID + ", BaseName=" + BaseName
				+ ", BaseAddre=" + BaseAddre + ", baseArea=" + baseArea
				+ ", Longitude=" + Longitude + ", Dimension=" + Dimension
				+ ", Country=" + Country + ", Province=" + Province + ", City="
				+ City + ", County=" + County + "]";
	}
	
	
	
	
}
