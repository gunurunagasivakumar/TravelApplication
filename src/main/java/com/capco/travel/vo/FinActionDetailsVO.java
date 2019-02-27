package com.capco.travel.vo;

public class FinActionDetailsVO {

	private Integer requestId;
	private String actionId;
	private String actionStatus;
	private String pendingItems;
	
		
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
	public FinActionDetailsVO() {
	
		// TODO Auto-generated constructor stub
	}
	


}
