package com.weinan.common.util;

import java.util.Calendar;
import java.util.Date;

public class AgeUtils {
	
    public static int getAgeByBirthday(Date birthday) {
		int age = 0;
		Calendar cal = Calendar.getInstance();
		if(cal.before(birthday)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int currentDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthday);
		int birthYear = cal.get(Calendar.YEAR);
		int birthMonth = cal.get(Calendar.MONTH);
		int birthDay = cal.get(Calendar.DAY_OF_MONTH);
		age = currentYear - birthYear;
		if(currentMonth <= birthMonth) {
			if(currentMonth == birthMonth) {
				if(currentDay < birthDay) {
					age--;
				}
			}else {
				age--;
			}
		}
		return age;
	}

}
