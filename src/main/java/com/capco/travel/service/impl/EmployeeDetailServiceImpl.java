/**
 * 
 */
package com.capco.travel.service.impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.dao.EmployeeDAO;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.service.EmployeeDetailService;
import com.capco.travel.util.TravelBeanUtils;
import com.capco.travel.vo.EmployeeDetailsVO;


@Service
@Transactional
public class EmployeeDetailServiceImpl implements EmployeeDetailService {
	private static final Logger logger = Logger.getLogger(EmployeeDetailServiceImpl.class);
	@Autowired
	EmployeeDAO employeeDAO;
	
	/**
	 * This method to add Employees Details into database*
	 * @methodName insertEmployeeDetails
	 * @param com.capco.travel.model.EmployeeDetailsBO
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void insertEmployeeDetails(EmployeeDetailsVO empDetails) throws TravelServiceException {
		logger.info("PersonalDetailServiceImpl : insertEmployeeDetails: Started");
		try {
			 EmployeeDetailsBO empDetailsBo = new EmployeeDetailsBO();
			 // Validate fields
			 if(empDetails.getEmployeeId() != 0) {
				 empDetailsBo.setEmployeeId(empDetails.getEmployeeId());
			 }
			 
			empDetailsBo = TravelBeanUtils.EmployeeDetailsVO2EmployeeDetailsBO(empDetails);
			employeeDAO.insertEmployeeDetails(empDetailsBo);
			logger.info("PersonalDetailServiceImpl : insertEmployeeDetails: Ended");
		}catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : insertEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}

	@Override
	public EmployeeDetailsBO getEmployeeDetails(int id)throws TravelServiceException{
		EmployeeDetailsBO employeesDetails =null;
		logger.info("PersonalDetailServiceImpl : getEmployeeDetails: Started");
		try {
			employeesDetails= employeeDAO.getEmployeeDetails(id);
			logger.info("PersonalDetailServiceImpl : getEmployeeDetails: Ended");
		}catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : getEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
		
		return employeesDetails;
	}
	
	/**
	 * this method will return employee details based on capco User Id
	 */
	@Override
	public EmployeeDetailsBO getEmployeeDetailsByCapcoUserId(String userId) throws TravelServiceException {
		EmployeeDetailsBO employeesDetails =null;
		logger.info("PersonalDetailServiceImpl : getEmployeeDetailsByCapcoUserId: Started");
		try {
			employeesDetails= employeeDAO.getEmployeeByCapcoUserId(userId);
			logger.info("PersonalDetailServiceImpl : getEmployeeDetailsByCapcoUserId: Ended");
		}catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : getEmployeeDetailsByCapcoUserId: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
		
		return employeesDetails;
	}
	
	@Override
	public void updateEmployeeDetails(EmployeeDetailsBO empDetails)throws TravelServiceException{
		logger.info("PersonalDetailServiceImpl : updateEmployeeDetails: Started");
		try {
			employeeDAO.updateEmployeeDetails(empDetails);
			logger.info("PersonalDetailServiceImpl : updateEmployeeDetails: Ended");
		}catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : updateEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}
	
	@Override
	public void deleteEmployeeDetails(int id) throws TravelServiceException{
		logger.info("PersonalDetailServiceImpl : deleteEmployeeDetails: Started");
		try {
			employeeDAO.deleteEmployeeDetails(id);
			logger.info("PersonalDetailServiceImpl : deleteEmployeeDetails: Ended");
		}catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : deleteEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
	}

	public List<EmployeeDetailsBO> getAllEmployeeDetails() throws TravelServiceException{
		logger.info("PersonalDetailServiceImpl : deleteEmployeeDetails: Started");
		List<EmployeeDetailsBO> employeeDetailsBOs=null;
		try {
			employeeDetailsBOs = employeeDAO.getAllEmployeeDetails();
			logger.info("PersonalDetailServiceImpl : deleteEmployeeDetails: Ended");
		}catch (DAOException ex) {
			logger.error("PersonalDetailServiceImpl : deleteEmployeeDetails: DAOException Caught: "+ex);
			throw new TravelServiceException(ex);
		}
		return employeeDetailsBOs;
	}
	
	/**
	 * This method to add Employees Details into database*
	 * @methodName updateTokenId
	 * @param int capcoUserId
	 * @throws com.capco.travel.custom.exception.TravelException.TravelServiceException
	 */
	@Override
	public void updateTokenId(String capcoUserId) throws TravelServiceException {
		logger.info("EmployeeDetailServiceImpl : updateTokenId: Started");
		try {
			employeeDAO.updateTokenId(capcoUserId);
			logger.info("EmployeeDetailServiceImpl : updateTokenId: Ended");
		} catch (DAOException e) {
			logger.error("PersonalDetailServiceImpl : updateTokenId: DAOException Caught: "+e);
			throw new TravelServiceException(e);
		}
	}
}
