package com.capco.travel.vo;

import java.util.List;

/**
 * @author e5544344
 * This class is the ProjectDetailsApproverDetails DTO class 
 *
 */

public class ProjectDetailsApproverDetailsDTO {
	
	 private List<ApproverGroupVO> listOfApprovers ;
	 private List<ProjectDetailsVO> listOfProjectDetails;
	 private List<PerDeimVO> perDeimList;
	 
	 
	
	
	/**
	 * @return the perDeimList
	 */
	public List<PerDeimVO> getPerDeimList() {
		return perDeimList;
	}
	/**
	 * @param perDeimList the perDeimList to set
	 */
	public void setPerDeimList(List<PerDeimVO> perDeimList) {
		this.perDeimList = perDeimList;
	}
	/**
	 * @return the listOfProjectDetails
	 */
	public List<ProjectDetailsVO> getListOfProjectDetails() {
		return listOfProjectDetails;
	}
	/**
	 * @param listOfProjectDetails the listOfProjectDetails to set
	 */
	public void setListOfProjectDetails(List<ProjectDetailsVO> listOfProjectDetails) {
		this.listOfProjectDetails = listOfProjectDetails;
	}
	/**
	 * @return the listOfApprovers
	 */
	public List<ApproverGroupVO> getListOfApprovers() {
		return listOfApprovers;
	}
	/**
	 * @param listOfApprovers the listOfApprovers to set
	 */
	public void setListOfApprovers(List<ApproverGroupVO> listOfApprovers) {
		this.listOfApprovers = listOfApprovers;
	}	

}
