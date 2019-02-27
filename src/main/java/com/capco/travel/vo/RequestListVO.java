package com.capco.travel.vo;

/**@author e5544344
 * This class is the RequestList view object class 
 */

public class RequestListVO extends BaseVO{
	
	private int requestId;
	
	private String currentStatus;
	
	private String requestedBy;
	
	
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
	 * @return the requestedBy
	 */
	public String getRequestedBy() {
		return requestedBy;
	}
	/**
	 * @param requestedBy the requestedBy to set
	 */
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	
	@Override
	public String toString() {
		return "RequestListVO [requestId=" + requestId + ", currentStatus=" + currentStatus + ", requestedBy="
				+ requestedBy + ", createdOn=" + createdOn + "]";
	}
	
}
