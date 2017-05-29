package com.senla.hotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calend {

	public static SimpleDateFormat myFormat = new SimpleDateFormat(Constant.DATEFORMAT);

	public static Long differenceBetweenDates(Date d1, Date d2) {

		Long d1MinusD2 = d1.getTime() - d2.getTime();
		return d1MinusD2 / (24 * 60 * 60 * 1000);

	}

	public static String getTextDate(Date dt) {
		return myFormat.format(dt);
	}

	public static Date getDateText(String text) throws ParseException {
		return myFormat.parse(text);

	}
}
