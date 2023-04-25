package com.camera.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "warning")
public class WarningEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name = null;
	@Column
	@NotNull
	private String serial;
	@Column
	private String content = null;
	@Column
	private String time_from = null;
	@Column
	private String time_to = null;
	@Column
	private String level = null;
	@Column
	private String face_image = null;
	@Column
	private String region_id = null;
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime_from() {
		return time_from;
	}
	public void setTime_from(String time_from) {
		this.time_from = time_from;
	}
	public String getTime_to() {
		return time_to;
	}
	public void setTime_to(String time_to) {
		this.time_to = time_to;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getFace_image() {
		return face_image;
	}
	public void setFace_image(String face_image) {
		this.face_image = face_image;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	
	
}
