package com.rainyhills.services.exception;

import java.io.Serializable;

import javax.ejb.ApplicationException;

/**
 * Abstract Class that represents the most high level specific exception for this application.
 * Every other exceptions used to specify an error in this application must inherit
 * from this class. 
 * 
 * @author Leandro Bianchini
 *
 */
@ApplicationException(rollback=true)
public abstract class RainyHillsException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1931242172833625385L;
	
	public RainyHillsException() {}
	
	public RainyHillsException(String message) {
		super(message);
	}

}
