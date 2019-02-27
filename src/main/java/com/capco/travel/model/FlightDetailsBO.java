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
 * This class is the FlightDetails Business class to map fields with DB columns
 * 
 * @author e5545730
 *
 */

@Entity
@Table(name = "flight_details", schema = "capco_travel_portal")
public class FlightDetailsBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_id")
	MainRequestBO mainRequestBO;

	/*@Column(name = "tour_type")
	private String tourType;*/

	@Column(name = "travel_type")
	private String travelType;

	/*@Column(name = "business_purpose")
	private String businessPurpose;*/

	@Column(name = "pref_class")
	private String prefClass;

	@Column(name = "meal_pref")
	private String mealPref;

	@Column(name = "passport_no")
	private String passportNumber;

	@Column(name = "passport_issue_date")
	private Date passportIssueDate;

	@Column(name = "passport_expiry_date")
	private Date passportExpiryDate;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "issuing_authority")
	private String issuingAuthority;

	@Column(name = "passport_issue_place")
	private String passportIssuePlace;

	@Column(name = "is_valid_visa")
	private Boolean isValidVisa;

	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "return_time")
	private String returnTime;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "departure_location")
	private String departureLocation;

	@Column(name = "departure_date")
	private Date departureDate;

	@Column(name = "departure_time")
	private String departureTime;

	@Column(name = "destination_location")
	private String destinationLocation;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "modified_on")
	private Date modifiedOn;

	@Column(name = "sur_name")
	private String surname;

	@Column(name = "given_name")
	private String givenName;

	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@Column(name = "origin_country")
	private String originCountry;
	
	@Column(name = "destination_country")
	private String destinationCountry;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "passport_issue_location")
	private String passportIssueLocation;

	
	@Column(name = "sponsored_by")
	private String sponsoredBy;


	public String getPassportIssueLocation() {
		return passportIssueLocation;
	}

	public void setPassportIssueLocation(String passportIssueLocation) {
		this.passportIssueLocation = passportIssueLocation;
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

/*	*//**
	 * @return the tourType
	 *//*
	public String getTourType() {
		return tourType;
	}

	*//**
	 * @param tourType the tourType to set
	 *//*
	public void setTourType(String tourType) {
		this.tourType = tourType;
	}*/

	/**
	 * @return the travelType
	 */
	public String getTravelType() {
		return travelType;
	}

	/**
	 * @param travelType the travelType to set
	 */
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	/**
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
	 * @return the prefClass
	 */
	public String getPrefClass() {
		return prefClass;
	}

	/**
	 * @param prefClass the prefClass to set
	 */
	public void setPrefClass(String prefClass) {
		this.prefClass = prefClass;
	}

	/**
	 * @return the mealPref
	 */
	public String getMealPref() {
		return mealPref;
	}

	/**
	 * @param mealPref the mealPref to set
	 */
	public void setMealPref(String mealPref) {
		this.mealPref = mealPref;
	}

	/**
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * @param passportNumber the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * @return the passportIssueDate
	 */
	public Date getPassportIssueDate() {
		return passportIssueDate;
	}

	/**
	 * @param passportIssueDate the passportIssueDate to set
	 */
	public void setPassportIssueDate(Date passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}

	/**
	 * @return the passportExpiryDate
	 */
	public Date getPassportExpiryDate() {
		return passportExpiryDate;
	}

	/**
	 * @param passportExpiryDate the passportExpiryDate to set
	 */
	public void setPassportExpiryDate(Date passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the issuingAuthority
	 */
	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	/**
	 * @param issuingAuthority the issuingAuthority to set
	 */
	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	/**
	 * @return the passportIssuePlace
	 */
	public String getPassportIssuePlace() {
		return passportIssuePlace;
	}

	/**
	 * @param passportIssuePlace the passportIssuePlace to set
	 */
	public void setPassportIssuePlace(String passportIssuePlace) {
		this.passportIssuePlace = passportIssuePlace;
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
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return the returnTime
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
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
	 * @return the departureLocation
	 */
	public String getDepartureLocation() {
		return departureLocation;
	}

	/**
	 * @param departureLocation the departureLocation to set
	 */
	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the departureTime
	 */
	public String getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the destinationLocation
	 */
	public String getDestinationLocation() {
		return destinationLocation;
	}

	/**
	 * @param destinationLocation the destinationLocation to set
	 */
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
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
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the originCountry
	 */
	public String getOriginCountry() {
		return originCountry;
	}

	/**
	 * @param originCountry the originCountry to set
	 */
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	/**
	 * @return the destinationCountry
	 */
	public String getDestinationCountry() {
		return destinationCountry;
	}

	/**
	 * @param destinationCountry the destinationCountry to set
	 */
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
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
