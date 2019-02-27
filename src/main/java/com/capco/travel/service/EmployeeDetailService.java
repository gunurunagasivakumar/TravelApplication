/**
 * 
 */
package com.capco.travel.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.capco.travel.custom.exception.TravelException.TravelServiceException;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.vo.EmployeeDetailsVO;

@Service()
public interface EmployeeDetailService {
	public void insertEmployeeDetails(EmployeeDetailsVO empDetails) throws TravelServiceException;
	public EmployeeDetailsBO getEmployeeDetails(int id)throws TravelServiceException;
	public void updateEmployeeDetails(EmployeeDetailsBO empDetails)throws TravelServiceException;
	public void deleteEmployeeDetails(int id)throws TravelServiceException;
	public List<EmployeeDetailsBO> getAllEmployeeDetails()throws TravelServiceException;
	public EmployeeDetailsBO getEmployeeDetailsByCapcoUserId(String userId)throws TravelServiceException;
	public void updateTokenId(String capcoUserId)throws TravelServiceException;
}
