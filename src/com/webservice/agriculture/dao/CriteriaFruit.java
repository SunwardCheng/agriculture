package com.webservice.agriculture.dao;
/**
 * 
 * @author Sunward
 * 
 */
public class CriteriaFruit {
	private String node;//�������ڵ���
	private String serial;//�������ڵ�ӿ�
	private String fruitsName;//ˮ��Ʒ��
	
	private float air_temperature;//�����¶�
	private float air_humidity;//����ʪ��
	private double illumination;//����ǿ��
	private String realtime;//�ɼ�ʱ��
	
	private float soil_temperature;//�����¶�
	private float soil_humidity;//����ʪ��
	
	private float air_acc_temperature;//����׼�¶�

	public String getNode() {
		if (node==null||node=="") {
			node="%%";
		}else {
			node="%"+node+"%";
		}
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getSerial() {
		if (serial==null||serial=="") {
			serial="%%";
		}else {
			serial="%"+serial+"%";
		}
		
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getFruitsName() {
		if (fruitsName==null||fruitsName=="") {
			fruitsName="%%";
		}else {
			fruitsName="%"+fruitsName+"%";
		}
		
		return fruitsName;
	}

	public void setFruitsName(String fruitsName) {
		this.fruitsName = fruitsName;
	}


	public float getAir_temperature() {
		return air_temperature;
	}

	public void setAir_temperature(float air_temperature) {
		this.air_temperature = air_temperature;
	}

	public float getAir_humidity() {
		return air_humidity;
	}

	public void setAir_humidity(float air_humidity) {
		this.air_humidity = air_humidity;
	}

	public double getIllumination() {
		return illumination;
	}

	public void setIllumination(double illumination) {
		this.illumination = illumination;
	}

	public String getRealtime() {
		return realtime;
	}

	public void setRealtime(String realtime) {
		this.realtime = realtime;
	}

	public float getSoil_temperature() {
		return soil_temperature;
	}

	public void setSoil_temperature(float soil_temperature) {
		this.soil_temperature = soil_temperature;
	}

	public float getSoil_humidity() {
		return soil_humidity;
	}

	public void setSoil_humidity(float soil_humidity) {
		this.soil_humidity = soil_humidity;
	}

	public float getAir_acc_temperature() {
		return air_acc_temperature;
	}

	public void setAir_acc_temperature(float air_acc_temperature) {
		this.air_acc_temperature = air_acc_temperature;
	}

	
	public CriteriaFruit(String node, String serial, String fruitsName) {
		super();
		this.node = node;
		this.serial = serial;
		this.fruitsName = fruitsName;
		
	}

	public CriteriaFruit() {

	}

	@Override
	public String toString() {
		return "CriteriaFruit [node=" + node + ", serial=" + serial
				+ ", fruitsName=" + fruitsName + ", air_temperature="
				+ air_temperature + ", air_humidity="
				+ air_humidity + ", illumination=" + illumination
				+ ", realtime=" + realtime + ", soil_temperature="
				+ soil_temperature + ", soil_humidity=" + soil_humidity
				+ ", air_acc_temperature=" + air_acc_temperature + "]";
	}

	

}
