package com.capco.travel.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capco.travel.vo.VisaRequestVO;

/**
 * This class is created to validate VisaDetails from main request
 * @author e5544354
 *
 */
@Component
public class VisaDetailsValidator implements Validator {

	private final Logger logger = Logger.getLogger(VisaDetailsValidator.class);
	
	@SuppressWarnings("rawtypes")
	@Override
    public boolean supports(Class clazz) {
      return VisaRequestVO.class.equals(clazz);
    }

	/**
	 * This method will validate all VisaDetails request fields and returns errors if any
	 */
    @Override
    public void validate(Object target, Errors errors) {
    	logger.info("VisaDetailsValidator : validate: Started");
    	VisaRequestVO visaRequestVO = (VisaRequestVO) target;
		if(visaRequestVO != null) {
		        ValidationUtils.rejectIfEmpty(errors, "travelDestination", "visaRequestVO.travelDestination.empty");
		        ValidationUtils.rejectIfEmpty(errors, "visaType", "visaRequestVO.visaType.empty");
		        //ValidationUtils.rejectIfEmpty(errors, "typeOfVisit", "visaRequestVO.typeOfVisit.empty");
		}

		logger.info("VisaDetailsValidator : validate: Ended");
    }


}
