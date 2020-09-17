package com.cgcl.beans;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
//@JsonPropertyOrder({"firstname","middlename","lastname","mothername","fathername","spousename","gender","martialstatus","dob","doi","highesteducation","primaryoccupation","customertypecode",
//	"residencestatus","panno","voterid","passportno","drivinglicense","uid","gstin","registrationorcinno","dinno","tanno","cibil_score","account_status","addressid","addresstype","address",
//	"city","pincode","state","mobilenumber","landlinenumber","email","ucic"})
//@JsonPropertyOrder(alphabetic=true)
public class MatchedCustomerDetailsBean {

//	@JsonProperty("ADDRESS")
//	private String address;
//	@JsonProperty("ADDRESS_ID")
//	private String addressid;
//	@JsonProperty("ADDRESS_TYPE")
//	private String addresstype;
	
	
	@ApiModelProperty(value = "NAME",position = 1)
	@JsonProperty("NAME")
	private String name;
	
	@ApiModelProperty(value = "FIRST_NAME",position = 2)
	@JsonProperty("FIRST_NAME")
   	private String firstname;
	
	@ApiModelProperty(value = "LAST_NAME",position = 3)
  	@JsonProperty("LAST_NAME")
  	private String lastname;
	

	@ApiModelProperty(value = "MIDDLE_NAME",position = 4)
 	@JsonProperty("MIDDLE_NAME")
 	private String middlename;
	
	
	
	//@JsonProperty("FATHER_NAME")
	@JsonIgnore
	private String fathername;
	
	@ApiModelProperty(value = "FATHER_FIRST_NAME",position = 5)
	@JsonProperty("FATHER_FIRST_NAME")
	private String fatherFirstName;
	
	@ApiModelProperty(value = "FATHER_LAST_NAME",position = 6)
	@JsonProperty("FATHER_LAST_NAME")
	private String fatherLastName;
	
	@ApiModelProperty(value = "MOTHER_NAME",position = 7)
	@JsonProperty("MOTHER_NAME")
	private String mothername;

	@ApiModelProperty(value = "SPOUSE_NAME",position = 8)
	@JsonProperty("SPOUSE_NAME")
	private String spousename;
	
	@ApiModelProperty(value = "DOB",position = 9)
	@JsonProperty("DOB")
	private Date dob;

	@ApiModelProperty(value = "DOI",position = 10)
	@JsonProperty("DOI")
	private Date doi;
	
	@ApiModelProperty(value = "GENDER",position = 11)
	@JsonProperty("GENDER")
	private String gender;
	
	@ApiModelProperty(value = "MARTIAL_STATUS",position = 12)
	@JsonProperty("MARTIAL_STATUS")
	private String martialstatus;
	
	@ApiModelProperty(value = "HIGHER_EDUCATION",position = 13)
	@JsonProperty("HIGHER_EDUCATION")
	private String highesteducation;
	
	@ApiModelProperty(value = "PRIMARY_OCCUPATION",position = 14)
	@JsonProperty("PRIMARY_OCCUPATION")
	private String primaryoccupation;
	
	
	@ApiModelProperty(value = "AADHAR_NO",position = 15)
	@JsonProperty("AADHAR_NO")
	private String aadharNo;
	
	@ApiModelProperty(value = "PAN_NO",position = 16)
	@JsonProperty("PAN_NO")
	private String panno;
	
	@ApiModelProperty(value = "PASSPORT_NO",position = 17)
	@JsonProperty("PASSPORT_NO")
	private String passportno;
	
	@ApiModelProperty(value = "DRIVING_LIC_NO",position = 18)
	@JsonProperty("DRIVING_LIC_NO")
	private String drivinglicense;
	
	@ApiModelProperty(value = "CUSTOMER_TYPE_CODE",position = 19)
	@JsonProperty("CUSTOMER_TYPE_CODE")
	private String customertypecode;
	
	@ApiModelProperty(value = "CIN",position = 20)
	@JsonProperty("CIN")
	private String registrationorcinno;
	
	@ApiModelProperty(value = "CIBIL_SCORE",position = 21)
	@JsonProperty("CIBIL_SCORE")
	private String cibil_score;
	
	@ApiModelProperty(value = "DIN",position = 22)
	@JsonProperty("DIN")
	private String dinno;

	@ApiModelProperty(value = "RESIDENT_STATUS",position = 23)
	@JsonProperty("RESIDENT_STATUS")
	private String residencestatus;
	
	@ApiModelProperty(value = "TAN",position = 24)
	@JsonProperty("TAN")
	private String tanno;
	
	@ApiModelProperty(value = "UCIC",position = 25)
	@JsonProperty("UCIC")
	private String ucic;
	
	@ApiModelProperty(value = "UID",position = 26)
	@JsonProperty("UID")
	private String uid;
	
	@ApiModelProperty(value = "VOTER_ID",position =27)
	@JsonProperty("VOTER_ID")
	private String voterid;
	
	@ApiModelProperty(value = "GSTIN",position = 28)
	@JsonProperty("GSTIN")
	private String gstin;
	

	
	//@JsonProperty("CURRENT_ADDRESS")
	@ApiModelProperty(value = "ADDRESS_0",position = 29)
	@JsonProperty("ADDRESS_0")
	private String currentAddress;
	
	//@JsonProperty("CURRENT_ADDRESS_ID")
	@ApiModelProperty(value = "ADDRESS_ID_0",position = 30)
	@JsonProperty("ADDRESS_ID_0")
	private String currentAddressId;
	
	//@JsonProperty("CURRENT_ADDRESS_TYPE")
	@ApiModelProperty(value = "ADDRESS_TYPE_0",position =31)
	@JsonProperty("ADDRESS_TYPE_0")
	private String currentAddressType;
	
	@ApiModelProperty(value = "CITY_0",position = 32)
	@JsonProperty("CITY_0")
	private String currentCity;
	
	//@JsonProperty("CURRENT_STATE")
	@ApiModelProperty(value = "STATE_0",position = 33)
	@JsonProperty("STATE_0")
	private String currentState;
	
	//@JsonProperty("CURRENT_PINCODE")
	@ApiModelProperty(value = "PINCODE_0",position = 34)
	@JsonProperty("PINCODE_0")
	private String currentPincode;
	
	
	
//	@JsonProperty("PERMANENT_ADDRESS")
	@ApiModelProperty(value = "ADDRESS_1",position = 35)
	@JsonProperty("ADDRESS_1")
	private String permanentAddress;
	
//	@JsonProperty("PERMANENT_ADDRESS_ID")
	@ApiModelProperty(value = "ADDRESS_ID_1",position =36)
	@JsonProperty("ADDRESS_ID_1")
	private String permanentAddressId;
	
//	@JsonProperty("PERMANENT_ADDRESS_TYPE")
	@ApiModelProperty(value = "ADDRESS_TYPE_1",position = 37)
	@JsonProperty("ADDRESS_TYPE_1")
	private String permanentAddressType;
	
	@ApiModelProperty(value = "CITY_1",position = 38)
	@JsonProperty("CITY_1")
	private String permanentCity;
	

//	@JsonProperty("PERMANENT_STATE")
	@ApiModelProperty(value = "STATE_1",position = 39)
	@JsonProperty("STATE_1")
	private String permanentState;
	
//	@JsonProperty("PERMANENT_PINCODE")
	@ApiModelProperty(value = "PINCODE_1",position = 40)
	@JsonProperty("PINCODE_1")
	private String permanentPincode;
	
	
	
//	@JsonProperty("EMPLOYER_ADDRESS")
	@ApiModelProperty(value = "ADDRESS_2",position = 41)
	@JsonProperty("ADDRESS_2")
	private String employerAddress;
	
//	@JsonProperty("EMPLOYER_ADDRESS_ID")
	@ApiModelProperty(value = "ADDRESS_ID_2",position =42)
	@JsonProperty("ADDRESS_ID_2")
	private String employerAddressId;
	
//	@JsonProperty("EMPLOYER_ADDRESS_TYPE")
	@ApiModelProperty(value = "ADDRESS_TYPE_2",position = 43)
	@JsonProperty("ADDRESS_TYPE_2")
	private String employerAddressType;
	
	
	@ApiModelProperty(value = "CITY_2",position = 44)
	@JsonProperty("CITY_2")
	private String employerCity;
	
//	@JsonProperty("EMPLOYER_PINCODE")
	@ApiModelProperty(value = "PINCODE_2",position = 45)
	@JsonProperty("PINCODE_2")
	private String employerPincode;
	
//	@JsonProperty("EMPLOYER_STATE")
	@ApiModelProperty(value = "STATE_2",position = 46)
	@JsonProperty("STATE_2")
	private String employerState;
	
//	@JsonProperty("FACTORY_ADDRESS")
	@ApiModelProperty(value = "ADDRESS_3",position = 47)
	@JsonProperty("ADDRESS_3")
	private String factoryAddress;
	
//	@JsonProperty("FACTORY_ADDRESS_ID")
	@ApiModelProperty(value = "ADDRESS_ID_3",position = 48)
	@JsonProperty("ADDRESS_ID_3")
	private String factoryAddressId;
	
//	@JsonProperty("FACTORY_ADDRESS_TYPE")
	@ApiModelProperty(value = "ADDRESS_TYPE_3",position = 49)
	@JsonProperty("ADDRESS_TYPE_3")
	private String factoryAddressType;
	
	@ApiModelProperty(value = "CITY_3",position = 50)
	@JsonProperty("CITY_3")
	private String factoryCity;
	
//	@JsonProperty("FACTORY_PINCODE")
	@ApiModelProperty(value = "PINCODE_3",position = 51)
	@JsonProperty("PINCODE_3")
	private String factoryPincode;
	
//	@JsonProperty("FACTORY_STATE")
	@ApiModelProperty(value = "STATE_3",position =52)
	@JsonProperty("STATE_3")
	private String factoryState;
	
	
	
//	@JsonProperty("CURRENT_PHONE")
	@ApiModelProperty(value = "PHONE_1",position =53)
	@JsonProperty("PHONE_1")
	private String currentPhone;
	
//	@JsonProperty("PERMANENT_PHONE")
	@ApiModelProperty(value = "PHONE_2",position = 54)
	@JsonProperty("PHONE_2")
	private String permanentPhone;
	
	@ApiModelProperty(value = "LANDLINE_NUMBER_1",position =55)
	@JsonProperty("LANDLINE_NUMBER_1")
	private String landlinenumber1;
	
//	@JsonProperty("PERMANENT_PHONE")
	@ApiModelProperty(value = "LANDLINE_NUMBER_2",position = 56)
	@JsonProperty("LANDLINE_NUMBER_2")
	private String landlinenumber2;
	
//	@JsonProperty("CURRENT_EMAIL")
	@ApiModelProperty(value = "EMAIL_1",position = 57)
	@JsonProperty("EMAIL_1")
	private String currentEmail;
//	@JsonProperty("PERMANENT_EMAIL")
	@ApiModelProperty(value = "EMAIL_2",position = 58)
	@JsonProperty("EMAIL_2")
	private String permanentEmail;

	
	@ApiModelProperty(value = "RECORD_TYPE",position =59)	
	@JsonProperty("RECORD_TYPE")
	private String recordType;
	
	@ApiModelProperty(value = "MATCH_CRITERIA",position =60)	
	@JsonProperty("MATCH_CRITERIA")
	private String matchCriteria;

	@ApiModelProperty(value = "MATCHED_ID",position =61)	
	@JsonProperty("MATCHED_ID")
	private String matchId;
	
	@ApiModelProperty(value = "SCALE_TYPE",position =62)	
	@JsonProperty("SCALE_TYPE")
	private String scaleType;

	@ApiModelProperty(value = "ACCOUNT_STATUS",position =63)	
	@JsonProperty("ACCOUNT_STATUS")
	private String accountStatus;

	}
