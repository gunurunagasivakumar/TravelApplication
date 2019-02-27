package com.capco.travel.vo;
import java.util.Date;

import com.capco.travel.util.JsonCalendarDeSerializer;
import com.capco.travel.util.JsonCalendarSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * This class is the CabDetails View object class 
 * @author e5544354
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class CabDetailsVO extends BaseVO {

	private int requestId;
	
	private String taxiTravelType;
	
	//private int numberOfPersons;
	
	private String pickupCity;
	
	private String dropCity;
	
	private String country;
	
	private String pickUpLocation;
	
	private String dropLocation;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date fromDate;
	
	private String fromTime;
	
	private String businessPurpose;
	
	@JsonSerialize(using = JsonCalendarSerializer.class)
	@JsonDeserialize(using = JsonCalendarDeSerializer.class)
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss",timezone="Asia/Kolkata")
	private Date fromDateAndTime;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date toDate;
	
	private String carType;
	
	private String remarks;
	
	private Boolean isActive;

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
	 * @return the taxiTravelType
	 */
	public String getTaxiTravelType() {
		return taxiTravelType;
	}

	/**
	 * @param taxiTravelType the taxiTravelType to set
	 */
	public void setTaxiTravelType(String taxiTravelType) {
		this.taxiTravelType = taxiTravelType;
	}


	

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the pickUpLocation
	 */
	public String getPickUpLocation() {
		return pickUpLocation;
	}

	/**
	 * @param pickUpLocation the pickUpLocation to set
	 */
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	/**
	 * @return the dropLocation
	 */
	public String getDropLocation() {
		return dropLocation;
	}

	/**
	 * @param dropLocation the dropLocation to set
	 */
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
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
	 * @return the carType
	 */
	public String getCarType() {
		return carType;
	}

	/**
	 * @param carType the carType to set
	 */
	public void setCarType(String carType) {
		this.carType = carType;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the businessPurpose
	 */
	public String getBusinessPurpose() {
		return businessPurpose;
	}

	/**
	 * @param businessPurpose the businessPurpose to set
	 */
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}

	/**
	 * @return the fromTime
	 */
	public String getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * @return the fromDateAndTime
	 */
	public Date getFromDateAndTime() {
		return fromDateAndTime;
	}

	/**
	 * @param fromDateAndTime the fromDateAndTime to set
	 */
	public void setFromDateAndTime(Date fromDateAndTime) {
		this.fromDateAndTime = fromDateAndTime;
	}

	/**
	 * @return the pickupCity
	 */
	public String getPickupCity() {
		return pickupCity;
	}

	/**
	 * @param pickupCity the pickupCity to set
	 */
	public void setPickupCity(String pickupCity) {
		this.pickupCity = pickupCity;
	}

	/**
	 * @return the dropCity
	 */
	public String getDropCity() {
		return dropCity;
	}

	/**
	 * @param dropCity the dropCity to set
	 */
	public void setDropCity(String dropCity) {
		this.dropCity = dropCity;
	}

	

}
