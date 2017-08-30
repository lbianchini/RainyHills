package com.rainyhills.services.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain Class that represents a Surface area.
 * 
 * @author Leandro Bianchini
 *
 */
public class SurfaceArea implements Serializable {

	private static final long serialVersionUID = -1207408558397428113L;

	private List<Hill> hills;
	private Integer waterVolume;
	private Integer processSteps;
	
	public SurfaceArea() {
		this.hills = new ArrayList<Hill>();
		this.waterVolume = 0;
		this.processSteps = 0;
	}
	
	public List<Hill> getHills(){
		return this.hills;
	}
	
	public Hill getHillByIndex(Integer index) {
		return this.hills.get(index);
	}
	
	public Integer getHillsCount() {
		return this.hills.size();
	}
	
	public void addHill(Hill hill) {
		hill.setIndex(this.hills.size());
		this.hills.add(hill);
	}

	public Integer getWaterVolume() {
		return waterVolume;
	}
	
	public void setWaterVolume(Integer waterVolume) {
		this.waterVolume = waterVolume;
	}

	public Integer getProcessSteps() {
		return processSteps;
	}

	public void addProcessStep() {
		this.processSteps++;
	}

}
