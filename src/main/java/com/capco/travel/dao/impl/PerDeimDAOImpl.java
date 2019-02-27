package com.capco.travel.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.PerDeimDAO;
import com.capco.travel.model.PerDeimBO;

@Repository
public class PerDeimDAOImpl implements PerDeimDAO{
	
	private static final Logger logger = Logger.getLogger(FlightDAOImpl.class);
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
	
	@Override
	public List<PerDeimBO> getPerDeimList() throws DAOException {	
		logger.info("PerDeimDAOImpl : getPerDeimList: Started");
		List<PerDeimBO> perDeimListBO = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			perDeimListBO = session.createQuery("from PerDeimBO").list();
			logger.info("PerDeimDAOImpl : getPerDeimList: Ended");
		} catch (Exception e) {
			logger.error("PerDeimDAOImpl : getPerDeimList: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return perDeimListBO;
	}



}
