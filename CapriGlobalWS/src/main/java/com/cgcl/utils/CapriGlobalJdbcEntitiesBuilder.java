package com.cgcl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cgcl.beans.AddressInformation;
import com.cgcl.beans.CgclAceBlkBean;
import com.cgcl.beans.CgclDgBlkBean;
import com.cgcl.beans.CgclLoanDetailsBean;
import com.cgcl.beans.CgclReportInputOutputBean;
import com.cgcl.beans.ContactInformation;
import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.beans.DemographicInformation;
import com.cgcl.beans.EmailInformation;
import com.cgcl.beans.MatchedAddressStrengths;
import com.cgcl.beans.MatchedCustomerDetailsBean;
import com.cgcl.beans.MatchedCustomerLoanDetailsBean;
import com.cgcl.beans.NegativeMatchedCustomerDetailsBean;
import com.cgcl.beans.PsxRequestBean;
import com.cgcl.exception.PosidexException;

@Component
@PropertySource("classpath:validation.properties")
public class CapriGlobalJdbcEntitiesBuilder {
	private static Logger logger = Logger.getLogger(CapriGlobalJdbcEntitiesBuilder.class.getName());
	
	@Autowired
	private Environment environment;
	
	public CgclDgBlkBean buidingDemographicRequestInfo(CustomerRequestInformation customerRequest, Date timestamp,long requestId) throws ParseException {
		CgclDgBlkBean dg_blk_request = new CgclDgBlkBean();
		dg_blk_request.setAccount_status(customerRequest.getDemographicInformation().getAccount_status());
		dg_blk_request.setName(customerRequest.getDemographicInformation().getFirstname()+" "+customerRequest.getDemographicInformation().getMiddlename()+" "+customerRequest.getDemographicInformation().getLastname());
		dg_blk_request.setCaste(customerRequest.getDemographicInformation().getCaste());
		dg_blk_request.setCustomer("mahadev");
		dg_blk_request.setRequestId(customerRequest.getRequestId());
		dg_blk_request.setCust_unq_id(customerRequest.getSourcecustomerid());
		dg_blk_request.setCust_id("100");
    	dg_blk_request.setLead_id("200");
		dg_blk_request.setBatch_id("300");
		dg_blk_request.setDeal_id_app_id("400");
	    dg_blk_request.setPsx_batch_id("500");
		dg_blk_request.setDuiflag("I_OR_U");   
		dg_blk_request.setEventtype("Matching");
		dg_blk_request.setCibil_score(customerRequest.getDemographicInformation().getCibil_score());
		dg_blk_request.setCustomercategory(customerRequest.getCustomercategory());
		dg_blk_request.setCustomertypecode(customerRequest.getDemographicInformation().getCustomertypecode());
		dg_blk_request.setDinno(customerRequest.getDemographicInformation().getDinno());
		dg_blk_request.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDob()));
		dg_blk_request.setDoi(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDoi()));
		dg_blk_request.setDrivinglicense(customerRequest.getDemographicInformation().getDrivinglicense());
		dg_blk_request.setFathername(customerRequest.getDemographicInformation().getFathername());
		dg_blk_request.setFirstname(customerRequest.getDemographicInformation().getFirstname());
		dg_blk_request.setGender(customerRequest.getDemographicInformation().getGender());
		dg_blk_request.setGstin(customerRequest.getDemographicInformation().getGstin());
		dg_blk_request.setHighesteducation(customerRequest.getDemographicInformation().getHighesteducation());
		dg_blk_request.setInserttime(timestamp);
		dg_blk_request.setLan(customerRequest.getDemographicInformation().getLan());
		dg_blk_request.setLastname(customerRequest.getDemographicInformation().getLastname());
		dg_blk_request.setLchgtime(timestamp);
		dg_blk_request.setMartialstatus(customerRequest.getDemographicInformation().getMartialstatus());
		dg_blk_request.setMatchingruleprofile(customerRequest.getMatchingruleprofile());
		dg_blk_request.setMiddlename(customerRequest.getDemographicInformation().getMiddlename());
//		dg_blk_request.setMothername(customerRequest.getMothername());
		dg_blk_request.setOld_psx_batch_id("");
		dg_blk_request.setPanno(customerRequest.getDemographicInformation().getPanno());
		dg_blk_request.setPassportno(customerRequest.getDemographicInformation().getPassportno());
		dg_blk_request.setPrimaryoccupation(customerRequest.getDemographicInformation().getPrimaryoccupation());
		dg_blk_request.setProduct(customerRequest.getDemographicInformation().getProduct());
		dg_blk_request.setRegistration_no(customerRequest.getDemographicInformation().getCno());
		dg_blk_request.setReligion(customerRequest.getDemographicInformation().getReligion());
		dg_blk_request.setResidencestatus(customerRequest.getDemographicInformation().getResidencestatus());
		dg_blk_request.setSourceapplicationid(customerRequest.getSourceapplicationid());
		dg_blk_request.setSourceapplicationno(customerRequest.getSourceapplicationno());
		dg_blk_request.setSourcecustomerid(customerRequest.getSourcecustomerid());
		dg_blk_request.setSourceauthenticationtoken(customerRequest.getSourceauthenticationtoken());
		dg_blk_request.setSourcesystemname(customerRequest.getSourcesystemname());
		dg_blk_request.setSpousename(customerRequest.getDemographicInformation().getSpousename());
		dg_blk_request.setTanno(customerRequest.getDemographicInformation().getTanno());
		dg_blk_request.setTitle(customerRequest.getDemographicInformation().getTitle());
		dg_blk_request.setUid(customerRequest.getDemographicInformation().getUid());
		dg_blk_request.setVoterid(customerRequest.getDemographicInformation().getVoterid());
		dg_blk_request.setRequestId(requestId);
		dg_blk_request.setAssignUcic(customerRequest.getAssignUcic());
		dg_blk_request.setCycNo("");
		dg_blk_request.setGstin("");
		return dg_blk_request;
	}

	public CgclAceBlkBean buidingAceRequestInfo(CustomerRequestInformation customerRequest, Date timestamp,long requestId) {
		CgclAceBlkBean ace_blk_request = new CgclAceBlkBean();
		String flattenedAddress=customerRequest.getAddressInformation().getAddress0()+"^;"+customerRequest.getAddressInformation().getAddress1()+"^;"+customerRequest.getAddressInformation().getAddress2()+"^;"+customerRequest.getAddressInformation().getAddress3()+"^;";
		String flattenedAddressId=customerRequest.getAddressInformation().getAddressid0()+"^;"+customerRequest.getAddressInformation().getAddressid1()+"^;"+customerRequest.getAddressInformation().getAddressid2()+"^;"+customerRequest.getAddressInformation().getAddressid3()+"^;";
		String flattenedAddressType=customerRequest.getAddressInformation().getAddresstype0()+"^;"+customerRequest.getAddressInformation().getAddresstype1()+"^;"+customerRequest.getAddressInformation().getAddresstype2()+"^;"+customerRequest.getAddressInformation().getAddresstype3()+"^;";
		String flattenedCity=customerRequest.getAddressInformation().getCity0()+"^;"+customerRequest.getAddressInformation().getCity1()+"^;"+customerRequest.getAddressInformation().getCity2()+"^;"+customerRequest.getAddressInformation().getCity3()+"^;";
		String flattenedPincode=customerRequest.getAddressInformation().getPincode0()+"^;"+customerRequest.getAddressInformation().getPincode1()+"^;"+customerRequest.getAddressInformation().getPincode2()+"^;"+customerRequest.getAddressInformation().getPincode3()+"^;";
		String flattenedState=customerRequest.getAddressInformation().getState0()+"^;"+customerRequest.getAddressInformation().getState1()+"^;"+customerRequest.getAddressInformation().getState2()+"^;"+customerRequest.getAddressInformation().getState3()+"^;";
		ace_blk_request.setAddress(flattenedAddress);
//		ace_blk_request.setAddressid(flattenedAddressId);
		ace_blk_request.setAddresstype(flattenedAddressType);
		// ace_blk_request.setBatch_id("");
		ace_blk_request.setCity(flattenedCity);
//		ace_blk_request.setCurrentAddCity(customerRequest.getCity());
//		ace_blk_request.setCurrentAddress(customerRequest.getAddress());
//		ace_blk_request.setCurrentAddressType(customerRequest.getAddresstype());
//		ace_blk_request.setCurrentPincode(customerRequest.getPincode());
//		ace_blk_request.setCurrentState(customerRequest.getState());
		ace_blk_request.setCustId("100");
	    ace_blk_request.setCustUnqId("200");
		ace_blk_request.setCustomerContact(customerRequest.getContactInformation().getContact0());
		// ace_blk_request.setCustomer_contact_type("");
		ace_blk_request.setCustomerLandline(customerRequest.getContactInformation().getLandline0());
		// ace_blk_request.setCustomer_no("");
		// ace_blk_request.setDeal_id_app_id("");
		ace_blk_request.setDuiflag("I_OR_U");
//		ace_blk_request.setEmail(customerRequest.getEmailInformation().getEmail());
		// ace_blk_request.setEmail_type("");
//		ace_blk_request.setEmployerAddCity(customerRequest.getCity());
//		ace_blk_request.setEmployerAddress(customerRequest.getAddress());
//		ace_blk_request.setEmployerAddressType(customerRequest.getAddresstype());
//		ace_blk_request.setEmployerPincode(customerRequest.getPincode());
//		ace_blk_request.setEmployerState(customerRequest.getState());
		 ace_blk_request.setEventtype("Matching");
		ace_blk_request.setInserttime(timestamp);
		ace_blk_request.setLchgtime(timestamp);
		// ace_blk_request.setLead_id("");
		// ace_blk_request.setOld_psx_batch_id("");
//		ace_blk_request.setPermanentAddCity(customerRequest.getCity());
//		ace_blk_request.setPermanentAddress(customerRequest.getAddress());
//		ace_blk_request.setPermanentAddressType(customerRequest.getAddresstype());
//		ace_blk_request.setPermanentPincode(customerRequest.getPincode());
//		ace_blk_request.setPermanentState(customerRequest.getState());
		ace_blk_request.setPincode(flattenedPincode);
		ace_blk_request.setPsxBatchId("1000");
		ace_blk_request.setRequestId(customerRequest.getRequestId());
		ace_blk_request.setState(flattenedState);
//		ace_blk_request.setMobilenumber(customerRequest.getContactInformation().getMobilenumber());
//		ace_blk_request.setLandlinenumber(customerRequest.getContactInformation().getLandlinenumber());
		ace_blk_request.setRequestId(requestId);
		return ace_blk_request;
	}

	public CgclAceBlkBean buidingAceIntradayInfo(CustomerRequestInformation customerRequest, Date timestamp,long requestId) {
		CgclAceBlkBean ace_blk_intraday = new CgclAceBlkBean();
		String flattenedAddress=customerRequest.getAddressInformation().getAddress0()+"^;"+customerRequest.getAddressInformation().getAddress1()+"^;"+customerRequest.getAddressInformation().getAddress2()+"^;"+customerRequest.getAddressInformation().getAddress3()+"^;";
		String flattenedAddressId=customerRequest.getAddressInformation().getAddressid0()+"^;"+customerRequest.getAddressInformation().getAddressid1()+"^;"+customerRequest.getAddressInformation().getAddressid2()+"^;"+customerRequest.getAddressInformation().getAddressid3()+"^;";
		String flattenedAddressType=customerRequest.getAddressInformation().getAddresstype0()+"^;"+customerRequest.getAddressInformation().getAddresstype1()+"^;"+customerRequest.getAddressInformation().getAddresstype2()+"^;"+customerRequest.getAddressInformation().getAddresstype3()+"^;";
		String flattenedCity=customerRequest.getAddressInformation().getCity0()+"^;"+customerRequest.getAddressInformation().getCity1()+"^;"+customerRequest.getAddressInformation().getCity2()+"^;"+customerRequest.getAddressInformation().getCity3()+"^;";
		String flattenedPincode=customerRequest.getAddressInformation().getPincode0()+"^;"+customerRequest.getAddressInformation().getPincode1()+"^;"+customerRequest.getAddressInformation().getPincode2()+"^;"+customerRequest.getAddressInformation().getPincode3()+"^;";
		String flattenedState=customerRequest.getAddressInformation().getState0()+"^;"+customerRequest.getAddressInformation().getState1()+"^;"+customerRequest.getAddressInformation().getState2()+"^;"+customerRequest.getAddressInformation().getState3()+"^;";
		ace_blk_intraday.setAddress(flattenedAddress);
	//	ace_blk_intraday.setAddressid(flattenedAddressId);
		ace_blk_intraday.setAddresstype(flattenedAddressType);
//		ace_blk_intraday.setBatch_id("");
		ace_blk_intraday.setCity(flattenedCity);
//		ace_blk_intraday.setCurrent_add_city(customerRequest.getCity());
//		ace_blk_intraday.setCurrent_address(customerRequest.getAddress());
//		ace_blk_intraday.setCurrent_address_type(customerRequest.getAddresstype());
//		ace_blk_intraday.setCurrent_pincode(customerRequest.getPincode());
//		ace_blk_intraday.setCurrent_state(customerRequest.getState());
		ace_blk_intraday.setCustId("100");
		ace_blk_intraday.setCustUnqId("200");
//		ace_blk_intraday.setCustomerContact(customerRequest.getContactInformation().getMobilenumber());
//		ace_blk_intraday.setCustomer_contact_type("");
//		ace_blk_intraday.setCustomerLandline(customerRequest.getContactInformation().getLandlinenumber());
//		ace_blk_intraday.setCustomer_no("");
//		ace_blk_intraday.setDeal_id_app_id("");
		ace_blk_intraday.setDuiflag("I_OR_U");
//		ace_blk_intraday.setEmail(customerRequest.getEmailInformation().getEmail());
//		ace_blk_intraday.setEmail_type("");
//		ace_blk_intraday.setEmployer_add_city(customerRequest.getCity());
//		ace_blk_intraday.setEmployer_address(customerRequest.getAddress());
//		ace_blk_intraday.setEmployer_address_type(customerRequest.getAddresstype());
//		ace_blk_intraday.setEmployer_pincode(customerRequest.getPincode());
//		ace_blk_intraday.setEmployer_state(customerRequest.getState());
		ace_blk_intraday.setEventtype("Intraday");
		ace_blk_intraday.setInserttime(timestamp);
		ace_blk_intraday.setLchgtime(timestamp);
//		ace_blk_intraday.setLead_id("");
//		ace_blk_intraday.setOld_psx_batch_id("");
//		ace_blk_intraday.setPermanent_add_city(customerRequest.getCity());
//		ace_blk_intraday.setPermanent_address(customerRequest.getAddress());
//		ace_blk_intraday.setPermanent_address_type(customerRequest.getAddresstype());
//		ace_blk_intraday.setPermanent_pincode(customerRequest.getPincode());
//		ace_blk_intraday.setPermanent_state(customerRequest.getState());
		ace_blk_intraday.setPincode(flattenedPincode);
		ace_blk_intraday.setPsxBatchId("2000");
		ace_blk_intraday.setRequestId(customerRequest.getRequestId());
		ace_blk_intraday.setState(flattenedState);
//		ace_blk_intraday.setMobilenumber(customerRequest.getContactInformation().getMobilenumber());
//		ace_blk_intraday.setLandlinenumber(customerRequest.getContactInformation().getLandlinenumber());
		ace_blk_intraday.setRequestId(requestId);
		return ace_blk_intraday;
	}

	public CgclDgBlkBean buidingDemographicIntradayInfo(CustomerRequestInformation customerRequest, Date timestamp,long requestId, String ucicId,String ucicType) throws ParseException {
		CgclDgBlkBean dg_blk_intraday = new CgclDgBlkBean();
		dg_blk_intraday.setAccount_status(customerRequest.getDemographicInformation().getAccount_status());
		dg_blk_intraday.setCaste(customerRequest.getDemographicInformation().getCaste());
		dg_blk_intraday.setCustomer(customerRequest.getDemographicInformation().getFirstname()+" "+customerRequest.getDemographicInformation().getMiddlename()+" "+customerRequest.getDemographicInformation().getLastname());
		dg_blk_intraday.setRequestId(customerRequest.getRequestId());
		dg_blk_intraday.setCust_unq_id(customerRequest.getSourcecustomerid());
		dg_blk_intraday.setCust_id("100");
    	dg_blk_intraday.setLead_id("200");
		dg_blk_intraday.setBatch_id("300");
		dg_blk_intraday.setDeal_id_app_id("400");
	    dg_blk_intraday.setPsx_batch_id("500");
		dg_blk_intraday.setDuiflag("I_OR_U");   
		dg_blk_intraday.setEventtype("Intraday");
		dg_blk_intraday.setCibil_score(customerRequest.getDemographicInformation().getCibil_score());
		dg_blk_intraday.setCustomercategory(customerRequest.getCustomercategory());
		dg_blk_intraday.setCustomertypecode(customerRequest.getDemographicInformation().getCustomertypecode());
		dg_blk_intraday.setDinno(customerRequest.getDemographicInformation().getDinno());
		dg_blk_intraday.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDob()));
		dg_blk_intraday.setDoi(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDemographicInformation().getDoi()));
		dg_blk_intraday.setDrivinglicense(customerRequest.getDemographicInformation().getDrivinglicense());
		dg_blk_intraday.setFathername(customerRequest.getDemographicInformation().getFathername());
		dg_blk_intraday.setFirstname(customerRequest.getDemographicInformation().getFirstname());
		dg_blk_intraday.setGender(customerRequest.getDemographicInformation().getGender());
		dg_blk_intraday.setGstin(customerRequest.getDemographicInformation().getGstin());
		dg_blk_intraday.setHighesteducation(customerRequest.getDemographicInformation().getHighesteducation());
		dg_blk_intraday.setInserttime(timestamp);
		dg_blk_intraday.setLan(customerRequest.getDemographicInformation().getLan());
		dg_blk_intraday.setLastname(customerRequest.getDemographicInformation().getLastname());
		dg_blk_intraday.setLchgtime(timestamp);
		dg_blk_intraday.setMartialstatus(customerRequest.getDemographicInformation().getMartialstatus());
		dg_blk_intraday.setMatchingruleprofile(customerRequest.getMatchingruleprofile());
		dg_blk_intraday.setMiddlename(customerRequest.getDemographicInformation().getMiddlename());
//		dg_blk_intraday.setMothername(customerRequest.getMothername());
		dg_blk_intraday.setOld_psx_batch_id("");
		dg_blk_intraday.setPanno(customerRequest.getDemographicInformation().getPanno());
		dg_blk_intraday.setPassportno(customerRequest.getDemographicInformation().getPassportno());
		dg_blk_intraday.setPrimaryoccupation(customerRequest.getDemographicInformation().getPrimaryoccupation());
		dg_blk_intraday.setProduct(customerRequest.getDemographicInformation().getProduct());
		dg_blk_intraday.setRegistration_no(customerRequest.getDemographicInformation().getCno());
		dg_blk_intraday.setReligion(customerRequest.getDemographicInformation().getReligion());
		dg_blk_intraday.setResidencestatus(customerRequest.getDemographicInformation().getResidencestatus());
		dg_blk_intraday.setSourceapplicationid(customerRequest.getSourceapplicationid());
		dg_blk_intraday.setSourceapplicationno(customerRequest.getSourceapplicationno());
		dg_blk_intraday.setSourcecustomerid(customerRequest.getSourcecustomerid());
		dg_blk_intraday.setSourceauthenticationtoken(customerRequest.getSourceauthenticationtoken());
		dg_blk_intraday.setSourcesystemname(customerRequest.getSourcesystemname());
		dg_blk_intraday.setSpousename(customerRequest.getDemographicInformation().getSpousename());
		dg_blk_intraday.setTanno(customerRequest.getDemographicInformation().getTanno());
		dg_blk_intraday.setTitle(customerRequest.getDemographicInformation().getTitle());
		dg_blk_intraday.setUid(customerRequest.getDemographicInformation().getUid());
		dg_blk_intraday.setVoterid(customerRequest.getDemographicInformation().getVoterid());
		dg_blk_intraday.setRequestId(requestId);
		dg_blk_intraday.setUcic(ucicId);
		dg_blk_intraday.setUcicType(ucicType);
		dg_blk_intraday.setAadharNo(customerRequest.getDemographicInformation().getAadhar_no());
		dg_blk_intraday.setCycNo("");
		dg_blk_intraday.setGstin("");
		return dg_blk_intraday;
	}

	public List<MatchedCustomerDetailsBean> buildingMatchedCustomerDetails(List<CgclReportInputOutputBean> reportedMatches) {
		List<MatchedCustomerDetailsBean> matchedCustomerDetailsBeanList=new ArrayList<>();
		try {
			if(reportedMatches!=null){
			reportedMatches.stream().forEach(c->{
				MatchedCustomerDetailsBean matchedCustomerDetailsBean=new MatchedCustomerDetailsBean();
		
				
				List<String> matchCriteria = c.getMatchtype() !=null ? Stream.of(c.getMatchtype().split(",")).collect(Collectors.toList()) : Collections.EMPTY_LIST;
				matchCriteria=matchCriteria.stream().map(String :: trim).map(String :: toUpperCase).collect(Collectors.toList());
				logger.info("Matched ID :::  "+ c.getCustunqid()+"     Match Criteria :: "+matchCriteria);
			//	List<String> filteredList = matchCriteria.stream(). filter(value -> value.toUpperCase().contains("ADDRESS_")).collect(Collectors.toList());

				List<String> filteredList = matchCriteria.stream(). filter(value -> value.toUpperCase().matches("(?i).*ADDRESS_.*")).collect(Collectors.toList());
						
				logger.info("Matched ID :::  "+ c.getCustunqid()+"     Filtered Match Criteria :: "+filteredList);
				String addressType=maxStrengths(c.getPermanentMaxStrength()!=null ? Integer.parseInt(c.getPermanentMaxStrength()) : 0,c.getCurrentMaxStrength()!=null ? Integer.parseInt(c.getCurrentMaxStrength()) : 0,c.getResidentMaxStrength()!=null ? Integer.parseInt(c.getResidentMaxStrength()) : 0,c.getEmployeeMaxStrength()!=null ? Integer.parseInt(c.getEmployeeMaxStrength()) : 0);
				List<String> maxAddressStrength=new ArrayList<>();
				maxAddressStrength.add(addressType);
				filteredList.retainAll(maxAddressStrength);
				if(filteredList !=null && !filteredList.isEmpty()){
				filteredList.stream().forEach(mAddressType->{
					
					logger.info("Max Address Strength Type "+addressType+" for Matched_Id "+c.getCustunqid());
					if(mAddressType.equalsIgnoreCase(environment.getProperty("addressType0")) && addressType.equalsIgnoreCase(environment.getProperty("addressType0"))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress0());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid0());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype0());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode0());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity0());
						matchedCustomerDetailsBean.setCurrentState(c.getState0());	
						
					}
					
					if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals("")) && mAddressType.equalsIgnoreCase(environment.getProperty("addressType1")) && addressType.equalsIgnoreCase(environment.getProperty("addressType1"))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress1());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid1());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype1());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode1());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity1());
						matchedCustomerDetailsBean.setCurrentState(c.getState1());	
						
					}
					if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals("")) && mAddressType.equalsIgnoreCase(environment.getProperty("addressType2")) && addressType.equalsIgnoreCase(environment.getProperty("addressType2"))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress2());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid2());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype2());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode2());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity2());
						matchedCustomerDetailsBean.setCurrentState(c.getState2());	
						
					}
					if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals("")) && mAddressType.equalsIgnoreCase(environment.getProperty("addressType3")) && addressType.equalsIgnoreCase(environment.getProperty("addressType3"))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress3());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid3());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype3());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode3());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity3());
						matchedCustomerDetailsBean.setCurrentState(c.getState3());	
					}
					if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals(""))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress0());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid0());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype0());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode0());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity0());
						matchedCustomerDetailsBean.setCurrentState(c.getState0());	
						if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals("")) && mAddressType.equalsIgnoreCase(environment.getProperty("addressType1"))){
							matchedCustomerDetailsBean.setCurrentAddress(c.getAddress1());
							matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid1());
							matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype1());
							matchedCustomerDetailsBean.setCurrentPincode(c.getPincode1());
							matchedCustomerDetailsBean.setCurrentCity(c.getCity1());
							matchedCustomerDetailsBean.setCurrentState(c.getState1());	
							if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals("")) && mAddressType.equalsIgnoreCase(environment.getProperty("addressType2"))){
								matchedCustomerDetailsBean.setCurrentAddress(c.getAddress2());
								matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid2());
								matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype2());
								matchedCustomerDetailsBean.setCurrentPincode(c.getPincode2());
								matchedCustomerDetailsBean.setCurrentCity(c.getCity2());
								matchedCustomerDetailsBean.setCurrentState(c.getState2());	
								if((matchedCustomerDetailsBean.getCurrentAddressType() == null || matchedCustomerDetailsBean.getCurrentAddressType().equals("")) && mAddressType.equalsIgnoreCase(environment.getProperty("addressType3"))){
									matchedCustomerDetailsBean.setCurrentAddress(c.getAddress3());
									matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid3());
									matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype3());
									matchedCustomerDetailsBean.setCurrentPincode(c.getPincode3());
									matchedCustomerDetailsBean.setCurrentCity(c.getCity3());
									matchedCustomerDetailsBean.setCurrentState(c.getState3());	
								}
							}
						}
					}
					
					
				});
				}else{
					
					if(c.getAddress0() !=null && !c.getAddress0().equals("")){
						
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress0());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid0());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype0());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode0());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity0());
						matchedCustomerDetailsBean.setCurrentState(c.getState0());
					}else if((matchedCustomerDetailsBean.getCurrentAddress() == null || matchedCustomerDetailsBean.getCurrentAddress().equals("")) && (c.getAddress1() !=null && !c.getAddress1().equals(""))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress1());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid1());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype1());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode1());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity1());
						matchedCustomerDetailsBean.setCurrentState(c.getState1());
					}else if((matchedCustomerDetailsBean.getCurrentAddress() == null || matchedCustomerDetailsBean.getCurrentAddress().equals("")) && (c.getAddress2() !=null && !c.getAddress2().equals(""))){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress2());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid2());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype2());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode2());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity2());
						matchedCustomerDetailsBean.setCurrentState(c.getState2());
					}else if((matchedCustomerDetailsBean.getCurrentAddress() == null || matchedCustomerDetailsBean.getCurrentAddress().equals("")) && (c.getAddress3() !=null && !c.getAddress().equals("")) ){
						matchedCustomerDetailsBean.setCurrentAddress(c.getAddress3());
						matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid3());
						matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype3());
						matchedCustomerDetailsBean.setCurrentPincode(c.getPincode3());
						matchedCustomerDetailsBean.setCurrentCity(c.getCity3());
						matchedCustomerDetailsBean.setCurrentState(c.getState3());
					}
				}
				
				
//				matchedCustomerDetailsBean.setCurrentAddress(c.getAddress0());
//				matchedCustomerDetailsBean.setCurrentAddressId(c.getAddressid0());
//				matchedCustomerDetailsBean.setCurrentAddressType(c.getAddresstype0());
//				matchedCustomerDetailsBean.setCurrentPincode(c.getPincode0());
//				matchedCustomerDetailsBean.setCurrentCity(c.getCity0());
//				matchedCustomerDetailsBean.setCurrentState(c.getState0());
//			
//				matchedCustomerDetailsBean.setPermanentAddress(c.getAddress1());
//				matchedCustomerDetailsBean.setPermanentAddressId(c.getAddressid1());
//				matchedCustomerDetailsBean.setPermanentAddressType(c.getAddresstype1());
//				matchedCustomerDetailsBean.setPermanentPincode(c.getPincode1());
//				matchedCustomerDetailsBean.setPermanentCity(c.getCity1());
//				matchedCustomerDetailsBean.setPermanentState(c.getState1());
//			
//				matchedCustomerDetailsBean.setEmployerAddress(c.getAddress2());
//				matchedCustomerDetailsBean.setEmployerAddressId(c.getAddressid2());
//				matchedCustomerDetailsBean.setEmployerAddressType(c.getAddresstype2());
//				matchedCustomerDetailsBean.setEmployerPincode(c.getPincode2());
//				matchedCustomerDetailsBean.setEmployerCity(c.getCity2());
//				matchedCustomerDetailsBean.setEmployerState(c.getState2());
//		
//				matchedCustomerDetailsBean.setFactoryAddress(c.getAddress3());
//				matchedCustomerDetailsBean.setFactoryAddressId(c.getAddressid3());
//				matchedCustomerDetailsBean.setFactoryAddressType(c.getAddresstype3());
//				matchedCustomerDetailsBean.setFactoryPincode(c.getPincode3());
//				matchedCustomerDetailsBean.setFactoryCity(c.getCity3());
//				matchedCustomerDetailsBean.setFactoryState(c.getState3());
				
//				matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact0());
//				matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact1());
//				matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline0());
//				matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline1());
//				matchedCustomerDetailsBean.setCurrentEmail(c.getEmail0());
//				matchedCustomerDetailsBean.setPermanentEmail(c.getEmail1());
			
				if (Boolean.parseBoolean(environment.getProperty("dynamicContactEmail")) == false) {
					 matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact0());
					 matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact1());
					 matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline0());
					 matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline1());
					 matchedCustomerDetailsBean.setCurrentEmail(c.getEmail0());
					 matchedCustomerDetailsBean.setPermanentEmail(c.getEmail1());
					} else {
					 if (c.getCustomercontact0() != null) {
					  matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact0());
					  if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact1() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact1());
					  } else if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact2() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact2());
					  } else if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact3() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact3());
					  } else if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact4() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact4());
					  }
					 } else if (matchedCustomerDetailsBean.getCurrentPhone() == null && c.getCustomercontact1() != null) {
					  matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact1());
					  if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact2() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact2());
					  } else if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact3() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact3());
					  } else if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact4() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact4());
					  }
					 } else if (matchedCustomerDetailsBean.getCurrentPhone() == null && c.getCustomercontact2() != null) {
					  matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact2());
					  if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact3() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact3());
					  } else if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact4() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact4());
					  }
					 } else if (matchedCustomerDetailsBean.getCurrentPhone() == null && c.getCustomercontact3() != null) {
					  matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact3());
					  if (matchedCustomerDetailsBean.getPermanentPhone() == null && c.getCustomercontact4() != null) {
					   matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact4());
					  }
					 } else if(matchedCustomerDetailsBean.getCurrentPhone() == null && c.getCustomercontact4() != null) {
					  matchedCustomerDetailsBean.setCurrentPhone(c.getCustomercontact4());
					 }


					 if (c.getCustomerlandline0() != null) {
					  matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline0());
					  if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline1() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline1());
					  } else if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline2() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline2());
					  } else if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline3() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline3());
					  } else if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline4() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline4());
					  }
					 } else if (matchedCustomerDetailsBean.getLandlinenumber1() == null && c.getCustomerlandline1() != null) {
					  matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline1());
					  if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline2() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline2());
					  } else if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline3() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline3());
					  } else if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline4() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline4());
					  }
					 } else if (matchedCustomerDetailsBean.getLandlinenumber1() == null && c.getCustomerlandline2() != null) {
					  matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline2());
					  if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline3() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline3());
					  } else if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline4() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline4());
					  }
					 } else if (matchedCustomerDetailsBean.getLandlinenumber1() == null && c.getCustomerlandline3() != null) {
					  matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline3());
					  if (matchedCustomerDetailsBean.getLandlinenumber2() == null && c.getCustomerlandline4() != null) {
					   matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline4());
					  }
					 } else if(matchedCustomerDetailsBean.getLandlinenumber1() == null && c.getCustomerlandline4() != null) {
					  matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline4());
					 }


					 if (c.getEmail0() != null) {
					  matchedCustomerDetailsBean.setCurrentEmail(c.getEmail0());
					  if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail1() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail1());
					  } else if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail2() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail2());
					  } else if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail3() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail3());
					  } else if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail4() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail4());
					  }
					 } else if (matchedCustomerDetailsBean.getCurrentEmail() == null && c.getEmail1() != null) {
					  matchedCustomerDetailsBean.setCurrentEmail(c.getEmail1());
					  if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail2() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail2());
					  } else if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail3() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail3());
					  } else if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail4() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail4());
					  }
					 } else if (matchedCustomerDetailsBean.getCurrentEmail() == null && c.getEmail2() != null) {
					  matchedCustomerDetailsBean.setCurrentEmail(c.getEmail2());
					  if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail3() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail3());
					  } else if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail4() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail4());
					  }
					 } else if (matchedCustomerDetailsBean.getCurrentEmail() == null && c.getEmail3() != null) {
					  matchedCustomerDetailsBean.setCurrentEmail(c.getEmail3());
					  if (matchedCustomerDetailsBean.getPermanentEmail() == null && c.getEmail4() != null) {
					   matchedCustomerDetailsBean.setPermanentEmail(c.getEmail4());
					  }
					 } else if(matchedCustomerDetailsBean.getCurrentEmail() == null && c.getEmail4() != null) {
					  matchedCustomerDetailsBean.setCurrentEmail(c.getEmail4());
					 }
					}
				
				matchedCustomerDetailsBean.setPermanentPhone(c.getCustomercontact1());
				matchedCustomerDetailsBean.setLandlinenumber1(c.getCustomerlandline0());
				matchedCustomerDetailsBean.setLandlinenumber2(c.getCustomerlandline1());
				matchedCustomerDetailsBean.setCurrentEmail(c.getEmail0());
				matchedCustomerDetailsBean.setPermanentEmail(c.getEmail1());
				
				
				matchedCustomerDetailsBean.setCibil_score(c.getCibilscore());
		//		matchedCustomerDetailsBean.setCity(c.get);
				
				matchedCustomerDetailsBean.setCustomertypecode(c.getCustomertypecode());
				matchedCustomerDetailsBean.setDinno(c.getDin());
				matchedCustomerDetailsBean.setDob(c.getDob());
				matchedCustomerDetailsBean.setDoi(c.getDoi());
				matchedCustomerDetailsBean.setDrivinglicense(c.getDrivinglicno());
	//			matchedCustomerDetailsBean.setEmail(c.getEmail0());
				matchedCustomerDetailsBean.setFathername(c.getFathername());
				matchedCustomerDetailsBean.setName(c.getName());
				matchedCustomerDetailsBean.setFirstname(c.getFirstname());
				matchedCustomerDetailsBean.setLastname(c.getLastname());
				matchedCustomerDetailsBean.setMiddlename(c.getMiddlename());
				matchedCustomerDetailsBean.setGender(c.getGender());
				matchedCustomerDetailsBean.setGstin(c.getGstin());
				matchedCustomerDetailsBean.setHighesteducation(c.getHighesteducation());
	//			matchedCustomerDetailsBean.setLandlinenumber(c.getCustomerlandline0());
	//			matchedCustomerDetailsBean.setLastname(c.getLastname());
				matchedCustomerDetailsBean.setMartialstatus(c.getMaritalstatus());
	//			matchedCustomerDetailsBean.setMiddlename(c.getMiddlename());
	//			matchedCustomerDetailsBean.setMobilenumber(c.getCustomercontact0());
				matchedCustomerDetailsBean.setMothername(c.getMothername());
				matchedCustomerDetailsBean.setPanno(c.getPanno());
				matchedCustomerDetailsBean.setPassportno(c.getPassportno());
//				matchedCustomerDetailsBean.setPincode(0);
				matchedCustomerDetailsBean.setPrimaryoccupation(c.getPrimaryoccupation());
				matchedCustomerDetailsBean.setRegistrationorcinno(c.getCin());
				matchedCustomerDetailsBean.setResidencestatus(c.getResidentstatus());
				matchedCustomerDetailsBean.setSpousename(c.getSpousename());
//				matchedCustomerDetailsBean.setState("");
				matchedCustomerDetailsBean.setTanno(c.getTan());
				matchedCustomerDetailsBean.setUcic(c.getUcic());
				matchedCustomerDetailsBean.setUid(c.getUid());
				matchedCustomerDetailsBean.setVoterid(c.getVoteridno());
				matchedCustomerDetailsBean.setAadharNo(c.getAadharno());
				matchedCustomerDetailsBean.setAccountStatus(c.getAccountstatus());
				matchedCustomerDetailsBean.setMatchCriteria(c.getMatchtype());
				matchedCustomerDetailsBean.setRecordType(c.getRecordtype());
				matchedCustomerDetailsBean.setMatchId(c.getCustunqid());
				matchedCustomerDetailsBean.setScaleType(c.getScaletype());
				matchedCustomerDetailsBean.setFatherFirstName(c.getFatherFirstName());
				matchedCustomerDetailsBean.setFatherLastName(c.getFatherLastName());
				matchedCustomerDetailsBeanList.add(matchedCustomerDetailsBean);
			}
			);
			}
		}catch(Exception e) {
			logger.error(e,e);
		}
		
		return matchedCustomerDetailsBeanList;
	}

	public String maxStrengths(int a, int b, int c, int d) {
		
		String maxAddressType="";
	    int max = a;

	    if (b > max){
	        max = b;
	        maxAddressType=environment.getProperty("addressType1");
	    }
	    if (c > max){
	        max = c;
	        maxAddressType=environment.getProperty("addressType2");
	    }
	    if (d > max){
	        max = d;
	        maxAddressType=environment.getProperty("addressType3");
	    }
	    
	    if(max == a)
	    	maxAddressType=environment.getProperty("addressType0");
	    	 

	     return maxAddressType;
	}
	public List<MatchedCustomerLoanDetailsBean> buildingMatchedLoanDetails(List<CgclLoanDetailsBean> loanMatches) {
		List<MatchedCustomerLoanDetailsBean> matchedCustomerLoanDetailsBeanList=new ArrayList<>();
		try {
			loanMatches.stream().forEach(l->{
				MatchedCustomerLoanDetailsBean matchedCustomerLoanDetailsBean=new MatchedCustomerLoanDetailsBean();
				matchedCustomerLoanDetailsBean.setApplicationno(l.getApplication_no());
				matchedCustomerLoanDetailsBean.setBasicloanamount(l.getBasic_loan_amount());
				matchedCustomerLoanDetailsBean.setCurrentpos(l.getCurrent_pos());
				matchedCustomerLoanDetailsBean.setCustomerno(l.getCustomer_no());
				matchedCustomerLoanDetailsBean.setDpd(l.getDpd());
				matchedCustomerLoanDetailsBean.setFinterestrate(l.getF_interest_rate());
				matchedCustomerLoanDetailsBean.setIncome(l.getIncome());
				matchedCustomerLoanDetailsBean.setLoanamount(l.getLoan_amount());
				matchedCustomerLoanDetailsBean.setLoanamountno(l.getLoan_account_no());
				matchedCustomerLoanDetailsBean.setLoanstatus(l.getStatus());
				matchedCustomerLoanDetailsBean.setStatus(l.getStatus());
				matchedCustomerLoanDetailsBean.setTenor(l.getTenor());
				matchedCustomerLoanDetailsBeanList.add(matchedCustomerLoanDetailsBean);
			});
		}catch(Exception e) {
			logger.error(e,e);
		}
		
		return matchedCustomerLoanDetailsBeanList;
	}

	public PsxRequestBean buildingPsxRequestInfo(long reqPsxid, Date timestamp, String custUnqId,String matchingruleprofile, String requestStatus) {
	
		PsxRequestBean psxRequest =new PsxRequestBean();
		psxRequest.setRequestId(reqPsxid+"");
		psxRequest.setInserttime(timestamp);
		psxRequest.setLchgtime(timestamp);
		psxRequest.setCustunqid(custUnqId);
		psxRequest.setProfileid(matchingruleprofile);
		psxRequest.setRequeststatus(requestStatus);
		return psxRequest;
	}

	public CustomerRequestInformation buildingCustomerAceObject(List<CgclAceBlkBean> custAceBlkRequestList) {
		CustomerRequestInformation customerRequestBean=new CustomerRequestInformation();
		int counter[]=new int[]{0};
		AddressInformation addressInfo=new AddressInformation();
		ContactInformation contactInfo=new ContactInformation();
		EmailInformation emailInfo=new EmailInformation();

		try{
			System.out.println("custAceBlkRequestList "+custAceBlkRequestList);
	//	custAceBlkRequestList.stream().forEach(custAceBlkRequest->{
			for(CgclAceBlkBean custAceBlkRequest:custAceBlkRequestList){
		System.out.println("custAceBlkRequest "+custAceBlkRequest);
		if(counter[0]==0){
			addressInfo.setAddress0(custAceBlkRequest.getAddress());
			addressInfo.setAddressid0(custAceBlkRequest.getAddressid());
			addressInfo.setAddresstype0(custAceBlkRequest.getAddresstype());
			addressInfo.setCity0(custAceBlkRequest.getCity());
			addressInfo.setPincode0(custAceBlkRequest.getPincode());
			addressInfo.setState0(custAceBlkRequest.getState());
			contactInfo.setContact0(custAceBlkRequest.getMobilenumber());
			contactInfo.setContactType0(custAceBlkRequest.getCustomerContactType());
			contactInfo.setLandline0(custAceBlkRequest.getLandlinenumber());
			contactInfo.setLandlineType0(custAceBlkRequest.getCustomerLandlineType());
			emailInfo.setEmail0(custAceBlkRequest.getEmail());
			emailInfo.setEmailType0(custAceBlkRequest.getEmailType());
		}
		if(counter[0]==1){
			addressInfo.setAddress1(custAceBlkRequest.getAddress());
			addressInfo.setAddressid1(custAceBlkRequest.getAddressid());
			addressInfo.setAddresstype1(custAceBlkRequest.getAddresstype());
			addressInfo.setCity1(custAceBlkRequest.getCity());
			addressInfo.setPincode1(custAceBlkRequest.getPincode());
			addressInfo.setState1(custAceBlkRequest.getState());
			contactInfo.setContact1(custAceBlkRequest.getMobilenumber());
			contactInfo.setContactType1(custAceBlkRequest.getCustomerContactType());
			contactInfo.setLandline1(custAceBlkRequest.getLandlinenumber());
			contactInfo.setLandlineType1(custAceBlkRequest.getCustomerLandlineType());
			emailInfo.setEmail1(custAceBlkRequest.getEmail());
			emailInfo.setEmailType1(custAceBlkRequest.getEmailType());
			}
		if(counter[0]==2){
			addressInfo.setAddress2(custAceBlkRequest.getAddress());
			addressInfo.setAddressid2(custAceBlkRequest.getAddressid());
			addressInfo.setAddresstype2(custAceBlkRequest.getAddresstype());
			addressInfo.setCity2(custAceBlkRequest.getCity());
			addressInfo.setPincode2(custAceBlkRequest.getPincode());
			addressInfo.setState2(custAceBlkRequest.getState());
//			customerRequestBean.getContactInformation().setContact2(custAceBlkRequest.getMobilenumber());
//			customerRequestBean.getContactInformation().setLandline0(custAceBlkRequest.getLandlinenumber());
//			customerRequestBean.getEmailInformation().setEmail0(custAceBlkRequest.getEmail());
			}
		if(counter[0]==3){
			addressInfo.setAddress3(custAceBlkRequest.getAddress());
			addressInfo.setAddressid3(custAceBlkRequest.getAddressid());
			addressInfo.setAddresstype3(custAceBlkRequest.getAddresstype());
			addressInfo.setCity3(custAceBlkRequest.getCity());
			addressInfo.setPincode3(custAceBlkRequest.getPincode());
			addressInfo.setState3(custAceBlkRequest.getState());
//			customerRequestBean.getContactInformation().setContact0(custAceBlkRequest.getMobilenumber());
//			customerRequestBean.getContactInformation().setLandline0(custAceBlkRequest.getLandlinenumber());
//			customerRequestBean.getEmailInformation().setEmail0(custAceBlkRequest.getEmail());
			}
	//	customerRequestBean.setAssignUcic(custDgBlkRequest.getAssignUcic());
		
		counter[0]++;
			}
			customerRequestBean.setAddressInformation(addressInfo);
			customerRequestBean.setContactInformation(contactInfo);
			customerRequestBean.setEmailInformation(emailInfo);
	//	});
		}catch(Exception e){
			e.printStackTrace();
		}
		return customerRequestBean;
	}
	public CustomerRequestInformation buildingCustomerDgObject(CgclDgBlkBean custDgBlkRequest) {
		CustomerRequestInformation customerRequestBean=new CustomerRequestInformation();
		try{
//Tax_id, ckyc_no,product,Religion,Title
		DemographicInformation dgcustomerRequestBean=new DemographicInformation();
		
		dgcustomerRequestBean.setAadhar_no(custDgBlkRequest.getAadharNo());
		dgcustomerRequestBean.setAccount_status(custDgBlkRequest.getAccount_status());
		dgcustomerRequestBean.setCustomertypecode(custDgBlkRequest.getCustomercategory());
		dgcustomerRequestBean.setCno(custDgBlkRequest.getCin());
		dgcustomerRequestBean.setCycNo(custDgBlkRequest.getCycNo());
		dgcustomerRequestBean.setCaste(custDgBlkRequest.getCaste());
		dgcustomerRequestBean.setCibil_score(custDgBlkRequest.getCibil_score());
		dgcustomerRequestBean.setCustomertypecode(custDgBlkRequest.getCustomertypecode());
		dgcustomerRequestBean.setDinno(custDgBlkRequest.getDinno());
		dgcustomerRequestBean.setDob(custDgBlkRequest.getDob()+"");
		dgcustomerRequestBean.setDoi(custDgBlkRequest.getDoi()+"");
		dgcustomerRequestBean.setDrivinglicense(custDgBlkRequest.getDrivinglicense());
		dgcustomerRequestBean.setFathername(custDgBlkRequest.getFathername());
		dgcustomerRequestBean.setFirstname(custDgBlkRequest.getFirstname());
		dgcustomerRequestBean.setGender(custDgBlkRequest.getGender());
		dgcustomerRequestBean.setGstin(custDgBlkRequest.getGstin());
		dgcustomerRequestBean.setHighesteducation(custDgBlkRequest.getHighesteducation());
		dgcustomerRequestBean.setLan(custDgBlkRequest.getLan());
		dgcustomerRequestBean.setLastname(custDgBlkRequest.getLastname());
		dgcustomerRequestBean.setMartialstatus(custDgBlkRequest.getMartialstatus());
		dgcustomerRequestBean.setMiddlename(custDgBlkRequest.getMiddlename());
		dgcustomerRequestBean.setMothername(custDgBlkRequest.getMotherName());
		dgcustomerRequestBean.setPanno(custDgBlkRequest.getPanno());
		dgcustomerRequestBean.setPassportno(custDgBlkRequest.getPassportno());
		dgcustomerRequestBean.setVoterid(custDgBlkRequest.getVoterid());
		dgcustomerRequestBean.setTanno(custDgBlkRequest.getTanno());
		dgcustomerRequestBean.setPrimaryoccupation(custDgBlkRequest.getPrimaryoccupation());
		dgcustomerRequestBean.setResidencestatus(custDgBlkRequest.getResidencestatus());
		dgcustomerRequestBean.setSpousename(custDgBlkRequest.getSpousename());
		dgcustomerRequestBean.setTaxId(custDgBlkRequest.getTaxId());
		dgcustomerRequestBean.setProduct(custDgBlkRequest.getProduct());
		dgcustomerRequestBean.setReligion(custDgBlkRequest.getReligion());
		dgcustomerRequestBean.setTitle(custDgBlkRequest.getTitle());
		dgcustomerRequestBean.setUid(custDgBlkRequest.getUid());
		dgcustomerRequestBean.setFatherFirstName(custDgBlkRequest.getFatherFirstName());
		dgcustomerRequestBean.setFatherLastName(custDgBlkRequest.getFatherLastName());
		customerRequestBean.setDemographicInformation(dgcustomerRequestBean);
		customerRequestBean.setMatchingruleprofile(custDgBlkRequest.getMatchingruleprofile());
		customerRequestBean.setCustomercategory(custDgBlkRequest.getCustomercategory());
		customerRequestBean.setSourceapplicationid(custDgBlkRequest.getSourceapplicationid()+"");
		customerRequestBean.setRequestId(custDgBlkRequest.getRequestId());
		customerRequestBean.setSourceapplicationno(custDgBlkRequest.getSourceapplicationno());
		customerRequestBean.setSourceauthenticationtoken(custDgBlkRequest.getSourceauthenticationtoken());
		System.out.println("Setting Cusotmer in Intraday Bean :: "+custDgBlkRequest.getSourcecustomerid());
		customerRequestBean.setSourcecustomerid(custDgBlkRequest.getSourcecustomerid());
			// customerRequestBean.setUcic(custDgBlkRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customerRequestBean;
	}

	public void checkingLengths(CustomerRequestInformation customerRequest)
			throws PosidexException {

		try {
			if (customerRequest.getCustomercategory() != null && customerRequest.getCustomercategory().length() > Integer.parseInt(environment.getProperty("customerCategorySize")))
				throw new PosidexException("CUSTOMER_CATEGORY Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("customerCategorySize"))+ " Actual Length :: "+ customerRequest.getCustomercategory().length());

			if (customerRequest.getMatchingruleprofile() != null && customerRequest.getMatchingruleprofile().length() > Integer.parseInt(environment.getProperty("matchingRuleProfileSize")))
				throw new PosidexException("MATCHING_RULE_PROFILE Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("matchingRuleProfileSize"))+ " Actual Length :: "+ customerRequest.getMatchingruleprofile().length());

			if (customerRequest.getSourceapplicationid() != null && customerRequest.getSourceapplicationid().length() > Integer.parseInt(environment.getProperty("sourceApplicationIdSize")))
				throw new PosidexException("SOURCE_APPLICATION_ID Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceApplicationIdSize"))+ " Actual Length :: "+ customerRequest.getSourceapplicationid().length());
			
			if (customerRequest.getSourceapplicationno() != null && customerRequest.getSourceapplicationno().length() > Integer.parseInt(environment.getProperty("sourceApplicationNoSize")))
				throw new PosidexException("SOURCE_APPLICATION_NO Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceApplicationNoSize"))+ " Actual Length :: "+ customerRequest.getSourceapplicationno().length());
			
			if (customerRequest.getSourceauthenticationtoken() != null && customerRequest.getSourceauthenticationtoken().length() > Integer.parseInt(environment.getProperty("sourceAuthenticationTokenSize")))
				throw new PosidexException("SOURCE_AUTHENTICATION_TOKEN Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceAuthenticationTokenSize"))+ " Actual Length :: "+ customerRequest.getSourceauthenticationtoken().length());
			
			if (customerRequest.getSourcecustomerid() != null && customerRequest.getSourcecustomerid().length() > Integer.parseInt(environment.getProperty("sourceCustomerIdSize")))
				throw new PosidexException("SOURCE_CUSTOMER_ID Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceCustomerIdSize"))+ " Actual Length :: "+ customerRequest.getSourcecustomerid().length());
			
			if (customerRequest.getSourcesystemname() != null && customerRequest.getSourcesystemname().length() > Integer.parseInt(environment.getProperty("sourceSystemNameSize")))
				throw new PosidexException("SOURCE_SYSTEM_NAME Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceSystemNameSize"))+ " Actual Length :: "+ customerRequest.getSourcesystemname().length());
			
			if (customerRequest.getDemographicInformation().getAadhar_no() != null && customerRequest.getDemographicInformation().getAadhar_no().length() > Integer.parseInt(environment.getProperty("aadharSize")))
				throw new PosidexException("AADHAR_NO Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("aadharSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getAadhar_no().length());

			if(customerRequest.getDemographicInformation().getAccount_status() != null && customerRequest.getDemographicInformation().getAccount_status().length() >Integer.parseInt(environment.getProperty("accountStatusSize")))
			    throw new PosidexException("ACCOUNT_STATUS  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("accountStatusSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getAccount_status().length());
			
			if(customerRequest.getDemographicInformation().getCaste() != null && customerRequest.getDemographicInformation().getCaste().length() >Integer.parseInt(environment.getProperty("casteSize")))
			    throw new PosidexException("CASTE  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("casteSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getCaste().length());

			if(customerRequest.getDemographicInformation().getCibil_score() != null && customerRequest.getDemographicInformation().getCibil_score().length() >Integer.parseInt(environment.getProperty("cibilScoreSize")))
				throw new PosidexException("CIBIL_SCORE  Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("cibilScoreSize"))+ " Actual Length :: "+ customerRequest.getDemographicInformation().getCibil_score().length());
			
			if(customerRequest.getDemographicInformation().getCno() != null && customerRequest.getDemographicInformation().getCno().length() >Integer.parseInt(environment.getProperty("cinNoSize")))
			    throw new PosidexException("CINNO  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("cinNoSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getCno().length());

			if(customerRequest.getDemographicInformation().getCustomertypecode() != null && customerRequest.getDemographicInformation().getCustomertypecode().length() >Integer.parseInt(environment.getProperty("customerTypeCodeSize")))
			    throw new PosidexException("CUSTOMER_TYPE_CODE  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("customerTypeCodeSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getCustomertypecode().length());

			if(customerRequest.getDemographicInformation().getCycNo() != null && customerRequest.getDemographicInformation().getCycNo().length() >Integer.parseInt(environment.getProperty("cycNoSize")))
			    throw new PosidexException("CYC_NO  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("cycNoSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getCycNo().length());
			
			if(customerRequest.getDemographicInformation().getDinno() != null && customerRequest.getDemographicInformation().getDinno().length() >Integer.parseInt(environment.getProperty("dinNoSize")))
			    throw new PosidexException("DINNO  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("dinNoSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getDinno().length());

			if(customerRequest.getDemographicInformation().getDob() != null && customerRequest.getDemographicInformation().getDob() != "" &&  !validateJavaDate(customerRequest.getDemographicInformation().getDob()))
			    throw new PosidexException("DOB  must be in dd-MM-yyyy format");
			if(customerRequest.getDemographicInformation().getDoi() != null && customerRequest.getDemographicInformation().getDoi() != "" && !validateJavaDate(customerRequest.getDemographicInformation().getDoi()))
			    throw new PosidexException("DOI  must be in dd-MM-yyyy format");
			
			if(customerRequest.getDemographicInformation().getDrivinglicense() != null && customerRequest.getDemographicInformation().getDrivinglicense().length() >Integer.parseInt(environment.getProperty("drivingLicenseSize")))
			    throw new PosidexException("DRIVING_LICENSE_NO Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("drivingLicenseSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getDrivinglicense().length());

//			if(customerRequest.getDemographicInformation().getFathername() != null && customerRequest.getDemographicInformation().getFathername().length() >Integer.parseInt(environment.getProperty("fatherNameSize")))
//			    throw new PosidexException("FATHER_NAME Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("fatherNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getFathername().length());
			
			
			
			if(customerRequest.getDemographicInformation().getFatherFirstName() != null && customerRequest.getDemographicInformation().getFatherFirstName().length() >Integer.parseInt(environment.getProperty("fatherFirstNameSize")))
			    throw new PosidexException("FATHER_FIRST_NAME Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("fatherFirstNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getFatherFirstName().length());
			
			if(customerRequest.getDemographicInformation().getFatherLastName() != null && customerRequest.getDemographicInformation().getFatherLastName().length() >Integer.parseInt(environment.getProperty("fatherLastNameSize")))
			    throw new PosidexException("FATHER_LAST_NAME Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("fatherLastNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getFatherLastName().length());
			
			
			
			if (customerRequest.getDemographicInformation().getFirstname() != null && customerRequest.getDemographicInformation().getFirstname().length() > Integer.parseInt(environment.getProperty("firstNameSize")))
				throw new PosidexException("FIRST_NAME Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("firstNameSize"))+ " Actual Length :: "+ customerRequest.getDemographicInformation().getFirstname().length());
			
			if(customerRequest.getDemographicInformation().getGender() != null && customerRequest.getDemographicInformation().getGender().length() >Integer.parseInt(environment.getProperty("genderSize")))
			    throw new PosidexException("GENDER Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("genderSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getGender().length());

			if(customerRequest.getDemographicInformation().getGstin() != null && customerRequest.getDemographicInformation().getGstin().length() >Integer.parseInt(environment.getProperty("gstinSize")))
			    throw new PosidexException("GSTIN  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("gstinSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getGstin().length());

			if(customerRequest.getDemographicInformation().getHighesteducation() != null && customerRequest.getDemographicInformation().getHighesteducation().length() >Integer.parseInt(environment.getProperty("highestEducationSize")))
			    throw new PosidexException("HIGHEST_EDUCATION  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("highestEducationSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getHighesteducation().length());
			
			if (customerRequest.getDemographicInformation().getLan() != null && customerRequest.getDemographicInformation().getLan().length() > Integer.parseInt(environment.getProperty("lanSize")))
				throw new PosidexException("LAN Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("lanSize"))+ " Actual Length :: "+ customerRequest.getDemographicInformation().getLan().length());
			
			if(customerRequest.getDemographicInformation().getLastname() != null && customerRequest.getDemographicInformation().getLastname().length() >Integer.parseInt(environment.getProperty("lastNameSize")))
			    throw new PosidexException("LAST_NAME Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("lastNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getLastname().length());
			
			if(customerRequest.getDemographicInformation().getMartialstatus() != null && customerRequest.getDemographicInformation().getMartialstatus().length() >Integer.parseInt(environment.getProperty("martialStatusSize")))
			    throw new PosidexException("MARTIAL_STATUS Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("martialStatusSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getMartialstatus().length());

			if(customerRequest.getDemographicInformation().getMiddlename() != null && customerRequest.getDemographicInformation().getMiddlename().length() >Integer.parseInt(environment.getProperty("middleNameSize")))
			    throw new PosidexException("MIDDLE_NAME  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("middleNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getMiddlename().length());

			if(customerRequest.getDemographicInformation().getMothername() != null && customerRequest.getDemographicInformation().getMothername().length() >Integer.parseInt(environment.getProperty("motherNameSize")))
			    throw new PosidexException("MOTHER_NAME Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("motherNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getMothername().length());

			if(customerRequest.getDemographicInformation().getPanno() != null && customerRequest.getDemographicInformation().getPanno().length() >Integer.parseInt(environment.getProperty("panNoSize")))
			    throw new PosidexException("PAN_NO  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("panNoSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getPanno().length());

			if(customerRequest.getDemographicInformation().getPassportno() != null && customerRequest.getDemographicInformation().getPassportno().length() >Integer.parseInt(environment.getProperty("passportNoSize")))
			    throw new PosidexException("PASSPORT_NO Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("passportNoSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getPassportno().length());

			if(customerRequest.getDemographicInformation().getPrimaryoccupation() != null && customerRequest.getDemographicInformation().getPrimaryoccupation().length() >Integer.parseInt(environment.getProperty("primaryOccupationSize")))
			    throw new PosidexException("PRIMARY_OCCUPATION  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("primaryOccupationSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getPrimaryoccupation().length());
			
			if (customerRequest.getDemographicInformation().getProduct() != null && customerRequest.getDemographicInformation().getProduct().length() > Integer.parseInt(environment.getProperty("productSize")))
				throw new PosidexException("PRODUCT Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("productSize"))+ " Actual Length :: "+ customerRequest.getDemographicInformation().getProduct().length());
			
			if(customerRequest.getDemographicInformation().getReligion() != null && customerRequest.getDemographicInformation().getReligion().length() >Integer.parseInt(environment.getProperty("religionSize")))
			    throw new PosidexException("RELIGION  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("religionSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getReligion().length());

			if(customerRequest.getDemographicInformation().getResidencestatus() != null && customerRequest.getDemographicInformation().getResidencestatus().length() >Integer.parseInt(environment.getProperty("residenceStatusSize")))
			    throw new PosidexException("RESIDENCE_STATUS  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("residenceStatusSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getResidencestatus().length());

			if(customerRequest.getDemographicInformation().getSpousename() != null && customerRequest.getDemographicInformation().getSpousename().length() >Integer.parseInt(environment.getProperty("spouseNameSize")))
			    throw new PosidexException("SPOUSE_NAME Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("spouseNameSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getSpousename().length());
			
			if(customerRequest.getDemographicInformation().getTanno() != null && customerRequest.getDemographicInformation().getTanno().length() >Integer.parseInt(environment.getProperty("tanNoSize")))
			    throw new PosidexException("TAN_NO  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("tanNoSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getTanno().length());
			
			if(customerRequest.getDemographicInformation().getTaxId() != null && customerRequest.getDemographicInformation().getTaxId().length() >Integer.parseInt(environment.getProperty("taxIdSize")))
			    throw new PosidexException("TAX_ID  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("taxIdSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getTaxId().length());
			
			if (customerRequest.getDemographicInformation().getTitle() != null && customerRequest.getDemographicInformation().getTitle().length() > Integer.parseInt(environment.getProperty("titleSize")))
				throw new PosidexException("TITLE Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("titleSize"))+ " Actual Length :: "+ customerRequest.getDemographicInformation().getTitle().length());
			
			if(customerRequest.getDemographicInformation().getUid() != 0 && noOfDigits(customerRequest.getDemographicInformation().getUid())  >Integer.parseInt(environment.getProperty("uidSize")))
			    throw new PosidexException("UID  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("uidSize"))+" Actual Length :: "+noOfDigits(customerRequest.getDemographicInformation().getUid()));

			if(customerRequest.getDemographicInformation().getVoterid() != null && customerRequest.getDemographicInformation().getVoterid().length() >Integer.parseInt(environment.getProperty("voterIdSize")))
			    throw new PosidexException("VOTER_ID Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("voterIdSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getVoterid().length());

			
			
			
//			if(customerRequest.getDemographicInformation().getDob() != null && customerRequest.getDemographicInformation().getDob().length() >Integer.parseInt(environment.getProperty("dobSize")))
//							    throw new PosidexException("DOB  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("dobSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getDob().length());
//			if(customerRequest.getDemographicInformation().getDoi() != null && customerRequest.getDemographicInformation().getDoi().length() >Integer.parseInt(environment.getProperty("doiSize")))
//							    throw new PosidexException("DOI  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("doiSize"))+" Actual Length :: "+customerRequest.getDemographicInformation().getDoi().length());
			
			if (customerRequest.getAddressInformation().getAddress0() != null && customerRequest.getAddressInformation().getAddress0().length() > Integer.parseInt(environment.getProperty("addressSize")))
				throw new PosidexException("ADDRESS_0  Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("addressSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getAddress0().length());
			if (customerRequest.getAddressInformation().getAddressid0() != 0 && noOfDigits(customerRequest.getAddressInformation().getAddressid0()) > Integer.parseInt(environment.getProperty("addressIdSize")))
				throw new PosidexException("ADDRESS_ID_0  Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("addressIdSize"))+ " Actual Length :: "+ noOfDigits(customerRequest.getAddressInformation().getAddressid0()));
			if (customerRequest.getAddressInformation().getAddresstype0() != null && customerRequest.getAddressInformation().getAddresstype0().length() > Integer.parseInt(environment.getProperty("addressTypeSize")))
				throw new PosidexException("ADDRESS_TYPE_0  Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("addressTypeSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getAddresstype0().length());
			if (customerRequest.getAddressInformation().getCity0() != null && customerRequest.getAddressInformation().getCity0().length() > Integer.parseInt(environment.getProperty("citySize")))
				throw new PosidexException("CITY_0  Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("citySize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getCity0().length());
			if (customerRequest.getAddressInformation().getPincode0() != null && customerRequest.getAddressInformation().getPincode0().length() > Integer.parseInt(environment.getProperty("pincodeSize")))
				throw new PosidexException("PINCODE_0  Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("pincodeSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getPincode0().length());
			if (customerRequest.getAddressInformation().getState0() != null && customerRequest.getAddressInformation().getState0().length() > Integer.parseInt(environment.getProperty("stateSize")))
				throw new PosidexException("STATE_0 Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("stateSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getState0().length());
			
			if (customerRequest.getAddressInformation().getAddress1() != null && customerRequest.getAddressInformation().getAddress1().length() > Integer.parseInt(environment.getProperty("addressSize")))
				throw new PosidexException("ADDRESS_1 Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("addressSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getAddress1().length());
			if (customerRequest.getAddressInformation().getAddressid1() != 0 && noOfDigits(customerRequest.getAddressInformation().getAddressid1()) > Integer.parseInt(environment.getProperty("addressIdSize")))
				throw new PosidexException("ADDRESS_ID_1  Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("addressIdSize"))+ " Actual Length :: "+ noOfDigits(customerRequest.getAddressInformation().getAddressid1()));
			if (customerRequest.getAddressInformation().getAddresstype1() != null && customerRequest.getAddressInformation().getAddresstype1().length() > Integer.parseInt(environment.getProperty("addressTypeSize")))
				throw new PosidexException("ADDRESS_TYPE_1  Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("addressTypeSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getAddresstype1().length());
			if (customerRequest.getAddressInformation().getCity1() != null && customerRequest.getAddressInformation().getCity1().length() > Integer.parseInt(environment.getProperty("citySize")))
				throw new PosidexException("CITY_1 Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("citySize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getCity1().length());
			if (customerRequest.getAddressInformation().getPincode1() != null && customerRequest.getAddressInformation().getPincode1().length() > Integer.parseInt(environment.getProperty("pincodeSize")))
				throw new PosidexException("PINCODE_1 Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("pincodeSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getPincode1().length());
			if (customerRequest.getAddressInformation().getState1() != null && customerRequest.getAddressInformation().getState1().length() > Integer.parseInt(environment.getProperty("stateSize")))				
				throw new PosidexException("STATE_1 Exceeds Length, Expected Length :: "+ Integer.parseInt(environment.getProperty("stateSize"))+ " Actual Length :: "+ customerRequest.getAddressInformation().getState1().length());
			
			if(customerRequest.getAddressInformation().getAddress2() != null && customerRequest.getAddressInformation().getAddress2().length() > Integer.parseInt(environment.getProperty("addressSize")))
				throw new PosidexException("ADDRESS_2  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("addressSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getAddress2().length());
			if(customerRequest.getAddressInformation().getAddressid2() != 0 && noOfDigits(customerRequest.getAddressInformation().getAddressid2()) >Integer.parseInt(environment.getProperty("addressIdSize")))
				throw new PosidexException("ADDRESS_ID_2 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("addressIdSize"))+" Actual Length :: "+noOfDigits(customerRequest.getAddressInformation().getAddressid2()));
			if(customerRequest.getAddressInformation().getAddresstype2() != null && customerRequest.getAddressInformation().getAddresstype2().length() > Integer.parseInt(environment.getProperty("addressTypeSize")))
				throw new PosidexException("ADDRESS_TYPE_2 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("addressTypeSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getAddresstype2().length());
			if(customerRequest.getAddressInformation().getCity2() != null && customerRequest.getAddressInformation().getCity2().length() > Integer.parseInt(environment.getProperty("citySize")))
				throw new PosidexException("CITY_2 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("citySize"))+" Actual Length :: "+customerRequest.getAddressInformation().getCity2().length());
			if(customerRequest.getAddressInformation().getPincode2() != null && customerRequest.getAddressInformation().getPincode2().length() > Integer.parseInt(environment.getProperty("pincodeSize")))
				throw new PosidexException("PINCODE_2 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("pincodeSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getPincode2().length());
			if(customerRequest.getAddressInformation().getState2() != null && customerRequest.getAddressInformation().getState2().length() > Integer.parseInt(environment.getProperty("stateSize")))
				throw new PosidexException("STATE_2 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("stateSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getState2().length());


			if(customerRequest.getAddressInformation().getAddress3() != null && customerRequest.getAddressInformation().getAddress3().length() >Integer.parseInt(environment.getProperty("addressSize")))
				throw new PosidexException("ADDRESS_3 Name  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("addressSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getAddress3().length());
			if(customerRequest.getAddressInformation().getAddressid3() != 0 && noOfDigits(customerRequest.getAddressInformation().getAddressid3()) > Integer.parseInt(environment.getProperty("addressIdSize")))
				throw new PosidexException("ADDRESS_ID_3 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("addressIdSize"))+" Actual Length :: "+noOfDigits(customerRequest.getAddressInformation().getAddressid3()));
			if(customerRequest.getAddressInformation().getAddresstype3() != null && customerRequest.getAddressInformation().getAddresstype3().length() >Integer.parseInt(environment.getProperty("addressTypeSize")))
				throw new PosidexException("ADDRESS_TYPE_3 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("addressTypeSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getAddresstype3().length());
			if(customerRequest.getAddressInformation().getCity3() != null && customerRequest.getAddressInformation().getCity3().length() >Integer.parseInt(environment.getProperty("citySize")))
				throw new PosidexException("CITY_3  Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("citySize"))+" Actual Length :: "+customerRequest.getAddressInformation().getCity3().length());
			if(customerRequest.getAddressInformation().getPincode3() != null && customerRequest.getAddressInformation().getPincode3().length() >Integer.parseInt(environment.getProperty("pincodeSize")))
				throw new PosidexException("PINCODE_3  Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("pincodeSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getPincode3().length());
			if(customerRequest.getAddressInformation().getState3() != null && customerRequest.getAddressInformation().getState3().length() >Integer.parseInt(environment.getProperty("stateSize")))
				throw new PosidexException("STATE_3 Exceeds Length,  Expected Length :: "+Integer.parseInt(environment.getProperty("stateSize"))+" Actual Length :: "+customerRequest.getAddressInformation().getState3().length());

		
			
			if(customerRequest.getContactInformation().getContactType0() != null && customerRequest.getContactInformation().getContactType0().length() >Integer.parseInt(environment.getProperty("contactTypeSize")))
							    throw new PosidexException("CUSTOMER_CONTACT_TYPE_0 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("contactTypeSize"))+" Actual Length :: "+customerRequest.getContactInformation().getContactType0().length());
			if(customerRequest.getContactInformation().getContact0() != null && customerRequest.getContactInformation().getContact0().length() >Integer.parseInt(environment.getProperty("mobileNumberSize")))
							    throw new PosidexException("CUSTOMER_CONTACT_0 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("mobileNumberSize"))+" Actual Length :: "+customerRequest.getContactInformation().getContact0().length());
		
			if(customerRequest.getContactInformation().getContactType1() != null && customerRequest.getContactInformation().getContactType1().length() >Integer.parseInt(environment.getProperty("contactTypeSize")))
			    throw new PosidexException("CUSTOMER_CONTACT_TYPE_1 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("contactTypeSize"))+" Actual Length :: "+customerRequest.getContactInformation().getContactType1().length());
			if(customerRequest.getContactInformation().getContact1() != null && customerRequest.getContactInformation().getContact1().length() >Integer.parseInt(environment.getProperty("mobileNumberSize")))
			    throw new PosidexException("CUSTOMER_CONTACT_1 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("mobileNumberSize"))+" Actual Length :: "+customerRequest.getContactInformation().getContact1().length());
			
			
			if(customerRequest.getContactInformation().getLandlineType0() != null && customerRequest.getContactInformation().getLandlineType0().length() >Integer.parseInt(environment.getProperty("landlineType")))
			    throw new PosidexException("CUSTOMER_LANDLINE_TYPE_0 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("contactTypeSize"))+" Actual Length :: "+customerRequest.getContactInformation().getLandlineType0().length());
			if(customerRequest.getContactInformation().getLandline0() != null && customerRequest.getContactInformation().getLandline0().length() >Integer.parseInt(environment.getProperty("landLineNumberSize")))
			    throw new PosidexException("CUSTOMER_LANDLINE_0 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("mobileNumberSize"))+" Actual Length :: "+customerRequest.getContactInformation().getLandline0().length());

			if(customerRequest.getContactInformation().getLandlineType1() != null && customerRequest.getContactInformation().getLandlineType1().length() >Integer.parseInt(environment.getProperty("landlineType")))
			    throw new PosidexException("CUSTOMER_LANDLINE_TYPE_1 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("contactTypeSize"))+" Actual Length :: "+customerRequest.getContactInformation().getLandlineType1().length());
			if(customerRequest.getContactInformation().getLandline1() != null && customerRequest.getContactInformation().getLandline1().length() >Integer.parseInt(environment.getProperty("landLineNumberSize")))
			    throw new PosidexException("CUSTOMER_LANDLINE_1 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("mobileNumberSize"))+" Actual Length :: "+customerRequest.getContactInformation().getLandline1().length());


			if(customerRequest.getEmailInformation().getEmailType0() != null && customerRequest.getEmailInformation().getEmailType0().length() >Integer.parseInt(environment.getProperty("emailType")))
							    throw new PosidexException("EMAIL_TYPE_0 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("emailType"))+" Actual Length :: "+customerRequest.getEmailInformation().getEmailType0().length());
			if(customerRequest.getEmailInformation().getEmail0() != null && customerRequest.getEmailInformation().getEmail0().length() >Integer.parseInt(environment.getProperty("emailSize")))
							    throw new PosidexException("EMAIL_ID_0 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("emailSize"))+" Actual Length :: "+customerRequest.getEmailInformation().getEmail0().length());
		
			if(customerRequest.getEmailInformation().getEmailType1() != null && customerRequest.getEmailInformation().getEmailType1().length() >Integer.parseInt(environment.getProperty("emailType")))
			    throw new PosidexException("EMAIL_TYPE_1 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("emailType"))+" Actual Length :: "+customerRequest.getEmailInformation().getEmailType1().length());
			if(customerRequest.getEmailInformation().getEmail1() != null && customerRequest.getEmailInformation().getEmail1().length() >Integer.parseInt(environment.getProperty("emailSize")))
			    throw new PosidexException("EMAIL_ID_1 Exceeds Length, Expected Length :: "+Integer.parseInt(environment.getProperty("emailSize"))+" Actual Length :: "+customerRequest.getEmailInformation().getEmail1().length());

		}catch(Exception e) {
			logger.error(e,e);
			throw new PosidexException(e.getMessage());
		}
	}

	public long noOfDigits(long num) {
		long count = 0;

		while (num != 0) {
			num /= 10;
			++count;
		}
		return count;
	}

	public  boolean validateJavaDate(String strDate) {
		boolean isValid=false;
		if (strDate != null) {
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
			sdfrmt.setLenient(false);
			try {
				Date javaDate = sdfrmt.parse(strDate);
				isValid=true;
			}
			catch (ParseException e) {
				isValid=false;
			}
		}
		return isValid;
	}

	public List<NegativeMatchedCustomerDetailsBean> buildingNegMatchedCustomerDetails(List<CgclReportInputOutputBean> reportedMatches) {
		List<NegativeMatchedCustomerDetailsBean> matchedCustomerDetailsBeanList=new ArrayList<>();
		try {
			if(reportedMatches !=null){
			reportedMatches.stream().forEach(c->{
				NegativeMatchedCustomerDetailsBean negMatchedCustomerDetailsBean=new NegativeMatchedCustomerDetailsBean();
				negMatchedCustomerDetailsBean.setAadharCard(c.getAadharno());
	//			negMatchedCustomerDetailsBean.setAddress(c.getAddress0()!=null ? c.getAddress0():c.getAddress1()!=null?c.getAddress1():c.getAddress2()!=null?c.getAddress2():c.getAddress3()!=null?c.getAddress3():new String());
				negMatchedCustomerDetailsBean.setAddress(c.getAddress());
				negMatchedCustomerDetailsBean.setAddressType(c.getAddressType());
				negMatchedCustomerDetailsBean.setCustomerfullname(c.getName());
				negMatchedCustomerDetailsBean.setDob(String.valueOf(c.getDob()));
				negMatchedCustomerDetailsBean.setMobileNo(c.getMobileNo());
				negMatchedCustomerDetailsBean.setMobileNoType(c.getMobileType());
				negMatchedCustomerDetailsBean.setPan(c.getPanno());
				negMatchedCustomerDetailsBean.setPassport(c.getPassportno());
				negMatchedCustomerDetailsBean.setPropertyAddress(c.getPropertyAddress());
				negMatchedCustomerDetailsBean.setPropertyAddressType(c.getPropertyAddressType());
				negMatchedCustomerDetailsBean.setReason(c.getReason());
				negMatchedCustomerDetailsBean.setSource(c.getSource());
				negMatchedCustomerDetailsBean.setMatchCriteria(c.getMatchtype());
				negMatchedCustomerDetailsBean.setMatchId(c.getCustunqid());
				negMatchedCustomerDetailsBean.setRecordType(c.getRecordtype());
				negMatchedCustomerDetailsBean.setVoterId(c.getVoteridno());
				matchedCustomerDetailsBeanList.add(negMatchedCustomerDetailsBean);
			}
			);
			}
		}catch(Exception e) {
			logger.error(e,e);
		}
		
		return matchedCustomerDetailsBeanList;
	}

	public List<CgclReportInputOutputBean> appendingAddressStrengths(List<CgclReportInputOutputBean> matchedCustomerList,List<MatchedAddressStrengths> matchedAddressStrengthsList) {

		if(matchedCustomerList !=null){
			IntStream.rangeClosed(0, matchedCustomerList.size()-1).forEach(i->{
				MatchedAddressStrengths matchingObject = matchedAddressStrengthsList.stream().filter(p -> p.getMatchedId().equalsIgnoreCase(matchedCustomerList.get(i).getCustunqid()) && p.getRecordType().equalsIgnoreCase(matchedCustomerList.get(i).getRecordtype())).findFirst().orElse(null);
					matchedCustomerList.get(i).setPermanentMaxStrength(matchingObject.getPermanentMaxStrength());
					matchedCustomerList.get(i).setCurrentMaxStrength(matchingObject.getCurrentMaxStrength());
					matchedCustomerList.get(i).setResidentMaxStrength(matchingObject.getResidentMaxStrength());
					matchedCustomerList.get(i).setEmployeeMaxStrength(matchingObject.getEmployeeMaxStrength());
				});
		}
		return matchedCustomerList;
	}
}
