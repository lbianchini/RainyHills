package com.rainyhills.services.domain;

import java.io.Serializable;

/**
 * Domain Class that represents a Hill.
 * 
 * @author Leandro Bianchini
 *
 */
public class Hill implements Serializable {

	private static final long serialVersionUID = 8620618510524037174L;
	
	private Integer index;
	private Integer height;
	private Integer waterVolume;
	
	public Hill() {}
	
	public Hill(Integer height) {
		this.height = height;
		this.waterVolume = 0;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getWaterVolume() {
		return waterVolume;
	}
	
	public void setWaterVolume(Integer waterVolume) {
		this.waterVolume = waterVolume;
	}
	
}
