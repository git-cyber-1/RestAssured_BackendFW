package com.ninza.hrm.api.genericutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}

	public String getSysDateYYYYDDMM() {
		java.util.Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;

	}

	public String getRequiredDate(int days) {
		Date dateobj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqdate = sdf.format(cal.getTime());
		return reqdate;
	}
	
	
	
}
