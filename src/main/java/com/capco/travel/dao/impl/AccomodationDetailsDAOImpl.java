package com.capco.travel.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.AccomodationDAO;
import com.capco.travel.model.AccomodationDetailsBO;

/**
 * This class is the DAO implementation for AccomodationDetails
 * @author e5544344
 *
 */

@Repository
public class AccomodationDetailsDAOImpl implements AccomodationDAO {
	private static final Logger logger = Logger.getLogger(AccomodationDetailsDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method will insert accomodation details
	 * 	  
	 * @author e5544344
	 * @methodName insertAccomodationDetails
	 * @param AccomodationDetailsBO
	 * @return void
	 * @throws DAOException
	 */
	@Override
	public void insertAccomodationDetails(AccomodationDetailsBO accomodationDetails) throws DAOException {
		logger.info("AccomodationDetailsDAOImpl : insertAccomodationDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.save(accomodationDetails);
			logger.info("AccomodationDetailsDAOImpl : insertAccomodationDetails: Ended");
		} catch (Exception e) {
			logger.error("AccomodationDetailsDAOImpl : insertAccomodationDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method will get accomodation details by requestId
	 * 	  
	 * @author e5544344
	 * @methodName getAccomodationDetails
	 * @param int
	 * @return AccomodationDetailsBO
	 * @throws DAOException
	 */
	@Override
	public AccomodationDetailsBO getAccomodationDetails(int requestId) throws DAOException {
		logger.info("AccomodationDetailsDAOImpl : getAccomodationDetails: Started");
		AccomodationDetailsBO accomodationDetailsBO;
		try {
			Session session = getSessionFactory().getCurrentSession();
			accomodationDetailsBO = (AccomodationDetailsBO) session.get(AccomodationDetailsBO.class,
					new Long(requestId));
			logger.info("AccomodationDetailsDAOImpl : getAccomodationDetails: Ended");
		} catch (Exception e) {
			logger.error("AccomodationDetailsDAOImpl : getAccomodationDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return accomodationDetailsBO;
	}

	/**
	 * This method will update existing accomodation details
	 * 	  
	 * @author e5544344
	 * @methodName updateAccomodationDetails
	 * @param AccomodationDetailsBO
	 * @return void
	 * @throws DAOException
	 */
	@Override
	public void updateAccomodationDetails(AccomodationDetailsBO accomodationDetails) throws DAOException {
		logger.info("AccomodationDetailsDAOImpl : updateAccomodationDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(accomodationDetails);
			logger.info("AccomodationDetailsDAOImpl : updateAccomodationDetails: Ended");
		} catch (Exception e) {
			logger.error("AccomodationDetailsDAOImpl : updateAccomodationDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method will delete existing accomodation details by requestId
	 * 	  
	 * @author e5544344
	 * @methodName deleteAccomodationDetails
	 * @param int
	 * @return void
	 * @throws DAOException
	 */
	@Override
	public void deleteAccomodationDetails(int requestId) throws DAOException {
		logger.info("AccomodationDetailsDAOImpl : deleteAccomodationDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			AccomodationDetailsBO accomodationDetails = (AccomodationDetailsBO) session.load(AccomodationDetailsBO.class, new Integer(requestId));
			if (null != accomodationDetails) {
				session.delete(accomodationDetails);
			}
			logger.info("AccomodationDetailsDAOImpl : deleteAccomodationDetails: Ended");
		} catch (Exception e) {
			logger.error("AccomodationDetailsDAOImpl : deleteAccomodationDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method will get all existing accomodation details 
	 * 	  
	 * @author e5544344
	 * @methodName getAllAccomodationDetails
	 * @return List<AccomodationDetailsBO>
	 * @throws DAOException
	 */
	@Override
	public List<AccomodationDetailsBO> getAllAccomodationDetails() throws DAOException {
		logger.info("AccomodationDetailsDAOImpl : getAllAccomodationDetails: Started");
		List<AccomodationDetailsBO> accomodationDetailsBOList = null;
		try {
			Session session = getSessionFactory().openSession();
			accomodationDetailsBOList = session.createQuery("from AccomodationDetailsBO").list();
			logger.info("AccomodationDetailsDAOImpl : getAllAccomodationDetails: Ended");
		} catch (Exception e) {
			logger.error("AccomodationDetailsDAOImpl : getAllAccomodationDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return accomodationDetailsBOList;
	}

}
