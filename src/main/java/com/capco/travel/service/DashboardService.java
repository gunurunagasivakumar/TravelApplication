package com.capco.travel.service;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.vo.RequestListVO;

public interface DashboardService {

	List<RequestListVO> getRequestsByRequestType(Integer userId,String type) throws TravelServiceException;

}
