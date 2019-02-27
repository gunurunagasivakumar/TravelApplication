package com.capco.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.service.ApproverService;
import com.capco.travel.service.FinRequestService;
import com.capco.travel.service.HcRequestService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.ErrorCodeHandlerVO;
import com.capco.travel.vo.FinActionDetailsVO;
import com.capco.travel.vo.HcActionDetailsVO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.RequestListDTO;

import javassist.NotFoundException;

/*
 * @Author-e5544344
 */

@RestController
public class ApproverActionController {

	private final Logger logger = Logger.getLogger(ApproverActionController.class);

	@Autowired
	private MainRequestService mainService;

	@Autowired
	ApproverService approverService;
	
	@Autowired
	HcRequestService hcRequestService;
	
	@Autowired
	FinRequestService finRequestService;

	/**
	 * This method will get all requests for approver id
	 * 
	 * @author e5544344
	 * @methodName getAllRequestByApproverId
	 * @param Integer
	 * @return ResponseEntity<RequestListDTO>
	 * @throws TravelServiceException
	 */
	@RequestMapping(value = "/request/approver/list", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public ResponseEntity<RequestListDTO> getAllRequestByApproverId(HttpServletRequest request)
			throws TravelServiceException {
		logger.info("ApproverActionController : getAllRequestByApproverId: Started");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		Integer approverId = (Integer) session.getAttribute("employeeId");
			RequestListDTO requestListDTO = new RequestListDTO();
			if (approverId == 0 || approverId == null) {
				logger.info("travel request for approver id " + approverId + " not found");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			try {
				requestListDTO.setRequestListVO(approverService.getAllRequestByApproverId(approverId));
			} catch (TravelServiceException ex) {
				logger.error(
						"ApproverActionController : getAllRequestByApproverId: TravelServiceException Caught : " + ex);
			}
			return new ResponseEntity<>(requestListDTO, HttpStatus.OK);
	}

	/**
	 * This method will change current status of a request
	 * 	  
	 * @author e5544344
	 * @methodName updateCurrentStatusByapproverID
	 * @param MainRequestBaseVO (Updated body)
	 * @return ResponseEntity<MainRequestTableVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/request/approver/action/{requestId}", method = RequestMethod.POST, headers = "Accept=application/json", produces ="application/json")
	public ResponseEntity<MainRequestBaseVO> updateCurrentStatusByapproverID(@RequestBody MainRequestBaseVO updateRequest,HttpServletRequest request,@PathVariable("requestId") Integer requestId) throws TravelServiceException{
		logger.info("ApproverActionController : updateCurrentStatusByapproverID: Started");
		if(updateRequest== null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		HttpSession session = request.getSession();	
		String userId = (String) session.getAttribute("userId");
		Integer approverId=(Integer)session.getAttribute("employeeId");
		//int approverId=0;
		MainRequestBaseVO newVo=null;
		//Integer requestId=updateRequest.getRequestId();
		MainRequestBO oldBo;
			
			if(userId == null){
				logger.info("ApproverActionController : updateCurrentStatusByapproverID: No user ID");
				return new ResponseEntity("no user id",HttpStatus.BAD_REQUEST);
			}
			
			if(updateRequest.getActionOnRequest().equalsIgnoreCase(TravelConstants.ONHOLD) || updateRequest.getActionOnRequest().equalsIgnoreCase(TravelConstants.PENDING_HC) || updateRequest.getActionOnRequest().equalsIgnoreCase(TravelConstants.PENDING_FINANCE) || updateRequest.getActionOnRequest().equalsIgnoreCase(TravelConstants.APPROVED) || updateRequest.getActionOnRequest().equalsIgnoreCase(TravelConstants.REJECTED)) {
				try {
					if(requestId==0) {
						logger.info("ApproverActionController : updateCurrentStatusByapproverID: No request ID");
						return new ResponseEntity("No request ID",HttpStatus.BAD_REQUEST);
					}
					//approverId = approverService.getEmployeeIdByUserID(userId);
					oldBo = approverService.getRequestById(requestId);	
					if(oldBo==null) {
						logger.info("ApproverActionController : updateCurrentStatusByapproverID: Ended");
						return new ResponseEntity("Invalid request id", HttpStatus.BAD_REQUEST);
					}	
					newVo = approverService.updateMainRequestTable(oldBo,updateRequest);
					//inserting requestHistoryLog
					RequestHistoryLogBO requestHistoryLogBO = mainService.createApproverRequestHistoryLog(oldBo, newVo,approverId);
					mainService.insertRequestHistoryLog(requestHistoryLogBO);	
					
					//sending email notification
					if(newVo.getCurrentStatus().equals(TravelConstants.L2_PENDING)) {
						mainService.sendEmailNotification(requestId, TravelConstants.EMAIL_NEW);
					}
					else {
						mainService.sendEmailNotification(requestId, newVo.getCurrentStatus());
					}
					
					logger.info("ApproverActionController : updateCurrentStatusByapproverID: Ended");
					
				} catch (TravelServiceException e1) {
					logger.error("ApproverActionController : updateCurrentStatusByapproverID: TravelServiceException Caught : " + e1);	
				}	
				return new ResponseEntity<>(newVo,HttpStatus.OK);
			}
		return new ResponseEntity<>(newVo,HttpStatus.OK);
	}
	
	/**
	 * This method is to add the actions to the HCOps
	 * 	  
	 * @author 12219
	 * @methodName addHcDetails
	 * @param HcActionDetailsVO (Updated body)
	 * @return ResponseEntity<MainRequestTableVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/addHcActionDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public String addHcDetails(@RequestBody HcActionDetailsVO hcDetailsVO) {
		logger.info("ApproverActionController : addHcActionDetails : Started");
		try {
			
			//employeeDetailService.insertEmployeeDetails(empDetails);
			
			hcRequestService.insertHcTasks(hcDetailsVO);
			
			logger.info("ApproverActionController : addHcDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ApproverActionController : addHcDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
	/**
	 * This method is to add the actions to the HCOps
	 * 	  
	 * @author 12219
	 * @methodName addHcDetails
	 * @param HcActionDetailsVO (Updated body)
	 * @return ResponseEntity<MainRequestTableVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/addFinActionDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<FinActionDetailsVO> addFinDetails(@RequestBody FinActionDetailsVO finDetailsVO) {
		logger.info("ApproverActionController : addFincActionDetails : Started");
		try {
			
			//employeeDetailService.insertEmployeeDetails(empDetails);
			
			finRequestService.insertFinTasks(finDetailsVO);
			
			logger.info("ApproverActionController : addFinDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ApproverActionController : addFinDetails : TravelServiceException Caught : " + ex);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			//return TravelConstants.RESULT_FAIL;
		}
		//return TravelConstants.RESULT_SUCCESS;
		//return new ResponseEntity<>(employeesDetailsVO, HttpStatus.OK);
		return new ResponseEntity<>(finDetailsVO, HttpStatus.OK);
	}
	/**
	 * This method is to update the actions to the HCOps
	 * 	  
	 * @author 12219
	 * @methodName addHcDetails
	 * @param HcActionDetailsVO (Updated body)
	 * @return ResponseEntity<MainRequestTableVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/updateHRResponse", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public String updateHRResponse(@RequestBody HcActionDetailsVO hcDetailsVO) {
		logger.info("ApproverActionController : addHcActionDetails : Started");
		try {
			
			//employeeDetailService.insertEmployeeDetails(empDetails);
			
			hcRequestService.updateHcTasks(hcDetailsVO);
			
			logger.info("ApproverActionController : addHcDetails : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ApproverActionController : addHcDetails : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
	/**
	 * This method is to update the actions to the Finance
	 * 	  
	 * @author 12219
	 * @methodName addHcDetails
	 * @param HcActionDetailsVO (Updated body)
	 * @return ResponseEntity<MainRequestTableVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/updateFinResponse", method = RequestMethod.PUT, headers = "Accept=application/json")
	@ResponseBody
	public String updateFinResponse(@RequestBody FinActionDetailsVO finDetailsVO) {
		logger.info("ApproverActionController : updateFinResponse : Started");
		try {
			
			//employeeDetailService.insertEmployeeDetails(empDetails);
			
			finRequestService.updateFinTasks(finDetailsVO);
			
			logger.info("ApproverActionController : updateFinResponse : Ended");
		} catch (TravelServiceException ex) {
			logger.error("ApproverActionController : updateFinResponse : TravelServiceException Caught : " + ex);
			return TravelConstants.RESULT_FAIL;
		}
		return TravelConstants.RESULT_SUCCESS;
	}
	@ExceptionHandler
	public ResponseEntity<Void> handleNotFoundException(NotFoundException ex) {
		logger.info("TravelRequestController : handleNotFoundException : isAlive Session Time-Out.. ");
		 return new  ResponseEntity(new ErrorCodeHandlerVO(HttpStatus.BAD_REQUEST.value(),"Session Time-Out"),HttpStatus.BAD_REQUEST);
	}

}
