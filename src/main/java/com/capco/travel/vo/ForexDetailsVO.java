package com.capco.travel.vo;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class is ForexDetails View Object class for frontend mappings
 * @author e5545565
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ForexDetailsVO extends BaseVO{
	private int forexRequestId;
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date forexToDate;
	
	//@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy",timezone="Asia/Kolkata")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone="Asia/Kolkata")
	private Date forexFromDate;
	
	private String forexCurrency;
	
	private String forexCountry;
	
	private int forexNoOfDays;
	
	//private double forexAmount;
	
	private String forexRemarks;
	
	private String forexCollectionCenter;
	
	private String forexBankDesk;	
	
	private Boolean isActive;
	
	private Integer cardNumber;

	private String cardHolderName;

	private String bank;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	

	/**
	 * @return the forexRequestId
	 */
	public final int getForexRequestId() {
		return forexRequestId;
	}
	
	/**
	 * @param forexRequestId the forexRequestId to set
	 */
	public final void setForexRequestId(int forexRequestId) {
		this.forexRequestId = forexRequestId;
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
	
/*	*//**
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
	}
	*/
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
	 * @return the cardNumber
	 */
	public Integer getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * @param cardHolderName the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	

}
