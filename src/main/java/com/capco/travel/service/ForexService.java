package com.capco.travel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.ForexDetailsVO;

@Service
public interface ForexService {
	
	public void insertForexDetails(ForexDetailsVO frxDetails) throws TravelServiceException;

	public ForexDetailsVO getForexDetails(int id) throws TravelServiceException;

	public void updateForexDetails(ForexDetailsVO forexDetailsVO) throws TravelServiceException;

	public void deleteForexDetails(int id) throws TravelServiceException;

	public List<ForexDetailsVO> getAllForexDetails() throws TravelServiceException;

}
