package com.capco.travel.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.LoginDAO;
import com.capco.travel.service.LoginService;


/**
 * This service is created to validate login user
 * @author e5542274
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginDAO loginDao;


	public Boolean validateLoginUser(String capcoUserId, String password) throws TravelServiceException {
		Boolean userExists = false;
		try {
			logger.info("LoginServiceImpl : validateLoginUser: Started");
			userExists = loginDao.getLoggedInUser(capcoUserId, password);
			logger.info("LoginServiceImpl : validateLoginUser: ended");
		} catch (DAOException e) {
			logger.error("LoginServiceImpl : validateLoginUser: Exception Caught: " + e);
			throw new TravelServiceException(e);
		}
		return userExists;
	}
	


}
