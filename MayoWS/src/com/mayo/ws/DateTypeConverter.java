package com.mayo.ws;

import java.util.Date;

public class DateTypeConverter {
	public static Date getDate(String date) {
		Date d = new Date();
		String[] arr = date.split("-");
		if(arr.length>=3)
		{
			d.setYear(Integer.parseInt(arr[0])-1900);
			d.setMonth(Integer.parseInt(arr[1]));
			if(arr[2].contains(" "))
				d.setDate(Integer.parseInt(arr[2].substring(0, arr[2].indexOf(" "))));
			else
				d.setDate(Integer.parseInt(arr[2]));		
		}

		return d;
	}
}


