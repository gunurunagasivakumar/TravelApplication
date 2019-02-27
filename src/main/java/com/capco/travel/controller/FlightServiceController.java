package com.capco.travel.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.service.FlightService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.FlightDetailsVO;

@RestController
public class FlightServiceController {
	private static final Logger logger = Logger.getLogger(FlightServiceController.class);
	@Autowired
	private FlightService flightService;

	/**
	 * This method to add flight Details in database*
	 * @methodName addFlightDetails
	 * @param com.capco.travel.model.FlightDetailsVO
	 */
	@RequestMapping(value = "/addFlightDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addFlightDetails(@RequestBody FlightDetailsVO flightDetails) {
		logger.info("FlightServiceController : addFlightDetails : Started");
		try {
			flightService.insertFlightDetails(flightDetails);
			logger.info("FlightServiceController : addFlightDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("FlightServiceController : addFlightDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	/**
	 * This method to get all flight Details from database*
	 * @methodName getAllFlightDetails
	 * @param com.capco.travel.model.FlightDetailsVO
	 */	
	@RequestMapping(value = "/getAllFlightDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FlightDetailsVO> getAllFlightDetails() {
		logger.info("FlightServiceController : getAllProjectDetails : Started");
		List<FlightDetailsVO> allFlightDetailsVOList = null;
		try {
			allFlightDetailsVOList = flightService.getAllFlightsDetails();
			logger.info("FlightServiceController : getAllFlightDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("FlightServiceController : getAllFlightDetails : TravelServiceException Caught : " + ex);
		}
		return allFlightDetailsVOList;
	}

	/**
	 * This method to get employee flight Details from database*
	 * @methodName getEmployeeFlightDetails
	 * @param int id
	 */	
	@RequestMapping(value = "/getEmployeeFlightDetails/{requestId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public FlightDetailsVO getEmployeeFlightDetails(@PathVariable("requestId") int requestId) {
		logger.info("FlightServiceController : getEmployeeFlightDetails : Started");
		FlightDetailsVO employeeFlightsDetails = null;
		try {
			employeeFlightsDetails = flightService.getEmployeeFlightsDetails(requestId);
			logger.info("FlightServiceController : getEmployeeFlightDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("FlightServiceController : getEmployeeFlightDetails : TravelServiceException Caught : " + ex);
		}
		return employeeFlightsDetails;
	}

	/**
	 * This method to get employee flight Details from database*
	 * @methodName updateFlightDetails
	 * @param com.capco.travel.model.FlightDetailsVO
	 */	
	@RequestMapping(value = "/updateFlightDetails", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateFlightDetails(@RequestBody FlightDetailsVO updateflightDetails) {
		logger.info("FlightServiceController : updateFlightDetails : Started");
		try {
			flightService.updateFlightDetails(updateflightDetails);
			logger.info("FlightServiceController : updateFlightDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("FlightServiceController : updateFlightDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	/**
	 * This method to get employee flight Details from database*
	 * @methodName deleteFlightDetails
	 * @param int id
	 */	
	@RequestMapping(value = "/deleteFlightDetails/{requestId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteFlightDetails(@PathVariable("requestId") int requestId) {

		logger.info("FlightServiceController : deleteFlightDetails : Started");
		try {
			flightService.deletFlightDetails(requestId);
			logger.info("FlightServiceController : deleteFlightDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("FlightServiceController : deleteFlightDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
}
