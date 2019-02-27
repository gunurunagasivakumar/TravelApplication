package com.capco.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.service.ApproverGroupService;
import com.capco.travel.service.MainRequestService;
import com.capco.travel.service.PerDeimService;
import com.capco.travel.service.ProjectDetailsService;
import com.capco.travel.service.RequestCountService;
import com.capco.travel.vo.ErrorCodeHandlerVO;
import com.capco.travel.vo.ProjectDetailsApproverDetailsDTO;
import com.capco.travel.vo.RequestCountVO;

import javassist.NotFoundException;

/*
 * @Author-e5544344
 */

@RestController
public class LoadDataController {
	private static final Logger logger = Logger.getLogger(LoadDataController.class);
	
	@Autowired
	private ProjectDetailsService projectService;
	
	@Autowired
	private ApproverGroupService approverGroupService;
	
	@Autowired
	private RequestCountService requestCountService;
	
	@Autowired
	private MainRequestService mainService;
	
	@Autowired
	private PerDeimService perDeimServie;
	
	/**
	 * This method will get list of approvers and project details
	 * 	  
	 * @author e5544344
	 * @methodName getAllApproverAndProjectDetails
	 * @param Integer - level
	 * @return ResponseEntity<ProjectDetailsApproverDetailsDTO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/request/load/data/{level}", method = RequestMethod.GET, headers = "Accept=application/json")
	public  ResponseEntity<ProjectDetailsApproverDetailsDTO> getAllApproverAndProjectDetails(@PathVariable("level") int level, HttpServletRequest request) throws TravelServiceException {
		logger.info("LoadDataController : getAllApproverAndProjectDetails : Started");	
		HttpSession session = request.getSession();	
		String userId = (String) session.getAttribute("userId");
		ProjectDetailsApproverDetailsDTO projectDetailsApproverDetailsDTO=new ProjectDetailsApproverDetailsDTO();
		try {
			projectDetailsApproverDetailsDTO.setListOfApprovers(approverGroupService.getAllApprovers(level));
			projectDetailsApproverDetailsDTO.setListOfProjectDetails(projectService.getAllProjectDetails());
			projectDetailsApproverDetailsDTO.setPerDeimList(perDeimServie.getPerDeimList());
			
			logger.info("LoadDataController : getAllApproverAndProjectDetails : Ended");
		}catch (TravelServiceException ex) {
			logger.error("LoadDataController : getAllApproverAndProjectDetails : TravelServiceException Caught : " + ex);
		}
		return new ResponseEntity<>(projectDetailsApproverDetailsDTO,HttpStatus.OK);
	}
	
	/**
	 * This method will get count of all pending and approved requests
	 * 	  
	 * @author e5544344
	 * @methodName getrequestCountByEmployeeId
	 * @param Integer - employeeId
	 * @return ResponseEntity<RequestCountVO>
	 * @throws TravelServiceException 
	 * 	 */	
	@RequestMapping(value = "/request/count", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<RequestCountVO> getrequestCountByEmployeeId(HttpServletRequest request) throws TravelServiceException{
		logger.info("LoadDataController : getrequestCountByEmployeeId : Started");	
		HttpSession session = request.getSession();	
		String userId = (String) session.getAttribute("userId");
		Integer employeeId = (Integer) session.getAttribute("employeeId");
		RequestCountVO requestCountVO=null;		
		if(employeeId==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			requestCountVO = requestCountService.getRequestCount(employeeId);
			logger.info("LoadDataController : getrequestCountByEmployeeId : ended");	
		} catch (TravelServiceException e) {
			logger.info("LoadDataController : getrequestCountByEmployeeId : Exception caught"+e);
			}
		return new ResponseEntity<>(requestCountVO,HttpStatus.OK);
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> handleNotFoundException(NotFoundException ex) {
		logger.info("TravelRequestController : handleNotFoundException : isAlive Session Time-Out.. ");
		 return new  ResponseEntity(new ErrorCodeHandlerVO(HttpStatus.BAD_REQUEST.value(),"Session Time-Out"),HttpStatus.BAD_REQUEST);
	}

}
