package com.cgcl.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"requestid","basicloanamount","currentpos","customerno","dpd","finterestrate","income","loanamount","loanamountno","loanstatus","status","tenor"})
public class MatchedCustomerLoanDetailsBean {

	@JsonProperty("APPLICATION_NO")
	private String applicationno;
	@JsonProperty("BASIC_LOAN_AMOUNT")
	private String basicloanamount;
	@JsonProperty("CURRENT_POSITION")
	private String currentpos;
	@JsonProperty("CUSTOMER_NO")
	private String customerno;
	@JsonProperty("DPD")
	private String dpd;
	@JsonProperty("F_INTEREST_RATE")
	private String finterestrate;
	@JsonProperty("INCOME")
	private String income;
	@JsonProperty("LAON_AMOUNT")
	private String loanamount;
	@JsonProperty("LOAN_AMOUNT_NO")
	private String loanamountno;
	@JsonProperty("LOAN_STATUS")
	private String loanstatus;
	@JsonProperty("STATUS")
	private String status;
	@JsonProperty("TENOR")
	private String tenor;
}
