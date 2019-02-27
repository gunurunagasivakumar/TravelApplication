package com.capco.travel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fin_action_table", schema = "capco_travel_portal")
public class FinViewBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "request_id")
	private Integer requestId;
	
	@Column(name = "action_id")
	private String actionId;
	
	@Column(name = "action_status")
	private String actionStatus;
	
	@Column(name = "pending_items")
	private String pendingItems;
	
	@Column(name="status")
	private String status;


	/**
	 * @return the requestId
	 */
	public Integer getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}

	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	/**
	 * @return the actionStatus
	 */
	public String getActionStatus() {
		return actionStatus;
	}

	/**
	 * @param actionStatus the actionStatus to set
	 */
	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	/**
	 * @return the pendingItems
	 */
	public String getPendingItems() {
		return pendingItems;
	}

	/**
	 * @param pendingItems the pendingItems to set
	 */
	public void setPendingItems(String pendingItems) {
		this.pendingItems = pendingItems;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
