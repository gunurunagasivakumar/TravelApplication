package com.capco.travel.vo;

import java.util.Date;

import com.capco.travel.util.JsonCalendarDeSerializer;
import com.capco.travel.util.JsonCalendarSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author e5544344
 * This class is the AccomodationDetails View object class 
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccomodationDetailsVO extends BaseVO {
	
	private int requestId;
	
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss",timezone="Asia/Kolkata")
	@JsonSerialize(using = JsonCalendarSerializer.class)
	@JsonDeserialize(using = JsonCalendarDeSerializer.class)
	private Date checkIn;
	
	@JsonSerialize(using = JsonCalendarSerializer.class)
	@JsonDeserialize(using = JsonCalendarDeSerializer.class)
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss",timezone="Asia/Kolkata")
	private Date checkOut;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date checkInDate;
	
	private String checkInTime;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date checkOutDate;
	
	private String checkOutTime;
	
	private String country;
	
	private String city;
	
	private String currency;
	
	private String budget;
	
	private String category;
	
	private String remarks;
	
	//private String businessPurpose;
	
	private Boolean isActive;
	
	private String stayLocation;
	
	private String sponsoredBy;

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
	 * @return the checkIn
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/**
	 * @param checkIn the checkIn to set
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @return the checkOut
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/**
	 * @param checkOut the checkOut to set
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the budget
	 */
	public String getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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


	@Override
	public String toString() {
		return "AccomodationDetailsVO [requestId=" + requestId + ", checkIn=" + checkIn + ", checkOut=" + checkOut
				+ ", country=" + country + ", city=" + city + ", currency=" + currency + ", budget=" + budget
				+ ", category=" + category + ", remarks=" + remarks + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + "]";
	}
/*
	*//**
	 * @return the businessPurpose
	 *//*
	public String getBusinessPurpose() {
		return businessPurpose;
	}

	*//**
	 * @param businessPurpose the businessPurpose to set
	 *//*
	public void setBusinessPurpose(String businessPurpose) {
		this.businessPurpose = businessPurpose;
	}*/

	/**
	 * @return the checkInDate
	 */
	public Date getCheckInDate() {
		return checkInDate;
	}

	/**
	 * @param checkInDate the checkInDate to set
	 */
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	

	/**
	 * @return the checkOutDate
	 */
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * @param checkOutDate the checkOutDate to set
	 */
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * @return the checkInTime
	 */
	public String getCheckInTime() {
		return checkInTime;
	}

	/**
	 * @param checkInTime the checkInTime to set
	 */
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

	/**
	 * @return the checkOutTime
	 */
	public String getCheckOutTime() {
		return checkOutTime;
	}

	/**
	 * @param checkOutTime the checkOutTime to set
	 */
	public void setCheckOutTime(String checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/**
	 * @return the stayLocation
	 */
	public String getStayLocation() {
		return stayLocation;
	}

	/**
	 * @param stayLocation the stayLocation to set
	 */
	public void setStayLocation(String stayLocation) {
		this.stayLocation = stayLocation;
	}

	/**
	 * @return the sponsoredBy
	 */
	public String getSponsoredBy() {
		return sponsoredBy;
	}

	/**
	 * @param sponsoredBy the sponsoredBy to set
	 */
	public void setSponsoredBy(String sponsoredBy) {
		this.sponsoredBy = sponsoredBy;
	}


	

}