package com.capco.travel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.DashboardDAO;
import com.capco.travel.service.DashboardService;
import com.capco.travel.util.TravelConstants;
import com.capco.travel.vo.RequestListVO;
@Transactional
@Service
public class DashboardServiceImpl implements DashboardService{
	
	private final Logger logger = Logger.getLogger(DashboardServiceImpl.class);
	
	@Autowired
	DashboardDAO dashboardDAO;
	
	@Override
	public List<RequestListVO> getRequestsByRequestType(Integer userId,String type) throws TravelServiceException{
		logger.info("DashboardServiceImpl : getRequestsByRequestType: Started");
		List<RequestListVO> requestList=new ArrayList<>();
		try{
			requestList=dashboardDAO.getAllRequestByUserId(userId,TravelConstants.tableMapping.get(type));
			logger.info("DashboardServiceImpl : getRequestsByRequestType: Ended");
		}catch (DAOException ex ) {
			logger.error("DashboardServiceImpl : getRequestsByRequestType: DAOException Caught: " + ex);
			throw new TravelServiceException(ex);
		}
		return requestList;
	}
	
	

}
