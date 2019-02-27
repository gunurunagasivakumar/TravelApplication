package com.capco.travel.service;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;

/**
 * @author e5542274
 *
 */
public interface LoginService {
	public Boolean validateLoginUser(String capcoUserId, String password) throws TravelServiceException;

}
