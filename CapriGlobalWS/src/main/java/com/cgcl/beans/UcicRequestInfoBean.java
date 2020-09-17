package com.cgcl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"requestid","sourceauthenticationtoken","sourcecustomerid","sourcesystemname","ucic","ucictype"})
public class UcicRequestInfoBean {

	@JsonProperty("REQUEST_ID")
	private long requestid;
	@JsonProperty("SOURCE_AUTHENTICATION_TOKEN")
	private String sourceauthenticationtoken;
	@JsonProperty("SOURCE_CUSTOMER_ID")
	private String sourcecustomerid;
	@JsonProperty("SOURCE_SYSTEM_NAME")
	private String sourcesystemname;
	@JsonProperty("UCIC")
	private int ucic;
	@JsonProperty("UCIC_TYPE")
	private String ucictype;
}
