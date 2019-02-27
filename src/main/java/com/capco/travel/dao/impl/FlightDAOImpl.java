package com.capco.travel.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.FlightDAO;
import com.capco.travel.model.FlightDetailsBO;

/**
 * This class is the DAO implementation for FlightDetails
 * @author e5545730
 *
 */

@Repository
public class FlightDAOImpl implements FlightDAO {
	private static final Logger logger = Logger.getLogger(FlightDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method to add flight Details into database*
	 * 
	 * @methodName insertFlightDetails
	 * @param com.capco.travel.model.FlightDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	@Override
	public void insertFlightDetails(FlightDetailsBO flightDetails) throws DAOException {
		logger.info("FlightDAOImpl : insertFlightDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.save(flightDetails);
			logger.info("FlightDAOImpl : insertFlightDetails: Ended");
		} catch (Exception e) {
			logger.error("FlightDAOImpl : insertFlightDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method to get all flight Details into database*
	 * 
	 * @methodName getAllFlightsDetails
	 * @param com.capco.travel.model.FlightDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	@Override
	public List<FlightDetailsBO> getAllFlightsDetails() throws DAOException {
		logger.info("FlightDAOImpl : getAllFlightsDetails: Started");
		List<FlightDetailsBO> allFlightDetails = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			allFlightDetails = session.createQuery("from FlightDetailsBO").list();
			logger.info("FlightDAOImpl : getAllFlightsDetails: Ended");
		} catch (Exception e) {
			logger.error("FlightDAOImpl : getAllFlightsDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return allFlightDetails;
	}

	/**
	 * This method to update flight Details into database*
	 * 
	 * @methodName updateFlightDetails
	 * @param com.capco.travel.model.FlightDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	@Override
	public void updateFlightDetails(FlightDetailsBO updateFlightDetails) throws DAOException {
		logger.info("FlightDAOImpl : updateFlightDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(updateFlightDetails);
			logger.info("FlightDAOImpl : updateFlightDetails: Ended");
		} catch (Exception e) {
			logger.error("FlightDAOImpl : updateFlightDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method to get employee flight Details into database*
	 * @methodName getEmployeeFlightsDetails
	 * @param int requestId
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	@Override
	public FlightDetailsBO getEmployeeFlightsDetails(int requestId) throws DAOException {
		logger.info("FlightDAOImpl : getEmployeeFlightsDetails: Started");
		FlightDetailsBO flightDetailsBO;
		try {
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from FlightDetailsBO where mainRequestBO.requestId =:requestId";
			Query query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			flightDetailsBO = (FlightDetailsBO) query.uniqueResult();
			logger.info("FlightDAOImpl : getEmployeeFlightsDetails: Ended");
		} catch (Exception e) {
			logger.error("FlightDAOImpl : getEmployeeFlightsDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return flightDetailsBO;
	}

	/**
	 * This method to delete employee flight Details into database*
	 * @methodName deletFlightDetails
	 * @param int requestId
	 * @throws com.capco.travel.custom.exception.TravelException.DAOException
	 */
	@Override
	public void deletFlightDetails(int requestId) throws DAOException {
		logger.info("FlightDAOImpl : deletFlightDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			FlightDetailsBO deletFlightDetails = (FlightDetailsBO) session.load(FlightDetailsBO.class, new Integer(requestId));
			if (null != deletFlightDetails) {
				session.delete(deletFlightDetails);
			}
			logger.info("FlightDAOImpl : deletFlightDetails: Ended");
		} catch (Exception e) {
			logger.error("FlightDAOImpl : deletFlightDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}

	}
}
