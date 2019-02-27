package com.capco.travel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class is the AccomodationDetails Business class to map fields with DB columns
 * @author e5544344
 */

@Entity
@Table(name = "accomodation_details", schema = "capco_travel_portal")
public class AccomodationDetailsBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id		
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id")
	MainRequestBO mainRequestBO;

	@Column(name = "check_in")
	private Date checkIn;

	@Column(name = "check_out")
	private Date checkOut;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "currency")
	private String currency;

	@Column(name = "budget")
	private String budget;

	@Column(name = "category")
	private String category;
/*
	@Column(name = "business_purpose")
	private String businessPurpose;*/

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "modified_on")
	private Date modifiedOn;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "address")
	private String address;
	
	@Column(name = "stay_location")
	private String stayLocation;
	
	@Column(name = "sponsored_by")
	private String sponsoredBy;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

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

	@Override
	public String toString() {
		return "AccomodationDetailsBO [mainRequestBO=" + mainRequestBO + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", country=" + country + ", city=" + city + ", currency=" + currency + ", budget=" + budget
				+ ", category=" + category + ", remarks=" + remarks + ", createdOn=" + createdOn + ", modifiedOn="
				+ modifiedOn + "]";
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

/*	*//**
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

}