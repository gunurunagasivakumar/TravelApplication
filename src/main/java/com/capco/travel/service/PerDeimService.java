package com.capco.travel.service;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;

import com.capco.travel.vo.PerDeimVO;

public interface PerDeimService {
	
	public List<PerDeimVO> getPerDeimList() throws TravelServiceException;
}
