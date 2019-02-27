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
import com.capco.travel.service.AccomodationService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.AccomodationDetailsVO;

@RestController
public class AccomodationServiceController {
	private static final Logger logger = Logger.getLogger(AccomodationServiceController.class);
	@Autowired
	AccomodationService accomodationDetailsService;
	
	/**
	 * This method to get AccomodationDetails from database*
	 * @methodName getAccomodationDetails
	 * @param long 
	 * @throws -
	 */
	@RequestMapping(value = "/getAccomodationDetails/{request_id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public AccomodationDetailsVO getAccomodationDetails(@PathVariable("request_id") int requestId) {
		logger.info("AccomodationServiceController : getAccomodationDetails : Started");
		AccomodationDetailsVO accomodationDetailsVO = null;
		try {
			accomodationDetailsVO = accomodationDetailsService.getAccomodationDetails(requestId);
		} catch (TravelServiceException ex) {
			logger.error("AccomodationServiceController : getAccomodationDetails : ServiceException Caught : " + ex);
		}
		return accomodationDetailsVO;
	}

	/**
	 * This method to insert Accomodation Details into database*
	 * @methodName insertAccomodationDetails
	 * @param com.capco.travel.vo.AccomodationDetailsVO
	 * @throws -
	 */
	@RequestMapping(value = "/addAccomodationDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addAccomodationDetails(@RequestBody AccomodationDetailsVO accomodationDetails) {
		try {
			accomodationDetailsService.insertAccomodationDetails(accomodationDetails);
		} catch (Exception ex) {
			logger.error("AccomodationServiceController : setAccomodationDetails : ServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	/**
	 * This method to update AccomodationDetails into database*
	 * @methodName updateAccomodationDetails
	 * @param com.capco.travel.vo.AccomodationDetailsVO
	 * @throws -
	 */
	@RequestMapping(value = "/updateAccomodationDetails", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateAccomodationDetails(@RequestBody AccomodationDetailsVO accomodationDetailsVO) {
		logger.info("AccomodationServiceController : updateAccomodationDetails : Started");
		try {
			accomodationDetailsService.updateAccomodationDetails(accomodationDetailsVO);
			logger.info("AccomodationServiceController : updateAccomodationDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("AccomodationServiceController : updateAccomodationDetails : ServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;

	}
	
	/**
	 * This method to get All AccomodationDetails from database*
	 * @methodName getAllAccomodationDetails
	 * @param -No param
	 * @throws -
	 */
	@RequestMapping(value = "/getAllAccomodationDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<AccomodationDetailsVO> getAllAccomodationDetails() {
		logger.info("AccomodationServiceController : getAllAccomodationDetails : Started");
		List<AccomodationDetailsVO> accomodationDetailsVOList = null;
		try {
			accomodationDetailsVOList = accomodationDetailsService.getAllAccomodationDetails();
			logger.info("AccomodationServiceController : getAllAccomodationDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("AccomodationServiceController : getAllAccomodationDetails : ServiceException Caught : " + ex);
		}
		
		return accomodationDetailsVOList;
	}

	/**
	 * This method to delete AccomodationDetails  from database*
	 * @methodName deleteAccomodationDetails
	 * @param long
	 * @throws 
	 */
	@RequestMapping(value = "/deleteAccomodationDetails/{request_id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteAccomodationDetails(@PathVariable("request_id") int requestId) {
		logger.info("AccomodationServiceController : deleteAccomodationDetails : Started");
		try {
			accomodationDetailsService.deleteAccomodationDetails(requestId);
			logger.info("AccomodationServiceController : deleteAccomodationDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("AccomodationServiceController : deleteAccomodationDetails : ServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
}
