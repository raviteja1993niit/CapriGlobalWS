package com.cgcl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Example {
	
	public static boolean validateJavaDate(String strDate) {
		/* Check if date is 'null' */
		boolean isValid=false;
		if (strDate != null) {
			/*
			 * Set preferred date format, For example MM-dd-yyyy,
			 * MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				Date javaDate = sdfrmt.parse(strDate);
				isValid=true;
				System.out.println(strDate + " is valid date format");
			}
			/* Date format is invalid */
			catch (ParseException e) {
				System.out.println(strDate + " is Invalid Date format");
				isValid=false;
			}
			/* Return true if date format is valid */
		}
		return isValid;
	}

	public static void main(String args[]) {
		// validateJavaDate("12/29/2016");
		validateJavaDate("22-07-1993");
		// validateJavaDate("12,29,2016");
	}
}