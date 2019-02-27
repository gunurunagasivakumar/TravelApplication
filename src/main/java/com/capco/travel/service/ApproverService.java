package com.capco.travel.service;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.RequestListVO;

public interface ApproverService {

	public List<RequestListVO> getAllRequestByApproverId(Integer approverId) throws TravelServiceException;
	public MainRequestBaseVO updateMainRequestTable(MainRequestBO dbData, MainRequestBaseVO newVO) throws TravelServiceException;
	public MainRequestBO getRequestById(Integer requestId) throws TravelServiceException;
	public int getEmployeeIdByUserID(String userId) throws TravelServiceException ;
	
}
