package com.webservice.agriculture.beans;

public class Fruits {
	
	private String FruitID;
	private String Name;
	private String Feature;
	private String mainValue;
	private String Depict;
	private float maxAirTemp;
	private float minAirTemp;
	private float maxAirHumi;
	private float minAirHumi;
	private float maxLight;
	private float minLight;
	private float maxSoilTemp;
	private float minSoilTemp;
	private float maxSoilHumi;
	private float minSoilHumi;
	
	public String getFruitID() {
		return FruitID;
	}
	public void setFruitID(String fruitID) {
		FruitID = fruitID;
	}
	public String getFeature() {
		return Feature;
	}
	public void setFeature(String feature) {
		Feature = feature;
	}
	public String getMainValue() {
		return mainValue;
	}
	public void setMainValue(String mainValue) {
		this.mainValue = mainValue;
	}
	public String getDepict() {
		return Depict;
	}
	public void setDepict(String depict) {
		Depict = depict;
	}
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public float getMaxAirTemp() {
		return maxAirTemp;
	}
	public void setMaxAirTemp(float maxAirTemp) {
		this.maxAirTemp = maxAirTemp;
	}
	public float getMinAirTemp() {
		return minAirTemp;
	}
	public void setMinAirTemp(float minAirTemp) {
		this.minAirTemp = minAirTemp;
	}
	public float getMaxAirHumi() {
		return maxAirHumi;
	}
	public void setMaxAirHumi(float maxAirHumi) {
		this.maxAirHumi = maxAirHumi;
	}
	public float getMinAirHumi() {
		return minAirHumi;
	}
	public void setMinAirHumi(float minAirHumi) {
		this.minAirHumi = minAirHumi;
	}
	public float getMaxLight() {
		return maxLight;
	}
	public void setMaxLight(float maxLight) {
		this.maxLight = maxLight;
	}
	public float getMinLight() {
		return minLight;
	}
	public void setMinLight(float minLight) {
		this.minLight = minLight;
	}
	public float getMaxSoilTemp() {
		return maxSoilTemp;
	}
	public void setMaxSoilTemp(float maxSoilTemp) {
		this.maxSoilTemp = maxSoilTemp;
	}
	public float getMinSoilTemp() {
		return minSoilTemp;
	}
	public void setMinSoilTemp(float minSoilTemp) {
		this.minSoilTemp = minSoilTemp;
	}
	public float getMaxSoilHumi() {
		return maxSoilHumi;
	}
	public void setMaxSoilHumi(float maxSoilHumi) {
		this.maxSoilHumi = maxSoilHumi;
	}
	public float getMinSoilHumi() {
		return minSoilHumi;
	}
	public void setMinSoilHumi(float minSoilHumi) {
		this.minSoilHumi = minSoilHumi;
	}

	
	
	public Fruits(String fruitID, String name, String feature,
			String mainValue, String depict, float maxAirTemp,
			float minAirTemp, float maxAirHumi, float minAirHumi,
			float maxLight, float minLight, float maxSoilTemp,
			float minSoilTemp, float maxSoilHumi, float minSoilHumi) {
		super();
		FruitID = fruitID;
		Name = name;
		Feature = feature;
		this.mainValue = mainValue;
		Depict = depict;
		this.maxAirTemp = maxAirTemp;
		this.minAirTemp = minAirTemp;
		this.maxAirHumi = maxAirHumi;
		this.minAirHumi = minAirHumi;
		this.maxLight = maxLight;
		this.minLight = minLight;
		this.maxSoilTemp = maxSoilTemp;
		this.minSoilTemp = minSoilTemp;
		this.maxSoilHumi = maxSoilHumi;
		this.minSoilHumi = minSoilHumi;
	}
	public Fruits() {
		
	}
	@Override
	public String toString() {
		return "Fruits [FruitID=" + FruitID + ", Name=" + Name + ", Feature="
				+ Feature + ", mainValue=" + mainValue + ", Depict=" + Depict
				+ ", maxAirTemp=" + maxAirTemp + ", minAirTemp=" + minAirTemp
				+ ", maxAirHumi=" + maxAirHumi + ", minAirHumi=" + minAirHumi
				+ ", maxLight=" + maxLight + ", minLight=" + minLight
				+ ", maxSoilTemp=" + maxSoilTemp + ", minSoilTemp="
				+ minSoilTemp + ", maxSoilHumi=" + maxSoilHumi
				+ ", minSoilHumi=" + minSoilHumi + "]";
	}

	
	
	
	
	
}
