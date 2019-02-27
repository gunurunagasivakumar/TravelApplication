package com.capco.travel.validator;

import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.vo.EmployeeDetailsVO;

/**
 * @author e5542274
 *
 */
public class EmployeeDetailsValidator {

	private EmployeeDetailsValidator() {
	}

	public static MainRequestBO validateEmployeeDetails(MainRequestBO mainRequestBO, EmployeeDetailsVO empDetailsvO) {
		EmployeeDetailsBO empDetailsBO = new EmployeeDetailsBO();
		if (empDetailsvO.getEmailId() != null) {
			empDetailsBO.setEmailId(empDetailsvO.getEmailId());
		}

		return mainRequestBO;
	}

}
