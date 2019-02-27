package com.capco.travel.vo;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is ViewRequest View Object class for frontend mappings
 * @author e5544354
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class VisaRequestVO extends BaseVO{
	
	private int requestId;
	
	private String travelDestination;
	
	private String visaType;
	
	//private String typeOfVisit;
	
	//private String businessPurpose;
	
	private String collectionDesk;
	
	private String visaRemarks;
	
	private Boolean isActive;
	
	private Boolean isValidVisa;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date toDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date fromDate;
	
	private Integer travellingDays;
	
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
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
	 * @return the travelDestination
	 */
	public String getTravelDestination() {
		return travelDestination;
	}

	/**
	 * @param travelDestination the travelDestination to set
	 */
	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
	}

	/**
	 * @return the visaType
	 */
	public String getVisaType() {
		return visaType;
	}

	/**
	 * @param visaType the visaType to set
	 */
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

/*	*//**
	 * @return the typeOfVisit
	 *//*
	public String getTypeOfVisit() {
		return typeOfVisit;
	}

	*//**
	 * @param typeOfVisit the typeOfVisit to set
	 *//*
	public void setTypeOfVisit(String typeOfVisit) {
		this.typeOfVisit = typeOfVisit;
	}
*/
	/**
	 * @return the businessPurpose
	 */
	/*public String getBusinessPurpose() {
		return businessPurpose;
	}

	*//**
	 * @param businessPurpose the businessPurpose to set
	 *//*
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}
*/
	/**
	 * @return the collectionDesk
	 */
	public String getCollectionDesk() {
		return collectionDesk;
	}

	/**
	 * @param collectionDesk the collectionDesk to set
	 */
	public void setCollectionDesk(String collectionDesk) {
		this.collectionDesk = collectionDesk;
	}

	/**
	 * @return the visaRemarks
	 */
	public String getVisaRemarks() {
		return visaRemarks;
	}

	/**
	 * @param visaRemarks the visaRemarks to set
	 */
	public void setVisaRemarks(String visaRemarks) {
		this.visaRemarks = visaRemarks;
	}

	/**
	 * @return the isValidVisa
	 */
	public Boolean getIsValidVisa() {
		return isValidVisa;
	}

	/**
	 * @param isValidVisa the isValidVisa to set
	 */
	public void setIsValidVisa(Boolean isValidVisa) {
		this.isValidVisa = isValidVisa;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the travellingDays
	 */
	public Integer getTravellingDays() {
		return travellingDays;
	}

	/**
	 * @param travellingDays the travellingDays to set
	 */
	public void setTravellingDays(Integer travellingDays) {
		this.travellingDays = travellingDays;
	}



	

	
	
}
