/**
 * 
 */
package com.capco.travel.vo;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author e5545730
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class PerDeimVO {

	private int uid;

	private String country;

	private double perdiem;

	private String currency;

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
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
	 * @return the perdiem
	 */
	public double getPerdiem() {
		return perdiem;
	}

	/**
	 * @param perdiem the perdiem to set
	 */
	public void setPerdiem(double perdiem) {
		this.perdiem = perdiem;
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

}
