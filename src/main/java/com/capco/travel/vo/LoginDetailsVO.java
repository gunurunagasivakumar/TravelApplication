/**
 * 
 */
package com.capco.travel.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is placeholder for login details 
 * @author e5542274
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginDetailsVO {
	
	private Integer employeeId;
	private String employeeName;
	private Integer approverId;
	private String capcoUserId;
	private String password;
	private String grade;
	private Long contactNumber;
	
	/**
	 * @return the employeeId
	 */
	public Integer getEmployeeId() {
		return employeeId;
	}
	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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
	/**
	 * @return the contactNumber
	 */
	public Long getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

}
