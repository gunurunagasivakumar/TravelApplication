package com.capco.travel.dao;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.RequestCountBO;

public interface RequestCountDAO {

	RequestCountBO getRequestCount(Integer employeeId) throws DAOException;

}
