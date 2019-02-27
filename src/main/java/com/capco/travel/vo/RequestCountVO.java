package com.capco.travel.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * @author e5544344
 * This class is the RequestCountVO View object class 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class RequestCountVO {
	
	int cabPending;
	int cabApproved;
	int flightPending;
	int flightApproved;
	int accomodationPending;
	int accomodationApproved;
	int forexPending;
	int forexApproved;
	int visaPending;
	int visaApproved;
	/**
	 * @return the cabPending
	 */
	public int getCabPending() {
		return cabPending;
	}
	/**
	 * @param cabPending the cabPending to set
	 */
	public void setCabPending(int cabPending) {
		this.cabPending = cabPending;
	}
	/**
	 * @return the cabApproved
	 */
	public int getCabApproved() {
		return cabApproved;
	}
	/**
	 * @param cabApproved the cabApproved to set
	 */
	public void setCabApproved(int cabApproved) {
		this.cabApproved = cabApproved;
	}
	/**
	 * @return the flightPending
	 */
	public int getFlightPending() {
		return flightPending;
	}
	/**
	 * @param flightPending the flightPending to set
	 */
	public void setFlightPending(int flightPending) {
		this.flightPending = flightPending;
	}
	/**
	 * @return the flightApproved
	 */
	public int getFlightApproved() {
		return flightApproved;
	}
	/**
	 * @param flightApproved the flightApproved to set
	 */
	public void setFlightApproved(int flightApproved) {
		this.flightApproved = flightApproved;
	}
	/**
	 * @return the accomodationPending
	 */
	public int getAccomodationPending() {
		return accomodationPending;
	}
	/**
	 * @param accomodationPending the accomodationPending to set
	 */
	public void setAccomodationPending(int accomodationPending) {
		this.accomodationPending = accomodationPending;
	}
	/**
	 * @return the accomodationApproved
	 */
	public int getAccomodationApproved() {
		return accomodationApproved;
	}
	/**
	 * @param accomodationApproved the accomodationApproved to set
	 */
	public void setAccomodationApproved(int accomodationApproved) {
		this.accomodationApproved = accomodationApproved;
	}
	/**
	 * @return the forexPending
	 */
	public int getForexPending() {
		return forexPending;
	}
	/**
	 * @param forexPending the forexPending to set
	 */
	public void setForexPending(int forexPending) {
		this.forexPending = forexPending;
	}
	/**
	 * @return the forexApproved
	 */
	public int getForexApproved() {
		return forexApproved;
	}
	/**
	 * @param forexApproved the forexApproved to set
	 */
	public void setForexApproved(int forexApproved) {
		this.forexApproved = forexApproved;
	}
	/**
	 * @return the visaPending
	 */
	public int getVisaPending() {
		return visaPending;
	}
	/**
	 * @param visaPending the visaPending to set
	 */
	public void setVisaPending(int visaPending) {
		this.visaPending = visaPending;
	}
	/**
	 * @return the visaApproved
	 */
	public int getVisaApproved() {
		return visaApproved;
	}
	/**
	 * @param visaApproved the visaApproved to set
	 */
	public void setVisaApproved(int visaApproved) {
		this.visaApproved = visaApproved;
	}
	
	
	

}
