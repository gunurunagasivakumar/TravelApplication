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
import com.capco.travel.service.ForexService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.ForexDetailsVO;
@RestController
public class ForexServiceController {
	private static final Logger logger = Logger.getLogger(ForexServiceController.class);
	@Autowired
	ForexService forexDetailService;

	@RequestMapping(value = "/getForexDetails/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ForexDetailsVO getForexDetails(@PathVariable("id") int id) {
		logger.info("ForexServiceController : getForexDetails : Started");
		ForexDetailsVO forexsDetails = null;
		try {
			forexsDetails = forexDetailService.getForexDetails(id);
		} catch (TravelServiceException ex) {
			logger.error("ForexServiceController : getAllForexDetails : TravelServiceException Caught : " + ex);
		}
		return forexsDetails;
	}

	@RequestMapping(value = "/getAllForexDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<ForexDetailsVO> getAllForexDetails() {
		logger.info("ForexServiceController : getAllForexDetails : Started");
		List<ForexDetailsVO> forexList = null;
		try {
			forexList = forexDetailService.getAllForexDetails();
			logger.info("ForexServiceController : getAllForexDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ForexServiceController : getAllForexDetails : TravelServiceException Caught : " + ex);
		}
		return forexList;
	}

	@RequestMapping(value = "/addForexDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addForex(@RequestBody ForexDetailsVO empDetails) {
		logger.info("ForexServiceController : addForex : Started");
		try {
			forexDetailService.insertForexDetails(empDetails);
			logger.info("ForexServiceController : addForex : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ForexServiceController : addForex : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	@RequestMapping(value = "/updateForexDetails", method = RequestMethod.PUT, headers = "Accept=application/json")
	public String updateForexDetails(@RequestBody ForexDetailsVO forexDetailsVO) {
		logger.info("ForexServiceController : updateForexDetails : Started");
		try {
			forexDetailService.updateForexDetails(forexDetailsVO);
			logger.info("ForexServiceController : updateForexDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ForexServiceController : updateForexDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}

	@RequestMapping(value = "/deleteForexDetails/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public String deleteForexDetails(@PathVariable("id") int id) {
		logger.info("ForexServiceController : deleteForexDetails : Started");
		try {
			forexDetailService.deleteForexDetails(id);
			logger.info("ForexServiceController : deleteForexDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ForexServiceController : deleteForexDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
}
