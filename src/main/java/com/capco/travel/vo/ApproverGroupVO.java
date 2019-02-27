package com.capco.travel.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author e5544344
 * This class is the ApproverGroup View object class
 *  
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ApproverGroupVO {
	
	private int approverId;
	
	private String approverName;
	
	private int level;
	
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

}
