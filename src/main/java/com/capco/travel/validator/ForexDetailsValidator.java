package com.capco.travel.validator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.capco.travel.vo.ForexDetailsVO;

/**
 * This class is created to validate forex details from main request
 * @author e5545565
 *
 */
@Component
public class ForexDetailsValidator implements Validator {
	private final Logger logger = Logger.getLogger(ForexDetailsValidator.class);

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return ForexDetailsVO.class.equals(clazz);
	}

	/**
	 * This method will validate all forex request fields and returns errors if any
	 */
	@Override
	public void validate(Object target, Errors errors) {
		logger.info("ForexDetailsValidator : validate: Ended");
		ForexDetailsVO forexDetailsVO = (ForexDetailsVO) target;
		if(forexDetailsVO != null) {
			ValidationUtils.rejectIfEmpty(errors, "forexCountry", "forex.country.empty");
			ValidationUtils.rejectIfEmpty(errors, "forexCurrency", "forex.currency.empty");
			/*if(forexDetailsVO.getForexAmount() == 0) {
				errors.rejectValue("forexAmount", "Forex Amount Cannot be Null");
			}*/
		}
		logger.info("ForexDetailsValidator : validate: Ended");
	}
}