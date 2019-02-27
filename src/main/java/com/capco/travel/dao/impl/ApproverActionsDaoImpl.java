package com.capco.travel.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.ApproverActionsDao;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.vo.RequestListVO;

@Repository
@Transactional
public class ApproverActionsDaoImpl implements ApproverActionsDao{
	private final static Logger logger = Logger.getLogger(ApproverActionsDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method will get all requests for approver id
	 * 	  
	 * @author e5544344
	 * @methodName getAllRequestByApproverId
	 * @param Integer approverID
	 * @return List<RequestListVO>
	 * 	 */	
	@Override
	public List<RequestListVO> getAllRequestByApproverId(Integer approverID) throws DAOException{
		logger.info("ApproverActionsDaoImpl : getAllRequestByApproverId: Started");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<RequestListVO> requestList=new ArrayList<>();
		try {
			Session session = getSessionFactory().getCurrentSession();			
			SQLQuery query = session.createSQLQuery("select mainRequest.request_id,mainRequest.current_status,mainRequest.created_on,ed.name from main_request mainRequest inner join employee_details ed on ed.employee_id=mainRequest.requested_by where mainRequest.approver_id =:approverid or mainRequest.request_id in (select distinct(request_id) from request_history where updated_by=:approverid) order by created_on desc;");
			query.setParameter("approverid", approverID);
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				RequestListVO requestListVO=new RequestListVO();
				if(row[0]!=null){
					requestListVO.setRequestId(Integer.parseInt(row[0].toString()));
				}
				if(row[1]!=null){
					requestListVO.setCurrentStatus(row[1].toString());
				}
				if(row[2]!=null){
					requestListVO.setCreatedOn(sf.parse(row[2].toString()));
				}
				if(row[3]!=null){
					requestListVO.setRequestedBy(row[3].toString());
				}				
				requestList.add(requestListVO);
				
			}
			logger.info("ApproverActionsDaoImpl : getAllRequestByApproverId: Ended");
		} catch (Exception e) {
			logger.error("ApproverActionsDaoImpl : getAllRequestByApproverId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestList;

	}

	/**
	 * This method will change current status of a request
	 * 	  
	 * @author e5544344
	 * @methodName updateMainReqestTable
	 * @param MainRequestBO updateRequest
	 * @return MainRequestBO
	 * 	 */	
	@Override
	public MainRequestBO updateMainReqestTable(MainRequestBO updateRequest) throws DAOException {
		logger.info("ApproverActionsDaoImpl : updateMainReuqest: Started");
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.update(updateRequest);			
			logger.info("ApproverActionsDaoImpl : updateMainReuqest: Ended");
		} catch (Exception e) {
			logger.error("ApproverActionsDaoImpl : updateMainReuqest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return updateRequest;
	}


	/**
	 * This method will get request using request id
	 * 	  
	 * @author e5544344
	 * @methodName getRequestDetailsByRequestId
	 * @param Integer requestId
	 * @return MainRequestBO
	 * 	 */	
	@Override
	public MainRequestBO getRequestDetailsByRequestId(int requestId) throws DAOException {
		logger.info("ApproverActionsDaoImpl  : getRequestDetailsByRequestId: Started");
		MainRequestBO mainRequestBO=null;
		try {
			logger.info("ApproverActionsDaoImpl  : getRequestDetailsByRequestId: Request Id: " + requestId);
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from MainRequestBO where requestId =:requestId";
			Query  query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			mainRequestBO  = (MainRequestBO ) query.uniqueResult();
			logger.info("ApproverActionsDaoImpl  : getRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("ApproverActionsDaoImpl  : getRequestDetailsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return mainRequestBO;
	}

	@Override
	public int getEmployeeIdByUserId(String userId) throws DAOException {
		logger.info("ApproverActionsDaoImpl  : getEmployeeIdByUserId: Started");
		int employeeId=0;
		try{
			logger.info("ApproverActionsDaoImpl  : getRequestDetailsByRequestId: userId Id: " + userId);
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from EmployeeDetailsBO where capcoUserId =:userId";
			Query  query = session.createQuery(sql);  
			query.setParameter("userId", userId); 
			EmployeeDetailsBO employeeDetailsBO=(EmployeeDetailsBO) query.uniqueResult();
			employeeId=employeeDetailsBO.getEmployeeId();
			logger.info("ApproverActionsDaoImpl  : getEmployeeIdByUserId: Ended");
		}catch(Exception e){
			logger.error("ApproverActionsDaoImpl  : getRequestDetailsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}

		return employeeId;
	}

}
