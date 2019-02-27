package com.capco.travel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capco.travel.beans.TravelRequestWrapper;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.vo.MainRequestBaseVO;
import com.capco.travel.vo.MainRequestVO;
import com.capco.travel.vo.RequestHistoryLogVO;
import com.capco.travel.vo.RequestListVO;

/**
 * @author e5542274
 *
 */
public interface MainRequestService {
	public Integer insertMainRequest(MainRequestVO newRequest) throws TravelServiceException;
	public MainRequestVO getRequestDetailsByRequestId(Integer requestId) throws TravelServiceException;
	public List<RequestListVO> getAllRequestByUserId(String userId) throws TravelServiceException;
	public Boolean deleteRequestByRequestId(MainRequestBO request) throws TravelServiceException;
	public MainRequestVO updateMainRequest(MainRequestBO dbData, MainRequestVO updateRequest) throws TravelServiceException;
	public MainRequestBO getMainRequestBOByRequestId(Integer requestId) throws TravelServiceException;
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws TravelServiceException;
	/*public List<RequestHistoryLogVO> getAllRequestHistoryLogs()throws TravelServiceException;
	public List<RequestHistoryLogVO> getRequestHistoryLogsByRequestId(int requestId) throws TravelServiceException;
	public List<RequestHistoryLogVO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws TravelServiceException;*/
	public ResponseEntity validateTravelRequestObject(MainRequestVO newRequest);
	public RequestHistoryLogBO createApproverRequestHistoryLog(MainRequestBO oldRequest, MainRequestBaseVO newRequest,int oldId);
	public boolean tokenValidation(int tokenId, String capcoUserId) throws TravelServiceException;
	public List<MainRequestVO> getLatestApprovedRequest(Integer requestedBy) throws TravelServiceException;
	public void sendEmailNotification(int requestId, String changeDone);
	public TravelRequestWrapper getAllRequestByTravelStatus() throws TravelServiceException;
	public List<HcViewBO> getAllRequestByHcStatus() throws TravelServiceException;
	public List<FinViewBO> getAllRequestOfFinance() throws TravelServiceException;
	public MainRequestBO updateMainRequestBO(MainRequestBO mainRequestBO, MainRequestVO updateRequest);
}
