package com.capco.travel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This Class is the ForexDetails Business Class maps all the fields with the DB table columns using hibernate
 * @author e5545565
 *
 */

@Entity
@Table(name = "forex_details", schema = "capco_travel_portal")
public class ForexDetailsBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "uid")
	private int uId;

	@OneToOne
	@JoinColumn(name = "request_id")
	MainRequestBO mainRequestBO;

	@Column(name = "to_date")
	private Date forexToDate;

	@Column(name = "from_date")
	private Date forexFromDate;

	@Column(name = "currency")
	private String forexCurrency;

	@Column(name = "country")
	private String forexCountry;

	@Column(name = "no_of_days")
	private int forexNoOfDays;

	/*@Column(name = "amount")
	private double forexAmount;*/

	@Column(name = "remarks")
	private String forexRemarks;

	@Column(name = "collection_center")
	private String forexCollectionCenter;

	@Column(name = "bank_desk")
	private String forexBankDesk;	
	
	@Column(name="created_on")
	private Date createdOn;

	@Column(name="modified_on")
	private Date modifiedOn;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "previously_travelled")
	private Boolean previouslyTravelledwithcapco;

	@Column(name = "card_number")
	private Integer cardNumber;

	@Column(name = "card_holder_name")
	private String cardHolderName;

	@Column(name = "bank")
	private String bank;

	public Boolean getPreviouslyTravelledwithcapco() {
		return previouslyTravelledwithcapco;
	}

	public void setPreviouslyTravelledwithcapco(Boolean previouslyTravelledwithcapco) {
		this.previouslyTravelledwithcapco = previouslyTravelledwithcapco;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the uId
	 */
	public final int getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	public final void setuId(int uId) {
		this.uId = uId;
	}

	/**
	 * @return the mainRequestBO
	 */
	public final MainRequestBO getMainRequestBO() {
		return mainRequestBO;
	}

	/**
	 * @param mainRequestBO the mainRequestBO to set
	 */
	public final void setMainRequestBO(MainRequestBO mainRequestBO) {
		this.mainRequestBO = mainRequestBO;
	}

	/**
	 * @return the forexToDate
	 */
	public final Date getForexToDate() {
		return forexToDate;
	}

	/**
	 * @param forexToDate the forexToDate to set
	 */
	public final void setForexToDate(Date forexToDate) {
		this.forexToDate = forexToDate;
	}

	/**
	 * @return the forexFromDate
	 */
	public final Date getForexFromDate() {
		return forexFromDate;
	}

	/**
	 * @param forexFromDate the forexFromDate to set
	 */
	public final void setForexFromDate(Date forexFromDate) {
		this.forexFromDate = forexFromDate;
	}

	/**
	 * @return the forexCurrency
	 */
	public final String getForexCurrency() {
		return forexCurrency;
	}

	/**
	 * @param forexCurrency the forexCurrency to set
	 */
	public final void setForexCurrency(String forexCurrency) {
		this.forexCurrency = forexCurrency;
	}

	/**
	 * @return the forexCountry
	 */
	public final String getForexCountry() {
		return forexCountry;
	}

	/**
	 * @param forexCountry the forexCountry to set
	 */
	public final void setForexCountry(String forexCountry) {
		this.forexCountry = forexCountry;
	}

	/**
	 * @return the forexNoOfDays
	 */
	public final int getForexNoOfDays() {
		return forexNoOfDays;
	}

	/**
	 * @param forexNoOfDays the forexNoOfDays to set
	 */
	public final void setForexNoOfDays(int forexNoOfDays) {
		this.forexNoOfDays = forexNoOfDays;
	}

	/**
	 * @return the forexAmount
	 *//*
	public final double getForexAmount() {
		return forexAmount;
	}

	*//**
	 * @param forexAmount the forexAmount to set
	 *//*
	public final void setForexAmount(double forexAmount) {
		this.forexAmount = forexAmount;
	}*/

	/**
	 * @return the forexRemarks
	 */
	public final String getForexRemarks() {
		return forexRemarks;
	}

	/**
	 * @param forexRemarks the forexRemarks to set
	 */
	public final void setForexRemarks(String forexRemarks) {
		this.forexRemarks = forexRemarks;
	}

	/**
	 * @return the forexCollectionCenter
	 */
	public final String getForexCollectionCenter() {
		return forexCollectionCenter;
	}

	/**
	 * @param forexCollectionCenter the forexCollectionCenter to set
	 */
	public final void setForexCollectionCenter(String forexCollectionCenter) {
		this.forexCollectionCenter = forexCollectionCenter;
	}

	/**
	 * @return the forexBankDesk
	 */
	public final String getForexBankDesk() {
		return forexBankDesk;
	}

	/**
	 * @param forexBankDesk the forexBankDesk to set
	 */
	public final void setForexBankDesk(String forexBankDesk) {
		this.forexBankDesk = forexBankDesk;
	}

	/**
	 * @return the createdOn
	 */
	public final Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public final void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the modifiedOn
	 */
	public final Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public final void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	
	
}
