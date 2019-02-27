package com.capco.travel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.capco.travel.custom.exception.TravelException.ParsingException;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertyUtils {
	private static HashMap<String, String> propMap;
	public static final Logger logger = Logger.getLogger(PropertyUtils.class);

	static {
		try {
			propMap = new HashMap();
			String fileName = TravelConstants.EMAIL_CONFIG_FILE;
			InputStream isProp = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
			Properties props = new Properties();
			props.load(isProp);
			Enumeration enum1 = props.keys();
			while (enum1.hasMoreElements()) {
				String key = (String) enum1.nextElement();
				String value = props.getProperty(key);
				propMap.put(key, value);
			}
		} catch (Exception ex) {

			logger.error(ex.getMessage());
		}
	}

	public static String getPropertyValue(String key) {
		if (propMap != null) 
			return propMap.get(key);
		else
			return "";
	}

	public static String getValue(String id, String propertyFile) throws ParsingException {
		String value = null;

		if (id == null || "".equals(id) || propertyFile == null || "".equals(propertyFile))
			throw new ParsingException("The value of parameter passed in function is null or empty string");

		String fileName = getPropertyValue(propertyFile);
		InputStream isProp = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
		Properties props = new Properties();

		try {
			props.loadFromXML(isProp);
		}catch (IOException e) {
			logger.error(e.getMessage());
			throw new ParsingException("Exception occured while loading property file :" + propertyFile);
		}
		Enumeration enum1 = props.keys();
		while (enum1.hasMoreElements()) {
			String key = (String) enum1.nextElement();

			if (id.equalsIgnoreCase(key))
				value = props.getProperty(key);
		}

		return value;
	}
	
	public static void main(String[] args) {
		logger.info("PropertyUtils : main: "+getPropertyValue("username"));
	}
}
