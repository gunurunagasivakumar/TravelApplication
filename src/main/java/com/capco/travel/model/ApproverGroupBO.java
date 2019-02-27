package com.capco.travel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class is the ApproverGroup Business class to map fields with DB columns
 * @author e5544344
 */

@Entity
@Table(name = "approver_group", schema = "capco_travel_portal")
public class ApproverGroupBO implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "approver_id")
	private int approverId;
	@Column(name="approver_level")
	private int level;

	@Column(name="email_address")
	private String email;
	
	/**
	 * @return the approverId
	 */
	public int getApproverId() {
		return approverId;
	}
	/**
	 * @param approverId the approverId to set
	 */
	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApproverGroupBO [approverId=" + approverId + ", level=" + level + "]";
	}
	

}
