package com.cgcl.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonPropertyOrder({"requestid", "account_status","sourcesystemname", "sourceauthenticationtoken", "sourcecustomerid", "status","responsecode","description","systemAssignedUcic","ucicDescription","custmatchcount","selfmatchcount","negativematchcount",
	"negativeMatchedCustomerDetails","matchedCustomerDetails","matchedCustomerLoanDetails"})
public class CustomerResultsBean {

	@ApiModelProperty(value = "REQUEST_ID",position = 0)
	@JsonProperty("REQUEST_ID")
	private long requestid;
	
	@ApiModelProperty(value = "ACCOUNT_STATUS",position = 1)
	@JsonProperty("ACCOUNT_STATUS")
	private String account_status;
	
	@ApiModelProperty(value = "SOURCE_SYSTEM_NAME",position = 2)
	@JsonProperty("SOURCE_SYSTEM_NAME")
	private String sourcesystemname;
	
	@ApiModelProperty(value = "SOURCE_AUTHENTICATION_TOKEN",position = 3)
	@JsonProperty("SOURCE_AUTHENTICATION_TOKEN")
	private String sourceauthenticationtoken;
	
	@ApiModelProperty(value = "SOURCE_AUTHENTICATION_TOKEN",position = 4)
	@JsonProperty("SOURCE_CUSTOMER_ID")
	private String sourcecustomerid;
	
	@ApiModelProperty(value = "STATUS",position = 5)
	@JsonProperty("STATUS")
	private String status;
	
	@ApiModelProperty(value = "RESPONSE_CODE",position =6)
	@JsonProperty("RESPONSE_CODE")
	private String responsecode;
	
	@ApiModelProperty(value = "DESCRIPTION",position =7)	
	@JsonProperty("DESCRIPTION")
	private String description;
	
	@ApiModelProperty(value = "POSIDEX_GENERATED_UCIC",position =8)	
	@JsonProperty("POSIDEX_GENERATED_UCIC")
	private String systemAssignedUcic;
	
//	@ApiModelProperty(value = "UCIC_DESCRIPTION",position =9)	
//	@JsonProperty("UCIC_DESCRIPTION")
//	private String ucicDescription;
	
	@ApiModelProperty(value = "CUSTOMER_MATCH_COUNT",position =10)	
	@JsonProperty("CUSTOMER_MATCH_COUNT")
	private Integer custmatchcount;
	
	@ApiModelProperty(value = "SELF_MATCH_COUNT",position =11)	
	@JsonProperty("SELF_MATCH_COUNT")
	private Integer selfmatchcount;
	
	@ApiModelProperty(value = "NEGATIVE_MATCH_COUNT",position =12)	
	@JsonProperty("NEGATIVE_MATCH_COUNT")
	private Integer negativematchcount;

	@ApiModelProperty(value = "NEGATIVE_MATCHES",position =13)	
	@JsonProperty("NEGATIVE_MATCHES")
	private List<NegativeMatchedCustomerDetailsBean> negativeMatchedCustomerDetails;

	@ApiModelProperty(value = "CUSTOMER_MATCHES",position =14)	
	@JsonProperty("CUSTOMER_MATCHES")
	private List<MatchedCustomerDetailsBean> matchedCustomerDetails;
	
	@ApiModelProperty(value = "LOAN_INFORMATION",position =15)	
	@JsonProperty("LOAN_INFORMATION")
	private List<MatchedCustomerLoanDetailsBean> matchedCustomerLoanDetails;
	
}
