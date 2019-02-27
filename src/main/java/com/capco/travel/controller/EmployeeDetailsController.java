package com.capco.travel.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.service.EmployeeDetailService;

import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.EmployeeDetailsVO;

@Controller
public class EmployeeDetailsController {
	private static final Logger logger = Logger.getLogger(EmployeeDetailsController.class);
	
	@Autowired
	EmployeeDetailService employeeDetailService;
	
	@RequestMapping(value = "/addEmployeeDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addEmployee(@RequestBody EmployeeDetailsVO empDetails) {
		logger.info("EmployeeDetailsController : addEmployee : Started");
		try {
			
			employeeDetailService.insertEmployeeDetails(empDetails);
			logger.info("EmployeeDetailsController : addEmployee : Ended");
		} catch (TravelServiceException ex) {
			logger.error("EmployeeDetailsController : addEmployee : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	@RequestMapping(value = "/getEmployeeDetails/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public EmployeeDetailsBO getEmployeeDetails(@PathVariable("id") int id) {
		logger.info("TravelRequestController : getEmployeeDetails : Started");
		EmployeeDetailsBO employeesDetails =null;
		try {
			 employeesDetails = employeeDetailService.getEmployeeDetails(id);
			logger.info("TravelRequestController : getEmployeeDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("TravelRequestController : getEmployeeDetails : TravelServiceException Caught : " + ex);
		}
		return employeesDetails;
	}

	@RequestMapping(value = "/updateEmployeeDetails", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateEmployeeDetails(@RequestBody EmployeeDetailsBO empDetails) {
		logger.info("EmployeeDetailsController : updateEmployeeDetails : Started");
		try {
			employeeDetailService.updateEmployeeDetails(empDetails);
			logger.info("EmployeeDetailsController : updateEmployeeDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("EmployeeDetailsController : updateEmployeeDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	@RequestMapping(value = "/getAllEmployeeDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<EmployeeDetailsBO> getAllEmployeeDetails() {
		logger.info("EmployeeDetailsController : getAllEmployeeDetails : Started");
		List<EmployeeDetailsBO> employeeList = null;
		try {
			employeeList = employeeDetailService.getAllEmployeeDetails();
			logger.info("EmployeeDetailsController : getAllEmployeeDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("EmployeeDetailsController : getAllEmployeeDetails : TravelServiceException Caught : " + ex);
		}
		return employeeList;
	}

	@RequestMapping(value = "/deleteEmployeeDetails/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteEmployeeDetails(@PathVariable("id") int id) {
		logger.info("EmployeeDetailsController : deleteEmployeeDetails : Started");
		try {
			employeeDetailService.deleteEmployeeDetails(id);
			logger.info("EmployeeDetailsController : deleteEmployeeDetails : Ended");
		} catch (TravelServiceException ex) {
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
}
