package com.capco.travel.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capco.travel.vo.CabDetailsVO;

/**
 * This class is created to validate Cab details from main request
 * 
 * @author e5544354
 *
 */
@Component
public class CabDetailsValidator implements Validator {
	private final Logger logger = Logger.getLogger(CabDetailsValidator.class);
	@Override
	public boolean supports(Class clazz) {
		return CabDetailsVO.class.equals(clazz);
	}

	/**
	 * This method will validate all Cab request fields and returns errors if any
	 */
	@Override
	public void validate(Object target, Errors errors) {
    	logger.info("CabDetailsValidator : validate: Started");
		CabDetailsVO cabDetailsVO = (CabDetailsVO) target;
		if (cabDetailsVO != null) {
			ValidationUtils.rejectIfEmpty(errors, "taxiTravelType", "cabDetails.taxiTravelType.empty");
			//ValidationUtils.rejectIfEmpty(errors, "numberOfPersons", "cabDetails.numberOfPersons.empty");
			ValidationUtils.rejectIfEmpty(errors, "pickupCity", "cabDetails.pickupCity.empty");
			//ValidationUtils.rejectIfEmpty(errors, "dropCity", "cabDetails.dropCity.empty");
			ValidationUtils.rejectIfEmpty(errors, "country", "cabDetails.country.empty");
			ValidationUtils.rejectIfEmpty(errors, "pickUpLocation", "cabDetails.pickUpLocation.empty");
			ValidationUtils.rejectIfEmpty(errors, "dropLocation", "cabDetails.dropLocation.empty");
			ValidationUtils.rejectIfEmpty(errors, "fromDateAndTime", "cabDetails.fromDateAndTime.empty");
			//ValidationUtils.rejectIfEmpty(errors, "toDate", "cabDetails.toDate.empty");
			ValidationUtils.rejectIfEmpty(errors, "carType", "cabDetails.carType.empty");
//			if (cabDetailsVO.getNumberOfPersons() == 0) {
//				errors.rejectValue("numberOfPersons", "cabDetails.numberOfPersons.zeroNotAllowed", "greaterThanZero");
//			}
		}
		logger.info("CabDetailsValidator : validate: Ended");
	}

}