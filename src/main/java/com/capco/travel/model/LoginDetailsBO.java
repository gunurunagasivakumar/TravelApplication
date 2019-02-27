/**
 * 
 */
package com.capco.travel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is placeholder for user table
 * @author e5542274
 *
 */
@Entity
@Table(name = "user", schema = "capco_travel_portal")
public class LoginDetailsBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "uid")
	private int uid;

	@Column(name = "capco_user_id")
	private String capcoUserId;

	@Column(name = "employee_id")
	private  Integer employoeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "grade")
	private String grade;

	@Column(name = "contact_number")
	private Long contactNumber;

	@Column(name = "password")
	private String password;

	@Column(name = "approver_id")
	private Integer approverId;

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the employoeeId
	 */
	public Integer getEmployoeeId() {
		return employoeeId;
	}

	/**
	 * @param employoeeId the employoeeId to set
	 */
	public void setEmployoeeId(Integer employoeeId) {
		this.employoeeId = employoeeId;
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




}
