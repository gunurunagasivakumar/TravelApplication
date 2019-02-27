package com.capco.travel.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is the MainRequest View Object class
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class MainRequestVO extends MainRequestBaseVO{
	
	
	private AccomodationDetailsVO accomodationRequest;
	
	private CabDetailsVO cabRequest;
	
	private FlightDetailsVO flightRequest;
	
	private ForexDetailsVO forexRequest;
	
	private VisaRequestVO visaRequestVO;	
	
	private List<RequestHistoryLogVO> commentList;
	
	private List<EmployeeDetailsVO> empData;
	
	public MainRequestVO() {
		empData=new ArrayList<>();
	}
	
	
	
	/**
	 * @return the accomodationRequest
	 */
	public AccomodationDetailsVO getAccomodationRequest() {
		return accomodationRequest;
	}
	
	/**
	 * @param accomodationRequest the accomodationRequest to set
	 */
	public void setAccomodationRequest(AccomodationDetailsVO accomodationRequest) {
		this.accomodationRequest = accomodationRequest;
	}
	
	/**
	 * @return the cabRequest
	 */
	public CabDetailsVO getCabRequest() {
		return cabRequest;
	}
	
	/**
	 * @param cabRequest the cabRequest to set
	 */
	public void setCabRequest(CabDetailsVO cabRequest) {
		this.cabRequest = cabRequest;
	}
	
	/**
	 * @return the flightRequest
	 */
	public FlightDetailsVO getFlightRequest() {
		return flightRequest;
	}
	
	/**
	 * @param flightRequest the flightRequest to set
	 */
	public void setFlightRequest(FlightDetailsVO flightRequest) {
		this.flightRequest = flightRequest;
	}
	
	
	
	/**
	 * @return the forexRequest
	 */
	public ForexDetailsVO getForexRequest() {
		return forexRequest;
	}
	
	/**
	 * @param forexRequest the forexRequest to set
	 */
	public void setForexRequest(ForexDetailsVO forexRequest) {
		this.forexRequest = forexRequest;
	}
	

	/**
	 * @return the commentList
	 */
	public List<RequestHistoryLogVO> getCommentList() {
		return commentList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(List<RequestHistoryLogVO> commentList) {
		this.commentList = commentList;
	}

	/**
	 * @return the visaRequestVO
	 */
	public VisaRequestVO getVisaRequestVO() {
		return visaRequestVO;
	}

	/**
	 * @param visaRequestVO the visaRequestVO to set
	 */
	public void setVisaRequestVO(VisaRequestVO visaRequestVO) {
		this.visaRequestVO = visaRequestVO;
	}
	

	/**
	 * @return the empData
	 */
	public List<EmployeeDetailsVO> getEmpData() {
		return empData;
	}

	/**
	 * @param empData the empData to set
	 */
	public void setEmpData(List<EmployeeDetailsVO> empData) {
		this.empData = empData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MainRequestVO [accomodationRequest=" + accomodationRequest + ", cabRequest=" + cabRequest
				+ ", flightRequest=" + flightRequest + ", forexRequest=" + forexRequest + ", visaRequestVO="
				+ visaRequestVO + ", commentList=" + commentList + "]";
	}


	
}
