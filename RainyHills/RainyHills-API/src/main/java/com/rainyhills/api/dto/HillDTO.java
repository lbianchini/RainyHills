package com.rainyhills.api.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class acting as a Data Transfer Object that represents a Hill domain and is 
 * responsible to expose its information to service consumers. 
 * 
 * @author Leandro Bianchini
 *
 */
@XmlRootElement
public class HillDTO implements Serializable {
	
	private static final long serialVersionUID = -3667434775907367116L;
	
	private Integer index;
	private Integer height;
	private Integer waterVolume;
	
	/**
	 * Get the index (sequence number) of the hill.
	 * 	
	 * @return Integer
	 */
	public Integer getIndex() {
		return index;
	}
	
	/**
	 * Set the hill's index (sequence number).
	 * 
	 * @param index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	/**
	 * Get the hill's height value.
	 * 
	 * @return Integer
	 */
	public Integer getHeight() {
		return height;
	}
	
	/**
	 * Set the hill's height value.
	 * 
	 * @param height
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	/**
	 * Get the water volume of the hill(A specific hill, not the surface).
	 * 
	 * @return Integer
	 */
	public Integer getWaterVolume() {
		return waterVolume;
	}
	
	/**
	 * Set the hill's water volume.
	 * 
	 * @param waterVolume
	 */
	public void setWaterVolume(Integer waterVolume) {
		this.waterVolume = waterVolume;
	}

}
