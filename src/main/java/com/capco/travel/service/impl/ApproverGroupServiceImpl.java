package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.ApproverGroupDAO;
import com.capco.travel.service.ApproverGroupService;
import com.capco.travel.vo.ApproverGroupVO;

@Transactional
@Service
public class ApproverGroupServiceImpl implements ApproverGroupService{
	private static final Logger logger = Logger.getLogger(ApproverGroupServiceImpl.class);

	@Autowired
	ApproverGroupDAO approverGroupDAO;

	/**
	 * This method will return VO list of all approvers using level
	 * 	  
	 * @author e5544344
	 * @methodName getAllApprovers
	 * @param int level
	 * @return List<ApproverGroupVO>
	 * 	 */	
	@Override
	public List<ApproverGroupVO> getAllApprovers(int level) throws TravelServiceException {
		logger.info("ApproverGroupServiceImpl :getAllApprovers: Started");
		List<ApproverGroupVO> listVO=new ArrayList<>();
		try {
			listVO = approverGroupDAO.getAllApproversDetails(level);
			logger.info("ApproverGroupServiceImpl :getAllApprovers: Ended");			
		} catch (DAOException ex) {
			logger.error("ApproverGroupServiceImpl :getAllApprovers:: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return listVO;
	}

}
