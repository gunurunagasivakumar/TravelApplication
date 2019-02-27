package com.capco.travel.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.FinRequestDAO;
import com.capco.travel.model.FinViewBO;

@Transactional
@Repository
public class FinRequestDAOImpl implements FinRequestDAO{
	
	private static final Logger logger = Logger.getLogger(FinRequestDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void getAllFinPendingDetails(FinViewBO finViewBO) throws DAOException {
		
		logger.info("FinRequestDAOImpl : getAllFinPendingDetails: Started");
		try {
			logger.info("ApproverActionController : addFinActionDetails : RequestId"+ finViewBO.getRequestId()+"ActionId "+finViewBO.getActionId()+" Status: "+ finViewBO.getActionStatus()+"PendingItems"+finViewBO.getPendingItems());
			Session session = getSessionFactory().getCurrentSession();
			int i = (Integer) session.save(finViewBO);
			logger.info("FinRequestDAOImpl : addFinActionDetails: int Value : " + i);
			logger.info("FinRequestDAOImpl : addFinActionDetails: Ended");
		} catch (Exception e) {
			logger.error("FinRequestDAOImpl : addFinActionDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		
	}
	
	@Override
	public void updateFinStatus(FinViewBO finViewBO) throws DAOException {
		
		logger.info("FinRequestDAOImpl : getAllFinPendingDetails: Started");
		try {
			logger.info("ApproverActionController : addFinActionDetails : RequestId"+ finViewBO.getRequestId());
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(finViewBO);
			logger.info("FinRequestDAOImpl : addFinActionDetails: Ended");
		} catch (Exception e) {
			logger.error("FinRequestDAOImpl : addFinActionDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		
	}

}
