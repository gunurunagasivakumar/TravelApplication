package com.capco.travel.service;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.AccomodationDetailsVO;

public interface AccomodationService {

	public void insertAccomodationDetails(AccomodationDetailsVO accomodationDetails) throws TravelServiceException;

	public AccomodationDetailsVO getAccomodationDetails(int requestID) throws TravelServiceException;

	public void updateAccomodationDetails(AccomodationDetailsVO accomodationDetailsVO) throws TravelServiceException;

	public void deleteAccomodationDetails(int requestID) throws TravelServiceException;

	public List<AccomodationDetailsVO> getAllAccomodationDetails() throws TravelServiceException;

}
