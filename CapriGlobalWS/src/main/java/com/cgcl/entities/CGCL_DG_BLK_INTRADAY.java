//package com.cgcl.entities;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Entity
//@Table(name = "cgcl_dg_blk_intraday")
//@Data
//public class CGCL_DG_BLK_INTRADAY {
//
//	@Id
//	@Column(name = "REQUEST_ID", unique = true, nullable = false,length = 50)
//	private long requestId;
//	
//	@Column(name = "CUST_UNQ_ID", nullable = false)
//	private String cust_unq_id;
//	
//	@Column(name = "CUST_ID", nullable = true)
//	private String cust_id;
//	
//	@Column(name = "LEAD_ID", nullable = true)
//	private String lead_id;
//	
//	@Column(name = "BATCH_ID")
//	private String batch_id;
//	
//	@Column(name = "DEAL_ID_APP_ID")
//	private String deal_id_app_id;
//	
//	@Column(name = "PSX_BATCH_ID")
//	private String psx_batch_id;
//	
//	@Column(name = "OLD_PSX_BATCH_ID")
//	private String old_psx_batch_id;
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
//	@Column(name = "NAME")
//	private Date name;
//	
//	@Column(name = "APPLICANT_FULL_NAME")
//	private String applicantFullName;
//	
//	@Column(name = "CIN")
//	private String cin;
//	
//	@Column(name = "FIRST_NAME",length = 100)
//	private String firstname;
//	
//	@Column(name = "MIDDLE_NAME",length = 50)
//	private String middlename;
//
//	@Column(name = "LAST_NAME",length = 50)
//	private String lastname;
//	
//	@Column(name = "DOB")
//	private Date dob;
//	
//	@Column(name = "PAN_NO",length = 10)
//	private String panno;
//	
//	@Column(name = "PASSPORT_NO",length = 50)
//	private String passportno;
//	
//	@Column(name = "VOTER_ID_NO",length = 50)
//	private String voterid;
//	
//	@Column(name = "AADHAR_NO")
//	private String aadharNo;
//	
//	@Column(name = "DRIVING_LIC_NO",length = 50)
//	private String drivinglicense;
//	
//	@Column(name = "FATHER_FULL_NAME",length = 200)
//	private String fathername;
//	
//	@Column(name = "HIGHEST_EDUCATION",length = 255)
//	private String highesteducation;
//	
//	@Column(name = "MOTHER_MAIDEN_NAME",length = 200)
//	private String motherMaidenName;
//		
//	@Column(name = "TAN",length = 50)
//	private String tanno;
//	
//	@Column(name = "PRIMARY_OCCUPATION",length = 255)
//	private String primaryoccupation;
//
//	@Column(name = "RESIDENT_STATUS",length = 255)
//	private String residencestatus;
//	
//	@Column(name = "SOURCE_SYSTEM",length = 50)
//	private String sourcesystemname;
//	
//	
//	@Column(name = "CUSTOMER_CATEGORY_TYPE",length = 3)
//	private String customercategory;
//	
//	@Column(name = "CUSTOMER_TYPE_CODE",length = 255)
//	private String customertypecode;
//
//	@Column(name = "GENDER",length = 1)
//	private String gender;
//	
//	@Column(name = "MARITAL_STATUS",length = 10)
//	private String martialstatus;
//	
//	@Column(name = "CUSTOMER_NO")
//	private String customer;
//	
//
//	@Column(name = "ACCOUNT_STATUS",length = 255)
//	private String account_status;
//
//	@Column(name = "CASTE",length = 20)
//	private String caste;
//
//
//	@Column(name = "CIBIL_SCORE",length = 255)
//	private String cibil_score;
//
//
//	@Column(name = "DINNO",length = 255)
//	private String dinno;
//
//
//	@Column(name = "DOI")
//	private Date doi;
//
//	@Column(name = "GSTIN",length = 50)
//	private String gstin;
//
//	@Column(name = "LAN",length = 20)
//	private String lan;
//
//
//	@Column(name = "MATCHING_RULE_PROFILE",length = 3)
//	private String matchingruleprofile;
//
//	@Column(name = "PRODUCT",length = 200)
//	private String product;
//	
//
//	@Column(name = "REGISTRATION_NO",length = 50)
//	private String registration_no;
//	
//
//	@Column(name = "RELIGION",length = 20)
//	private String religion;
//
//	@Column(name = "SOURCE_APPLICATION_ID",length = 10)
//	private int sourceapplicationid;
//	
//
//	@Column(name = "SOURCE_APPLICATION_NO")
//	private String sourceapplicationno;
//	
//
//	@Column(name = "SOURCE_AUTHENTICATION_TOKEN",length =100)
//	private String sourceauthenticationtoken;
//	
//
//	@Column(name = "SOURCE_CUSTOMER_ID",length = 32)
//	private String sourcecustomerid;
//
//	@Column(name = "SPOUSE_NAME",length = 200)
//	private String spousename;
//
//	@Column(name = "TITLE",length = 60)
//	private String title;
//	
//
//	@Column(name = "UID", scale = 12)
//	private int uid;
//	
//	@Column(name = "CKYC_NO")
//	private String cycNo;
//	
//	@Column(name = "MOTHER_NAME")
//	private String motherName;
//	
//	@Column(name = "TAX_ID")
//	private String taxId;
//
//	@Column(name = "UCIC")
//	private String ucic;
//	
//	@Column(name = "UCIC_TYPE")
//	private String ucicType;
//	
//	@Column(name = "UID_TYPE")
//	private String uidType;
//	
//	
//	
//}
