package com.community.dev.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateFormatUtility {

	// FastDateParser is Save for MultiThreading
	// so you can make FastDateParser for Static variable
	public static FastDateFormat SIMPLE_DATE_FORMAT = FastDateFormat.getInstance("MM-dd-yyyy");

	public static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");

	public static String parseSimpleDate(LocalDateTime dt) {

		return SIMPLE_DATE_FORMAT.format(dt);

	}

}
