package com.capco.travel.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.service.ProjectDetailsService;
import com.capco.travel.vo.ProjectDetailsVO;

@RestController
public class ProjectDetailsController {
	private static final Logger logger = Logger.getLogger(ProjectDetailsController.class);
	
	@Autowired
	private ProjectDetailsService projectService;

	/**
	 * This method to get all Project Details from database*
	 * @methodName getAllProjectDetails
	 */
	@RequestMapping(value = "/getAllProjectDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public  List<ProjectDetailsVO> getAllProjectDetails() {
		logger.info("ProjectDetailsController : getAllProjectDetails : Started");
		List<ProjectDetailsVO> listVo=new ArrayList<>();
		try {
			listVo = projectService.getAllProjectDetails();
			logger.info("CabServiceController : getAllProjectDetails : Ended");
		}catch (TravelServiceException ex) {
			logger.error("ProjectDetailsController : getAllProjectDetails : TravelServiceException Caught : " + ex);
		}
		return listVo;
	}

	/**
	 * This method to get Project Details from database*
	 * @methodName getProjectDetails
	 * @param String
	 */
	@RequestMapping(value = "/getProjectDetails/{projectCode}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ProjectDetailsVO getProjectDetails(@PathVariable("projectCode") String projectCode) {
		logger.info("ProjectDetailsController : getProjectDetails : Started");
		ProjectDetailsVO projectDetailsVO =null;
		try {
			projectDetailsVO = projectService.getProjectDetails(projectCode);
			logger.info("ProjectDetailsController : getProjectDetails : Ended");
		}catch (TravelServiceException ex) {
			logger.error("ProjectDetailsController : getProjectDetails : TravelServiceException Caught : " + ex);
		}
		return projectDetailsVO;
	}

	
}
