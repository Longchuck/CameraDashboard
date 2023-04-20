package com.virtualcam.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cameras")
public class CameraEntity {
	private String id;
	private String serial;
	private String home_id;
	private String url;
	private String connection;	
	private String security_level;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getHome_id() {
		return home_id;
	}
	public void setHome_id(String home_id) {
		this.home_id = home_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public String getSecurity_level() {
		return security_level;
	}
	public void setSecurity_level(String security_level) {
		this.security_level = security_level;
	}
	
}
