package com.shinhan.heehee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date getUtilDate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		try {
			result = sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static java.sql.Date getSqlDate(String d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date result = null;
		try {
			result = new java.sql.Date(sdf.parse(d).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
}
