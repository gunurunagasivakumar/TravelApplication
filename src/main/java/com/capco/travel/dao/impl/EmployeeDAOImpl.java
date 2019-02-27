package com.capco.travel.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.EmployeeDAO;
import com.capco.travel.model.EmployeeDetailsBO;

/**
 * This class is the DAO implementation for the EmployeeDetails
 * @author e5545565
 *
 */
@Transactional
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private final static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method inserts EmployeeDetailsBO object to the DB table
	 * @param EmployeeDetailsBO
	 */
	public void insertEmployeeDetails(EmployeeDetailsBO empDetails) throws DAOException {
		logger.info("EmployeeDAOImpl : insertEmployeeDetails: Started");
		try {
			logger.info("TravelRequestController : addEmployee : EmployeeId "+empDetails.getEmployeeId()+" Name: "+ empDetails.getEmployeeName());
			Session session = getSessionFactory().getCurrentSession();
			int i = (Integer) session.save(empDetails);
			logger.info("EmployeeDAOImpl : insertEmployeeDetails: int Value : " + i);
			logger.info("EmployeeDAOImpl : insertEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : insertEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method is to get the Employee Details according to the requested EmployeeId(Capco-Id)
	 * @param employee id
	 */
	public EmployeeDetailsBO getEmployeeDetails(int employeeId) throws DAOException {
		logger.info("EmployeeDAOImpl : getEmployeeDetails: Started");
		EmployeeDetailsBO empDetails;
		try {
			logger.info("TravelRequestController : getEmployeeDetails : EmployeeId "+employeeId);
			/*Session session = getSessionFactory().getCurrentSession();
			empDetails = (EmployeeDetailsBO) session.get(EmployeeDetailsBO.class, new Integer(id));*/
			Session session = getSessionFactory().openSession();
			String sql ="from EmployeeDetailsBO where employeeId =:employeeId ";
            Query  query = session.createQuery(sql);  
            query.setParameter("employeeId", employeeId);  
            empDetails = (EmployeeDetailsBO) query.uniqueResult();
			logger.info("EmployeeDAOImpl : getEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : getEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return empDetails;
	}

	/**
	 * This method is to get all the Employee Details
	 * @return List of all the Employee Details
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeDetailsBO> getAllEmployeeDetails() throws DAOException {

		logger.info("EmployeeDAOImpl : getAllEmployeeDetails: Started");
		List<EmployeeDetailsBO> employeeList = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			employeeList = session.createQuery("from EmployeeDetailsBO").list();
			logger.info("EmployeeDAOImpl : getAllEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : getAllEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return employeeList;
	}

	/**
	 * This method is to get all the Employee Details
	 * @return List of all the Employee Details
	 */
	@SuppressWarnings("unchecked")
	public EmployeeDetailsBO getEmployeeByCapcoUserId(String userId) throws DAOException {

		logger.info("EmployeeDAOImpl : getEmployeeByCapcoUserId: Started");
		EmployeeDetailsBO employeeDetailsBO = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Query query = session.createQuery("from EmployeeDetailsBO e where e.capcoUserId =:userId");
			query.setParameter("userId", userId);
			employeeDetailsBO =(EmployeeDetailsBO) query.uniqueResult();
			logger.info("EmployeeDAOImpl : getEmployeeByCapcoUserId: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : getEmployeeByCapcoUserId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return employeeDetailsBO;
	}

	/**
	 * This method is to update employee details if the object contains any changes
	 * @param EmployeeDetailsBO
	 */
	public void updateEmployeeDetails(EmployeeDetailsBO empDetails) throws DAOException {
		logger.info("EmployeeDAOImpl : updateEmployeeDetails: Started");
		try {
			logger.info("TravelRequestController : updateEmployeeDetails : EmployeeId "+empDetails.getEmployeeId());
			Session session = getSessionFactory().getCurrentSession();
			session.saveOrUpdate(empDetails);
			logger.info("EmployeeDAOImpl : updateEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : updateEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}

	/**
	 * This method is to delete the EmployeeDetails according to the employeeId
	 * @param id
	 */
	public void deleteEmployeeDetails(int id) throws DAOException {
		logger.info("EmployeeDAOImpl : deleteEmployeeDetails: Started");
		try {
			logger.info("TravelRequestController : updateEmployeeDetails : EmployeeId "+id);
			Session session = getSessionFactory().getCurrentSession();
			EmployeeDetailsBO empDetails = (EmployeeDetailsBO) session.load(EmployeeDetailsBO.class, new Integer(id));
			if (null != empDetails) {
				session.delete(empDetails);
			}
			logger.info("EmployeeDAOImpl : deleteEmployeeDetails: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : deleteEmployeeDetails: Exception Caught: " + e);
			throw new DAOException(e);
		}
	}
	public boolean tokenValidation(int tokenId, String capcoId) throws TravelServiceException {
		// TODO Auto-generated method stub
	
			logger.info("TravelRequestController : tokenValidation : tokenId :"+tokenId+": capcoId :"+capcoId);
			Session session = getSessionFactory().openSession();
			EmployeeDetailsBO empDetails = (EmployeeDetailsBO) session.load(EmployeeDetailsBO.class, new String(capcoId));
			System.out.println(empDetails.getTokenId());
			if (null != empDetails) {
				if(empDetails.getTokenId() == tokenId) {
				return true;}
			}		
			logger.info("EmployeeDAOImpl : tokenValidation: Ended");
			return false;
		
	}

	/**
	 * This method is to update tokenId to zero after logout  
	 * @param capcoUserId
	 */
	@Override
	public void updateTokenId(String capcoUserId) throws DAOException
	{
		logger.info("EmployeeDAOImpl : updateTokenId: Started");
		
		try {
			logger.info("EmployeeDAOImpl : updateTokenId: Updated By: " + capcoUserId+" started");
			Session session = getSessionFactory().getCurrentSession();
			String sql ="update EmployeeDetailsBO set tokenId=0 where capcoUserId=:capcoUserId";
			Query query = session.createQuery(sql);  
			query.setParameter("capcoUserId", capcoUserId);
			query.executeUpdate();
			logger.info("EmployeeDAOImpl : updateTokenId: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : updateTokenId: Exception Caught: " + e);
			throw new DAOException(e);
		}
		
		
	}
	
	/**
	 * This method is to get the Employee Email according to the requested EmployeeId
	 * @author e5544354
	 * @methodName getEmployeeEmail
	 * @param int employee id
	 * @return String
	 * @throws DAOException
	 */
	public Map<Integer,EmployeeDetailsBO> getEmployeeEmail(List<Integer> employeeList) throws DAOException {
		logger.info("EmployeeDAOImpl : getEmployeeEmail: Started");
		Map<Integer, EmployeeDetailsBO> employeeEmail = new HashMap<>();
		try {
			logger.info("EmployeeDAOImpl : getEmployeeEmail : getting emails");
			Session session = getSessionFactory().getCurrentSession();
			String sql ="from EmployeeDetailsBO where employeeId IN (:employeeList)";
			Query query = session.createQuery(sql);  
			query.setParameterList("employeeList", employeeList);  
			List<EmployeeDetailsBO> list  = query.list();
			for(EmployeeDetailsBO employeeDetailsBO: list) {
				//employeeEmail.put((int)row[0], row[1].toString());
				employeeEmail.put(employeeDetailsBO.getEmployeeId(), employeeDetailsBO);
			}
			logger.info("EmployeeDAOImpl : getEmployeeEmail: Ended");
		} catch (Exception e) {
			logger.error("EmployeeDAOImpl : getEmployeeEmail: Exception Caught: " + e);
			throw new DAOException(e);
		}
		return employeeEmail;
	}
}
