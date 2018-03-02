package com.webservice.agriculture.beans;

public class Environment {
	private String SensorID;
	private float AirTemperature;
	private float AirHumidity;
	private float SoilTemperature;
	private float SoilHumidity;
	private float Illumination;
	private String realTime;
	public String getSensorID() {
		return SensorID;
	}
	public void setSensorID(String sensorID) {
		SensorID = sensorID;
	}
	public float getAirTemperature() {
		return AirTemperature;
	}
	public void setAirTemperature(float airTemperature) {
		AirTemperature = airTemperature;
	}
	public float getAirHumidity() {
		return AirHumidity;
	}
	public void setAirHumidity(float airHumidity) {
		AirHumidity = airHumidity;
	}
	public float getSoilTemperature() {
		return SoilTemperature;
	}
	public void setSoilTemperature(float soilTemperature) {
		SoilTemperature = soilTemperature;
	}
	public float getSoilHumidity() {
		return SoilHumidity;
	}
	public void setSoilHumidity(float soilHumidity) {
		SoilHumidity = soilHumidity;
	}
	public float getIllumination() {
		return Illumination;
	}
	public void setIllumination(float illumination) {
		Illumination = illumination;
	}
	public String getRealTime() {
		return realTime;
	}
	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}
	public Environment(String sensorID, float airTemperature,
			float airHumidity, float soilTemperature, float soilHumidity,
			float illumination, String realTime) {
		super();
		SensorID = sensorID;
		AirTemperature = airTemperature;
		AirHumidity = airHumidity;
		SoilTemperature = soilTemperature;
		SoilHumidity = soilHumidity;
		Illumination = illumination;
		this.realTime = realTime;
	}
	public Environment() {
		
	}
	@Override
	public String toString() {
		return "Environment [SensorID=" + SensorID + ", AirTemperature="
				+ AirTemperature + ", AirHumidity=" + AirHumidity
				+ ", SoilTemperature=" + SoilTemperature + ", SoilHumidity="
				+ SoilHumidity + ", Illumination=" + Illumination
				+ ", realTime=" + realTime + "]";
	}
	
	
}
