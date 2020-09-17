package com.cgcl.beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "assignUcic","customercategory","matchingruleprofile","sourceapplicationid", "sourceapplicationno", "sourceauthenticationtoken", "sourcesystemname", "sourcecustomerid"})
public class CustomerRequestInformation{
	//@JsonProperty("REQUEST_ID")
	@JsonIgnore
	private long requestId;
	
	@ApiModelProperty(value = "ASSIGN_UCIC",position = 1)
	@JsonProperty("ASSIGN_UCIC")
	private String assignUcic;
	
	@ApiModelProperty(value = "CUSTOMER_CATEGORY",position = 2)
	@JsonProperty("CUSTOMER_CATEGORY")
	private String customercategory;
	
	@ApiModelProperty(value = "MATCHING_RULE_PROFILE",position = 3)
	@JsonProperty("MATCHING_RULE_PROFILE")
	private String matchingruleprofile;
	
	@ApiModelProperty(value = "SOURCE_APPLICATION_ID",position = 4)
	@JsonProperty("SOURCE_APPLICATION_ID")
	private String sourceapplicationid;
	
	@ApiModelProperty(value = "SOURCE_APPLICATION_NO",position = 5)
	@JsonProperty("SOURCE_APPLICATION_NO")
	private String sourceapplicationno;
	
	@ApiModelProperty(value = "SOURCE_AUTHENTICATION_TOKEN",position = 6)
	@JsonProperty("SOURCE_AUTHENTICATION_TOKEN")
	private String sourceauthenticationtoken;
	
	@ApiModelProperty(value = "SOURCE_CUSTOMER_ID",position = 7)
	@JsonProperty("SOURCE_CUSTOMER_ID")
	private String sourcecustomerid;
	
	@ApiModelProperty(value = "SOURCE_SYSTEM_NAME",position = 8)
	@JsonProperty("SOURCE_SYSTEM_NAME")
	private String sourcesystemname;
	
	@ApiModelProperty(value = "DEMOGRAPHIC_INFORMATION",position = 9)
	@JsonProperty("DEMOGRAPHIC_INFORMATION")
	private DemographicInformation demographicInformation;
	
	@ApiModelProperty(value = "ADDRESS_INFORMATION",position = 10)
	@JsonProperty("ADDRESS_INFORMATION")
	private AddressInformation addressInformation;
	
	@ApiModelProperty(value = "CONTACT_INFORMATION",position = 11)
	@JsonProperty("CONTACT_INFORMATION")
	private ContactInformation contactInformation;
	
	@ApiModelProperty(value = "EMAIL_INFORMATION",position = 12)
	@JsonProperty("EMAIL_INFORMATION")
	private EmailInformation emailInformation;
	
	
	//CKYC_NO,TAX_ID,UCIC,UCIC_TYPE,UID_TYPE
	
	@JsonIgnore
	private String ucic;
	@JsonIgnore
	private String ucicType;
	@JsonIgnore
	private String uidType;
	
	
}
