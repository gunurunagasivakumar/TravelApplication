package com.capco.travel.dao;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.CabDetailsBO;

public interface CabDAO {

	public void insertCabDetails(CabDetailsBO cabDetails) throws DAOException;

	public CabDetailsBO getCabDetails(int requestId) throws DAOException;

	public void updateCabDetails(CabDetailsBO cabDetails) throws DAOException;

	public void deleteCabDetails(int requestId) throws DAOException;

	public List<CabDetailsBO> getAllCabDetails() throws DAOException;

}
