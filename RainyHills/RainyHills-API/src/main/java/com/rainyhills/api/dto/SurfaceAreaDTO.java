package com.rainyhills.api.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class acting as a Data Transfer Object that represents a Surface domain and 
 * responsible to expose its information to service consumers. 
 * 
 * @author Leandro Bianchini
 *
 */
@XmlRootElement
public class SurfaceAreaDTO implements Serializable {
	
	private static final long serialVersionUID = -8241730673404098483L;
	
	private String hillsHeight;
	private List<HillDTO> hills;
	private Integer hillsCount;
	private Integer waterVolume;
	private Integer processSteps;
	private Long timeElapsedMilli;
	private Long timeElapsedNano;
	private String message = "none";
	
	/**
	 * Get the hills height values separated by comma.
	 * 
	 * @return String
	 */
	@XmlElement
	public String getHillsHeight() {
		return hillsHeight;
	}

	/**
	 * Set the hills height values
	 * 
	 * @param hillsHeight
	 */
	public void setHillsHeight(String hillsHeight) {
		this.hillsHeight = hillsHeight;
	}
	
	/**
	 * Set the hills count.
	 * 
	 * @return
	 */
	public void setHillsCount(Integer hillsCount) {
		this.hillsCount = hillsCount;
	}

	/**
	 * Get the hills count.
	 * 
	 * @return
	 */
	@XmlElement
	public Integer getHillsCount() {
		return this.hillsCount;
	}

	/**
	 * Get the list of hills that composes a surface area.
	 * 
	 * @return List<HillDtoOutput>
	 */
	@XmlElement
	public List<HillDTO> getHills() {
		return hills;
	}
	
	/**
	 * Set a list of hills to compose a surface area.
	 * 
	 * @param hills
	 */
	public void setHills(List<HillDTO> hills) {
		this.hills = hills;
	}


	/**
	 * Get the water volume of the Surface(All water volume between hills).
	 * 
	 * @return Integer
	 */
	@XmlElement
	public Integer getWaterVolume() {
		return waterVolume;
	}
	
	/**
	 * Set the surface's water volume.
	 * 
	 * @param waterVolume
	 */
	public void setWaterVolume(Integer waterVolume) {
		this.waterVolume = waterVolume;
	}

	/**
	 * Get the number of iterations necessary to calculate the water volume.
	 * 
	 * @return
	 */
	@XmlElement
	public Integer getProcessSteps() {
		return processSteps;
	}

	/**
	 * Set the number of iterations were necessary to calculate the water volume.
	 * 
	 * @param processSteps
	 */
	public void setProcessSteps(Integer processSteps) {
		this.processSteps = processSteps;
	}

	/**
	 * Get the elapsed time calculation in milliseconds.
	 * 
	 * @return
	 */
	@XmlElement
	public Long getTimeElapsedMilli() {
		return timeElapsedMilli;
	}

	/**
	 * Set the elapsed time to process the calculation in milliseconds.
	 * 
	 * @param timeElapsedMilli
	 */
	public void setTimeElapsedMilli(Long timeElapsedMilli) {
		this.timeElapsedMilli = timeElapsedMilli;
	}

	/**
	 * Get the elapsed time calculation in nanoseconds.
	 * 
	 * @return
	 */
	@XmlElement
	public Long getTimeElapsedNano() {
		return timeElapsedNano;
	}

	/**
	 * Set the elapsed time to process the calculation in nanoseconds
	 * 
	 * @param timeElapsedNano
	 */
	public void setTimeElapsedNano(Long timeElapsedNano) {
		this.timeElapsedNano = timeElapsedNano;
	}

	/**
	 * Get the message information regard the surface area.
	 * 
	 * @return String
	 */
	@XmlElement(defaultValue="None")
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message information regard the surface area.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
