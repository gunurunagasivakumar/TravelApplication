package com.capco.travel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hc_action_table", schema = "capco_travel_portal")
public class HcViewBO implements Serializable{
	
	public HcViewBO() {
		
	}

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

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getPendingItems() {
		return pendingItems;
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

	public void setPendingItems(String pendingItems) {
		this.pendingItems = pendingItems;
	}	
	
	
	
}
