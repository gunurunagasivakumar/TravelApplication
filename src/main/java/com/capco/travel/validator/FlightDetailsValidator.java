package com.capco.travel.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capco.travel.vo.FlightDetailsVO;

/**
 * This class is created to validate flightDetails from main request
 * 
 * @author e5544354
 *
 */
@Component
public class FlightDetailsValidator implements Validator {
	private final Logger logger = Logger.getLogger(FlightDetailsValidator.class);

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return FlightDetailsVO.class.equals(clazz);
	}

	/**
	 * This method will validate all flight request fields and returns errors if any
	 */
	@Override
	public void validate(Object target, Errors errors) {
		logger.info("FlightDetailsValidator : validate: Started");
		FlightDetailsVO flightDetailsVO = (FlightDetailsVO) target;
		if (flightDetailsVO != null) {
			//ValidationUtils.rejectIfEmpty(errors, "tourType", "flightDetails.tourType.empty");
			ValidationUtils.rejectIfEmpty(errors, "travelType", "flightDetails.travelType.empty");
			//ValidationUtils.rejectIfEmpty(errors, "businessPurpose", "flightDetails.businessPurpose.empty");
			//ValidationUtils.rejectIfEmpty(errors, "prefClass", "flightDetails.prefClass.empty");
			ValidationUtils.rejectIfEmpty(errors, "departureDate", "flightDetails.departureDate.empty");
			ValidationUtils.rejectIfEmpty(errors, "departureTime", "flightDetails.departureTime.empty");
			ValidationUtils.rejectIfEmpty(errors, "destinationLocation", "flightDetails.destinationLocation.empty");
			ValidationUtils.rejectIfEmpty(errors, "departureLocation", "flightDetails.departureLocation.empty");
			//ValidationUtils.rejectIfEmpty(errors, "surname", "flightDetails.surname.empty");
			//ValidationUtils.rejectIfEmpty(errors, "givenName", "flightDetails.givenName.empty");
			//ValidationUtils.rejectIfEmpty(errors, "dateOfBirth", "flightDetails.dateOfBirth.empty");

			logger.info("FlightDetailsValidator : validate: Ended");
		}
	}
}