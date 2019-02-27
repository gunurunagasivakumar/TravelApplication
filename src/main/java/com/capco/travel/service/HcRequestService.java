package com.capco.travel.service;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;

import com.capco.travel.vo.HcActionDetailsVO;

public interface HcRequestService {

	public void insertHcTasks(HcActionDetailsVO hcDetailsVO)  throws TravelServiceException;

	public void updateHcTasks(HcActionDetailsVO hcDetailsVO) throws TravelServiceException;
}
