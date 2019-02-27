package com.capco.travel.beans;

import java.util.List;

import com.capco.travel.model.ActionDetailsBO;
import com.capco.travel.model.EmployeeDetailsBO;
import com.capco.travel.model.MainRequestBO;

public class TravelRequestWrapper {
	
	public List<MainRequestBO> mainRequestBO;
	public List<EmployeeDetailsBO> employeeDetailsBO;
	public List<ActionDetailsBO> actionDetailsBO;
	/**
	 * @return the mainRequestBO
	 */
	public List<MainRequestBO> getMainRequestBO() {
		return mainRequestBO;
	}
	/**
	 * @param mainRequestBO the mainRequestBO to set
	 */
	public void setMainRequestBO(List<MainRequestBO> mainRequestBO) {
		this.mainRequestBO = mainRequestBO;
	}
	/**
	 * @return the employeeDetailsBO
	 */
	public List<EmployeeDetailsBO> getEmployeeDetailsBO() {
		return employeeDetailsBO;
	}
	/**
	 * @param employeeDetailsBO the employeeDetailsBO to set
	 */
	public void setEmployeeDetailsBO(List<EmployeeDetailsBO> employeeDetailsBO) {
		this.employeeDetailsBO = employeeDetailsBO;
	}
	/**
	 * @return the actionDetailsBO
	 */
	public List<ActionDetailsBO> getActionDetailsBO() {
		return actionDetailsBO;
	}
	/**
	 * @param actionDetailsBO the actionDetailsBO to set
	 */
	public void setActionDetailsBO(List<ActionDetailsBO> actionDetailsBO) {
		this.actionDetailsBO = actionDetailsBO;
	}
	
	
}
