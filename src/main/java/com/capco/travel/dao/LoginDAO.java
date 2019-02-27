/**
 * 
 */
package com.capco.travel.dao;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.LoginDetailsBO;

/**
 * @author e5542274
 *
 */
public interface LoginDAO {
	public Boolean getLoggedInUser(String capcoUserId, String password) throws DAOException;

}
