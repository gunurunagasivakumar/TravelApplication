package com.capco.travel.vo;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is the FlightDetails View Object class
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class FlightDetailsVO extends BaseVO{

	private int requestId;
	
	//private String tourType;
	
	private String travelType;
	
	//private String businessPurpose;
	
	private String prefClass;
	
	private String mealPref;
	
	private String passportNumber;	
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date passportIssueDate;	
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date passportExpiryDate;	
	
	private String nationality;
	
	private String issuingAuthority;
	
	private String passportIssuePlace;
	
	private Boolean isValidVisa;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date returnDate;	
	
	private String returnTime;
	
	private String remarks;
	
	private String departureLocation;	
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date departureDate;
	
	private String departureTime;
	
	private String destinationLocation;
	
	
	private String surname;
	
	private String givenName;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date dateOfBirth;
	
	private String originCountry;
	
	private String destinationCountry;
	
	private Boolean isActive;
	
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
	}
*/
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
	}
*/
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
	
	@Override
	public String toString() {
		return "FlightDetailsVO [requestId=" + requestId + ", travelType=" + travelType
				+ ", prefClass=" + prefClass + ", mealPref=" + mealPref
				+ ", passportNumber=" + passportNumber + ", passportIssueDate=" + passportIssueDate
				+ ", passportExpiryDate=" + passportExpiryDate + ", nationality=" + nationality + ", issuingAuthority="
				+ issuingAuthority + ", passportIssuePlace=" + passportIssuePlace + ", isValidVisa=" + isValidVisa
				+ ", returnDate=" + returnDate + ", returnTime=" + returnTime + ", remarks=" + remarks
				+ ", departureLocation=" + departureLocation + ", departureDate=" + departureDate + ", departureTime="
				+ departureTime + ", destinationLocation=" + destinationLocation + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", surname=" + surname + ", givenName=" + givenName + ", dateOfBirth="
				+ dateOfBirth + "]";
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
