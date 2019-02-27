package com.capco.travel.beans;

public class EmployeeRegistration {
	
	String name;
	String employeeNumber;
	String projectName;
	String travelDestination;
	String employeeEmailID;
	
	public String getEmployeeEmailID() {
		return employeeEmailID;
	}

	public void setEmployeeEmailID(String employeeEmailID) {
		this.employeeEmailID = employeeEmailID;
	}

	boolean isosInfo;
	
	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public boolean isIsosInfo() {
		return isosInfo;
	}

	public void setIsosInfo(boolean isosInfo) {
		this.isosInfo = isosInfo;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getTravelDestination() {
		return travelDestination;
	}

	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
	}

	public String getName() {
	return name;
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
	

}
