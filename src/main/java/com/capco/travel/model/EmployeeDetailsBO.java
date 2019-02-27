package com.capco.travel.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is the EmployeeDetails Business class to map fields with DB
 * columns
 * 
 * @author e5545565
 *
 */
@Entity
@Table(name = "employee_details", schema = "capco_travel_portal")
public class EmployeeDetailsBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "name")
	private String employeeName;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Id
	@Column(name = "capco_user_id")
	private String capcoUserId;
	
	@Column(name = "employee_id")
	private int employeeId;
	
	@Column(name = "mobile_number")
	private Long employeeMobileNumber;
	
	@Column(name = "is_approver")
	private Boolean isApprover;	

	@Column(name = "is_travelView")
	private Boolean istravelView;
	
	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "modified_on")
	private Date modifiedOn;
	
	@Column(name = "employee_location")
	private String employeeLocation;
	
	@Column(name = "project_code")
	private String projectCode;

	@Column(name = "token_id")
	private int tokenId;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name="activity_code")
	private String activityCode;
	
	@Column(name="type_of_project")
	private String typeOfProject;
	
	@Column(name="purpose_of_travel")
	private String purposeOfTravel;
	
	@Column(name = "tour_type")
	private String tourType;
	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeMobileNumber
	 */
	public Long getEmployeeMobileNumber() {
		return employeeMobileNumber;
	}

	/**
	 * @param employeeMobileNumber the employeeMobileNumber to set
	 */
	public void setEmployeeMobileNumber(Long employeeMobileNumber) {
		this.employeeMobileNumber = employeeMobileNumber;
	}

	/**
	 * @return the isApprover
	 */
	public Boolean getIsApprover() {
		return isApprover;
	}

	/**
	 * @param isApprover the isApprover to set
	 */
	public void setIsApprover(Boolean isApprover) {
		this.isApprover = isApprover;
	}

	

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
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
	 * @return the tokenId
	 */
	public int getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
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

	public void setIstravelView(Boolean istravelView) {
		this.istravelView = istravelView;
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
