package com.capco.travel.dao.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.HcRequestDAO;
import com.capco.travel.model.HcViewBO;

@Transactional
@Repository
public class HcRequestDAOImpl implements HcRequestDAO{
	
	private static final Logger logger = Logger.getLogger(HcRequestDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * This method inserts HcViewBO object to the DB table
	 * @param EmployeeDetailsBO
	 */
	@Override
	public void getAllHcPendingDetails(HcViewBO hcViewBO) throws DAOException {
		logger.info("HcRequestDAOImpl : insertAllHcPendingDetails: Started");
		try {
			logger.info("ApproverActionController : addHcActionDetails : RequestId"+ hcViewBO.getRequestId()+"ActionId "+hcViewBO.getActionId()+" Status: "+ hcViewBO.getActionStatus()+"PendingItems"+hcViewBO.getPendingItems());
			Session session = getSessionFactory().getCurrentSession();
			int i = (Integer) session.save(hcViewBO);
			logger.info("EmployeeDAOImpl : insertEmployeeDetails: int Value : " + i);
			logger.info("EmployeeDAOImpl : insertEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : insertEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}
	/**
	 * This method update object to the DB table
	 * @param EmployeeDetailsBO
	 */
	@Override
	public void updateHcStatus(HcViewBO hcViewBO) throws DAOException {
		logger.info("HcRequestDAOImpl : insertAllHcPendingDetails: Started");
		try {
			logger.info("ApproverActionController : addHcActionDetails : RequestId"+ hcViewBO.getRequestId());
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(hcViewBO);
			logger.info("EmployeeDAOImpl : insertEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : insertEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	

}
