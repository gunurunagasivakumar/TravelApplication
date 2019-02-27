package com.capco.travel.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capco.travel.vo.AccomodationDetailsVO;

/**
 * This class is created to validate Accomodation details from main request
 * @author e5544354
 *
 */
@Component
public class AccomodationDetailsValidator implements Validator {
	private final Logger logger = Logger.getLogger(AccomodationDetailsValidator.class);

	@SuppressWarnings("rawtypes")
	@Override
    public boolean supports(Class clazz) {
      return AccomodationDetailsVO.class.equals(clazz);
    }

	/**
	 * This method will validate all accomodation request fields and returns errors if any
	 */
    @Override
    public void validate(Object target, Errors errors) {
    	logger.info("AccomodationDetailsValidator : validate: Started");
    	AccomodationDetailsVO accomodationDetailsVO = (AccomodationDetailsVO) target;
		if(accomodationDetailsVO != null) {
		        ValidationUtils.rejectIfEmpty(errors, "checkIn", "accomodationDetails.checkIn.empty");
		        ValidationUtils.rejectIfEmpty(errors, "checkOut", "accomodationDetails.checkOut.empty");
		        ValidationUtils.rejectIfEmpty(errors, "city", "accomodationDetails.city.empty");
		        ValidationUtils.rejectIfEmpty(errors, "country", "accomodationDetails.country.empty");        
		}

      logger.info("AccomodationDetailsValidator : validate: Ended");
    }


}