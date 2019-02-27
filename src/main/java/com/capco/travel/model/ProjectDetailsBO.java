package com.capco.travel.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is the ProjectDetails Business class to map fields with DB columns
 * @author e5544354
 */

@Entity
@Table(name = "project_details", schema = "capco_travel_portal")
public class ProjectDetailsBO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "project_code")
	private String projectCode;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="activity_code")
	private String activityCode;
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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the acitivityCode
	 */
	public String getActivityCode() {
		return activityCode;
	}

	/**
	 * @param acitivityCode the acitivityCode to set
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

}
