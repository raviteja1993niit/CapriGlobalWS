package com.cgcl.beans;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"customerfullname","dob","aadharCard","voterId","passport","pan","mobileNo","mobileNoType","address","addressType","propertyAddress","propertyAddressType","reason","source","recordType","matchCriteria","matchId"})
public class NegativeMatchedCustomerDetailsBean {

//	@JsonProperty("ADDRESS")
//	private String address;
//	@JsonProperty("CITY")
//	private String city;
//	@JsonProperty("COUNTRY")
//	private String country;
//	@JsonProperty("CUSTOMER_NAME")
//	private String customerfullname;
//	@JsonProperty("DOB")
//	private Date dob;
//	@JsonProperty("IDENTITY_NUMBER")
//	private String identitynumber;
//	@JsonProperty("IDENTITY_TYPE")
//	private String identitytype;
	
	@ApiModelProperty(value = "CUSTOMER_NAME",position =0)
	@JsonProperty("CUSTOMER_NAME")
	private String customerfullname;
//	@ApiModelProperty(value = "FIRSTNAME",position =8)
//	@JsonProperty("FIRSTNAME")
//	public String firstName;
//	@ApiModelProperty(value = "CUSTOMER_MATCH_COUNT",position =8)
//	@JsonProperty("MIDDLENAME")
//	public String middleName;
//	@ApiModelProperty(value = "CUSTOMER_MATCH_COUNT",position =8)
//	@JsonProperty("LASTNAME")
//	public String lastName;
	@ApiModelProperty(value = "DOB",position =1)
	@JsonProperty("DOB")
	public String dob;
	@ApiModelProperty(value = "AADHAR_NO",position =2)
	@JsonProperty("AADHAR_NO")
	public String aadharCard;
	@ApiModelProperty(value = "VOTER_ID",position =3)
	@JsonProperty("VOTER_ID")
	public String voterId;
	@ApiModelProperty(value = "PASSPORT_NO",position =4)
	@JsonProperty("PASSPORT_NO")
	public String passport;
	@ApiModelProperty(value = "PAN_NO",position =5)
	@JsonProperty("PAN_NO")
	public String pan;
	@ApiModelProperty(value = "MOBILE_NO",position =6)
	@JsonProperty("MOBILE_NO")
	public String mobileNo;
	@ApiModelProperty(value = "MOBILE_NO_TYPE",position =7)
	@JsonProperty("MOBILE_NO_TYPE")
	public String mobileNoType;
	@ApiModelProperty(value = "ADDRESS",position =8)
	@JsonProperty("ADDRESS")
	public String address;
	@ApiModelProperty(value = "ADDRESS_TYPE",position =9)
	@JsonProperty("ADDRESS_TYPE")
	public String addressType;
//	@ApiModelProperty(value = "CITY",position =8)
//	@JsonProperty("CITY")
//	private String city;
	@ApiModelProperty(value = "PROPERTY_ADDRESS",position =10)
	@JsonProperty("PROPERTY_ADDRESS")
	public String propertyAddress;
	@ApiModelProperty(value = "PROPERTY_ADDRESS_TYPE",position =11)
	@JsonProperty("PROPERTY_ADDRESS_TYPE")
	public String propertyAddressType;
	@ApiModelProperty(value = "REASON",position =12)
	@JsonProperty("REASON")
	public String reason;
	@ApiModelProperty(value = "SOURCE",position =13)
	@JsonProperty("SOURCE")
	public String source;
	
	@ApiModelProperty(value = "RECORD_TYPE",position =14)	
	@JsonProperty("RECORD_TYPE")
	private String recordType;
	
	@ApiModelProperty(value = "MATCH_CRITERIA",position =15)	
	@JsonProperty("MATCH_CRITERIA")
	private String matchCriteria;

	@ApiModelProperty(value = "MATCHED_ID",position =16)	
	@JsonProperty("MATCHED_ID")
	private String matchId;
}
