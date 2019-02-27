package com.capco.travel.dao;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.FlightDetailsBO;

public interface FlightDAO {
	
	public void insertFlightDetails(FlightDetailsBO flightDetails) throws DAOException;

	public List<FlightDetailsBO> getAllFlightsDetails() throws DAOException;

	public void updateFlightDetails(FlightDetailsBO updateflightDetails) throws DAOException;

	public FlightDetailsBO getEmployeeFlightsDetails(int requestId) throws DAOException;

	public void deletFlightDetails(int requestId) throws DAOException;
}
