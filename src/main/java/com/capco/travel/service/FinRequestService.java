package com.capco.travel.service;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.FinActionDetailsVO;

public interface FinRequestService {
	

	public void insertFinTasks(FinActionDetailsVO finDetailsVO)  throws TravelServiceException;
	public void updateFinTasks(FinActionDetailsVO finDetailsVO)  throws TravelServiceException;

}
