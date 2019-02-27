package com.capco.travel.dao;

import java.util.List;

import com.capco.travel.beans.TravelRequestWrapper;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.model.ActionDetailsBO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.vo.RequestListVO;

/**
 * @author e5542274
 *
 */
public interface MainRequestDAO {
	public Integer insertMainReuqest(MainRequestBO mainRequestBO) throws DAOException;
	public MainRequestBO getRequestDetailsByRequestId(int requestId) throws DAOException;
	public List<RequestListVO> getRequestDetailsByUserId(int requestedBy) throws DAOException;
	public boolean deleteRequestByRequestId(MainRequestBO mainRequest) throws DAOException;
	public MainRequestBO updateMainRequest(MainRequestBO mainRequestBO) throws DAOException;
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws DAOException;
	/*public List<RequestHistoryLogBO> getAllRequestHistoryLogs() throws DAOException;
	public List<RequestHistoryLogBO> getRequestHistoryLogsByRequestId(int requestId) throws DAOException;
	public List<RequestHistoryLogBO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws DAOException;*/
	public List<Object[]> getRequestHistoryCommentsByRequestId(int requestId) throws DAOException;
	public List<MainRequestBO> getLatestApprovedRequest(int requestedBy) throws DAOException;
	boolean deleteRequestByRequestIdTest(MainRequestBO mainRequestBO) throws DAOException;
	public TravelRequestWrapper getRequestDetailsByTravelStatus() throws DAOException;
	public List<HcViewBO> getRequestDetailsByHcStatus() throws DAOException;
	public List<FinViewBO> getRequestDetailsByFinance() throws DAOException;
	//public List<ActionDetailsBO> getActionStatus() throws DAOException;
	
	
}
