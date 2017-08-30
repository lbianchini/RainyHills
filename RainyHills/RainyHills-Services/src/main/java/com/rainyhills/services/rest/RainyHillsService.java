package com.rainyhills.services.rest;

import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.rainyhills.api.dto.SurfaceAreaDTO;
import com.rainyhills.services.config.ConstantMessages;
import com.rainyhills.services.controller.RainyHillsController;
import com.rainyhills.services.domain.SurfaceArea;
import com.rainyhills.services.exception.RainyHillsException;


/**
 * Class responsible to create and exposes services regarding
 * Surface Area.
 * 
 * @author Leandro Bianchini
 *
 */
@Local
@Stateless
@Path("surface-area")
public class RainyHillsService {

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(ConstantMessages.CONST_RESOURCE_BUNDLE_MESSAGES);

	@EJB
	private RainyHillsController rainyHillsController;
	
	private static  Mapper mapper = new DozerBeanMapper();	
	
	/**
	 * Service responsible to receive surface area with hills height as input,
	 * calulate its water volume and returns a new surface area as result.
	 * 
	 * @param surfaceAreaInput
	 * @return Response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/calculate")
	public Response calculateWaterVolume(SurfaceAreaDTO surfaceAreaInput) {

		Response serviceResponse;
		SurfaceAreaDTO surfaceAreaOutput;
		
		try {
			//Proceed with water volume calculation considering time elapsed.
			Long startTimeMilli = System.currentTimeMillis();
			Long startTimeNano = System.nanoTime();
			SurfaceArea surfaceArea = rainyHillsController.calculateWaterVolume(surfaceAreaInput.getHillsHeight());
			Long elapsedTimeMilli = System.currentTimeMillis() - startTimeMilli;
			Long elapsedTimeNano = System.nanoTime() - startTimeNano;
			
			//Convert a SurfaceArea object to a SurfaceAreaDTO object and set the Elapsed Time as a message
			surfaceAreaOutput = mapper.map(surfaceArea, SurfaceAreaDTO.class);
			surfaceAreaOutput.setTimeElapsedMilli(elapsedTimeMilli);
			surfaceAreaOutput.setTimeElapsedNano(elapsedTimeNano);
			
			surfaceAreaOutput.setHillsHeight(surfaceAreaInput.getHillsHeight());
			surfaceAreaOutput.setHillsCount(surfaceAreaOutput.getHills().size());
			
			//Send the response
			serviceResponse = Response.ok(surfaceAreaOutput, MediaType.APPLICATION_JSON).build();
		} catch (RainyHillsException e) {
			surfaceAreaOutput = new SurfaceAreaDTO();
			surfaceAreaOutput.setMessage(e.getMessage());
			serviceResponse = Response.status(Status.OK).entity(surfaceAreaOutput).build(); 
		}

		return serviceResponse;
	}
	
	/**
	 * Service just to check if REST services are onlune.
	 * 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/status")
	public Response status() {
		SurfaceAreaDTO response = new SurfaceAreaDTO();
		response.setMessage(resourceBundle.getString(ConstantMessages.CONST_MSG_SERVICE_RUNNING));
		return Response.ok(response).build();
	}

}
