package com.yumendedao.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
	
	public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void sleepBySecond(int seconds){
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String dateToString(Date date){
		return sdf1.format(date);
	}
	
	public static Date StrToDate(String str){
		Date date = null;
		try {
			date = sdf1.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static final SimpleDateFormat TIMESTAMP = new SimpleDateFormat("yyyyMMddHHmmss");

	public static final String getTimeStamp() {
		return TIMESTAMP.format(new Date(System.currentTimeMillis())).toString();
	}
	
	public static final String getTimeStamp(SimpleDateFormat sdf){
		return sdf.format(new Date(System.currentTimeMillis())).toString();
	}

}
