package com.camera.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cameras")
public class CameraEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotNull
	private String serial;
	@Column
	private String home_id = null;
	@Column
	@NotNull
	private String url;
	@Column
	private String connection = null;	
	@Column
	private String security_level = null;
	@Column
	private String status = null;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
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
