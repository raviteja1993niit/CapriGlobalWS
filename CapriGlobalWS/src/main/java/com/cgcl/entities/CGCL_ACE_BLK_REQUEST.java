//package com.cgcl.entities;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Entity
//@Table(name = "cgcl_ace_blk_request")
//@Data
//public class CGCL_ACE_BLK_REQUEST {
//
//	@Id
//	@Column(name = "REQUEST_ID", unique = true, nullable = false)
//	private long requestId;
//	
//	@Column(name = "ADDRESS", length = 1000)
//	private String address;
//	
//
//	@Column(name = "ADDRESS_ID",length = 50)
//	private int addressid;
//	
//
//	@Column(name = "ADDRESS_TYPE",length = 60,nullable = false)
//	private String addresstype;
//	
//	@Column(name = "CITY",length = 50)
//	private String city;
//	
//	@Column(name = "MOBILE_NUMBER",length = 15)
//	private String mobilenumber;
//		
//	@Column(name = "LANDLINE_NUMBER",length = 15)
//	private String landlinenumber;
//	
//	@Column(name = "PINCODE",length = 6)
//	private Integer pincode;
//	
//	@Column(name = "CUSTOMER_NO")
//	private String customerNo;
//	
//	@Column(name = "EMAIL",length = 50)
//	private String email;
//	
//	@Column(name = "STATE",length = 50)
//	private String state;
//	
//	@Column(name = "CUST_UNQ_ID")
//	private String custUnqId;
//	
//	@Column(name = "CUST_ID")
//	private String custId;
//	
//	@Column(name = "LEAD_ID")
//	private String leadId;
//	
//	@Column(name = "BATCH_ID")
//	private String batchId;
//	
//	@Column(name = "DEAL_ID_APP_ID")
//	private String dealIdAppId;
//	
//	@Column(name = "PSX_BATCH_ID")
//	private String psxBatchId;
//	
//	@Column(name = "OLD_PSX_BATCH_ID")
//	private String oldPsxBatchId;
//	
//	@Column(name = "LCHGTIME")
//	private Date lchgtime;
//	
//	@Column(name = "DUIFLAG")
//	private String duiflag;
//	
//	@Column(name = "EVENTTYPE")
//	private String eventtype;
//	
//	@Column(name = "INSERT_TIME")
//	private Date inserttime;
//	
////	@Column(name = "CURRENT_ADDRESS")
////	private String currentAddress;
////	
////	@Column(name = "CURRENT_ADD_CITY")
////	private String currentAddCity;
////	
////	@Column(name = "CURRENT_PINCODE")
////	private Integer currentPincode;
////	
////	@Column(name = "CURRENT_STATE")
////	private String currentState;
////	
////	@Column(name = "CURRENT_ADDRESS_TYPE")
////	private String currentAddressType;
////	
////	@Column(name = "PERMANENT_ADDRESS")
////	private String permanentAddress;
////	
////	@Column(name = "PERMANENT_ADD_CITY")
////	private String permanentAddCity;
////	
////	@Column(name = "PERMANENT_PINCODE")
////	private Integer permanentPincode;
////	
////	@Column(name = "PERMANENT_STATE")
////	private String permanentState;
////	
////	@Column(name = "PERMANENT_ADDRESS_TYPE")
////	private String permanentAddressType;
////	
////	@Column(name = "EMPLOYER_ADDRESS")
////	private String employerAddress;
////	
////	@Column(name = "EMPLOYER_ADD_CITY")
////	private String employerAddCity;
////	
////	@Column(name = "EMPLOYER_PINCODE")
////	private Integer employerPincode;
////	
////	@Column(name = "EMPLOYER_STATE")
////	private String employerState;
////	
////	@Column(name = "EMPLOYER_ADDRESS_TYPE")
////	private String employerAddressType;
//	
//	@Column(name = "CUSTOMER_CONTACT")
//	private String customerContact;
//	
//	@Column(name = "CUSTOMER_CONTACT_TYPE")
//	private String customerContactType;
//	
//	@Column(name = "CUSTOMER_LANDLINE")
//	private String customerLandline;
//	
//	@Column(name = "CUSTOMER_LANDLINE_TYPE")
//	private String customerLandlineType;
//	
//	@Column(name = "EMAIL_TYPE")
//	private String emailType;
//		
//}
