package com.capco.travel.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonCalendarDeSerializer extends JsonDeserializer<Date> {

	private static final Logger logger = Logger.getLogger(JsonCalendarDeSerializer.class);
	
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	logger.info("JsonCalendarDeSerializer : deserialize(): Started");
    	String dateAsString = jsonparser.getText();
        try{
            Date date= JsonCalendarSerializer.FORMATTER.parse(dateAsString);
			
            Calendar calendar = Calendar.getInstance(
            		JsonCalendarSerializer.LOCAL_TIME_ZONE, 
            		JsonCalendarSerializer.LOCALE_LOCAL
            );
            calendar.setTime(date);
            logger.info("JsonCalendarDeSerializer : deserialize(): Ended");
            return calendar.getTime();
        } catch (java.text.ParseException e) {
        	logger.info("JsonCalendarDeSerializer : deserialize(): Exception caught: "+e);
		}
        
    	return null;
    }
}


