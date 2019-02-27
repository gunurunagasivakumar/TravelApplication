package com.capco.travel.dao;

import java.util.List;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.vo.RequestListVO;

public interface DashboardDAO {

	List<RequestListVO> getAllRequestByUserId(Integer userId,String tableName) throws DAOException;

}
