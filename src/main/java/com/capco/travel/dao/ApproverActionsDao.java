package com.capco.travel.dao;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.vo.RequestListVO;

public interface ApproverActionsDao {

	public List<RequestListVO> getAllRequestByApproverId(Integer approverId) throws DAOException;

	public MainRequestBO updateMainReqestTable(MainRequestBO updateRequest) throws DAOException;

	public MainRequestBO getRequestDetailsByRequestId(int requestId) throws DAOException;

	public int getEmployeeIdByUserId(String userId) throws DAOException;

}
