package com.capco.travel.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.ForexDAO;
import com.capco.travel.model.ForexDetailsBO;

/**
 * This class is the DAO implementation for ForexDetails
 * @author e5545565
 *
 */
@Repository
public class ForexDAOImpl implements ForexDAO {
	private final static Logger logger = Logger.getLogger(ForexDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method is to insert ForexDetails into the DataBase
	 * @param forexDetailsBO
	 * @return void
	 */
	@Override
	public void insertForexDetails(ForexDetailsBO frxDetails) throws DAOException {
		logger.info("ForexDAOImpl : insertForexDetails: Started");
		try {
			Session session = getSessionFactory().openSession();
			session.save(frxDetails);
		} catch (Exception e) {
			logger.error("ForexDAOImpl : insertForexDetails : Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method is to get the ForexDetails according to the requested Id
	 * @param request id
	 * @return ForexDetailsBO
	 */
	@Override
	public ForexDetailsBO getForexDetails(int requestId) throws DAOException {
		logger.info("ForexDAOImpl : getForexDetails: Started");
		ForexDetailsBO frxDetails;
		try {
			Session session = getSessionFactory().getCurrentSession();
			System.out.println(requestId);
            String sql ="from ForexDetailsBO where mainRequestBO.requestId =:requestId ";
            Query  query = session.createQuery(sql);  
            query.setParameter("requestId", requestId);  
            frxDetails = (ForexDetailsBO) query.uniqueResult();
		} catch (Exception e) {
			logger.error("ForexDAOImpl : getForexDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return frxDetails;
	}

	/**
	 * This method is to get All the ForexDetails from DB
	 * @return List of all the forex details
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ForexDetailsBO> getAllForexDetails() throws DAOException {
		logger.info("ForexDAOImpl : getAllForexDetails: Started");
		List<ForexDetailsBO> forexDetailsBOList = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			forexDetailsBOList = session.createQuery("from ForexDetailsBO").list();
			logger.info("ForexDAOImpl : getAllForexDetails: Ended");
		} catch (Exception e) {
			logger.error("ForexDAOImpl : getAllForexDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return forexDetailsBOList;
	}

	/**
	 * This method is to update the forexDetails if the object contains any changes to be updated
	 * @param ForexDetailsBO
	 */
	@Override
	public void updateForexDetails(ForexDetailsBO frxDetails) throws DAOException {
		logger.info("ForexDAOImpl : updateForexDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(frxDetails);
			logger.info("ForexDAOImpl : updateForexDetails: Ended");
		} catch (Exception e) {
			logger.error("ForexDAOImpl : updateForexDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method deletes the ForexDetails according to the id
	 * @param id
	 */
	@Override
	public void deleteForexDetails(int id) throws DAOException {
		logger.info("ForexDAOImpl : deleteForexDetails: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			ForexDetailsBO frxDetails = (ForexDetailsBO) session.load(ForexDetailsBO.class, new Integer(id));
			if (null != frxDetails) {
				session.delete(frxDetails);
			}
			logger.info("ForexDAOImpl : deleteForexDetails: Ended");
		} catch (Exception e) {
			logger.error("ForexDAOImpl : deleteForexDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

}
