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
//@Table(name = "cgcl_ace_blk_intraday")
//@Data
//public class CGCL_ACE_BLK_INTRADAY {
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
//	private int  addressid;
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
//	private String customer_no;
//	
//	@Column(name = "EMAIL",length = 50)
//	private String email;
//	
//	@Column(name = "STATE",length = 50)
//	private String state;
//	
//	@Column(name = "CUST_UNQ_ID")
//	private String cust_unq_id;
//	
//	@Column(name = "CUST_ID")
//	private String cust_id;
//	
//	@Column(name = "LEAD_ID")
//	private String lead_id;
//	
//	@Column(name = "BATCH_ID")
//	private String batch_id;
//	
//	@Column(name = "DEAL_ID_APP_ID")
//	private String deal_id_app_id;
//	
//	@Column(name = "PSX_BATCH_ID")
//	private String psxBatchId;
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
////	@Column(name = "CURRENT_ADDRESS")
////	private String current_address;
////	
////	@Column(name = "CURRENT_ADD_CITY")
////	private String current_add_city;
////	
////	@Column(name = "CURRENT_PINCODE")
////	private Integer current_pincode;
////	
////	@Column(name = "CURRENT_STATE")
////	private String current_state;
////	
////	@Column(name = "CURRENT_ADDRESS_TYPE")
////	private String current_address_type;
////	
////	@Column(name = "PERMANENT_ADDRESS")
////	private String permanent_address;
////	
////	@Column(name = "PERMANENT_ADD_CITY")
////	private String permanent_add_city;
////	
////	@Column(name = "PERMANENT_PINCODE")
////	private Integer permanent_pincode;
////	
////	@Column(name = "PERMANENT_STATE")
////	private String permanent_state;
////	
////	@Column(name = "PERMANENT_ADDRESS_TYPE")
////	private String permanent_address_type;
////	
////	@Column(name = "EMPLOYER_ADDRESS")
////	private String employer_address;
////	
////	@Column(name = "EMPLOYER_ADD_CITY")
////	private String employer_add_city;
////	
////	@Column(name = "EMPLOYER_PINCODE")
////	private Integer employer_pincode;
////	
////	@Column(name = "EMPLOYER_STATE")
////	private String employer_state;
////	
////	@Column(name = "EMPLOYER_ADDRESS_TYPE")
////	private String employer_address_type;
//	
//	@Column(name = "CUSTOMER_CONTACT")
//	private String customer_contact;
//	
//	@Column(name = "CUSTOMER_CONTACT_TYPE")
//	private String customer_contact_type;
//	
//	@Column(name = "CUSTOMER_LANDLINE")
//	private String customer_landline;
//	
//	@Column(name = "CUSTOMER_LANDLINE_TYPE")
//	private String customer_landline_type;
//	
//	@Column(name = "EMAIL_TYPE")
//	private String email_type;
//		
//}
