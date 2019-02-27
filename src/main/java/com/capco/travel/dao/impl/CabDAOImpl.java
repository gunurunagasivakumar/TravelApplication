package com.capco.travel.dao.impl;

import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.CabDAO;
import com.capco.travel.model.CabDetailsBO;

/**
 * This class is the DAO implementation for the CabDetails
 * @author e5544354
 *
 */

@Repository
@Transactional
public class CabDAOImpl implements CabDAO {
	private static final Logger logger = Logger.getLogger(CabDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method to add Cab Details into database*
	 * 
	 * @methodName insertCabDetails
	 * @param com.capco.travel.model.CabDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	public void insertCabDetails(CabDetailsBO cabDetails) throws DAOException {
		logger.info("CabDAOImpl : insertCabDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.save(cabDetails);
			logger.info("CabDAOImpl : insertCabDetails: Ended");
		} catch (Exception e) {
			logger.error("CabDAOImpl : insertCabDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method to get Cab Details from database*
	 * 
	 * @methodName getCabDetails
	 * @return CabDetailsBO
	 * @param long
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	public CabDetailsBO getCabDetails(int requestId) throws DAOException {
		logger.info("CabDAOImpl : getCabDetails: Started");
		CabDetailsBO cabDetails;
		try {
			logger.info("CabDAOImpl : getCabDetails: Cab Detail Id: " + requestId);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from CabDetailsBO where mainRequestBO.requestId =:requestId";
			Query query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			cabDetails = (CabDetailsBO) query.uniqueResult();
			
			logger.info("CabDAOImpl : getCabDetails: Ended");
		} catch (Exception e) {
			logger.error("CabDAOImpl : getCabDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return cabDetails;
	}

	/**
	 * This method to get all Cab Details from database*
	 * 
	 * @methodName getAllCabDetails
	 * @return List<CabDetailsBO>
	 * @throws com.capco.travel.custom.exception.TravelException.ServiceException
	 */
	public List<CabDetailsBO> getAllCabDetails() throws DAOException {
		logger.info("CabDAOImpl : getAllCabDetails: Started");
		List<CabDetailsBO> cabDetailsList = null;
		try {
			Session session = getSessionFactory().openSession();
			cabDetailsList = session.createQuery("from CabDetailsBO").list();
			logger.info("CabDAOImpl : getAllCabDetails: Ended");
		} catch (Exception e) {
			logger.error("CabDAOImpl : getAllCabDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return cabDetailsList;
	}

	/**
	 * This method to update Cab Details in database*
	 * 
	 * @methodName updateCabDetails
	 * @param com.capco.travel.model.CabDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	@Override
	public void updateCabDetails(CabDetailsBO cabDetails) throws DAOException {
		logger.info("CabDAOImpl : updateCabDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(cabDetails);
			logger.info("CabDAOImpl : updateCabDetails: Ended");
		} catch (Exception e) {
			logger.error("CabDAOImpl : updateCabDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}

	}

	/**
	 * This method to delete Cab Details from database*
	 * 
	 * @methodName deleteCabDetails
	 * @param long
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	public void deleteCabDetails(int requestId) throws DAOException {
		logger.info("CabDAOImpl : deleteCabDetails: Started");
		try {
			CabDetailsBO cabDetails;
			Session session = getSessionFactory().getCurrentSession();
			cabDetails = (CabDetailsBO) session.load(CabDetailsBO.class, requestId);
			if (cabDetails != null) {
				session.delete(cabDetails);
			}
			logger.info("CabDAOImpl : deleteCabDetails: Ended");
		} catch (Exception e) {
			logger.error("CabDAOImpl : deleteCabDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

}
