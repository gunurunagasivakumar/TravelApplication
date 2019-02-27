package com.capco.travel.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.beans.TravelRequestWrapper;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.TravelDetailsBO;
import com.capco.travel.service.EmployeeDetailService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.service.RequestCountService;
import com.capco.travel.util.TravelConstants;

import javassist.NotFoundException;

import com.capco.travel.vo.ErrorCodeHandlerVO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.RequestCountVO;
import com.capco.travel.vo.RequestListDTO;
import com.capco.travel.vo.RequestListVO;
import com.capco.travel.vo.RequestsDTO;
import com.capco.travel.vo.TravelRequestDTO;

@Controller
@RestController
public class TravelRequestController {

	private final Logger logger = Logger.getLogger(TravelRequestController.class);

	@Autowired
	private MainRequestService mainRequestService;
	
	@Autowired
	private RequestCountService requestCountService;
	
	@Autowired
	private EmployeeDetailService employeeDetailsService;
	

	/**
	 * This method is to add a new incoming travel request to the DB
	 * 
	 * @methodName addTravelRequest
	 * @param travelRequest
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request", method = RequestMethod.PUT, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Void> addTravelRequest(@RequestBody TravelRequestDTO travelRequest,
			HttpServletRequest request) throws TravelServiceException {
		logger.info("TravelRequestController : addTravelRequest: Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);

			MainRequestVO newRequest = travelRequest.getTravelRequest();
			if (newRequest.getCabRequest() == null && newRequest.getAccomodationRequest() == null
					&& newRequest.getFlightRequest() == null && newRequest.getForexRequest() == null
					&& newRequest.getVisaRequestVO() == null) {
				logger.error("TravelRequestController : addTravelRequest: errorEmptyTravelRequestObject");
				return new ResponseEntity("Empty Data ", HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (mainRequestService.validateTravelRequestObject(newRequest) != null) {
				logger.info("TravelRequestController : validating the main request object");
				return new ResponseEntity(mainRequestService.validateTravelRequestObject(newRequest),
						HttpStatus.NOT_ACCEPTABLE);
			} else {

				Integer travelRequestId = 0;
				try {
					travelRequestId = mainRequestService.insertMainRequest(newRequest);
					logger.info(
							"TravelRequestController : addTravelRequest: new Travel Request Id :" + travelRequestId);
				} catch (TravelServiceException e) {
					logger.error("TravelRequestController : addTravelRequest: Exception Caught" + e);
				}

				logger.info("TravelRequestController : addTravelRequest: ended");
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
	}

	/**
	 * This method is to update travel request object according to the requested id
	 * 
	 * @methodName updateTravelRequest
	 * @param travelRequest
	 * @return ResponseEntity<TravelRequestDTO>
	 * @throws DAOException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/request/status/{requestId}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<TravelRequestDTO> updateTravelRequest(@RequestBody TravelRequestDTO travelRequest,
			@PathVariable("requestId") String reqId, HttpServletRequest request)
			throws NumberFormatException, TravelServiceException {
		logger.info("TravelRequestController : updateTravelRequest: Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
			if (validateNumberFormate(reqId) == false) {
				return new ResponseEntity("Request Id cannot be String.", HttpStatus.NOT_FOUND);
			}
			Integer requestId = Integer.parseInt(reqId);
			MainRequestBO mainRequestBO = null;
			try{
				mainRequestBO = mainRequestService.getMainRequestBOByRequestId(requestId);
					
			}catch (TravelServiceException e) {
				logger.error("TravelRequestController : updateTravelRequest: Exception Caught" + e);
			}
			if (mainRequestBO == null) {
				logger.info("TravelRequestController : updateTravelRequest: mainRequestBO: not found");
				return new ResponseEntity("No data found", HttpStatus.NOT_FOUND);
			} 
			else {
				MainRequestVO updateRequests = travelRequest.getTravelRequest();
				TravelRequestDTO travelRequestDTOS;
				
				if(updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.PENDING_HC) || updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.PENDING_FINANCE) || updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.ISSUED_FINANCE) || updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.ISSUED_HC) || updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.ONHOLD) || updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.COMPLETED)|| updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.DRAFT)|| updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.ISSUED_HC_FINANCE)|| updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.PENDING_HC_FINANCE) || updateRequests.getCurrentStatus().equalsIgnoreCase(TravelConstants.L1_PENDING)) {
					
					try {
							travelRequestDTOS = new TravelRequestDTO();
							if (updateRequests.getRequestId() == requestId) {
								updateRequests = mainRequestService.updateMainRequest(mainRequestBO,updateRequests);
							travelRequestDTOS.setTravelRequest(updateRequests);
							logger.info("TravelRequestController : updateTravelRequest: request updated");
							return new ResponseEntity<TravelRequestDTO>(travelRequestDTOS, HttpStatus.OK);
							}
					}
							catch (TravelServiceException e) {
								logger.error("TravelRequestController : updateTravelRequest: Exception Caught" + e);
					}
					
				}
//				if (updateRequest.getActionOnRequest().equals(TravelConstants.CANCELLED)) {
//					Boolean requestDeleted = null;
//					try {
//						if (mainRequestBO.getCurrentStatus().equals(TravelConstants.L1_PENDING)) {
//							//requestDeleted = mainRequestService.cancelRequestDetailsByRequestId(mainRequestBO);
//							logger.info("TravelRequestController : updateTravelRequest:  Request cancelled :"
//									+ requestDeleted);
//							return new ResponseEntity("Cancelled", HttpStatus.OK);
//						}
//
//					} catch (TravelServiceException e) {
//
//						logger.info("TravelRequestController : updateTravelRequest: Exception Caught:" + e);
//					}
//
//				}
				
				MainRequestVO updateRequest = travelRequest.getTravelRequest();
				TravelRequestDTO travelRequestDTO = null;
				if (updateRequest.getActionOnRequest().equals(TravelConstants.EDIT)) {
					try {
						travelRequestDTO = new TravelRequestDTO();
						if (updateRequest.getRequestId() == requestId &&
								(mainRequestBO.getCurrentStatus().equalsIgnoreCase(TravelConstants.L1_PENDING) ||
								 mainRequestBO.getCurrentStatus().equalsIgnoreCase(TravelConstants.REJECTED)) ||
								mainRequestBO.getCurrentStatus().equalsIgnoreCase(TravelConstants.PENDING)
								) {
							updateRequest = mainRequestService.updateMainRequest(mainRequestBO, updateRequest);
							travelRequestDTO.setTravelRequest(updateRequest);
							logger.info("TravelRequestController : updateTravelRequest: request updated");
							return new ResponseEntity<TravelRequestDTO>(travelRequestDTO, HttpStatus.OK);
						}
					} catch (TravelServiceException e) {
						logger.error("TravelRequestController : updateTravelRequest: Exception Caught" + e);
					}
				}
				
			}
			
			return new ResponseEntity("invalid request", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method is to delete travel request by requested id
	 * @methodName cancelTravelRequest
	 * @param travelRequest
	 * @return ResponseEntity<Void>
	 * @throws DAOException 
	 */
	@RequestMapping(value = "/request/cancel/{requestId}", method = RequestMethod.POST, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<Void> cancelTravelRequest(@PathVariable("requestId") Integer requestId, HttpServletRequest request) throws TravelServiceException {
		logger.info("TravelRequestController : cancelTravelRequest: Started");
		HttpSession session = request.getSession();		
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
			Boolean requestDeleted = null;
			try {
				MainRequestBO mainRequestBO = mainRequestService.getMainRequestBOByRequestId(requestId);
				if (null == mainRequestBO) {
					logger.info("travel request with requestId " + requestId + " not found");
					return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
				}
				if (mainRequestBO.getCurrentStatus().equals(TravelConstants.L1_PENDING)||mainRequestBO.getCurrentStatus().equals(TravelConstants.DRAFT)) {
					requestDeleted = mainRequestService.deleteRequestByRequestId(mainRequestBO);
				}

				logger.info("TravelRequestController : cancelTravelRequest:  Request deleted flag:" + requestDeleted);
			} catch (TravelServiceException e) {
				logger.error("TravelRequestController : cancelTravelRequest: Exception Caught" + e);
			}
			logger.info("TravelRequestController : cancelTravelRequest: ended");
			return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * This method is to get travel request by requested id
	 * 
	 * @methodName getTravelRequest
	 * @param travelRequest
	 * @return ResponseEntity Object with Staus Code
	 * @throws TravelServiceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/request/{requestId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<TravelRequestDTO> getTravelRequest(@PathVariable("requestId") String reqId,
			HttpServletRequest request) throws NumberFormatException, TravelServiceException {
		logger.info("TravelRequestController : getTravelRequest: Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
			TravelRequestDTO travelRequestDTO = new TravelRequestDTO();
			String regx = "\\d+";
			Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(reqId);
			boolean b = matcher.find();
			if (b == false) {
				return new ResponseEntity("Request Id cannot be String.", HttpStatus.NOT_FOUND);
			}

			Integer requestId = Integer.parseInt(reqId);
			if (requestId == 0) {
				logger.info("travel request with requestId " + requestId + " not found");
				return new ResponseEntity("Request Id cannot be Null.", HttpStatus.NOT_FOUND);
			}
			try {
				MainRequestVO mainRequestVO = mainRequestService.getRequestDetailsByRequestId(requestId);
				if (mainRequestVO == null) {
					return new ResponseEntity("No Data Found for the Requested ID", HttpStatus.CREATED);
				}
				travelRequestDTO.setTravelRequest(mainRequestVO);

			} catch (TravelServiceException e) {
				logger.error("TravelRequestController : getTravelRequest: Exception Caught" + e);
			}

			logger.info("TravelRequestController : getTravelRequest: ended");
			return new ResponseEntity<TravelRequestDTO>(travelRequestDTO, HttpStatus.CREATED);
	}

	/**
	 * @return the mainRequestService
	 */
	public MainRequestService getMainRequestService() {
		return mainRequestService;
	}

	/**
	 * @param mainRequestService
	 *            the mainRequestService to set
	 */
	public void setMainRequestService(MainRequestService mainRequestService) {
		this.mainRequestService = mainRequestService;
	}

	/**
	 * @author e5544354 This method to get all entries from request_history table
	 * @methodName getAllRequestHistoryLogs
	 * @return List<RequestHistoryLogVO>
	 * @throws TravelServiceException
	 */
	/*
	 * @RequestMapping(value = "/getAllRequestHistory", method = RequestMethod.GET,
	 * headers = "Accept=application/json") public
	 * ResponseEntity<List<RequestHistoryLogVO>>
	 * getAllRequestHistoryLogs(HttpServletRequest request) throws
	 * TravelServiceException {
	 * logger.info("TravelRequestController : getAllRequestHistoryLogs : Started");
	 * HttpSession session = request.getSession(); Integer tokenId = (Integer)
	 * session.getAttribute("tokenId"); String userId = (String)
	 * session.getAttribute("userId"); System.out.println(tokenId+" "+userId);
	 * if((tokenId != null && tokenId != 0) &&
	 * mainRequestService.tokenValidation(tokenId, userId)) {
	 * List<RequestHistoryLogVO> listVO = null; try { listVO =
	 * mainRequestService.getAllRequestHistoryLogs();
	 * logger.info("TravelRequestController : getAllRequestHistoryLogs : Ended"); }
	 * catch (TravelServiceException ex) { logger.
	 * error("TravelRequestController : getAllRequestHistoryLogs : TravelServiceException Caught : "
	 * + ex); } return new ResponseEntity(listVO, HttpStatus.OK); } else { return
	 * new ResponseEntity("token not found for requested user",
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 *//**
		 * @author e5544354 This method to get all entries from request_history table by
		 *         request_id
		 * @methodName getRequestHistoryByRequestId
		 * @param int
		 * @return List<RequestHistoryLogVO>
		 * @throws TravelServiceException
		 */
	/*
	 * @RequestMapping(value = "/getRequestHistoryByRequestId/{requestId}", method =
	 * RequestMethod.GET, headers = "Accept=application/json") public
	 * ResponseEntity<List<RequestHistoryLogVO>>
	 * getRequestHistoryLogsByRequestId(@PathVariable("requestId") int requestId,
	 * HttpServletRequest request) throws TravelServiceException { logger.
	 * info("TravelRequestController : getRequestHistoryLogsByRequestId : Started");
	 * HttpSession session = request.getSession(); Integer tokenId = (Integer)
	 * session.getAttribute("tokenId"); String userId = (String)
	 * session.getAttribute("userId"); System.out.println(tokenId+" "+userId);
	 * if((tokenId != null && tokenId != 0) &&
	 * mainRequestService.tokenValidation(tokenId, userId)) {
	 * List<RequestHistoryLogVO> listVO = null; try { listVO =
	 * mainRequestService.getRequestHistoryLogsByRequestId(requestId); logger.
	 * info("TravelRequestController : getRequestHistoryLogsByRequestId : Ended"); }
	 * catch (TravelServiceException ex) { logger.
	 * error("TravelRequestController : getRequestHistoryLogsByRequestId : TravelServiceException Caught : "
	 * + ex); } return new ResponseEntity(listVO, HttpStatus.OK); } else { return
	 * new ResponseEntity("token not found for requested user",
	 * HttpStatus.BAD_REQUEST); } }
	 * 
	 *//**
		 * @author e5544354 This method to get all entries from request_history table by
		 *         updated_by
		 * @methodName getRequestHistoryLogsByUpdatedBy
		 * @param int
		 * @return List<RequestHistoryLogVO>
		 * @throws TravelServiceException
		 *//*
			 * @RequestMapping(value = "/getRequestHistoryByUpdatedBy/{updatedBy}", method =
			 * RequestMethod.GET, headers = "Accept=application/json") public
			 * ResponseEntity<List<RequestHistoryLogVO>>
			 * getRequestHistoryLogsByUpdatedBy(@PathVariable("updatedBy") int updatedBy,
			 * HttpServletRequest request) throws TravelServiceException { logger.
			 * info("TravelRequestController : getRequestHistoryLogsByUpdatedBy : Started");
			 * HttpSession session = request.getSession(); Integer tokenId = (Integer)
			 * session.getAttribute("tokenId"); String userId = (String)
			 * session.getAttribute("userId"); System.out.println(tokenId+" "+userId);
			 * if((tokenId != null && tokenId != 0) &&
			 * mainRequestService.tokenValidation(tokenId, userId)) {
			 * List<RequestHistoryLogVO> listVO = null; try { listVO =
			 * mainRequestService.getRequestHistoryLogsByUpdatedBy(updatedBy); logger.
			 * info("TravelRequestController : getRequestHistoryLogsByUpdatedBy : Ended"); }
			 * catch (TravelServiceException ex) { logger.
			 * error("TravelRequestController : getRequestHistoryLogsByUpdatedBy : TravelServiceException Caught : "
			 * + ex); } return new ResponseEntity(listVO, HttpStatus.OK); } else { return
			 * new ResponseEntity("token not found for requested user",
			 * HttpStatus.BAD_REQUEST); } }
			 */

	/**
	 * @author e5545730 This method to get all request of user
	 * @methodName getLatestApprovedRequest
	 * @param int
	 *            requestedBy
	 * @return List<MainRequestVO>
	 * @throws TravelServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request/user/approved", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<RequestsDTO> getLatestApprovedRequest(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("TravelRequestController : getLatestApprovedRequest : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		int employeeId = (int) session.getAttribute("employeeId");
		System.out.println(userId);

			List<MainRequestVO> listVO = null;
			RequestsDTO requestsDTO = null;
			if (employeeId == 0) {
				logger.info("travel request not found");
				return new ResponseEntity("Requested Id should not be 0", HttpStatus.NOT_FOUND);
			} else {
				try {
					listVO = mainRequestService.getLatestApprovedRequest(employeeId);
					if (listVO != null) {
						requestsDTO = new RequestsDTO();
						requestsDTO.setMainRequestVOList(listVO);
						RequestCountVO requestCountVO = requestCountService.getRequestCount(employeeId);
						requestsDTO.setRequestCountVO(requestCountVO);
						logger.info("TravelRequestController : getLatestApprovedRequest : Ended");
						return new ResponseEntity<RequestsDTO>(requestsDTO, HttpStatus.OK);
					}
				} catch (TravelServiceException ex) {
					logger.error("TravelRequestController : getLatestApprovedRequest : TravelServiceException Caught : "
							+ ex);
				}
				return new ResponseEntity("No data found", HttpStatus.NOT_FOUND);
			}
	}

	/**
	 * @author e5545730 This method to get all request of user
	 * @methodName getRequestDetailsByUserId
	 * @param int
	 *            requestedBy
	 * @return List<MainRequestVO>
	 * @throws TravelServiceException
	 */
/*	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<RequestsDTO> getRequestDetailsByUserId(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("TravelRequestController : getRequestDetailsByUserId : Started");
		HttpSession session = request.getSession();
		Integer tokenId = (Integer) session.getAttribute("tokenId");
		String userId = (String) session.getAttribute("userId");
		Integer employeeId = (Integer) session.getAttribute("employeeId");
		System.out.println(tokenId + " " + userId+""+employeeId);
		if ((tokenId != null && tokenId != 0) && mainRequestService.tokenValidation(tokenId, userId)) {

			List<MainRequestVO> listVO = null;
			RequestsDTO requestsDTO = null;
			if (employeeId == 0) {
				logger.info("travel request not found");
				return new ResponseEntity("Requested Id should not be 0", HttpStatus.NOT_FOUND);
			} else {
				try {
					listVO = mainRequestService.getRequestDetailsByRequestedId(employeeId);
					if (listVO != null) {
						requestsDTO = new RequestsDTO();
						requestsDTO.setMainRequestVOList(listVO);
						RequestCountVO requestCountVO = requestCountService.getRequestCount(employeeId);
						requestsDTO.setRequestCountVO(requestCountVO);
						logger.info("TravelRequestController : getLatestApprovedRequest : Ended");
					}
					logger.info("TravelRequestController : getRequestDetailsByUserId : Ended");
				} catch (TravelServiceException ex) {
					logger.error(
							"TravelRequestController : getRequestDetailsByUserId : TravelServiceException Caught : "
									+ ex);
				}
				return new ResponseEntity<RequestsDTO>(requestsDTO, HttpStatus.OK);
			}
		} else {
			logger.info("TravelRequestController : getRequestDetailsByUserId : token not found for requested user");
			return new ResponseEntity("token not found for requested user", HttpStatus.BAD_REQUEST);
		}
	}*/	
	
	/**
	 * @author e5545730 This method to get all request of user
	 * @methodName getRequestDetailsByUserId
	 * @param int
	 *            requestedBy
	 * @return List<MainRequestVO>
	 * @throws TravelServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<RequestListVO>> getRequestDetailsByUserId(HttpServletRequest request) throws TravelServiceException {
		logger.info("TravelRequestController : getRequestDetailsByUserId : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		Integer employeeId = (Integer) session.getAttribute("employeeId");
		System.out.println(userId+employeeId);
			List<RequestListVO> listVO = null;
			//RequestListDTO requestListDTO = new RequestListDTO();
				try {
					//requestListDTO.setRequestListVO(mainRequestService.getAllRequestByUserId(employeeId));
					listVO = mainRequestService.getAllRequestByUserId(employeeId.toString());
					logger.info("TravelRequestController : getRequestDetailsByUserId : Ended");
				} catch (TravelServiceException ex) {
					logger.error(
							"TravelRequestController : getRequestDetailsByUserId : TravelServiceException Caught : "
									+ ex);
				}
				return new ResponseEntity(listVO, HttpStatus.OK);
	}
	
	/**
	 * @author - This method to get all request for Travel desk user
	 * @methodName getRequestDetailsByTravelStatus
	 *
	 * @return List<MainRequestBO>
	 * @throws TravelServiceException
	 */
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request/travelUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<MainRequestBO> getRequestDetailsByTravelStatus(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("TravelRequestController : TravelRequestView : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");		
		System.out.println(userId);		
		List<MainRequestBO> listBO = null;
		List<HcViewBO> hclistBO = null;
		List<FinViewBO> finlistBO = null;
		List<EmployeeDetailsBO> emplistBO = null;
		if (userId.equalsIgnoreCase(null)) {
			logger.info("travel request not found");
			return new ResponseEntity("User Id should not be null", HttpStatus.NOT_FOUND);
		} else {
			try{
				listBO = mainRequestService.getAllRequestByTravelStatus();
				hclistBO = mainRequestService.getAllRequestByHcStatus();
				finlistBO = mainRequestService.getAllRequestOfFinance();
				//emplistBO = employeeDetailsService.getAllEmployeeDetails();
				logger.info("TravelRequestController : Travel User:  Travel Status:");
			} catch (TravelServiceException e) {
				logger.error("TravelRequestController : getRequestDetailsByTravelStatus: Exception Caught" + e);
			}
			logger.info("TravelRequestController : getRequestDetailsByTravelStatus: ended");
			return new ResponseEntity(listBO, HttpStatus.OK);
	}

	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request/travelUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<TravelDetailsBO> getRequestDetailsByTravelStatus(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("TravelRequestController : TravelRequestView : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");		
		System.out.println(userId);		
		TravelRequestWrapper listBO = null;
		List<HcViewBO> hclistBO = null;
		List<FinViewBO> finlistBO = null;
		List<EmployeeDetailsBO> emplistBO = null;
		TravelDetailsBO travelDetailsBO = new TravelDetailsBO();
		if (userId.equalsIgnoreCase(null)) {
			logger.info("travel request not found");
			return new ResponseEntity("User Id should not be null", HttpStatus.NOT_FOUND);
		} else {
			try{
				listBO = mainRequestService.getAllRequestByTravelStatus();
				hclistBO = mainRequestService.getAllRequestByHcStatus();
				finlistBO = mainRequestService.getAllRequestOfFinance();
				emplistBO = employeeDetailsService.getAllEmployeeDetails();
				if(listBO!=null)
				{
					travelDetailsBO.setListBO(listBO);
				}
				if(hclistBO!=null)
				{
					travelDetailsBO.setHclistBO(hclistBO);
				}
				if(finlistBO!=null)
				{
					travelDetailsBO.setFinlistBO(finlistBO);
				}
				if(emplistBO!=null)
				{
					travelDetailsBO.setEmplistBO(emplistBO);
				}
				
				logger.info("TravelRequestController : Travel User:  Travel Status:");
			} catch (TravelServiceException e) {
				logger.error("TravelRequestController : getRequestDetailsByTravelStatus: Exception Caught" + e);
			}
			logger.info("TravelRequestController : getRequestDetailsByTravelStatus: ended");
			return new ResponseEntity(travelDetailsBO, HttpStatus.OK);
	}

	}
	/**
	 * @author - This method to get all request for Travel desk user
	 * @methodName getRequestDetailsByHcOpsStatus
	 *
	 * @return List<MainRequestBO>
	 * @throws TravelServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request/hcOpsUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<HcViewBO> getRequestDetailsByHcStatus(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("TravelRequestController : TravelRequestView : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");		
		System.out.println(userId);		
		List<HcViewBO> listBO = null;
		if (userId.equalsIgnoreCase(null)) {
			logger.info("travel request not found");
			return new ResponseEntity("User Id should not be null", HttpStatus.NOT_FOUND);
		} else {
			try{
				listBO = mainRequestService.getAllRequestByHcStatus();
				logger.info("TravelRequestController : HC User:  Travel Status:");
			} catch (TravelServiceException e) {
				logger.error("TravelRequestController : getRequestDetailsByHcStatus: Exception Caught" + e);
			}
			logger.info("TravelRequestController : getRequestDetailsByHcStatus: ended");
			return new ResponseEntity(listBO, HttpStatus.OK);
	}

	}
	/**
	 * @author - This method to get all request for Travel desk user
	 * @methodName getRequestDetailsByHcOpsStatus
	 *
	 * @return List<MainRequestBO>
	 * @throws TravelServiceException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/request/financeUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<FinViewBO> getRequestDetailsOfFinance(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("TravelRequestController : FianceRequestView : Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");		
		System.out.println(userId);		
		List<FinViewBO> listBO = null;
		if (userId.equalsIgnoreCase(null)) {
			logger.info("travel request not found");
			return new ResponseEntity("User Id should not be null", HttpStatus.NOT_FOUND);
		} else {
			try{
				listBO = mainRequestService.getAllRequestOfFinance();
				logger.info("TravelRequestController : HC User:  Travel Status:");
			} catch (TravelServiceException e) {
				logger.error("TravelRequestController : getRequestDetailsByHcStatus: Exception Caught" + e);
			}
			logger.info("TravelRequestController : getRequestDetailsByHcStatus: ended");
			return new ResponseEntity(listBO, HttpStatus.OK);
	}

	}
				

	public boolean validateNumberFormate(String reqId) {
		String regx = "\\d+";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(reqId);
		boolean b = matcher.find();
		if (b == false) {
			return false;
		}
		return true;
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> handleNotFoundException(NotFoundException ex) {
		logger.info("TravelRequestController : handleNotFoundException : isAlive Session Time-Out.. ");
		 return new  ResponseEntity(new ErrorCodeHandlerVO(HttpStatus.BAD_REQUEST.value(),"Session Time-Out"),HttpStatus.BAD_REQUEST);
	}
}