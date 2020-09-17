package com.cgcl.beans;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder(alphabetic=true)
public class DemographicInformation {
	
	@JsonProperty("AADHAR_NO")
	private String aadhar_no;
	
	@JsonProperty("ACCOUNT_STATUS")
	private String account_status;

	@JsonProperty("CASTE")
	private String caste;
	
	@JsonProperty("CIBIL_SCORE")
	private String cibil_score;
	
	@JsonProperty("CUSTOMER_TYPE_CODE")
	private String customertypecode;
	
	@JsonProperty("DINNO")
	private String dinno;
	
	@JsonProperty("DOB")
	private String dob;
	
	@JsonProperty("DOI")
	private String doi;
	
	@JsonProperty("DRIVING_LICENSE_NO")
	private String drivinglicense;
	
	//@JsonProperty("FATHER_NAME")
	@JsonIgnore
	private String fathername;
	
	@JsonProperty("FATHER_FIRST_NAME")
	private String fatherFirstName;
	
	@JsonProperty("FATHER_LAST_NAME")
	private String fatherLastName;
	
	@JsonProperty("FIRST_NAME")
	private String firstname;
	
	@JsonProperty("GENDER")
	private String gender;
	
	@JsonProperty("GSTIN")
	private String gstin;
	
	@JsonProperty("HIGHEST_EDUCATION")
	private String highesteducation;
	
	@JsonProperty("LAN")
	private String lan;
	
	@JsonProperty("LAST_NAME")
	private String lastname;
	
	@JsonProperty("MARTIAL_STATUS")
	private String martialstatus;
	
	@JsonProperty("MIDDLE_NAME")
	private String middlename;
	
	@JsonProperty("MOTHER_NAME")
	private String mothername;
	
	@JsonProperty("PAN_NO")
	private String panno;
	
	@JsonProperty("PASSPORT_NO")
	private String passportno;
	
	@JsonProperty("PRIMARY_OCCUPATION")
	private String primaryoccupation;
	
	@JsonProperty("PRODUCT")
	private String product;
	
//	@JsonProperty("REGISTRATION_NO")
	@JsonProperty("CINNO")
	private String cno;
//	private String registration_no;
	
	@JsonProperty("RELIGION")
	private String religion;
	
	@JsonProperty("RESIDENCE_STATUS")
	private String residencestatus;
	
	@JsonProperty("SPOUSE_NAME")
	private String spousename;
	
	
	@JsonProperty("TAN_NO")
	private String tanno;
	
	@JsonProperty("TITLE")
	private String title;
	
	@JsonProperty("UID")
	private int uid;
	
	@JsonProperty("VOTER_ID")
	private String voterid;
	
	@JsonProperty("CYC_NO")
	private String cycNo;
	
	@JsonProperty("TAX_ID")
	private String taxId;

}
