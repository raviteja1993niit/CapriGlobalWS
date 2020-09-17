package com.cgcl.beans;

import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"requestid","sourceauthenticationtoken","sourcecustomerid","sourcesystemname","ucic","ucictype"})
public class UcicResponseInfoBean {

	@JsonProperty("REQUEST_ID")
	private String requestid;
	@JsonProperty("SOURCE_AUTHENTICATION_TOKEN")
	private String sourceauthenticationtoken;
	@JsonProperty("SOURCE_CUSTOMER_ID")
	private String sourcecustomerid;
	@JsonProperty("SOURCE_SYSTEM_NAME")
	private String sourcesystemname;
	@JsonProperty("STATUS_INFO")
	private String statusinfo;
	@JsonProperty("RESPONSE_CODE")
	private String responseCode;
	@JsonProperty("UCIC")
	private String ucic;
	@JsonProperty("UCIC_TYPE")
	private String ucictype;
	@ApiModelProperty(value = "DESCRIPTION",position =7)	
	@JsonProperty("DESCRIPTION")
	private String description;
}
