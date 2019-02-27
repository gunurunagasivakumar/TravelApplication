package com.capco.travel.vo;

/**
 * @author e5544344
 * This class is the MainRequestTable View object class 
 *
 */

public class MainRequestBaseVO extends BaseVO{
	
	private int requestId;
	
	private Integer requestedBy;
	
	private String requestedFor;

	private Integer approverId;
	
	private String actionOnRequest;
	
	private String currentStatus;
	
	private String remark;
	
	private Boolean billable;	
	
	private String requestType;
	
	private String projectCode;
	
	private String projectName;
	
	private String tourType;
	
	private String purposeOfTravel;
	
	private String activityCode;
	
	private String typeOfProject;
	
	private String isos;
	
	private String insurance;
	
	private String employeeLocation;
	
	private String mobileNumber;
	
	
	
	

	/**
	 * @return the billable
	 */
	public Boolean getBillable() {
		return billable;
	}

	/**
	 * @param billable the billable to set
	 */
	public void setBillable(Boolean billable) {
		this.billable = billable;
	}

	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the requestedBy
	 */
	public Integer getRequestedBy() {
		return requestedBy;
	}

	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * @return the requestedFor
	 */
	public String getRequestedFor() {
		return requestedFor;
	}

	/**
	 * @param requestedFor the requestedFor to set
	 */
	public void setRequestedFor(String requestedFor) {
		this.requestedFor = requestedFor;
	}

	/**
	 * @return the approverId
	 */
	public Integer getApproverId() {
		return approverId;
	}

	/**
	 * @param approverId the approverId to set
	 */
	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	/**
	 * @return the actionOnRequest
	 */
	public String getActionOnRequest() {
		return actionOnRequest;
	}

	/**
	 * @param actionOnRequest the actionOnRequest to set
	 */
	public void setActionOnRequest(String actionOnRequest) {
		this.actionOnRequest = actionOnRequest;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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
	 * @return the isos
	 */
	public String getIsos() {
		return isos;
	}

	/**
	 * @param isos the isos to set
	 */
	public void setIsos(String isos) {
		this.isos = isos;
	}

	/**
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MainRequestBaseVO [requestId=" + requestId + ", requestedBy=" + requestedBy + ", requestedFor="
				+ requestedFor + ", approverId=" + approverId + ", actionOnRequest=" + actionOnRequest
				+ ", currentStatus=" + currentStatus+",employeeLocation=" + employeeLocation+",mobileNumber=" + mobileNumber + ", remark=" + remark + ", billable=" + billable
				+", purposeOfTravel=" + purposeOfTravel +", typeOfProject=" + typeOfProject +","
						+ " activityCode=" + activityCode+", isos=" + isos +", insurance=" + insurance +"]";
	}

	/**
	 * @return the employeeLocation
	 */
	public String getEmployeeLocation() {
		return employeeLocation;
	}

	/**
	 * @param employeeLocation the employeeLocation to set
	 */
	public void setEmployeeLocation(String employeeLocation) {
		this.employeeLocation = employeeLocation;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	

}
