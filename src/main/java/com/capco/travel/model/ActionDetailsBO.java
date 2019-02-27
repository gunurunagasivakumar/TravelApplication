package com.capco.travel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name = "action_details", schema = "capco_travel_portal")
	public class ActionDetailsBO implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name = "Request_id")
		private Integer requestId;
		
		@Column(name = "Action_id")
		private String actionId;
		
		@Column(name = "Pending_actions")
		private String PendingActions;
		
		@Column(name="action_status")
		private String actionStatus;
		
		@Column(name = "pending_status")
		private String pendingStatus;
		
		@Column(name = "request_status")
		private String requestStatus;

		public ActionDetailsBO() {
					// TODO Auto-generated constructor stub
		}

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
		 * @return the pendingActions
		 */
		public String getPendingActions() {
			return PendingActions;
		}

		/**
		 * @param pendingActions the pendingActions to set
		 */
		public void setPendingActions(String pendingActions) {
			PendingActions = pendingActions;
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
		 * @return the pendingStatus
		 */
		public String getPendingStatus() {
			return pendingStatus;
		}

		/**
		 * @param pendingStatus the pendingStatus to set
		 */
		public void setPendingStatus(String pendingStatus) {
			this.pendingStatus = pendingStatus;
		}

		/**
		 * @return the requestStatus
		 */
		public String getRequestStatus() {
			return requestStatus;
		}

		/**
		 * @param requestStatus the requestStatus to set
		 */
		public void setRequestStatus(String requestStatus) {
			this.requestStatus = requestStatus;
		}

}
