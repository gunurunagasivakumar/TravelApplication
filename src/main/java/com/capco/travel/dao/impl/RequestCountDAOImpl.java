package com.capco.travel.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.dao.RequestCountDAO;
import com.capco.travel.model.RequestCountBO;

@Repository
@Transactional
public class RequestCountDAOImpl implements RequestCountDAO{
	private static final Logger logger = Logger.getLogger(RequestCountDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * This method will get all request count for approver id
	 * 	  
	 * @author e5544344
	 * @methodName getRequestCount
	 * @param Integer employeeId
	 * @return RequestCountBO
	 * 	 */	
	@Override
	public RequestCountBO getRequestCount(Integer employeeId) throws DAOException{
		logger.info("RequestCountDAOImpl : getRequestCount: Started");
		RequestCountBO requestCountBO=new RequestCountBO();
		try {
			Session session = getSessionFactory().getCurrentSession();	
			SQLQuery query = session.createSQLQuery("select getCabPending(:employeeid) as cabPending,getCabApproved(:employeeid) as cabApproved,getAccomodationPending(:employeeid) as accomodationPending,getAccomodationApproved(:employeeid) as accomodationApproved,getFlightPending(:employeeid) as flightPending,getFlightApproved(:employeeid) as flightApproved,getForexPending(:employeeid) as forexPending,getForexApproved(:employeeid) as forexApproved,getVisaPending(:employeeid) as visaPending,getVisaApproved(:employeeid) as visaApproved;");
			query.setParameter("employeeid", employeeId);
			List<Object[]> rows = query.list();
			for(Object[] row : rows){
				requestCountBO.setCabPending(Integer.parseInt(row[0].toString()));
				requestCountBO.setCabApproved(Integer.parseInt(row[1].toString()));
				requestCountBO.setAccomodationPending(Integer.parseInt(row[2].toString()));
				requestCountBO.setAccomodationApproved(Integer.parseInt(row[3].toString()));
				requestCountBO.setFlightPending(Integer.parseInt(row[4].toString()));
				requestCountBO.setFlightApproved(Integer.parseInt(row[5].toString()));
				requestCountBO.setForexPending(Integer.parseInt(row[6].toString()));
				requestCountBO.setForexApproved(Integer.parseInt(row[7].toString()));
				requestCountBO.setVisaPending(Integer.parseInt(row[8].toString()));
				requestCountBO.setVisaApproved(Integer.parseInt(row[9].toString()));
				logger.info("ApproverActionsDaoImpl : getAllRequestByApproverId: Ended");
			}
			logger.info("RequestCountDAOImpl : getRequestCount: requestCount :"+requestCountBO);
			logger.info("RequestCountDAOImpl : getRequestCount: Ended");
			
		} catch (Exception e) {
			logger.error("RequestCountDAOImpl : getRequestCount:  Exception Caught: " + e);
			throw new DAOException(e);
		}
		return requestCountBO;
		
	}

}
