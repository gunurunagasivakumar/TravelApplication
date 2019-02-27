package com.capco.travel.beans;

public class EmployeeRegistrationTravel {

	String name;
	String projectName;
	String employeeNumber;
	String employeeTravelDestination;
	String employeeEmailID;
	
	public String getEmployeeEmailID() {
		return employeeEmailID;
	}
	public void setEmployeeEmailID(String employeeEmailID) {
		this.employeeEmailID = employeeEmailID;
	}
	boolean employeeisosInfo;
	
	public String getName() {
		return name;
	}
	public boolean isEmployeeisosInfo() {
		return employeeisosInfo;
	}
	public void setEmployeeisosInfo(boolean employeeisosInfo) {
		this.employeeisosInfo = employeeisosInfo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeTravelDestination() {
		return employeeTravelDestination;
	}
	public void setEmployeeTravelDestination(String employeeTravelDestination) {
		this.employeeTravelDestination = employeeTravelDestination;
	}
}
