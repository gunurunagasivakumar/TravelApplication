/**
 * 
 */
package com.capco.travel.dao.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.LoginDAO;
import com.capco.travel.model.LoginDetailsBO;

/**
 * @author e5542274
 *
 */
@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO {
	private static final Logger logger = Logger.getLogger(LoginDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Boolean getLoggedInUser(String capcoUserId, String password) throws DAOException {
		logger.info("LoginDAOImpl : getLoggedInUser: Started");
		boolean userExists = false;
		try {
			Session session = getSessionFactory().getCurrentSession();
			String sql = "from LoginDetailsBO where capcoUserId =:capcoUserId and password=:password";
			Query query =session.createQuery(sql);
			query.setParameter("capcoUserId", capcoUserId);
			query.setParameter("password", password);
			LoginDetailsBO loginDetailsBO  = (LoginDetailsBO) query.uniqueResult();
			if(loginDetailsBO != null) {
				userExists = true;
			}

			logger.info("LoginDAOImpl : getLoggedInUser: Ended");
		} catch (Exception e) {
			logger.error("LoginDAOImpl : getLoggedInUser: Exception Caught: " + e);
			throw new DAOException(e);
		}

		return userExists;
	}

}