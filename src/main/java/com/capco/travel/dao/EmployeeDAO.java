package com.capco.travel.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

import com.capco.travel.custom.exception.TravelException.DAOException;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.EmployeeDetailsBO;

public interface EmployeeDAO {

	public SessionFactory getSessionFactory();
	public void setSessionFactory(SessionFactory sessionFactory);
	public void insertEmployeeDetails(EmployeeDetailsBO empDetails) throws DAOException;
	public EmployeeDetailsBO getEmployeeDetails(int i) throws DAOException;
	public void updateEmployeeDetails(EmployeeDetailsBO empDetails) throws DAOException;
	public void deleteEmployeeDetails(int id) throws DAOException;
	public  List<EmployeeDetailsBO> getAllEmployeeDetails() throws DAOException;
	public EmployeeDetailsBO getEmployeeByCapcoUserId(String userId) throws DAOException;
	public boolean tokenValidation(int tokenId, String capcoUserId) throws TravelServiceException;
	public void updateTokenId(String capcoUserId)throws DAOException;
	public Map<Integer,EmployeeDetailsBO> getEmployeeEmail(List<Integer> employeeList) throws DAOException;
}