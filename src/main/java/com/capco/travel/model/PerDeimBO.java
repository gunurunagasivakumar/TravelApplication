/**
 * 
 */
package com.capco.travel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author e5542274
 *
 */
@Entity
@Table(name = "perdiem_details", schema = "capco_travel_portal")
public class PerDeimBO {
	
	@Id
	@Column(name = "uid")
	private int uid;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "perdiem")
	private double perdiem;
	
	@Column(name = "currency")
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
