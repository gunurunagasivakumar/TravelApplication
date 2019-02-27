package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.ProjectDetailsDAO;
import com.capco.travel.model.ProjectDetailsBO;
import com.capco.travel.service.ProjectDetailsService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.ProjectDetailsVO;
@Transactional
@Service
public class ProjectDetailsServiceImpl implements ProjectDetailsService {
	private static final Logger logger = Logger.getLogger(ProjectDetailsServiceImpl.class);
	@Autowired
	ProjectDetailsDAO projectDetailsDAO;


	/**
	 * This method to get all Project Details from database*
	 * @methodName getAllProjectDetails
	 * @return List<ProjectDetailsVO>
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public List<ProjectDetailsVO>getAllProjectDetails() throws TravelServiceException {
		logger.info("ProjectDetailsServiceImpl : getAllProjectDetails(): Started");
		 List<ProjectDetailsVO> listVO =null;
		try {
			 List<ProjectDetailsBO> listBO = projectDetailsDAO.getAllProjectDetails();
			 if(listBO!=null && !listBO.isEmpty()) {
				 listVO = new ArrayList<>();
				 for (ProjectDetailsBO projectDetailsBO : listBO) {
					 ProjectDetailsVO projectDetailsVO = new ProjectDetailsVO();
					 BeanUtils.copyProperties(projectDetailsBO, projectDetailsVO);
					 listVO.add(projectDetailsVO);
				 }
		}logger.info("ProjectDetailsServiceImpl : getAllProjectDetails: Ended");
	} catch (DAOException ex) {
		logger.error("ProjectDetailsServiceImpl : getAllProjectDetails: DAOException Caught: " + ex);
		throw new TravelServiceException(ex);
	}
		return listVO;
	}

	/**
	 * This method to get Project Details from database*
	 * @methodName getProjectDetails
	 * @param String
	 * @return ProjectDetailsVO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public ProjectDetailsVO getProjectDetails(String projectId) throws TravelServiceException {
		logger.info("ProjectDetailsServiceImpl : getProjectDetails: Started");
		ProjectDetailsVO projectDetailsVO = new ProjectDetailsVO();
		try {
			ProjectDetailsBO projectDetailsBO = projectDetailsDAO.getProjectDetails(projectId);
			if(projectDetailsBO!=null) {
				projectDetailsVO = TravelBeanUtils.ProjectDetailsBO2ProjectDetailsVO(projectDetailsBO);
			}
			else throw new DAOException(TravelConstants.RESULT_NULL_OBJECT+projectId);
			logger.info("ProjectDetailsServiceImpl : getProjectDetails: Ended");
		} catch (DAOException ex) {
			logger.error("ProjectDetailsServiceImpl : getProjectDetails: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return projectDetailsVO;
	}
}
