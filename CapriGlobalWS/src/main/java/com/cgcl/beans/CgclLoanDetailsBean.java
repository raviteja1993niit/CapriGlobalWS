package com.cgcl.beans;

import lombok.Data;

@Data
public class CgclLoanDetailsBean {
	public long id;
	public String customer_no;
	public String application_no;
	public String loan_account_no;
	public String basic_loan_amount;
	public String loan_amount;
	public String tenor;
	public String f_interest_rate;
	public String dpd;
	public String current_pos;
	public String income;
	public String status;
	public String loan_status;
}
