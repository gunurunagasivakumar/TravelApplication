/**
 * 
 */
package com.capco.travel.service;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.FlightDetailsVO;

public interface FlightService {
	
	public void insertFlightDetails(FlightDetailsVO flightDetails) throws TravelServiceException;

	public List<FlightDetailsVO> getAllFlightsDetails() throws TravelServiceException;

	public FlightDetailsVO getEmployeeFlightsDetails(int requestId) throws TravelServiceException;

	public void updateFlightDetails(FlightDetailsVO updateflightDetails) throws TravelServiceException;

	public void deletFlightDetails(int requestId) throws TravelServiceException;

}
