package com.capco.travel.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.beans.TravelRequestWrapper;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.MainRequestDAO;
import com.capco.travel.model.ActionDetailsBO;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.FinViewBO;
import com.capco.travel.model.HcViewBO;
import com.capco.travel.model.MainRequestBO;
import com.capco.travel.model.RequestHistoryLogBO;
import com.capco.travel.vo.RequestListVO;

/**
 * This class is the DAO implementation for the MainRequest
 * @author e5542274
 *
 */
@Repository
@Transactional
public class MainRequestDAOImpl implements MainRequestDAO {
	private final static Logger logger = Logger.getLogger(MainRequestDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	public Integer insertMainReuqest(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : insertMainReuqest: Started");
		Integer requestId = 0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			requestId = (Integer)session.save(mainRequestBO);	
			logger.info("MainRequestDAOImpl : insertMainReuqest: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : insertMainReuqest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestId;
	}

	/**
	 * 
	 */
	public MainRequestBO updateMainRequest(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : updateMainReuqest: Started");
		Integer requestId = 0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			session.update(mainRequestBO);			
			logger.info("MainRequestDAOImpl : updateMainReuqest: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : updateMainReuqest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return mainRequestBO;
	}

	/**
	 * 
	 */
	public MainRequestBO getRequestDetailsByRequestId(int requestId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Started");
		MainRequestBO mainRequestBO  = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Request Id: " + requestId);
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from MainRequestBO where requestId =:requestId";
			Query  query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			mainRequestBO = (MainRequestBO) query.uniqueResult();
			if(mainRequestBO == null) {
				return null;
			}
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return mainRequestBO;
	}
	/*
	 * By TravelUserId
	*/
/*	public MainRequestBO getRequestDetailsByTravelId(int userId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Started");
		MainRequestBO mainRequestBO  = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Request Id: " + userId);
			Session session = getSessionFactory().getCurrentSession();
			String sql =select * from EmployeeDetailsBO e, RequestHistoryLogBO r where e.employeeId = r.updatedBy and r.requestId = :requestId order by r.createdOn desc";
			Query  query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			mainRequestBO = (MainRequestBO) query.uniqueResult();
			if(mainRequestBO == null) {
				return null;
			}
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return mainRequestBO;
	}
*/
	public boolean deleteRequestByRequestId(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Started");
		boolean requestDeleted  = false;
		try {
			logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Request Id: " + mainRequestBO.getRequestId());
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(mainRequestBO);
			requestDeleted = true;
			logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Exception Caught: " + e);
			requestDeleted = false;
			throw new DAOException(e);
		}
		return requestDeleted;
	}

	/**
	 * this method will add entry in request_history table
	 * @author e5544354
	 * @methodName insertRequestHistoryLog
	 * @param RequestHistoryLogBO
	 * @return Integer
	 * @throws DAOException
	 */
	@Override
	public Integer insertRequestHistoryLog(RequestHistoryLogBO requestHistoryLogBO) throws DAOException {
		logger.info("MainRequestDAOImpl : insertRequestHistoryLog: Started");
		Integer i =0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			i =(Integer) session.save(requestHistoryLogBO);
			logger.info("MainRequestDAOImpl : insertRequestHistoryLog: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : insertRequestHistoryLog: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return i;
	}

/*	*//**
	 * this method will get all entries from request_history table
	 * @author e5544354
	 * @methodName getAllRequestHistoryLogs
	 * @return Integer
	 * @throws DAOException
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<RequestHistoryLogBO> getAllRequestHistoryLogs() throws DAOException {
		logger.info("MainRequestDAOImpl : getAllRequestHistoryLogs: Started");
		List<RequestHistoryLogBO> requestHistoryList = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			requestHistoryList = session.createQuery("from RequestHistoryLogBO").list();
			logger.info("MainRequestDAOImpl : getAllRequestHistoryLogs: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getAllRequestHistoryLogs: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestHistoryList;
	}

	*//**
	 * this method will get entries from request_history table by request_id
	 * @author e5544354
	 * @methodName getRequestHistoryLogsByRequestId
	 * @param int
	 * @return Integer
	 * @throws DAOException
	 *//*
	@SuppressWarnings("unchecked")
	@Override
	public List<RequestHistoryLogBO> getRequestHistoryLogsByRequestId(int requestId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Started");
		List<RequestHistoryLogBO> requestHistoryList = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Request Id: " + requestId);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from RequestHistoryLogBO where requestId =:requestId";
			Query query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			requestHistoryList = query.list();
			
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestHistoryLogsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestHistoryList;
	}
	


	*//**
	 * this method will get entries from request_history table by updated_by
	 * @author e5544354
	 * @methodName getRequestHistoryLogsByUpdatedBy
	 * @param int
	 * @return Integer
	 * @throws DAOException
	 *//*
	@Override
	public List<RequestHistoryLogBO> getRequestHistoryLogsByUpdatedBy(int updatedBy) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Started");
		List<RequestHistoryLogBO> requestHistoryList = null;
		try {
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Updated By: " + updatedBy);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from RequestHistoryLogBO where updatedBy =:updatedBy";
			Query query = session.createQuery(sql);  
			query.setParameter("updatedBy", updatedBy);  
			requestHistoryList = query.list();
			
			logger.info("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestHistoryLogsByUpdatedBy: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestHistoryList;
	}*/
	
	/**
	 * this method will give comments from the request_history table
	 * @author e5544354
	 * @methodName getRequestHistoryCommentsByRequestId
	 * @param int
	 * @return List<Object[]>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRequestHistoryCommentsByRequestId(int requestId) throws DAOException {
		logger.info("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Started");
		List<Object[]> list = new ArrayList<>();
		try {
			logger.info("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Request Id: " + requestId);
			
			Session session = getSessionFactory().getCurrentSession();
			String sql ="select r.requestId, r.createdOn, r.status, r.remarks,  r.updatedBy, e.employeeName, e.purposeOfTravel, e.activityCode, e.typeOfProject from EmployeeDetailsBO e, RequestHistoryLogBO r where e.employeeId = r.updatedBy and r.requestId = :requestId order by r.createdOn desc";
			Query query = session.createQuery(sql);  
			query.setParameter("requestId", requestId);  
			list  = query.list();
			
			logger.info("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestHistoryCommentsByRequestId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return list;
	}
	
	/**
	 * This method will get user all requests by requested Id (user id)
	 * @author e5545730
	 * @methodName getRequestDetailsByRequestedId
	 * @param MainRequestBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<RequestListVO> getRequestDetailsByUserId(int requestedBy) throws DAOException {
		
	    logger.info("MainRequestDAOImpl : getRequestDetailsByRequestedId: Started");
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<RequestListVO> requestDetailsList = new ArrayList<RequestListVO>();
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestedId: Request Id: " + requestedBy);
			Session session = getSessionFactory().getCurrentSession();
			String sql ="select m.requestId,m.currentStatus,m.createdOn from MainRequestBO m where m.requestedBy =:requestedBy order by m.createdOn desc";
			Query query = session.createQuery(sql);  
			query.setParameter("requestedBy", requestedBy);  
			//requestDetailsList = query.list();
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
				requestDetailsList.add(requestListVO);
			}
			logger.info("MainRequestDAOImpl : getRequestDetailsByRequestedId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByRequestedId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestDetailsList;
	}

	/**
	 * This method will get five approved user requests by requestedBy Id (user id)
	 * @author e5545730
	 * @methodName getLatestApprovedRequest
	 * @param MainRequestBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
	public List<MainRequestBO> getLatestApprovedRequest(int requestedBy) throws DAOException {
		logger.info("MainRequestDAOImpl : getLatestApprovedRequest: Started");
		List<MainRequestBO> requestDetailsList = null;
		try {
			logger.info("MainRequestDAOImpl : getLatestApprovedRequest: Request Id: " + requestedBy);	
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from MainRequestBO where requestedBy =:requestedBy and currentStatus ='Approved' order by createdOn desc";
			Query query = session.createQuery(sql);  
			query.setParameter("requestedBy", requestedBy); 
			query.setMaxResults(7);
			requestDetailsList = query.list();
			logger.info("MainRequestDAOImpl : getLatestApprovedRequest: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getLatestApprovedRequest: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestDetailsList;
	}
	
	@Override
	public boolean deleteRequestByRequestIdTest(MainRequestBO mainRequestBO) throws DAOException {
		logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Started");
		boolean requestDeleted  = false;
		try {
			logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Request Id: " + mainRequestBO.getRequestId());
			Session session = getSessionFactory().getCurrentSession();
			session.delete(mainRequestBO);
			requestDeleted = true;
			logger.info("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : deleteRequestDetailsByRequestId: Exception Caught: " + e);
			requestDeleted = false;
			throw new DAOException(e);
		}
		return requestDeleted;
	}
	
	/**
	 * This method will get user all requests by user id
	 * @author 12219
	 * @methodName getRequestDetailsByTravelStatus
	 * @param MainRequestBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
		public TravelRequestWrapper getRequestDetailsByTravelStatus() throws DAOException {
		
	    logger.info("MainRequestDAOImpl : getRequestDetailsByTravelStatus: Started");
	    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   // List<TravelRequestWrapper> travelRequestWrapper = new ArrayList<TravelRequestWrapper>();
	    TravelRequestWrapper travelRequestWrapper = new TravelRequestWrapper();
		List<MainRequestBO> requestDetailsList = new ArrayList<MainRequestBO>();
		//List<HcViewBO> hcrequestDetailsList = new ArrayList<HcViewBO>();
		//List<FinViewBO> finrequestDetailsList = new ArrayList<FinViewBO>();
		List<EmployeeDetailsBO> emprequestDetailsList = new ArrayList<EmployeeDetailsBO>();
		List<ActionDetailsBO> actionDetailsList = new ArrayList<ActionDetailsBO>();
		
		
		
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByTravelStatus: Request Id: " );
			Session session = getSessionFactory().getCurrentSession();
			String mainTablesql = "select m.requestId,m.requestedBy,m.currentStatus,m.createdOn from MainRequestBO m";
			/*String sql = "SELECT h.requestId as RequestID, e.employeeName as requestedBy, m.currentStatus,m.createdOn,h.status as HCTableStatus, f.status as FINTableStatus" + 
					"        FROM HcViewBO h, FinViewBO f, MainRequestBO m, EmployeeDetailsBO e "+ 
					"            WHERE "+
					"            m.requestId=h.requestId" + "            AND m.requestId =f.requestId" + 
					"             AND m.currentStatus = 'Approved'" + 
					"              AND h.status = 'pending with Travel'" + 
					"             AND h.actionStatus = 'Issued to HCOPS'" + 
					"             AND f.status = 'pending with Travel'" + 
					"             AND f.actionStatus = 'Issued to FIN'" + 
					"             and m.requestedBy = e.employeeId";*/
			String sql = "SELECT m.requestId, m.requestedBy, m.currentStatus,m.createdOn FROM MainRequestBO m WHERE m.currentStatus in('Approved','On-Hold','Completed','Issued by HC,Finance')";
			//String sql = "SELECT m.requestId, m.requestedBy, m.currentStatus,m.createdOn FROM MainRequestBO m WHERE m.currentStatus in('Approved','Issued by HC','Issued from Finance','On-Hold')";
			//,'Issued by HC','Issued by Finance'
					
					
					/*"SELECT m.requestId as RequestID, m.requestedBy, m.currentStatus,m.createdOn,ad.actionId as actiondetails,ad.requestStatus as ActionStatus,ad.pendingStatus as PendingStatus " +
					 "FROM MainRequestBO m, ActionDetailsBO ad " +
					 "WHERE "+
					 "m.requestId = ad.requestId and m.currentStatus in ('Approved','Issued by HC','Issued from Finance') and ad.pendingStatus in('Pending with Travel','On-Hold','Completed') AND ad.actionStatus in('Issued by HC','Issued from Finance')";
					 */
					
					//"SELECT m.request_id as RequestID, m.requested_by, m.current_status,m.created_on FROM main_request m WHERE m.current_status in('Approved','Issued by HC','Issued from Finance')";
		                
					
			Query query = session.createQuery(sql); 
			logger.info(query.toString());
			List<Object[]> rows = query.list();
			if(rows.isEmpty())
			{
				Query mainQuery = session.createQuery(mainTablesql);
				logger.info(mainQuery.toString());
				List<Object[]> mainTablerows = mainQuery.list();
				
				for(Object[] row : mainTablerows){
					MainRequestBO mainListBO=new MainRequestBO();
					
					if(row[0]!=null){
						mainListBO.setRequestId(Integer.parseInt(row[0].toString()));
					}
					if(row[1]!=null){
						mainListBO.setRequestedBy(Integer.parseInt(row[1].toString()));
					}
					
					if(row[2]!=null){
						mainListBO.setCurrentStatus(row[2].toString());
					}
					if(row[3]!=null){
						mainListBO.setCreatedOn(sf.parse(row[3].toString()));
					}
					
					requestDetailsList.add(mainListBO);
				}
				travelRequestWrapper.setMainRequestBO(requestDetailsList);
			}
			else
			{
			for(Object[] row : rows){
				MainRequestBO mainListBO=new MainRequestBO();
				//HcViewBO hcBO = new HcViewBO();
				//FinViewBO finBO = new FinViewBO();
				EmployeeDetailsBO employeeBO = new EmployeeDetailsBO();
				ActionDetailsBO actionBO = new ActionDetailsBO();
				
				
				if(row[0]!=null){
					logger.info(row[0].toString());
					mainListBO.setRequestId(Integer.parseInt(row[0].toString()));
					//hcBO.setRequestId(Integer.parseInt(row[0].toString()));
				}
				if(row[1]!=null){
					mainListBO.setRequestedBy(Integer.parseInt(row[1].toString()));
					employeeBO.setEmployeeName((row[1].toString()));
				}
				if(row[2]!=null){
					mainListBO.setCurrentStatus(row[2].toString());
				}
				if(row[3]!=null){
					mainListBO.setCreatedOn(sf.parse(row[3].toString()));
				}
			/*	if(row[4]!=null){
					actionBO.setActionId(row[4].toString());
				}
				if(row[5]!=null){
					actionBO.setActionStatus(row[5].toString());
				}
				if(row[6]!=null){
					actionBO.setPendingStatus(row[6].toString());
				}*/
				requestDetailsList.add(mainListBO);
				//hcrequestDetailsList.add(hcBO);
				//finrequestDetailsList.add(finBO);
				emprequestDetailsList.add(employeeBO);
				//actionDetailsList.add(actionBO);
				
				
				
			}
			
			travelRequestWrapper.setMainRequestBO(requestDetailsList);
			//travelRequestWrapper.setActionDetailsBO(actionDetailsList);
			travelRequestWrapper.setEmployeeDetailsBO(emprequestDetailsList);
			/*logger.info(travelRequestWrapper.getMainRequestBO().getRequestId());
			logger.info(travelRequestWrapper.getActionDetailsBO().getActionStatus());
			logger.info(travelRequestWrapper.getMainRequestBO().getCreatedOn());*/
		}
			logger.info("MainRequestDAOImpl : getRequestDetailsByTravelStatus: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByTravelStatus: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return travelRequestWrapper;
	}

	/**
	 * This method will get user all requests by user id
	 * @author 12219
	 * @methodName getRequestDetailsForHcView
	 * @param HcViewBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
		public List<HcViewBO> getRequestDetailsByHcStatus() throws DAOException {
		
	    logger.info("MainRequestDAOImpl : getRequestDetailsByHcStatus: Started");
	    List<HcViewBO> hcDetailsList = new ArrayList<HcViewBO>();
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByHcStatus: Request Id: " );
			Session session = getSessionFactory().getCurrentSession();
			//String sql = "select h.requestId,h.actionId,h.actionStatus,h.pendingItems from HcViewBO h INNER JOIN MainRequestBO m ON m.requestId = h.requestId"; 
			String sql = "select h.requestId,h.actionId,h.actionStatus,h.pendingItems from HcViewBO h, MainRequestBO m where m.requestId = h.requestId";
			Query query = session.createQuery(sql); 
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				HcViewBO hcListBO=new HcViewBO();
				if(row[0]!=null){
					hcListBO.setRequestId(Integer.parseInt(row[0].toString()));
				}
				if(row[1]!=null){
					hcListBO.setActionId(row[1].toString());
				}
				if(row[2]!=null){
					hcListBO.setActionStatus(row[2].toString());
				}	
				if(row[3]!=null){
					hcListBO.setPendingItems(row[3].toString());
				}	
				hcDetailsList.add(hcListBO);
			}
			logger.info("MainRequestDAOImpl : getRequestDetailsByTravelStatus: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByTravelStatus: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return hcDetailsList;
	}
	

	/**
	 * This method will get user all requests by user id
	 * @author 12219
	 * @methodName getRequestDetailsOfFiance
	 * @param HcViewBO
	 * @return list
	 * @throws DAOException
	 */
	@Override
		public List<FinViewBO> getRequestDetailsByFinance() throws DAOException {
		
	    logger.info("MainRequestDAOImpl : getRequestDetailsByFinance: Started");
	    List<FinViewBO> finDetailsList = new ArrayList<FinViewBO>();
		try {
			logger.info("MainRequestDAOImpl : getRequestDetailsByFinance: Request Id: " );
			Session session = getSessionFactory().getCurrentSession();
			//String sql = "select f.requestId,f.actionId,f.actionStatus,f.pendingItems from FinViewBO f INNER JOIN MainRequestBO m ON m.requestId = f.requestId"; 
			String sql = "select f.requestId,f.actionId,f.actionStatus,f.pendingItems,f.status from FinViewBO f ,MainRequestBO m where m.requestId = f.requestId";
			Query query = session.createQuery(sql); 
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				FinViewBO finListBO=new FinViewBO();
				if(row[0]!=null){
					finListBO.setRequestId(Integer.parseInt(row[0].toString()));
				}
				if(row[1]!=null){
					finListBO.setActionId(row[1].toString());
				}
				if(row[2]!=null){
					finListBO.setActionStatus(row[2].toString());
				}	
				if(row[3]!=null){
					finListBO.setPendingItems(row[3].toString());
				}
				if(row[4]!=null){
					finListBO.setStatus(row[4].toString());
				}	
				finDetailsList.add(finListBO);
			}
			logger.info("MainRequestDAOImpl : getRequestDetailsByFinance: Ended");
		} catch (Exception e) {
			logger.error("MainRequestDAOImpl : getRequestDetailsByFinance: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return finDetailsList;
	}
	
	
	
}

