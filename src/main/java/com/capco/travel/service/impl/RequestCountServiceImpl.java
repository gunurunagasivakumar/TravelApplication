package com.capco.travel.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.RequestCountDAO;
import com.capco.travel.model.RequestCountBO;
import com.capco.travel.service.RequestCountService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.RequestCountVO;

@Transactional
@Service
public class RequestCountServiceImpl implements RequestCountService{
	private static final Logger logger = Logger.getLogger(RequestCountServiceImpl.class);
	@Autowired
	RequestCountDAO requestCountDAO;
	
	
	@Override
	public RequestCountVO getRequestCount(Integer employeeId) throws TravelServiceException{
		logger.info("RequestCountServiceImpl : getRequestCount(): Started");
		RequestCountVO requestCountVO=null;
		if(employeeId!=null){
			try{
				RequestCountBO requestCountBO=requestCountDAO.getRequestCount(employeeId);
				requestCountVO=TravelBeanUtils.requestCountBO2requestCountVO(requestCountBO);
				logger.info("RequestCountServiceImpl : getRequestCount(): Ended");
			}catch(Exception e){
				logger.info("RequestCountServiceImpl : getRequestCount(): Exception caught"+e);
			}
			
		}
		return requestCountVO;
	}

}
