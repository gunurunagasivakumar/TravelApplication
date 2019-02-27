package com.capco.travel.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.HcRequestDAO;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.service.HcRequestService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.HcActionDetailsVO;

@Transactional
@Service
public class HcRequestServiceImpl implements HcRequestService{
	private static final Logger logger = Logger.getLogger(HcRequestServiceImpl.class);
	/**
	 * This method to add HC Details into database*
	 * @methodName insertEmployeeDetails
	 * @param com.capco.travel.model.EmployeeDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Autowired
	HcRequestDAO hcRequestDAO;

	
	@Override
	public void insertHcTasks(HcActionDetailsVO hcDetailsVO) throws TravelServiceException {
		logger.info("PersonalDetailServiceImpl : insertEmployeeDetails: Started");
		try {
			
			
				logger.info("Creating hcViewBO Object");
				HcViewBO hcViewBO = new HcViewBO();
			
				 hcViewBO.setRequestId(hcDetailsVO.getRequestId());
				 hcViewBO.setActionId(hcDetailsVO.getActionId());
				 hcViewBO.setActionStatus(hcDetailsVO.getActionStatus());
				 hcViewBO.setPendingItems(hcDetailsVO.getPendingItems());
					 
			 hcViewBO = TravelBeanUtils.HcActionDetailsVO2HcViewBO(hcDetailsVO);
			 hcRequestDAO.getAllHcPendingDetails(hcViewBO);
			logger.info("PersonalDetailServiceImpl : insertEmployeeDetails: Ended");
		}
		catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : insertEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}
		
		@Override
		public void updateHcTasks(HcActionDetailsVO hcDetailsVO) throws TravelServiceException {
			logger.info("PersonalDetailServiceImpl : insertEmployeeDetails: Started");
			try {
				
				
					logger.info("Creating hcViewBO Object");
					HcViewBO hcViewBO = new HcViewBO();
				
					 hcViewBO.setRequestId(hcDetailsVO.getRequestId());
					 hcViewBO.setActionId(hcDetailsVO.getActionId());
					 hcViewBO.setActionStatus(hcDetailsVO.getActionStatus());
					 hcViewBO.setPendingItems(hcDetailsVO.getPendingItems());
						 
				 hcViewBO = TravelBeanUtils.HcActionDetailsVO2HcViewBO(hcDetailsVO);
				 hcRequestDAO.updateHcStatus(hcViewBO);
				logger.info("PersonalDetailServiceImpl : insertEmployeeDetails: Ended");
			}
		
		catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : insertEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}
	

}
