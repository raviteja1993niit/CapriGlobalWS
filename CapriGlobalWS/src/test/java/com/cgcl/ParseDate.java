package com.cgcl;
import java.text.SimpleDateFormat;

public class ParseDate {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String date = "";
		try {
			String date1 = new SimpleDateFormat("dd-MM-yyyy").format(dateFormat.parse(date));
			System.out.println("Date >> "+date1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
