package com.rainyhills.services.exception;

import java.io.Serializable;

/**
 * Class that represents a specific exception for errors on Water Volume calculation.
 * 
 * @author Leandro Bianchini
 *
 */
public class WaterVolumeCalculateException extends RainyHillsException implements Serializable {

	private static final long serialVersionUID = -5563154381613356469L;

	public WaterVolumeCalculateException() {}
	
	public WaterVolumeCalculateException(String message) {
		super(message);
	}
	
}
