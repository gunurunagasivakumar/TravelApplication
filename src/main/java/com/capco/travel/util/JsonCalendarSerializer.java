package com.capco.travel.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonCalendarSerializer extends JsonSerializer<Date> {

	 public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    public static final Locale LOCALE_LOCAL = Locale.getDefault();
	    public static final TimeZone LOCAL_TIME_ZONE = Calendar.getInstance(LOCALE_LOCAL).getTimeZone();

	    @Override
	    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
	            throws IOException, JsonProcessingException {
	        if (value == null) {
	            gen.writeNull();
	        } else {
	            gen.writeString(FORMATTER.format(value.getTime()));
	        }
	    }

}
