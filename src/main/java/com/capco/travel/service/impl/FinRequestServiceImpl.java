package com.capco.travel.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.FinRequestDAO;
import com.capco.travel.dao.HcRequestDAO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.service.FinRequestService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.FinActionDetailsVO;
import com.capco.travel.vo.HcActionDetailsVO;

@Transactional
@Service
public class FinRequestServiceImpl implements FinRequestService{
	
	private static final Logger logger = Logger.getLogger(FinRequestServiceImpl.class);
	/**
	 * This method to add HC Details into database*
	 * @methodName insertEmployeeDetails
	 * @param com.capco.travel.model.EmployeeDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Autowired
	FinRequestDAO finRequestDAO;

	
	public void insertFinTasks(FinActionDetailsVO finDetailsVO) throws TravelServiceException {
		logger.info("FinRequestServiceImpl : insertFinTasks: Started");
		
		try {
			
				logger.info("Creating finViewBO Object");
				FinViewBO finViewBO = new FinViewBO();
			
				finViewBO.setRequestId(finDetailsVO.getRequestId());
				finViewBO.setActionId(finDetailsVO.getActionId());
				finViewBO.setActionStatus(finDetailsVO.getActionStatus());
				finViewBO.setPendingItems(finDetailsVO.getPendingItems());
				 
					 
				finViewBO = TravelBeanUtils.FinActionDetailsVO2FinViewBO(finDetailsVO);
				finRequestDAO.getAllFinPendingDetails(finViewBO);
			logger.info("FinRequestServiceImpl : insertFinTasks: Ended");
		}catch (DAOException ex) {
			logger.error("FinRequestServiceImpl : insertFinTasks: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}
	
	public void updateFinTasks(FinActionDetailsVO finDetailsVO) throws TravelServiceException {
		logger.info("FinRequestServiceImpl : insertFinTasks: Started");
		
		try {
			
				logger.info("Creating finViewBO Object");
				FinViewBO finViewBO = new FinViewBO();
			
				finViewBO.setRequestId(finDetailsVO.getRequestId());
				finViewBO.setActionId(finDetailsVO.getActionId());
				finViewBO.setActionStatus(finDetailsVO.getActionStatus());
				finViewBO.setPendingItems(finDetailsVO.getPendingItems());
				 
					 
				finViewBO = TravelBeanUtils.FinActionDetailsVO2FinViewBO(finDetailsVO);
				finRequestDAO.updateFinStatus(finViewBO);
			logger.info("FinRequestServiceImpl : insertFinTasks: Ended");
		}catch (DAOException ex) {
			logger.error("FinRequestServiceImpl : insertFinTasks: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}

}
