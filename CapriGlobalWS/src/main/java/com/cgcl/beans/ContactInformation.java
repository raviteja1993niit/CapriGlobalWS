package com.cgcl.beans;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder(alphabetic=true)
public class ContactInformation {
	
	@JsonProperty("CUSTOMER_CONTACT_0")
	private String contact0;

	@JsonProperty("CUSTOMER_CONTACT_TYPE_0")
	private String contactType0;
	
	@JsonProperty("CUSTOMER_LANDLINE_0")
	private String landline0;

	
	@JsonProperty("CUSTOMER_LANDLINE_TYPE_0")
	private String landlineType0;

	
	@JsonProperty("CUSTOMER_CONTACT_1")
	private String contact1;
	

	@JsonProperty("CUSTOMER_CONTACT_TYPE_1")
	private String contactType1;
	
	@JsonProperty("CUSTOMER_LANDLINE_1")
	private String landline1;
	
	@JsonProperty("CUSTOMER_LANDLINE_TYPE_1")
	private String landlineType1;
}
