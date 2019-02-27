package com.capco.travel.vo;

/**
 * @author e5542274
 * This class is the TravelRequest DTO class 
 */

public class TravelRequestDTO {
	
	
	private MainRequestVO travelRequest;

	/**
	 * @return the travelRequest
	 */
	public MainRequestVO getTravelRequest() {
		return travelRequest;
	}

	/**
	 * @param travelRequest the travelRequest to set
	 */
	public void setTravelRequest(MainRequestVO travelRequest) {
		this.travelRequest = travelRequest;
		
	}

}
