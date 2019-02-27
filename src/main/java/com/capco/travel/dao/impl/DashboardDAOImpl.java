package com.capco.travel.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.DashboardDAO;
import com.capco.travel.vo.RequestListVO;

@Repository
@Transactional
public class DashboardDAOImpl implements DashboardDAO{
	
	
	private final static Logger logger = Logger.getLogger(DashboardDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<RequestListVO> getAllRequestByUserId(Integer userId,String tableName) throws DAOException{
		logger.info("DashboardDAOImpl : getAllRequestByUserId: Started");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<RequestListVO> requestList=new ArrayList<>();
		try {
			Session session = getSessionFactory().getCurrentSession();
			String sql="select  m.request_id,m.current_status,m.created_on from main_request m inner join "+tableName+" tb on tb.request_id=m.request_id where m.requested_by=:userId and tb.is_active=1 order by m.created_on desc";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("userId", userId);
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
				requestList.add(requestListVO);				
			}
			logger.info("DashboardDAOImpl : getAllRequestByUserId: Ended");
		} catch (Exception e) {
			logger.error("DashboardDAOImpl : getAllRequestByUserId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestList;

	}

}
