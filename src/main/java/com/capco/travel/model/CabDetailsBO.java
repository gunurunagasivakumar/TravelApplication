package com.capco.travel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class is the CabDetails Business class to map fields with DB columns
 * 
 * @author e5544354
 *
 */
@Entity
@Table(name = "cab_details", schema = "capco_travel_portal")
public class CabDetailsBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name = "request_id")
	private MainRequestBO mainRequestBO;
	
	@Column(name = "taxi_travel_type")
	private String taxiTravelType;
	
	@Column(name = "pickup_city")
	private String pickupCity;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pickup_location")
	private String pickUpLocation;
	
	@Column(name = "drop_location")
	private String dropLocation;
	
	@Column(name = "drop_city")
	private String dropCity;
	
	@Column(name = "from_date")
	private Date fromDateAndTime;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "car_type")
	private String carType;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "business_purpose")
	private String businessPurpose;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "modified_on")
	private Date modifiedOn;
	
	@Column(name = "is_active")
	private Boolean isActive;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the mainRequestBO
	 */
	public MainRequestBO getMainRequestBO() {
		return mainRequestBO;
	}

	/**
	 * @param mainRequestBO the mainRequestBO to set
	 */
	public void setMainRequestBO(MainRequestBO mainRequestBO) {
		this.mainRequestBO = mainRequestBO;
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
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
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