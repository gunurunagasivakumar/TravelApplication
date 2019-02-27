package com.capco.travel.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is the EmployeeDetails View Object class
 * @author e5545565
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class EmployeeDetailsVO extends BaseVO{
	
	private String employeeName;
	
	private String emailId;
	
	private Integer employeeId;
	
	private Long employeeMobileNumber;
	
	private Boolean isApprover;
	
	private Boolean istravelView;	

	private String employeeLocation;
	
	private String projectCode;
	
	private String capcoUserId;
	
	private String grade;
	
	private String approverName;
	
	private String projectName;
	
	private Boolean travelUser;
	
	private String activityCode;
	
	private String typeOfProject;
	
	private String purposeOfTravel;
	
	private String tourType;
	
	/**
	 * @return the approverName
	 */
	public String getApproverName() {
		return approverName;
	}

	/**
	 * @param approverName the approverName to set
	 */
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the employeeName
	 */
	public final String getEmployeeName() {
		return employeeName;
	}
	
	/**
	 * @param employeeName the employeeName to set
	 */
	public final void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	/**
	 * @return the emailId
	 */
	public final String getEmailId() {
		return emailId;
	}
	
	/**
	 * @param emailId the emailId to set
	 */
	public final void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/**
	 * @return the employeeId
	 */
	public final Integer getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * @param employeeId the employeeId to set
	 */
	public final void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	/**
	 * @return the employeeMobileNumber
	 */
	public final Long getEmployeeMobileNumber() {
		return employeeMobileNumber;
	}
	
	/**
	 * @param employeeMobileNumber the employeeMobileNumber to set
	 */
	public final void setEmployeeMobileNumber(Long employeeMobileNumber) {
		this.employeeMobileNumber = employeeMobileNumber;
	}
	
	/**
	 * @return the isApprover
	 */
	public final Boolean getIsApprover() {
		return isApprover;
	}
	
	/**
	 * @param isApprover the isApprover to set
	 */
	public final void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	/**
	 * @return the employeeLocation
	 */
	public final String getEmployeeLocation() {
		return employeeLocation;
	}
	
	/**
	 * @param employeeLocation the employeeLocation to set
	 */
	public final void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}
	
	/**
	 * @return the projectCode
	 */
	public final String getProjectCode() {
		return projectCode;
	}
	
	/**
	 * @param projectCode the projectCode to set
	 */
	public final void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return the capcoUserId
	 */
	public String getCapcoUserId() {
		return capcoUserId;
	}

	/**
	 * @param capcoUserId the capcoUserId to set
	 */
	public void setCapcoUserId(String capcoUserId) {
		this.capcoUserId = capcoUserId;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Boolean getIstravelView() {
		return istravelView;
	}

	public void setIstravelView(Boolean istravelView) {
		this.istravelView = istravelView;
	}

	public Boolean getTravelUser() {
		return travelUser;
	}

	public void setTravelUser(Boolean travelUser) {
		this.travelUser = travelUser;
	}

	/**
	 * @return the activityCode
	 */
	public String getActivityCode() {
		return activityCode;
	}

	/**
	 * @param activityCode the activityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	/**
	 * @return the typeOfProject
	 */
	public String getTypeOfProject() {
		return typeOfProject;
	}

	/**
	 * @param typeOfProject the typeOfProject to set
	 */
	public void setTypeOfProject(String typeOfProject) {
		this.typeOfProject = typeOfProject;
	}

	/**
	 * @return the purposeOfTravel
	 */
	public String getPurposeOfTravel() {
		return purposeOfTravel;
	}

	/**
	 * @param purposeOfTravel the purposeOfTravel to set
	 */
	public void setPurposeOfTravel(String purposeOfTravel) {
		this.purposeOfTravel = purposeOfTravel;
	}

	/**
	 * @return the tourType
	 */
	public String getTourType() {
		return tourType;
	}

	/**
	 * @param tourType the tourType to set
	 */
	public void setTourType(String tourType) {
		this.tourType = tourType;
	}

}
