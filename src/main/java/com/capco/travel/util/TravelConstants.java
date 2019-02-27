package com.capco.travel.util;

import java.util.HashMap;

/**
 * @author e5542274
 *
 */
public class TravelConstants {

	public static final String ADMIN = "admin";
	public static final String PROPERTIES_XML_FILE_NAME = "application.properties";
	public static final String RESULT_SUCCESS = "Success";
	public static final String RESULT_FAIL = "Fail";
	public static final String SUBMITTED = "submitted";
	public static final String APPROVED = "Approved";
	public static final String ONHOLD = "On-Hold";
	public static final String PENDING_FINANCE = "Pending with Finance";
	public static final String PENDING_HC = "Pending with HC";
	public static final String ISSUED_FINANCE = "Issued by Finance";
	public static final String PENDING_HC_FINANCE = "Pending with HC,Finance";
	public static final String ISSUED_HC_FINANCE = "Issued by HC,Finance";
	public static final String ISSUED_HC = "Issued by HC";
	public static final String EMAIL_CONFIG_FILE = "emailConfig.properties";
	public static final String RESULT_NULL_OBJECT = "No data found for ID: ";
	public static final String DELETED = "deleted";
	public static final String MODIFIED = "modified";
	public static final String CANCELLED = "Cancelled";
	public static final String REJECTED = "Rejected";
	public static final String USERID = "userId";
	public static final String PASSWORD = "password";
	public static final String L1_PENDING = "Manager Approval Pending";
	public static final String L2_PENDING = "Partner Approval Pending";
	public static final String EMAIL_NEW = "raised a";
	public static final String EMAIL_UPDATE = "updated the";
	public static final String PENDING = "Pending";
	public static final String EDIT = "Edit";
	public static final String DRAFT = "Draft";
	public static final String COMPLETED = "Completed";
	public static HashMap<String, String> tableMapping=null;
	static
    {
        tableMapping = new HashMap<String, String>();
        tableMapping.put("Visa", "visa_request");
        tableMapping.put("Forex", "forex_details");
        tableMapping.put("Flight", "flight_details");
        tableMapping.put("Accomodation", "accomodation_details");
        tableMapping.put("Cab", "cab_details");
        
        
    }
	
	
}
