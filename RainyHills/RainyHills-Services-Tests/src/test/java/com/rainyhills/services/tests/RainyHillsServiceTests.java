package com.rainyhills.services.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.rainyhills.api.dto.SurfaceAreaDTO;

/**
 * Class responsible to perform tests on Services.  
 * 
 * @author Leandro Bianchini
 *
 */
public class RainyHillsServiceTests {
	
	public RainyHillsServiceTests() {
		baseURI = "http://localhost/RainyHills-Services/api/surface-area";
		port = 8080;		
	}
	
	/**
	 * Test intended to check if the Application Service is running
	 */
	@Test
	public void mustBeServiceRunning() {
		given()
			.header("Accept", MediaType.APPLICATION_JSON)
			.get("/status")
			.then()
			.assertThat()
			.body("message", equalTo("Service is Running"));
	}
	
	/**
	 * Test intended to check if the water volume is 2
	 */	
	@Test
	public void mustWaterVolumeBe2() {
		
		SurfaceAreaDTO inputBody = new SurfaceAreaDTO();
		inputBody.setHillsHeight("1,0,0,1");
		
		given()
			.header("Accept", MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(inputBody)
			.post("/water-volume")
			.then()
			.assertThat()
			.body("waterVolume", equalTo(2));
	}
	
	/**
	 * Test intended to check if the water volume is 4
	 */	
	@Test
	public void mustWaterVolumeBe4() {
		
		SurfaceAreaDTO inputBody = new SurfaceAreaDTO();
		inputBody.setHillsHeight("2,0,2,0,2");
		
		given()
			.header("Accept", MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(inputBody)
			.post("/water-volume")
			.then()
			.assertThat()
			.body("waterVolume", equalTo(4));
	}

	
	/**
	 * Test intended to check if the water volume is 10
	 */	
	@Test
	public void mustWaterVolumeBe10() {
		
		SurfaceAreaDTO inputBody = new SurfaceAreaDTO();
		inputBody.setHillsHeight("5,0,0,5");
		
		given()
			.header("Accept", MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(inputBody)
			.post("/water-volume")
			.then()
			.assertThat()
			.body("waterVolume", equalTo(10));
	}

	/**
	 * Test intended to check if an input error by pattern will be returned.
	 */	
	@Test
	public void mustThrowExceptionByPattern() {
		
		SurfaceAreaDTO inputBody = new SurfaceAreaDTO();
		inputBody.setHillsHeight("1;0,0,1");
		
		given()
			.header("Accept", MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(inputBody)
			.post("/water-volume")
			.then()
			.assertThat()
			.body("message", equalTo("Hills height must be integer numbers."));
	}
	
	/**
	 * Test intended to check if an input error by a negative number will be returned.
	 */	
	@Test
	public void mustThrowExceptionByNegativeNumber() {
		
		SurfaceAreaDTO inputBody = new SurfaceAreaDTO();
		inputBody.setHillsHeight("1,0,0,-1");
		
		given()
			.header("Accept", MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(inputBody)
			.post("/water-volume")
			.then()
			.assertThat()
			.body("message", equalTo("Hills height must be positive numbers."));
	}	

}
