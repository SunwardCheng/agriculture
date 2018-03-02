package com.webservice.agriculture.beans;

public class Relation {
	private String BaseID;
	private String FruitID;
	private int AreaID;
	private String SensorID;
	public String getBaseID() {
		return BaseID;
	}
	public void setBaseID(String baseID) {
		BaseID = baseID;
	}
	public String getFruitID() {
		return FruitID;
	}
	public void setFruitID(String fruitID) {
		FruitID = fruitID;
	}
	public int getAreaID() {
		return AreaID;
	}
	public void setAreaID(int areaID) {
		AreaID = areaID;
	}
	public String getSensorID() {
		return SensorID;
	}
	public void setSensorID(String sensorID) {
		SensorID = sensorID;
	}
	public Relation(String baseID, String fruitID, int areaID,
			String sensorID) {
		super();
		BaseID = baseID;
		FruitID = fruitID;
		AreaID = areaID;
		SensorID = sensorID;
	}
	public Relation() {
	
	}
	@Override
	public String toString() {
		return "Relation [BaseID=" + BaseID + ", FruitID=" + FruitID
				+ ", AreaID=" + AreaID + ", SensorID=" + SensorID + "]";
	}
	
	
	
}
