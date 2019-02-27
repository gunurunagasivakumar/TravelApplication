package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.ApproverActionsDao;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.service.ApproverService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.RequestListVO;

@Transactional
@Service
public class ApproverServiceImpl implements ApproverService{
	private final Logger logger = Logger.getLogger(ApproverServiceImpl.class);

	@Autowired
	ApproverActionsDao approverActionsDao;

	/**
	 * This method will return VO list of all requests using approver id
	 * 	  
	 * @author e5544344
	 * @methodName getAllRequestByApproverId
	 * @param Integer approverId
	 * @return List<RequestListVO>
	 * 	 */	
	@Override
	public List<RequestListVO> getAllRequestByApproverId(Integer approverId) throws TravelServiceException{
		logger.info("ApproverServiceImpl : getAllRequestByApproverId: Started");
		List<RequestListVO> requestList=new ArrayList<>();
		try{
			requestList=approverActionsDao.getAllRequestByApproverId(approverId);
			logger.info("ApproverServiceImpl : getAllRequestByApproverId: Ended");
		}catch (DAOException ex ) {
			logger.error("ApproverServiceImpl : getAllRequestByApproverId: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return requestList;
	}

	/**
	 * This method will update current status in main request table
	 * 	  
	 * @author e5544344
	 * @methodName updateMainRequestTable
	 * @param MainRequestBO dbData, MainRequestTableVO updateRequest
	 * @return MainRequestTableVO
	 * 	 */	
	@Override
	public MainRequestBaseVO updateMainRequestTable(MainRequestBO dbData, MainRequestBaseVO updateRequest)
			throws TravelServiceException {
		MainRequestBaseVO mainRequestTableVO = null;
		MainRequestBO mainRequestBO = null;
		try {
			logger.info("ApproverServiceImpl : updateMainRequestTable: Started");
			if(updateRequest.getApproverId()!=null){
				dbData.setApproverId(updateRequest.getApproverId());
			}
			if(updateRequest.getModifiedOn()!=null){
				dbData.setModifiedOn(new Date());
			}
			if(updateRequest.getRemark()!=null){
				dbData.setRemark(updateRequest.getRemark());
			}
			if(updateRequest.getCurrentStatus()!=null){
				dbData.setCurrentStatus(updateRequest.getCurrentStatus());
			}		
			
			if(updateRequest.getBillable()!=null){
				dbData.setBillable(updateRequest.getBillable());
			}
			
			mainRequestBO=approverActionsDao.updateMainReqestTable(dbData);
			mainRequestTableVO=TravelBeanUtils.mainRequestBO2MainRequestVO(mainRequestBO);
			logger.info("ApproverServiceImpl : updateMainRequestTable: ended");
		} catch (DAOException ex) {
			logger.error("ApproverServiceImpl : updateMainRequestTable: Exception Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return mainRequestTableVO;
	}

	/**
	 * This method will update return request using request id
	 * 	  
	 * @author e5544344
	 * @methodName getRequestById
	 * @param Integer requestId
	 * @return MainRequestBO
	 * 	 */	
	@Override
	public MainRequestBO getRequestById(Integer requestId)  {
		MainRequestBO mainRequestBO=new MainRequestBO();
		try {
			logger.info("ApproverServiceImpl : getRequestById: Started");
			mainRequestBO=approverActionsDao.getRequestDetailsByRequestId(requestId);
			logger.info("ApproverServiceImpl : getRequestById: ended");
		} catch (DAOException e) {
			logger.error("ApproverServiceImpl : getRequestById: Exception Caught: " + e);
		}
		return mainRequestBO;
	}
	
	/**
	 * This method will return employee id using capco user id
	 * 	  
	 * @author e5544344
	 * @methodName getEmployeeIdByUserID
	 * @param Integer userId
	 * @return int
	 * 	 */
	@Override
	public int getEmployeeIdByUserID(String userId) throws TravelServiceException {
		logger.info("ApproverServiceImpl : getEmployeeIdByUserID: Started");
		int employeeId=0;
		if(userId==null){
			throw new TravelServiceException("no user id");
		}
		try {
			employeeId=approverActionsDao.getEmployeeIdByUserId(userId);
			logger.info("ApproverServiceImpl : getEmployeeIdByUserID: Ended");
		}catch(DAOException ex) {
			logger.error("ApproverServiceImpl : getEmployeeIdByUserID: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		
		return employeeId;
	}

}
