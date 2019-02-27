package com.capco.travel.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.service.CabService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.CabDetailsVO;

@RestController
public class CabServiceController {

	private static final Logger logger = Logger.getLogger(CabServiceController.class);
	
	@Autowired
	private CabService cabService;
	
	/**
	 * This method to add Cab Details into database*
	 * @methodName addCabDetails
	 * @param com.capco.travel.model.CabDetailsVO
	 */
	@RequestMapping(value = "/addCabDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCab(@RequestBody CabDetailsVO cabDetails) {
		logger.info("CabServiceController : addCab : Started");
		try {
			cabService.insertCabDetails(cabDetails);
			logger.info("CabServiceController : addCab : Ended");
		} catch (TravelServiceException ex) {
			logger.error("CabServiceController : addCab : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	/**
	 * This method to get Cab Details from database*
	 * @methodName getCabDetails
	 * @param int
	 */
	@RequestMapping(value = "/getCabDetails/{requestId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<CabDetailsVO> getCabDetails(@PathVariable("requestId") int requestId) {
		logger.info("CabServiceController : getCabs : Started");
		CabDetailsVO cabDetails =null;
		try {
			 cabDetails = cabService.getCabDetails(requestId);
			logger.info("CabServiceController : getCabs : Ended");
		} catch (TravelServiceException ex) {
			logger.error("CabServiceController : getCabs : ServiceException Caught : " + ex);
			return new ResponseEntity(TravelConstants.RESULT_NULL_OBJECT+requestId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cabDetails, HttpStatus.OK);
	}

	/**
	 * This method to get all Cab Details from database*
	 * @methodName getAllCabDetails
	 */
	@RequestMapping(value = "/getAllCabDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<CabDetailsVO> getAllCabs() {
		logger.info("CabServiceController : getAllCabs : Started");
		List<CabDetailsVO> listOfCabs=null;
		try {
			listOfCabs = cabService.getAllCabDetails();
			logger.info("CabServiceController : getAllCabs : Ended");
		} catch (TravelServiceException ex) {
			logger.error("CabServiceController : getAllCabs : TravelServiceException Caught : " + ex);
		}
		return listOfCabs;
	}

	/**
	 * This method to update Cab Details in database*
	 * @methodName updateCabDetails
	 * @param com.capco.travel.model.CabDetailsVO
	 */
	@RequestMapping(value = "/updateCabDetails", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateCabDetails(@RequestBody CabDetailsVO cabDetails) {
		logger.info("CabServiceController : updateCabDetails : Started");
		try {
			cabService.updateCabDetails(cabDetails);
			logger.info("CabServiceController : updateCabDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("CabServiceController : updateCabDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	/**
	 * This method to delete Cab Details from database*
	 * @methodName deleteCabDetails
	 * @param int
	 */
	@RequestMapping(value = "/deleteCabDetails/{requestId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteCabs(@PathVariable("requestId") int requestId) {
		logger.info("CabServiceController : deleteCabs : Started");
		try {
			cabService.deleteCabDetails(requestId);
			logger.info("CabServiceController : deleteCabs : Ended");
		} catch (TravelServiceException ex) {
			logger.error("CabServiceController : deleteCabs : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
}
