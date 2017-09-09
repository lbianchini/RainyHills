package com.rainyhills.services.controller;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.rainyhills.services.config.ConstantMessages;
import com.rainyhills.services.domain.Hill;
import com.rainyhills.services.domain.SurfaceArea;
import com.rainyhills.services.exception.WaterVolumeCalculateException;

/**
 * Class responsible for the Surface area Business Logic calculation.
 * 
 * @author Leandro Bianchini
 *
 */
@Local
@Stateless
public class RainyHillsController {

	 private static ResourceBundle resourceBundle = ResourceBundle.getBundle(ConstantMessages.CONST_RESOURCE_BUNDLE_MESSAGES);
	
	/**
	 * Method responsible to calculate surface water volume receiving, 
	 * as input param a comma separated hill height values string.
	 * 
	 * @param hillsHeight
	 * @return SurfaceArea
	 * @throws WaterVolumeCalculateException
	 */
	public SurfaceArea calculateWaterVolume(String hillsHeight) throws WaterVolumeCalculateException {
		SurfaceArea surfaceArea;
		try {
			
			//Split the values into a array of integers
			int[] hillsList = Arrays.stream(hillsHeight.split(",")).mapToInt(i -> Integer.parseInt(i.trim())).toArray();
			
			//Call the method who calculates the water volume receiving as input an array of ints.
			surfaceArea = calculateWaterVolume(hillsList);
			
		} catch (PatternSyntaxException e) {
			//Exeption thrown if split fails.
			throw new WaterVolumeCalculateException(resourceBundle.getString(ConstantMessages.CONST_MSG_PATTERN_EXCEPTION));
		} catch (NumberFormatException e) {
			//Exeption thrown if there is some invalid integer number.
			throw new WaterVolumeCalculateException(resourceBundle.getString(ConstantMessages.CONST_MSG_NUMBER_FORMAT_EXCEPTION));
		} catch (WaterVolumeCalculateException e) {
			//Exception thrown by calculateWaterVolume method
			throw e;
		} catch (Exception e) {
			throw new WaterVolumeCalculateException(e.getMessage());
		}
		return surfaceArea;
	}

	/**
	 * Method responsible to calculate surface water volume receiving, 
	 * as input hill height values array int.
	 * 
	 * @param hillsHeight
	 * @return SurfaceArea
	 * @throws WaterVolumeCalculateException
	 */
	public SurfaceArea calculateWaterVolume(int[] hillsHeight) throws WaterVolumeCalculateException {
		SurfaceArea surfaceArea = new SurfaceArea();

		//Check if there is any negative numbers
		if (Arrays.stream(hillsHeight).filter(i -> i < 0).findAny().isPresent()) {
			throw new WaterVolumeCalculateException(resourceBundle.getString(ConstantMessages.CONST_MSG_NEGATIVE_NUMBER_EXCEPTION));
		}
		
		//Fill Surface area with height values hills
		Arrays.stream(hillsHeight).forEach(i -> surfaceArea.addHill(new Hill(i)));
		
		//Call the method who calculates the water volume receiving as input a SurfaceArea object.
		calculateWaterVolume(surfaceArea);
		
		return surfaceArea;
	}

	/**
	 * Method responsible to calculate surface water volume receiving 
	 * as input a SurfaceArea object.
	 * 
	 * @param hillsHeight
	 * @return SurfaceArea
	 * @throws WaterVolumeCalculateException
	 */
	private SurfaceArea calculateWaterVolume(SurfaceArea surfaceArea) {

		if(surfaceArea.getHillsCount() < 3) {
			return surfaceArea;
		}
		
		//Initializing variables used to calculate water volume.
		Integer surfaceAreaWaterVolume = 0;
		Integer highestLeftHillHeight = surfaceArea.getHillByIndex(0).getHeight();
		Integer highestRightHillHeight = surfaceArea.getHillByIndex(surfaceArea.getHillsCount() - 1).getHeight();
		Integer leftIndex = 1;
		Integer rightIndex = surfaceArea.getHillsCount() - 2;
		
		//Iterating the list of hills from both sides(left and right) 
		//to determine the highest hill from each side.
		while (leftIndex <= rightIndex) {

			surfaceArea.addProcessStep();
			
			Hill leftHill = surfaceArea.getHillByIndex(leftIndex);
			Hill rightHill = surfaceArea.getHillByIndex(rightIndex);

			if (highestLeftHillHeight <= highestRightHillHeight) {
				highestLeftHillHeight = Math.max(highestLeftHillHeight, leftHill.getHeight());
				leftHill.setWaterVolume(highestLeftHillHeight - leftHill.getHeight());
				surfaceAreaWaterVolume += leftHill.getWaterVolume();
				leftIndex++;
			} else {
				highestRightHillHeight = Math.max(highestRightHillHeight, rightHill.getHeight());
				rightHill.setWaterVolume(highestRightHillHeight - rightHill.getHeight());
				surfaceAreaWaterVolume += rightHill.getWaterVolume();
				rightIndex--;
			}
		}

		surfaceArea.setWaterVolume(surfaceAreaWaterVolume);

		return surfaceArea;
	}

}
