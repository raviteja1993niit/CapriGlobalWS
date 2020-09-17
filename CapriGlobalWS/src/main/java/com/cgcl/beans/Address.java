package com.cgcl.beans;

import lombok.Data;

@Data
public class Address {
	
	private String address;
	
	private int addressid;

	private String addresstype;
		
	private String city;
		
	private String pincode;

	private String state;
}
