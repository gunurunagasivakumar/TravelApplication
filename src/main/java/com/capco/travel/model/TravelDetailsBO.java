package com.capco.travel.model;

import java.util.List;

import com.capco.travel.beans.TravelRequestWrapper;

public class TravelDetailsBO {

	TravelRequestWrapper listBO = null;
	List<HcViewBO> hclistBO = null;
	List<FinViewBO> finlistBO = null;
	List<EmployeeDetailsBO> emplistBO = null;
	/**
	 * @return the listBO
	 */
	/**
	 * @param listBO the listBO to set
	 */
	public void setListBO(TravelRequestWrapper listBO) {
		this.listBO = listBO;
	}
	/**
	 * @return the listBO
	 */
	public TravelRequestWrapper getListBO() {
		return listBO;
	}
	/**
	 * @return the hclistBO
	 */
	public List<HcViewBO> getHclistBO() {
		return hclistBO;
	}
	/**
	 * @param hclistBO the hclistBO to set
	 */
	public void setHclistBO(List<HcViewBO> hclistBO) {
		this.hclistBO = hclistBO;
	}
	/**
	 * @return the finlistBO
	 */
	public List<FinViewBO> getFinlistBO() {
		return finlistBO;
	}
	/**
	 * @param finlistBO the finlistBO to set
	 */
	public void setFinlistBO(List<FinViewBO> finlistBO) {
		this.finlistBO = finlistBO;
	}
	/**
	 * @return the emplistBO
	 */
	public List<EmployeeDetailsBO> getEmplistBO() {
		return emplistBO;
	}
	/**
	 * @param emplistBO the emplistBO to set
	 */
	public void setEmplistBO(List<EmployeeDetailsBO> emplistBO) {
		this.emplistBO = emplistBO;
	}
	
	
	
	
}
