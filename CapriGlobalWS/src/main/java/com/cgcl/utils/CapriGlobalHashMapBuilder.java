package com.cgcl.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cgcl.beans.ACE;
import com.cgcl.beans.Address;
import com.cgcl.beans.Contact;
import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.beans.Email;
import com.cgcl.beans.MatchedAddressStrengths;

@Component
public class CapriGlobalHashMapBuilder {
	
	private static Logger logger = Logger.getLogger(CapriGlobalHashMapBuilder.class.getName());
	
	
	
	@Autowired
	private Environment environment;
	
	
	
	public HashMap<String, Object> buildAceHashMap(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId)throws Exception {
		logger.info("Inside build ACE HashMap");
		HashMap<String, Object> dg_row1 = new HashMap<>();
		try {
			
			String flattenedAddress=buidingFlattenedAddress(customerRequest);
		//	String flattenedAddressId=customerRequest.getAddressInformation().getAddressid0()+"^;"+customerRequest.getAddressInformation().getAddressid1()+"^;"+customerRequest.getAddressInformation().getAddressid2()+"^;"+customerRequest.getAddressInformation().getAddressid3()+"^;";
			String flattenedAddressType=buidingFlattenedAddressType(customerRequest);
			String flattenedCity=buidingFlattenedCity(customerRequest);
			String flattenedPincode=buidingFlattenedPincode(customerRequest);
			String flattenedState=buidingFlattenedState(customerRequest);
			String flattenedContact=buidingFlattenedContact(customerRequest) ;
			String flattenedContactType=buidingFlattenedContactType(customerRequest) ;
			String flattenedLandLine=buidingFlattenedLandLine(customerRequest);
			String flattenedLandLineType=buidingFlattenedLandLineType(customerRequest);
			String flattenedEmail=buidingFlattenedEmail(customerRequest);
			String flattenedEmailType=buidingFlattenedEmailType(customerRequest);
			
			if (flattenedAddress != null && !("".equals(flattenedAddress)))
				dg_row1.put("ADDRESS", flattenedAddress);
			if (flattenedAddressType != null && !("".equals(flattenedAddressType)))
				dg_row1.put("ADDRESS_TYPE", flattenedAddressType);
		
			if (flattenedCity != null && !("".equals(flattenedCity)))
				dg_row1.put("CITY", flattenedCity);
			if (flattenedPincode != null && !("".equals(flattenedPincode)))
				dg_row1.put("PINCODE",flattenedPincode);
			if (flattenedState != null && !("".equals(flattenedState)))
				dg_row1.put("STATE", flattenedState);
			
			if (flattenedEmail != null && !("".equals(flattenedEmail)))
				dg_row1.put("EMAIL", flattenedEmail);
			if (flattenedEmailType != null && !("".equals(flattenedEmailType)))
				dg_row1.put("EMAIL_TYPE", flattenedEmailType);
			if (flattenedContact!= null && !("".equals(flattenedContact)))
				dg_row1.put("CUSTOMER_CONTACT", flattenedContact);
			if (flattenedContactType != null && !("".equals(flattenedContactType)))
				dg_row1.put("CUSTOMER_CONTACT_TYPE",flattenedContactType);
					
			if (flattenedLandLine != null && !("".equals(flattenedLandLine)))
				dg_row1.put("CUSTOMER_LANDLINE", flattenedLandLine);
			if (flattenedLandLineType != null && !("".equals(flattenedLandLineType)))
				dg_row1.put("CUSTOMER_LANDLINE_TYPE", flattenedLandLineType);
			
			if (flattenedAddress != null && !("".equals(flattenedAddress)))
				dg_row1.put("PROPADDRESS", flattenedAddress);
			if (flattenedAddressType != null && !("".equals(flattenedAddressType)))
				dg_row1.put("PROPADDRESS_TYPE", flattenedAddressType);
			
			if (flattenedContact!= null && !("".equals(flattenedContact)))
				dg_row1.put("MOBILE", flattenedContact);
			if (flattenedContactType != null && !("".equals(flattenedContactType)))
				dg_row1.put("MOBILE_TYPE",flattenedContactType);
			
			if (customerRequest.getSourcecustomerid() != null && !("".equals(customerRequest.getSourcecustomerid())))
				dg_row1.put("CUSTOMER_NO", customerRequest.getSourcecustomerid());
			dg_row1.put("INSERT_TIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
			dg_row1.put("LCHGTIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
			dg_row1.put("PSX_BATCH_ID", psxBatchId);
			dg_row1.put("CUST_UNQ_ID", customerRequest.getSourcecustomerid()+"_"+customerRequest.getSourcesystemname());
			dg_row1.put("DUIFLAG", "I_OR_U");
		} catch (Exception e) {
			logger.error(e,e);
			logger.error("Exception while getting BuildHashMap:");
			logger.error(e, e);

			throw e;
		}
		return dg_row1;
	}
	
	
	
	
	private String buidingFlattenedAddress(CustomerRequestInformation customerRequest) {
	    String flattenedAddress="";
	    logger.info("Inside building Flattened Address ");
	    if(customerRequest.getAddressInformation().getAddress0() != null && !customerRequest.getAddressInformation().getAddress0().equals("") && !customerRequest.getAddressInformation().getAddress0().equalsIgnoreCase("null")){
	    	flattenedAddress=flattenedAddress+customerRequest.getAddressInformation().getAddress0()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getAddress1() != null && !customerRequest.getAddressInformation().getAddress1().equals("") && !customerRequest.getAddressInformation().getAddress1().equalsIgnoreCase("null")){
	    	flattenedAddress=flattenedAddress+customerRequest.getAddressInformation().getAddress1()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getAddress2() != null && !customerRequest.getAddressInformation().getAddress2().equals("") && !customerRequest.getAddressInformation().getAddress2().equalsIgnoreCase("null")){
	    	flattenedAddress=flattenedAddress+customerRequest.getAddressInformation().getAddress2()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getAddress3() != null && !customerRequest.getAddressInformation().getAddress3().equals("") && !customerRequest.getAddressInformation().getAddress3().equalsIgnoreCase("null")){
	    	flattenedAddress=flattenedAddress+customerRequest.getAddressInformation().getAddress3()+"^;";
	    }
		return flattenedAddress;
	}
	private String buidingFlattenedAddressType(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened Address Type ");
	    String flattenedAddressType="";
	    if(customerRequest.getAddressInformation().getAddresstype0() != null && !customerRequest.getAddressInformation().getAddresstype0().equals("") && !customerRequest.getAddressInformation().getAddresstype0().equalsIgnoreCase("null")){
	    	flattenedAddressType=flattenedAddressType+customerRequest.getAddressInformation().getAddresstype0()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getAddresstype1() != null && !customerRequest.getAddressInformation().getAddresstype1().equals("") && !customerRequest.getAddressInformation().getAddresstype1().equalsIgnoreCase("null")){
	    	flattenedAddressType=flattenedAddressType+customerRequest.getAddressInformation().getAddresstype1()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getAddresstype2() != null && !customerRequest.getAddressInformation().getAddresstype2().equals("") && !customerRequest.getAddressInformation().getAddresstype2().equalsIgnoreCase("null")){
	    	flattenedAddressType=flattenedAddressType+customerRequest.getAddressInformation().getAddresstype2()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getAddresstype3() != null && !customerRequest.getAddressInformation().getAddresstype3().equals("") && !customerRequest.getAddressInformation().getAddresstype3().equalsIgnoreCase("null")){
	    	flattenedAddressType=flattenedAddressType+customerRequest.getAddressInformation().getAddresstype3()+"^;";
	    }
		return flattenedAddressType;
	}
	private String buidingFlattenedCity(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened City ");
	    String flattenedCity="";
	    if(customerRequest.getAddressInformation().getCity0() != null && !customerRequest.getAddressInformation().getCity0().equals("")  && !customerRequest.getAddressInformation().getCity0().equalsIgnoreCase("null")){
	    	flattenedCity=flattenedCity+customerRequest.getAddressInformation().getCity0()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getCity1() != null && !customerRequest.getAddressInformation().getCity1().equals("")  && !customerRequest.getAddressInformation().getCity1().equalsIgnoreCase("null")){
	    	flattenedCity=flattenedCity+customerRequest.getAddressInformation().getCity1()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getCity2() != null && !customerRequest.getAddressInformation().getCity2().equals("")  && !customerRequest.getAddressInformation().getCity2().equalsIgnoreCase("null")){
	    	flattenedCity=flattenedCity+customerRequest.getAddressInformation().getCity2()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getCity3() != null && !customerRequest.getAddressInformation().getCity3().equals("")  && !customerRequest.getAddressInformation().getCity3().equalsIgnoreCase("null")){
	    	flattenedCity=flattenedCity+customerRequest.getAddressInformation().getCity3()+"^;";
	    }
		return flattenedCity;
	}
	private String buidingFlattenedPincode(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened Pincode ");
	    String flattenedPincode="";
	    if(customerRequest.getAddressInformation().getPincode0() != null && !customerRequest.getAddressInformation().getPincode0().equals("")  && !customerRequest.getAddressInformation().getPincode0().equalsIgnoreCase("null")){
	    	flattenedPincode=flattenedPincode+customerRequest.getAddressInformation().getPincode0()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getPincode1() != null && !customerRequest.getAddressInformation().getPincode1().equals("")  && !customerRequest.getAddressInformation().getPincode1().equalsIgnoreCase("null")){
	    	flattenedPincode=flattenedPincode+customerRequest.getAddressInformation().getPincode1()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getPincode2() != null && !customerRequest.getAddressInformation().getPincode2().equals("")  && !customerRequest.getAddressInformation().getPincode2().equalsIgnoreCase("null")){
	    	flattenedPincode=flattenedPincode+customerRequest.getAddressInformation().getPincode2()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getPincode3() != null && !customerRequest.getAddressInformation().getPincode3().equals("") && !customerRequest.getAddressInformation().getPincode3().equalsIgnoreCase("null")){
	    	flattenedPincode=flattenedPincode+customerRequest.getAddressInformation().getPincode3()+"^;";
	    }
		return flattenedPincode;
	}
	private String buidingFlattenedState(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened State ");
	    String flattenedState="";
	    if(customerRequest.getAddressInformation().getState0() != null && !customerRequest.getAddressInformation().getState0().equals("") && !customerRequest.getAddressInformation().getState0().equalsIgnoreCase("null")){
	    	flattenedState=flattenedState+customerRequest.getAddressInformation().getState0()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getState1() != null && !customerRequest.getAddressInformation().getState1().equals("") && !customerRequest.getAddressInformation().getState1().equalsIgnoreCase("null") ){
	    	flattenedState=flattenedState+customerRequest.getAddressInformation().getState1()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getState2() != null && !customerRequest.getAddressInformation().getState2().equals("") && !customerRequest.getAddressInformation().getState2().equalsIgnoreCase("null")){
	    	flattenedState=flattenedState+customerRequest.getAddressInformation().getState2()+"^;";
	    }
	    if(customerRequest.getAddressInformation().getState3() != null && !customerRequest.getAddressInformation().getState3().equals("") && !customerRequest.getAddressInformation().getState3().equalsIgnoreCase("null")){
	    	flattenedState=flattenedState+customerRequest.getAddressInformation().getState3()+"^;";
	    }
		return flattenedState;
	}
	private String buidingFlattenedContact(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened Contact ");
	    String flattenedContact="";
	    if(customerRequest.getContactInformation().getContact0() != null && !customerRequest.getContactInformation().getContact0().equals("") && !customerRequest.getContactInformation().getContact0().equalsIgnoreCase("null")){
	    	flattenedContact=flattenedContact+customerRequest.getContactInformation().getContact0()+"^;";
	    }
	    if(customerRequest.getContactInformation().getContact1() != null && !customerRequest.getContactInformation().getContact1().equals("") && !customerRequest.getContactInformation().getContact1().equalsIgnoreCase("null")){
	    	flattenedContact=flattenedContact+customerRequest.getContactInformation().getContact1()+"^;";
	    }
		return flattenedContact;
	}
	private String buidingFlattenedContactType(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened Type ");
	    String flattenedContactType="";
	    if(customerRequest.getContactInformation().getContactType0() != null && !customerRequest.getContactInformation().getContactType0().equals("") && !customerRequest.getContactInformation().getContactType0().equalsIgnoreCase("null")){
	    	flattenedContactType=flattenedContactType+customerRequest.getContactInformation().getContactType0()+"^;";
	    }
	    if(customerRequest.getContactInformation().getContactType1() != null && !customerRequest.getContactInformation().getContactType1().equals("") && !customerRequest.getContactInformation().getContactType1().equalsIgnoreCase("null")){
	    	flattenedContactType=flattenedContactType+customerRequest.getContactInformation().getContactType1()+"^;";
	    }
		return flattenedContactType;
	}
	private String buidingFlattenedLandLine(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened LandLine ");
	    String flattenedLandLine="";
	    if(customerRequest.getContactInformation().getLandline0() != null && !customerRequest.getContactInformation().getLandline0().equals("") && !customerRequest.getContactInformation().getLandline0().equalsIgnoreCase("null")){
	    	flattenedLandLine=flattenedLandLine+customerRequest.getContactInformation().getLandline0()+"^;";
	    }
	    if(customerRequest.getContactInformation().getLandline1() != null && !customerRequest.getContactInformation().getLandline1().equals("") && !customerRequest.getContactInformation().getLandline1().equalsIgnoreCase("null")){
	    	flattenedLandLine=flattenedLandLine+customerRequest.getContactInformation().getLandline1()+"^;";
	    }
		return flattenedLandLine;
	}
	private String buidingFlattenedLandLineType(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened LandLine Type");
	    String flattenedLandLineType="";
	    if(customerRequest.getContactInformation().getLandlineType0() != null && !customerRequest.getContactInformation().getLandlineType0().equals("") && !customerRequest.getContactInformation().getLandlineType0().equalsIgnoreCase("null")){
	    	flattenedLandLineType=flattenedLandLineType+customerRequest.getContactInformation().getLandlineType0()+"^;";
	    }
	    if(customerRequest.getContactInformation().getLandlineType1() != null && !customerRequest.getContactInformation().getLandlineType1().equals("") && !customerRequest.getContactInformation().getLandlineType1().equalsIgnoreCase("null")){
	    	flattenedLandLineType=flattenedLandLineType+customerRequest.getContactInformation().getLandlineType1()+"^;";
	    }
		return flattenedLandLineType;
	}
	private String buidingFlattenedEmail(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened Email ");

	    String flattenedEmail="";
	    if(customerRequest.getEmailInformation().getEmail0() != null && !customerRequest.getEmailInformation().getEmail0().equals("") && !customerRequest.getEmailInformation().getEmail0().equalsIgnoreCase("null") ){
	    	flattenedEmail=flattenedEmail+customerRequest.getEmailInformation().getEmail0()+"^;";
	    }
	    if(customerRequest.getEmailInformation().getEmail1() != null && !customerRequest.getEmailInformation().getEmail1().equals("") && !customerRequest.getEmailInformation().getEmail1().equalsIgnoreCase("null")){
	    	flattenedEmail=flattenedEmail+customerRequest.getEmailInformation().getEmail1()+"^;";
	    }
		return flattenedEmail;
	}
	private String buidingFlattenedEmailType(CustomerRequestInformation customerRequest) {
		logger.info("Inside building Flattened Email Type ");

	    String flattenedEmailType="";
	    if(customerRequest.getEmailInformation().getEmailType0() != null && !customerRequest.getEmailInformation().getEmailType0().equals("") && !customerRequest.getEmailInformation().getEmailType0().equalsIgnoreCase("null")){
	    	flattenedEmailType=flattenedEmailType+customerRequest.getEmailInformation().getEmailType0()+"^;";
	    }
	    if(customerRequest.getEmailInformation().getEmailType1() != null && !customerRequest.getEmailInformation().getEmailType1().equals("") && !customerRequest.getEmailInformation().getEmailType1().equalsIgnoreCase("null")){
	    	flattenedEmailType=flattenedEmailType+customerRequest.getEmailInformation().getEmailType1()+"^;";
	    }
		return flattenedEmailType;
	}
	public HashMap<String, Object> buildDGHashMap(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId, String custUnqId) throws Exception {
		logger.info("Inside Building Demographic HashMap ");
		
		String name="";
		HashMap<String, Object> dg_row1 = new HashMap<>();
		try {
		
			name=customerRequest.getDemographicInformation().getFirstname()+ " "+ customerRequest.getDemographicInformation().getMiddlename()+ " "+ customerRequest.getDemographicInformation().getLastname();
			if (name != null && !("".equals(name))){
				dg_row1.put("NAME",name);
				dg_row1.put("APPLICANT_FULL_NAME",name);
			}
			if (customerRequest.getDemographicInformation().getCno() != null && !("".equals(customerRequest.getDemographicInformation().getCno())))
			dg_row1.put("CIN", customerRequest.getDemographicInformation().getCno());
			if (customerRequest.getDemographicInformation().getDinno() != null && !("".equals(customerRequest.getDemographicInformation().getDinno())))
				dg_row1.put("DIN", customerRequest.getDemographicInformation().getDinno());
			if (customerRequest.getDemographicInformation().getFirstname() != null && !("".equals(customerRequest.getDemographicInformation().getFirstname())))
			dg_row1.put("FIRST_NAME",  customerRequest.getDemographicInformation().getFirstname());
			if ( customerRequest.getDemographicInformation().getMiddlename() != null && !("".equals( customerRequest.getDemographicInformation().getMiddlename())))
			dg_row1.put("MIDDLE_NAME",  customerRequest.getDemographicInformation().getMiddlename());
			if (customerRequest.getDemographicInformation().getLastname() != null && !("".equals(customerRequest.getDemographicInformation().getLastname())))
			dg_row1.put("LAST_NAME",  customerRequest.getDemographicInformation().getLastname());
			if (customerRequest.getDemographicInformation().getDob() != null && !("".equals(customerRequest.getDemographicInformation().getDob())))
			dg_row1.put("DOB",customerRequest.getDemographicInformation().getDob() );
			if (customerRequest.getDemographicInformation().getDoi() != null && !("".equals(customerRequest.getDemographicInformation().getDoi())))
				dg_row1.put("DOI",customerRequest.getDemographicInformation().getDoi() );
			if (customerRequest.getDemographicInformation().getPanno() != null && !("".equals(customerRequest.getDemographicInformation().getPanno())))
			dg_row1.put("PAN_NO", customerRequest.getDemographicInformation().getPanno());
			if (customerRequest.getDemographicInformation().getPassportno() != null && !("".equals(customerRequest.getDemographicInformation().getPassportno())))
			dg_row1.put("PASSPORT_NO",customerRequest.getDemographicInformation().getPassportno() );
			if (customerRequest.getDemographicInformation().getVoterid() != null && !("".equals(customerRequest.getDemographicInformation().getVoterid())))
			dg_row1.put("VOTER_ID_NO",customerRequest.getDemographicInformation().getVoterid() );
			if (customerRequest.getDemographicInformation().getAadhar_no() != null && !("".equals(customerRequest.getDemographicInformation().getAadhar_no())))
			dg_row1.put("AADHAR_NO", customerRequest.getDemographicInformation().getAadhar_no());
			if (customerRequest.getDemographicInformation().getDrivinglicense() != null && !("".equals(customerRequest.getDemographicInformation().getDrivinglicense())))
			dg_row1.put("DRIVING_LIC_NO", customerRequest.getDemographicInformation().getDrivinglicense());
			if (customerRequest.getDemographicInformation().getFathername() != null && !("".equals(customerRequest.getDemographicInformation().getFathername())))
			dg_row1.put("FATHER_NAME", customerRequest.getDemographicInformation().getFathername());
			if (customerRequest.getDemographicInformation().getHighesteducation() != null && !("".equals(customerRequest.getDemographicInformation().getHighesteducation())))
			dg_row1.put("HIGHEST_EDUCATION", customerRequest.getDemographicInformation().getHighesteducation());
			if (customerRequest.getDemographicInformation().getMothername() != null && !("".equals(customerRequest.getDemographicInformation().getMothername())))
			dg_row1.put("MOTHER_NAME", customerRequest.getDemographicInformation().getMothername());
			if (customerRequest.getDemographicInformation().getSpousename() != null && !("".equals(customerRequest.getDemographicInformation().getSpousename())))
			dg_row1.put("SPOUSE_NAME", customerRequest.getDemographicInformation().getSpousename());
			if (customerRequest.getDemographicInformation().getTanno() != null && !("".equals(customerRequest.getDemographicInformation().getTanno())))
			dg_row1.put("TAN", customerRequest.getDemographicInformation().getTanno());
			if (customerRequest.getDemographicInformation().getPrimaryoccupation()  != null && !("".equals(customerRequest.getDemographicInformation().getPrimaryoccupation() )))
			dg_row1.put("PRIMARY_OCCUPATION",customerRequest.getDemographicInformation().getPrimaryoccupation() );
			if (customerRequest.getDemographicInformation().getResidencestatus() != null && !("".equals(customerRequest.getDemographicInformation().getResidencestatus())))
			dg_row1.put("RESIDENT_STATUS", customerRequest.getDemographicInformation().getResidencestatus());
			if (customerRequest.getSourcesystemname() != null && !("".equals(customerRequest.getSourcesystemname())))
			dg_row1.put("SOURCE_SYSTEM", customerRequest.getSourcesystemname());
			if (customerRequest.getCustomercategory() != null && !("".equals(customerRequest.getCustomercategory())))
			dg_row1.put("CUSTOMER_CATEGORY_TYPE",customerRequest.getCustomercategory() );
			if (customerRequest.getDemographicInformation().getCustomertypecode() != null && !("".equals(customerRequest.getDemographicInformation().getCustomertypecode())))
			dg_row1.put("CUSTOMER_TYPE_CODE", customerRequest.getDemographicInformation().getCustomertypecode());
			if (customerRequest.getDemographicInformation().getGender()  != null && !("".equals(customerRequest.getDemographicInformation().getGender() )))
			dg_row1.put("GENDER",customerRequest.getDemographicInformation().getGender());
			if (customerRequest.getDemographicInformation().getMartialstatus() != null && !("".equals(customerRequest.getDemographicInformation().getMartialstatus())))
			dg_row1.put("MARITAL_STATUS", customerRequest.getDemographicInformation().getMartialstatus());
			if (customerRequest.getDemographicInformation().getCycNo() != null && !("".equals(customerRequest.getDemographicInformation().getCycNo())))
				dg_row1.put("CKYC_NO", customerRequest.getDemographicInformation().getCycNo());
			if (customerRequest.getDemographicInformation().getGstin() != null && !("".equals(customerRequest.getDemographicInformation().getGstin())))
				dg_row1.put("GSTIN", customerRequest.getDemographicInformation().getGstin());
			if (customerRequest.getDemographicInformation().getTaxId() != null && !("".equals(customerRequest.getDemographicInformation().getTaxId())))
				dg_row1.put("TAX_ID", customerRequest.getDemographicInformation().getTaxId());
			dg_row1.put("INSERT_TIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
			dg_row1.put("LCHGTIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
			dg_row1.put("PSX_BATCH_ID",psxBatchId );
			dg_row1.put("CUST_UNQ_ID", custUnqId);
			dg_row1.put("DUIFLAG", "I_OR_U");
		} catch (Exception e) {
			logger.error(e,e);
			logger.error("Exception while getting BuildHashMap:");
			logger.error(e, e);

			throw e;
		}
		return dg_row1;
}
	

	public ArrayList<HashMap<String, Object>> buildAceObjectHashMap(CustomerRequestInformation customerRequest, Date timestamp,String psxBatchId, String custUnqId) {
	   ArrayList<HashMap<String, Object>> dg_row1List=new ArrayList<>();
	  
	   List<Address> addressList=buidingAddress(customerRequest);
	   List<Contact> contactList=buildingContact(customerRequest);
	   List<Email> emailList=buildingEmail(customerRequest);    
	   logger.info("Address List Size ::: "+addressList.size()+" Contact List Size ::: "+contactList.size()+" Email List Size ::: "+emailList.size());
	   int maxSize=maxSize(addressList.size(),contactList.size(),emailList.size());
	   IntStream.rangeClosed(0, maxSize-1).forEach(i ->{
		   HashMap<String, Object> dg_row1 = new HashMap<>();
			if (addressList.size() > i && addressList.get(i).getAddress() != null && !("".equals(addressList.get(i).getAddress()))){
				dg_row1.put("ADDRESS", addressList.get(i).getAddress());
				dg_row1.put("PROPADDRESS", addressList.get(i).getAddress());
			}
			if (addressList.size() > i && addressList.get(i).getAddresstype() != null && !("".equals(addressList.get(i).getAddresstype()))){
				dg_row1.put("ADDRESS_TYPE", addressList.get(i).getAddresstype());
				dg_row1.put("PROPADDRESS_TYPE", addressList.get(i).getAddresstype());
			}
			if (addressList.size() > i && addressList.get(i).getCity() != null && !("".equals(addressList.get(i).getCity())))
				dg_row1.put("CITY", addressList.get(i).getCity());
			if (addressList.size() > i && addressList.get(i).getPincode() != null && !("".equals(addressList.get(i).getPincode())))
				dg_row1.put("PINCODE",addressList.get(i).getPincode());
			if (addressList.size() > i && addressList.get(i).getState() != null && !("".equals(addressList.get(i).getState())))
				dg_row1.put("STATE", addressList.get(i).getState());
			
			if (emailList.size() > i && emailList.get(i).getEmail() != null && !("".equals(emailList.get(i).getEmail())))
				dg_row1.put("EMAIL", emailList.get(i).getEmail());
			if (emailList.size() > i && emailList.get(i).getEmailType() != null && !("".equals(emailList.get(i).getEmailType())))
				dg_row1.put("EMAIL_TYPE", emailList.get(i).getEmailType());
			
			if (contactList.size() > i && contactList.get(i).getContact() != null && !("".equals(contactList.get(i).getContact()))){
				dg_row1.put("CUSTOMER_CONTACT", contactList.get(i).getContact());
				dg_row1.put("MOBILE", contactList.get(i).getContact());
			}
			if (contactList.size() > i && contactList.get(i).getContactType() != null && !("".equals(contactList.get(i).getContactType()))){
				dg_row1.put("CUSTOMER_CONTACT_TYPE",contactList.get(i).getContactType());
				dg_row1.put("MOBILE_TYPE",contactList.get(i).getContactType());
			}
			if (contactList.size() > i && contactList.get(i).getLandline() != null && !("".equals(contactList.get(i).getLandline())))
				dg_row1.put("CUSTOMER_LANDLINE", contactList.get(i).getLandline());
			if (contactList.size() > i && contactList.get(i).getLandlineType() != null && !("".equals(contactList.get(i).getLandlineType())))
				dg_row1.put("CUSTOMER_LANDLINE_TYPE", contactList.get(i).getLandlineType());
			
			if (customerRequest.getSourcecustomerid() != null && !("".equals(customerRequest.getSourcecustomerid())))
				dg_row1.put("CUSTOMER_NO", customerRequest.getSourcecustomerid());
			dg_row1.put("INSERT_TIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
			dg_row1.put("LCHGTIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));
			dg_row1.put("PSX_BATCH_ID", psxBatchId);
			dg_row1.put("CUST_UNQ_ID", custUnqId);
			dg_row1.put("DUIFLAG", "I_OR_U");
			dg_row1List.add(dg_row1);
	   });
	   
		return dg_row1List;
	}
	
	public List<ACE> buildAceObject(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId) {
		   List<ACE> aceList=new ArrayList<>();
		  
		   List<Address> addressList=buidingAddress(customerRequest);
		   List<Contact> contactList=buildingContact(customerRequest);
		   List<Email> emailList=buildingEmail(customerRequest);    
		   logger.info("Inside ACE building object Address List Size ::: "+addressList.size()+" Contact List Size ::: "+contactList.size()+" Email List Size ::: "+emailList.size());
		   int maxSize=maxSize(addressList.size(),contactList.size(),emailList.size());
		   IntStream.rangeClosed(0, maxSize-1).forEach(i ->{
			   ACE ace=new ACE();
				if (addressList.size() > i && addressList.get(i).getAddress() != null && !("".equals(addressList.get(i).getAddress()))){
					ace.setAddress(addressList.get(i).getAddress());
				}
				if (addressList.size() > i && addressList.get(i).getAddresstype() != null && !("".equals(addressList.get(i).getAddresstype()))){
					ace.setAddresstype(addressList.get(i).getAddresstype());
					
				}
				if (addressList.size() > i && addressList.get(i).getAddressid() != 0 && !("".equals(addressList.get(i).getAddressid())))
					ace.setAddressid(addressList.get(i).getAddressid());
				if (addressList.size() > i && addressList.get(i).getCity() != null && !("".equals(addressList.get(i).getCity())))
					ace.setCity(addressList.get(i).getCity());
				if (addressList.size() > i && addressList.get(i).getPincode() != null && !("".equals(addressList.get(i).getPincode())))
					ace.setPincode(addressList.get(i).getPincode());
				if (addressList.size() > i && addressList.get(i).getState() != null && !("".equals(addressList.get(i).getState())))
					ace.setState(addressList.get(i).getState());
				
				if (emailList.size() > i && emailList.get(i).getEmail() != null && !("".equals(emailList.get(i).getEmail())))
					ace.setEmail(emailList.get(i).getEmail());
				if (emailList.size() > i && emailList.get(i).getEmailType() != null && !("".equals(emailList.get(i).getEmailType())))
					ace.setEmailType(emailList.get(i).getEmailType());
				
				if (contactList.size() > i && contactList.get(i).getContact() != null && !("".equals(contactList.get(i).getContact()))){
					ace.setContact(contactList.get(i).getContact());
					
				}
				if (contactList.size() > i && contactList.get(i).getContactType() != null && !("".equals(contactList.get(i).getContactType()))){
					ace.setContactType(contactList.get(i).getContactType());
				}
				if (contactList.size() > i && contactList.get(i).getLandline() != null && !("".equals(contactList.get(i).getLandline())))
					ace.setLandline(contactList.get(i).getLandline());
				if (contactList.size() > i && contactList.get(i).getLandlineType() != null && !("".equals(contactList.get(i).getLandlineType())))
					ace.setLandlineType(contactList.get(i).getLandlineType());
				
			aceList.add(ace);
		   });
		   
			return aceList;
		}

	
	

	public int maxSize(int num1, int num2, int num3){
		
		  if (num1 >= num2 && num1 >= num3)
			return num1;
	      else if (num2 >= num1 && num2 >= num3)
	    	  return num2;
	      else
	    	  return num3;
		
	}


	private List<Email> buildingEmail(CustomerRequestInformation customerRequest) {
		List<Email> emailList=new ArrayList<>();
		   Email email0=new Email();
		   if(isNotNullAndNotEmpty(customerRequest.getEmailInformation().getEmail0())){
		   email0.setEmail(customerRequest.getEmailInformation().getEmail0());
		   email0.setEmailType(customerRequest.getEmailInformation().getEmailType0());
		   }
		   if((email0.getEmail() !=null && !"".equals(email0.getEmail())))
			   emailList.add(email0);
		   
		   Email email1=new Email();
		   if(isNotNullAndNotEmpty(customerRequest.getEmailInformation().getEmail1())){
		   email1.setEmail(customerRequest.getEmailInformation().getEmail1());
		   email1.setEmailType(customerRequest.getEmailInformation().getEmailType1());
		   }
		   if((email1.getEmail() !=null && !"".equals(email1.getEmail())))
			   emailList.add(email1);
		   return emailList;
	}




	private List<Contact> buildingContact(CustomerRequestInformation customerRequest) {
		List<Contact> contactList=new ArrayList<>();
		   Contact contact0=new Contact();
		   if(isNotNullAndNotEmpty(customerRequest.getContactInformation().getContact0())){
		   contact0.setContact(customerRequest.getContactInformation().getContact0());
		   contact0.setContactType(customerRequest.getContactInformation().getContactType0());
		   }
		   if(isNotNullAndNotEmpty(customerRequest.getContactInformation().getLandline0())){
		   contact0.setLandline(customerRequest.getContactInformation().getLandline0());
		   contact0.setLandlineType(customerRequest.getContactInformation().getLandlineType0());
		   }
		   if((contact0.getContact() !=null && !"".equals(contact0.getContact())) || (contact0.getLandline() !=null && !"".equals(contact0.getLandline())))
			   contactList.add(contact0);
		   
		   Contact contact1=new Contact();
		   if(isNotNullAndNotEmpty(customerRequest.getContactInformation().getContact1())){
		   contact1.setContact(customerRequest.getContactInformation().getContact1());
		   contact1.setContactType(customerRequest.getContactInformation().getContactType1());
		   }
		   if(isNotNullAndNotEmpty(customerRequest.getContactInformation().getLandline1())){
		   contact1.setLandline(customerRequest.getContactInformation().getLandline1());
		   contact1.setLandlineType(customerRequest.getContactInformation().getLandlineType1());
		   }
		   if((contact1.getContact() !=null && !"".equals(contact1.getContact())) || (contact1.getLandline() !=null && !"".equals(contact1.getLandline())))
			   contactList.add(contact1);
		   return contactList;
	}

	public boolean isNotNullAndNotEmpty(String str){
		if(str!=null && !str.equals(null) && !str.equals(""))
		   return true;
		else
		    return false;
		}


	private List<Address>  buidingAddress(CustomerRequestInformation customerRequest) {
		List<Address> addressList=new ArrayList<>();
		   Address address0=new Address();
		   if(isNotNullAndNotEmpty(customerRequest.getAddressInformation().getAddress0())){
		   address0.setAddress(customerRequest.getAddressInformation().getAddress0());
		   address0.setAddressid(customerRequest.getAddressInformation().getAddressid0());
		   address0.setAddresstype(customerRequest.getAddressInformation().getAddresstype0());
		   address0.setCity(customerRequest.getAddressInformation().getCity0());
		   address0.setPincode(customerRequest.getAddressInformation().getPincode0());
		   address0.setState(customerRequest.getAddressInformation().getState0());
		   }
		   if((address0.getAddress() !=null && !"".equals(address0.getAddress())) || (address0.getCity() !=null && !"".equals(address0.getCity())) || (address0.getPincode() !=null && !"".equals(address0.getPincode())) || (address0.getState() !=null && !"".equals(address0.getState())))
			   addressList.add(address0);
		   
		   Address address1=new Address();
		   if(isNotNullAndNotEmpty(customerRequest.getAddressInformation().getAddress1())){
		   address1.setAddress(customerRequest.getAddressInformation().getAddress1());
		   address1.setAddressid(customerRequest.getAddressInformation().getAddressid1());
		   address1.setAddresstype(customerRequest.getAddressInformation().getAddresstype1());
		   address1.setCity(customerRequest.getAddressInformation().getCity1());
		   address1.setPincode(customerRequest.getAddressInformation().getPincode1());
		   address1.setState(customerRequest.getAddressInformation().getState1());
		   }
		   if((address1.getAddress() !=null && !"".equals(address1.getAddress())) || (address1.getCity() !=null && !"".equals(address1.getCity())) || (address1.getPincode() !=null && !"".equals(address1.getPincode())) || (address1.getState() !=null && !"".equals(address1.getState())))
			   	addressList.add(address1);
		   
		   Address address2=new Address();
		   if(isNotNullAndNotEmpty(customerRequest.getAddressInformation().getAddress2())){
		   address2.setAddress(customerRequest.getAddressInformation().getAddress2());
		   address2.setAddressid(customerRequest.getAddressInformation().getAddressid2());
		   address2.setAddresstype(customerRequest.getAddressInformation().getAddresstype2());
		   address2.setCity(customerRequest.getAddressInformation().getCity2());
		   address2.setPincode(customerRequest.getAddressInformation().getPincode2());
		   address2.setState(customerRequest.getAddressInformation().getState2());
		   }
		   if((address2.getAddress() !=null && !"".equals(address2.getAddress())) || (address2.getCity() !=null && !"".equals(address2.getCity())) || (address2.getPincode() !=null && !"".equals(address2.getPincode())) || (address2.getState() !=null && !"".equals(address2.getState())))
			   addressList.add(address2);
		   
		   Address address3=new Address();
		   if(isNotNullAndNotEmpty(customerRequest.getAddressInformation().getAddress3())){
		   address3.setAddress(customerRequest.getAddressInformation().getAddress3());
		   address3.setAddressid(customerRequest.getAddressInformation().getAddressid3());
		   address3.setAddresstype(customerRequest.getAddressInformation().getAddresstype3());
		   address3.setCity(customerRequest.getAddressInformation().getCity3());
		   address3.setPincode(customerRequest.getAddressInformation().getPincode3());
		   address3.setState(customerRequest.getAddressInformation().getState3());
		   }
		   if((address3.getAddress() !=null && !"".equals(address3.getAddress())) || (address3.getCity() !=null && !"".equals(address3.getCity())) || (address3.getPincode() !=null && !"".equals(address3.getPincode())) || (address3.getState() !=null && !"".equals(address3.getState())))
			   addressList.add(address3);
		return addressList;
	}
	
	public List<MatchedAddressStrengths> findingAddressStrengths(String filteredMsg) {
		
		int index1,index2,index3=0;
		char ch = ' ';
		StringBuilder sb = new StringBuilder(filteredMsg);
		if(filteredMsg.contains("NEGATIVECUSTBASE")){
		 index1=filteredMsg.indexOf("results"); 
		 index2=nthOccurrence(filteredMsg,"sourceSystemName",3); 
		 index3=filteredMsg.indexOf("paramMap");
		
			sb.setCharAt(index1+10, ch);
			sb.setCharAt(index2-5, ch);
			sb.setCharAt(index2-3, ch);
			sb.setCharAt(index3-4, ch);
			filteredMsg = sb.toString();
			logger.info("Filtered Address for both Source System :: "+filteredMsg);
		}else{
			 index1=filteredMsg.indexOf("results"); 
			 index3=filteredMsg.indexOf("paramMap");
			 
				sb.setCharAt(index1+10, ch);
				sb.setCharAt(index3-7, ch);
				filteredMsg = sb.toString();
				logger.info("Filtered Address for only Customer Source System :: "+filteredMsg);
		}
		
		
		
		
		JSONObject obj = new JSONObject(filteredMsg);
		JSONArray arr = obj.getJSONArray("results");
		List<MatchedAddressStrengths> matchedAddressStrengthsList=new ArrayList<>();
		if(arr !=null){
			
			
		for (int i = 0; i < arr.length()-1; i++)
		{
			
		    JSONArray arr1 = arr.getJSONObject(i).getJSONArray("offline");
		    if(arr1 !=null){
			for (int j = 0; j < arr1.length(); j++)
			{
				MatchedAddressStrengths matchedAddressStrengths=new MatchedAddressStrengths();
				
				matchedAddressStrengths.setRecordType("OFFLINE");
				
				if(arr1.getJSONObject(i).has("id"))
					matchedAddressStrengths.setMatchedId(arr1.getJSONObject(i).getString("id"));
				
				if(arr1.getJSONObject(i).getJSONObject("Strengths").has(environment.getProperty("strength0")))
					matchedAddressStrengths.setPermanentMaxStrength(arr1.getJSONObject(i).getJSONObject("Strengths").getString(environment.getProperty("strength0")));
				
				if(arr1.getJSONObject(i).getJSONObject("Strengths").has(environment.getProperty("strength1")))
					matchedAddressStrengths.setCurrentMaxStrength(arr1.getJSONObject(i).getJSONObject("Strengths").getString(environment.getProperty("strength1")));
				
				if(arr1.getJSONObject(i).getJSONObject("Strengths").has(environment.getProperty("strength2")))
					matchedAddressStrengths.setResidentMaxStrength(arr1.getJSONObject(i).getJSONObject("Strengths").getString(environment.getProperty("strength2")));

				if(arr1.getJSONObject(i).getJSONObject("Strengths").has(environment.getProperty("strength3")))
					matchedAddressStrengths.setEmployeeMaxStrength(arr1.getJSONObject(i).getJSONObject("Strengths").getString(environment.getProperty("strength3")));
				matchedAddressStrengthsList.add(matchedAddressStrengths);
			}}
			JSONArray arr2 =arr.getJSONObject(i).getJSONArray("online");
			
			if(arr2 !=null){
			for (int j = 0; j < arr2.length(); j++)
			{
				MatchedAddressStrengths matchedAddressStrengths=new MatchedAddressStrengths();
				
				matchedAddressStrengths.setRecordType("ONLINE");
				
				if(arr2.getJSONObject(j).has("id"))
					matchedAddressStrengths.setMatchedId(arr2.getJSONObject(j).getString("id"));
				
				if(arr2.getJSONObject(j).getJSONObject("Strengths").has(environment.getProperty("strength0")))
					matchedAddressStrengths.setPermanentMaxStrength(arr2.getJSONObject(j).getJSONObject("Strengths").getString(environment.getProperty("strength0")));
				
				if(arr2.getJSONObject(j).getJSONObject("Strengths").has(environment.getProperty("strength1")))
					matchedAddressStrengths.setCurrentMaxStrength(arr2.getJSONObject(j).getJSONObject("Strengths").getString(environment.getProperty("strength1")));
				
				if(arr2.getJSONObject(j).getJSONObject("Strengths").has(environment.getProperty("strength2")))
					matchedAddressStrengths.setResidentMaxStrength(arr2.getJSONObject(j).getJSONObject("Strengths").getString(environment.getProperty("strength2")));

				if(arr2.getJSONObject(j).getJSONObject("Strengths").has(environment.getProperty("strength3")))
					matchedAddressStrengths.setEmployeeMaxStrength(arr2.getJSONObject(j).getJSONObject("Strengths").getString(environment.getProperty("strength3")));
				matchedAddressStrengthsList.add(matchedAddressStrengths);
			}}
		}}
		
		logger.info("MatchedAddressStrengthsList :: "+matchedAddressStrengthsList);
		return matchedAddressStrengthsList;
	}
	
	 public  int nthOccurrence(String str1, String str2, int n) {
		 
	        String tempStr = str1;
	        int tempIndex = -1;
	        int finalIndex = 0;
	        for(int occurrence = 0; occurrence < n ; ++occurrence){
	            tempIndex = tempStr.indexOf(str2);
	            if(tempIndex==-1){
	                finalIndex = 0;
	                break;
	            }
	            tempStr = tempStr.substring(++tempIndex);
	            finalIndex+=tempIndex;
	        }
	        return --finalIndex;
	    }

}