package com.capco.travel.service;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.RequestCountVO;

public interface RequestCountService {

	RequestCountVO getRequestCount(Integer employeeId) throws TravelServiceException;

}
