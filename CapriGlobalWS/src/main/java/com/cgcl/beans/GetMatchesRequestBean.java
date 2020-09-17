package com.cgcl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonPropertyOrder({"requestid", "sourcesystemname", "sourceauthenticationtoken"})
public class GetMatchesRequestBean {

	@ApiModelProperty(value = "REQUEST_ID",position = 0)
	@JsonProperty("REQUEST_ID")
	private long requestid;
	@ApiModelProperty(value = "SOURCE_SYSTEM_NAME",position = 1)
	@JsonProperty("SOURCE_SYSTEM_NAME")
	private String sourcesystemname;
	@ApiModelProperty(value = "SOURCE_AUTHENTICATION_TOKEN",position = 2)
	@JsonProperty("SOURCE_AUTHENTICATION_TOKEN")
	private String sourceauthenticationtoken;
}
