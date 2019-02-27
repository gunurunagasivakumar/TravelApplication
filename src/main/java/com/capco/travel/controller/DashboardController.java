package com.capco.travel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.service.DashboardService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.RequestListDTO;
import com.capco.travel.vo.RequestListVO;

/*
 * @uthor- e5544344
 */

@RestController
public class DashboardController {

	private final Logger logger = Logger.getLogger(DashboardController.class);

	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(value = "/request/filter/{type}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<RequestListDTO> getDashBoardRequest(HttpServletRequest request,@PathVariable String type) throws TravelServiceException{
		logger.info("DashboardController : getDashBoardRequest : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		Integer employeeId = (Integer) session.getAttribute("employeeId");
		System.out.println(userId+employeeId);
		RequestListDTO requestListDTO = new RequestListDTO();
		if (employeeId == 0 || employeeId == null) {
			logger.info("travel request for approver id " + employeeId + " not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(!TravelConstants.tableMapping.containsKey(type)){
			logger.info("Invalid type");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			requestListDTO.setRequestListVO(dashboardService.getRequestsByRequestType(employeeId,type));
		} catch (TravelServiceException ex) {
			logger.error(
					"DashboardController : getDashBoardRequest : TravelServiceException Caught : " + ex);
		}
		return new ResponseEntity<>(requestListDTO, HttpStatus.OK);




	}
}
