package com.cgcl.beans;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder(alphabetic=true)
public class EmailInformation {
	
	@JsonProperty("EMAIL_ID_0")
	private String email0;
	
	@JsonProperty("EMAIL_TYPE_0")
	private String emailType0;
	
	@JsonProperty("EMAIL_ID_1")
	private String email1;
	
	@JsonProperty("EMAIL_TYPE_1")
	private String emailType1;
}
