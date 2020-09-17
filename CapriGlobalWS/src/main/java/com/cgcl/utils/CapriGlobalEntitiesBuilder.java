//package com.cgcl.utils;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Component;
//
//import com.cgcl.beans.CustomerRequestBean;
//import com.cgcl.beans.MatchedCustomerDetailsBean;
//import com.cgcl.beans.MatchedCustomerLoanDetailsBean;
//import com.cgcl.entities.CGCL_ACE_BLK_INTRADAY;
//import com.cgcl.entities.CGCL_ACE_BLK_REQUEST;
//import com.cgcl.entities.CGCL_DG_BLK_INTRADAY;
//import com.cgcl.entities.CGCL_DG_BLK_REQUEST;
//import com.cgcl.entities.CGCL_LOAN_DETAILS;
//import com.cgcl.entities.CGCL_REPORT_INPUT_OUTPUT;
//import com.cgcl.entities.PSX_REQUEST;
//
//@Component
//public class CapriGlobalEntitiesBuilder {
//	private static Logger logger = Logger.getLogger(CapriGlobalEntitiesBuilder.class.getName());
//
//	public CGCL_DG_BLK_REQUEST buidingDemographicRequestInfo(CustomerRequestBean customerRequest, Date timestamp,long requestId) throws ParseException {
//		CGCL_DG_BLK_REQUEST dg_blk_request = new CGCL_DG_BLK_REQUEST();
//		dg_blk_request.setAccount_status(customerRequest.getAccount_status());
//		dg_blk_request.setCaste(customerRequest.getCaste());
//		dg_blk_request.setCustomer("mahadev");
//		dg_blk_request.setRequestId(customerRequest.getRequestId());
//		dg_blk_request.setCust_unq_id(customerRequest.getSourcecustomerid());
//		dg_blk_request.setCust_id("100");
//    	dg_blk_request.setLead_id("200");
//		dg_blk_request.setBatch_id("300");
//		dg_blk_request.setDeal_id_app_id("400");
//	    dg_blk_request.setPsx_batch_id("500");
//		dg_blk_request.setDuiflag("I_OR_U");   
//		dg_blk_request.setEventtype("Matching");
//		dg_blk_request.setCibil_score(customerRequest.getCibil_score());
//		dg_blk_request.setCustomercategory(customerRequest.getCustomercategory());
//		dg_blk_request.setCustomertypecode(customerRequest.getCustomertypecode());
//		dg_blk_request.setDinno(customerRequest.getDinno());
//		dg_blk_request.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDob()));
//		dg_blk_request.setDoi(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDoi()));
//		dg_blk_request.setDrivinglicense(customerRequest.getDrivinglicense());
//		dg_blk_request.setFathername(customerRequest.getFathername());
//		dg_blk_request.setFirstname(customerRequest.getFirstname());
//		dg_blk_request.setGender(customerRequest.getGender());
//		dg_blk_request.setGstin(customerRequest.getGstin());
//		dg_blk_request.setHighesteducation(customerRequest.getHighesteducation());
//		dg_blk_request.setInserttime(timestamp);
//		dg_blk_request.setLan(customerRequest.getLan());
//		dg_blk_request.setLastname(customerRequest.getLastname());
//		dg_blk_request.setLchgtime(timestamp);
//		dg_blk_request.setMartialstatus(customerRequest.getMartialstatus());
//		dg_blk_request.setMatchingruleprofile(customerRequest.getMatchingruleprofile());
//		dg_blk_request.setMiddlename(customerRequest.getMiddlename());
////		dg_blk_request.setMothername(customerRequest.getMothername());
//		dg_blk_request.setOld_psx_batch_id("");
//		dg_blk_request.setPanno(customerRequest.getPanno());
//		dg_blk_request.setPassportno(customerRequest.getPassportno());
//		dg_blk_request.setPrimaryoccupation(customerRequest.getPrimaryoccupation());
//		dg_blk_request.setProduct(customerRequest.getProduct());
//		dg_blk_request.setRegistration_no(customerRequest.getRegistration_no());
//		dg_blk_request.setReligion(customerRequest.getReligion());
//		dg_blk_request.setResidencestatus(customerRequest.getResidencestatus());
//		dg_blk_request.setSourceapplicationid(customerRequest.getSourceapplicationid());
//		dg_blk_request.setSourceapplicationno(customerRequest.getSourceapplicationno());
//		dg_blk_request.setSourcecustomerid(customerRequest.getSourcecustomerid());
//		dg_blk_request.setSourceauthenticationtoken(customerRequest.getSourceauthenticationtoken());
//		dg_blk_request.setSourcesystemname(customerRequest.getSourcesystemname());
//		dg_blk_request.setSpousename(customerRequest.getSpousename());
//		dg_blk_request.setTanno(customerRequest.getTanno());
//		dg_blk_request.setTitle(customerRequest.getTitle());
//		dg_blk_request.setUid(customerRequest.getUid());
//		dg_blk_request.setVoterid(customerRequest.getVoterid());
//		dg_blk_request.setRequestId(requestId);
//		dg_blk_request.setAssignUcic(customerRequest.getAssignUcic());
//		dg_blk_request.setCycNo("");
//		dg_blk_request.setGstin("");
//		return dg_blk_request;
//	}
//
//	public CGCL_ACE_BLK_REQUEST buidingAceRequestInfo(CustomerRequestBean customerRequest, Date timestamp,long requestId) {
//		CGCL_ACE_BLK_REQUEST ace_blk_request = new CGCL_ACE_BLK_REQUEST();
//		ace_blk_request.setAddress(customerRequest.getAddress());
//		ace_blk_request.setAddressid(customerRequest.getAddressid());
//		ace_blk_request.setAddresstype(customerRequest.getAddresstype());
//		// ace_blk_request.setBatch_id("");
//		ace_blk_request.setCity(customerRequest.getCity());
////		ace_blk_request.setCurrentAddCity(customerRequest.getCity());
////		ace_blk_request.setCurrentAddress(customerRequest.getAddress());
////		ace_blk_request.setCurrentAddressType(customerRequest.getAddresstype());
////		ace_blk_request.setCurrentPincode(customerRequest.getPincode());
////		ace_blk_request.setCurrentState(customerRequest.getState());
//		ace_blk_request.setCustId("100");
//	    ace_blk_request.setCustUnqId("200");
//		ace_blk_request.setCustomerContact(customerRequest.getMobilenumber());
//		// ace_blk_request.setCustomer_contact_type("");
//		ace_blk_request.setCustomerLandline(customerRequest.getLandlinenumber());
//		// ace_blk_request.setCustomer_no("");
//		// ace_blk_request.setDeal_id_app_id("");
//		ace_blk_request.setDuiflag("I_OR_U");
//		ace_blk_request.setEmail(customerRequest.getEmail());
//		// ace_blk_request.setEmail_type("");
////		ace_blk_request.setEmployerAddCity(customerRequest.getCity());
////		ace_blk_request.setEmployerAddress(customerRequest.getAddress());
////		ace_blk_request.setEmployerAddressType(customerRequest.getAddresstype());
////		ace_blk_request.setEmployerPincode(customerRequest.getPincode());
////		ace_blk_request.setEmployerState(customerRequest.getState());
//		 ace_blk_request.setEventtype("Matching");
//		ace_blk_request.setInserttime(timestamp);
//		ace_blk_request.setLchgtime(timestamp);
//		// ace_blk_request.setLead_id("");
//		// ace_blk_request.setOld_psx_batch_id("");
////		ace_blk_request.setPermanentAddCity(customerRequest.getCity());
////		ace_blk_request.setPermanentAddress(customerRequest.getAddress());
////		ace_blk_request.setPermanentAddressType(customerRequest.getAddresstype());
////		ace_blk_request.setPermanentPincode(customerRequest.getPincode());
////		ace_blk_request.setPermanentState(customerRequest.getState());
//		ace_blk_request.setPincode(customerRequest.getPincode());
//		ace_blk_request.setPsxBatchId("1000");
//		ace_blk_request.setRequestId(customerRequest.getRequestId());
//		ace_blk_request.setState(customerRequest.getState());
//		ace_blk_request.setMobilenumber(customerRequest.getMobilenumber());
//		ace_blk_request.setLandlinenumber(customerRequest.getLandlinenumber());
//		ace_blk_request.setRequestId(requestId);
//		return ace_blk_request;
//	}
//
//	public CGCL_ACE_BLK_INTRADAY buidingAceIntradayInfo(CustomerRequestBean customerRequest, Date timestamp,long requestId) {
//		CGCL_ACE_BLK_INTRADAY ace_blk_intraday = new CGCL_ACE_BLK_INTRADAY();
//		ace_blk_intraday.setAddress(customerRequest.getAddress());
//		ace_blk_intraday.setAddressid(customerRequest.getAddressid());
//		ace_blk_intraday.setAddresstype(customerRequest.getAddresstype());
////		ace_blk_intraday.setBatch_id("");
//		ace_blk_intraday.setCity(customerRequest.getCity());
////		ace_blk_intraday.setCurrent_add_city(customerRequest.getCity());
////		ace_blk_intraday.setCurrent_address(customerRequest.getAddress());
////		ace_blk_intraday.setCurrent_address_type(customerRequest.getAddresstype());
////		ace_blk_intraday.setCurrent_pincode(customerRequest.getPincode());
////		ace_blk_intraday.setCurrent_state(customerRequest.getState());
//		ace_blk_intraday.setCust_id("100");
//		ace_blk_intraday.setCust_unq_id("200");
//		ace_blk_intraday.setCustomer_contact(customerRequest.getMobilenumber());
////		ace_blk_intraday.setCustomer_contact_type("");
//		ace_blk_intraday.setCustomer_landline(customerRequest.getLandlinenumber());
////		ace_blk_intraday.setCustomer_no("");
////		ace_blk_intraday.setDeal_id_app_id("");
//		ace_blk_intraday.setDuiflag("I_OR_U");
//		ace_blk_intraday.setEmail(customerRequest.getEmail());
////		ace_blk_intraday.setEmail_type("");
////		ace_blk_intraday.setEmployer_add_city(customerRequest.getCity());
////		ace_blk_intraday.setEmployer_address(customerRequest.getAddress());
////		ace_blk_intraday.setEmployer_address_type(customerRequest.getAddresstype());
////		ace_blk_intraday.setEmployer_pincode(customerRequest.getPincode());
////		ace_blk_intraday.setEmployer_state(customerRequest.getState());
//		ace_blk_intraday.setEventtype("Intraday");
//		ace_blk_intraday.setInserttime(timestamp);
//		ace_blk_intraday.setLchgtime(timestamp);
////		ace_blk_intraday.setLead_id("");
////		ace_blk_intraday.setOld_psx_batch_id("");
////		ace_blk_intraday.setPermanent_add_city(customerRequest.getCity());
////		ace_blk_intraday.setPermanent_address(customerRequest.getAddress());
////		ace_blk_intraday.setPermanent_address_type(customerRequest.getAddresstype());
////		ace_blk_intraday.setPermanent_pincode(customerRequest.getPincode());
////		ace_blk_intraday.setPermanent_state(customerRequest.getState());
//		ace_blk_intraday.setPincode(customerRequest.getPincode());
//		ace_blk_intraday.setPsxBatchId("2000");
//		ace_blk_intraday.setRequestId(customerRequest.getRequestId());
//		ace_blk_intraday.setState(customerRequest.getState());
//		ace_blk_intraday.setMobilenumber(customerRequest.getMobilenumber());
//		ace_blk_intraday.setLandlinenumber(customerRequest.getLandlinenumber());
//		ace_blk_intraday.setRequestId(requestId);
//		return ace_blk_intraday;
//	}
//
//	public CGCL_DG_BLK_INTRADAY buidingDemographicIntradayInfo(CustomerRequestBean customerRequest, Date timestamp,long requestId, String ucicId,String ucicType) throws ParseException {
//		CGCL_DG_BLK_INTRADAY dg_blk_intraday = new CGCL_DG_BLK_INTRADAY();
//		dg_blk_intraday.setAccount_status(customerRequest.getAccount_status());
//		dg_blk_intraday.setCaste(customerRequest.getCaste());
//		dg_blk_intraday.setCustomer("mahadev");
//		dg_blk_intraday.setRequestId(customerRequest.getRequestId());
//		dg_blk_intraday.setCust_unq_id(customerRequest.getSourcecustomerid());
//		dg_blk_intraday.setCust_id("100");
//    	dg_blk_intraday.setLead_id("200");
//		dg_blk_intraday.setBatch_id("300");
//		dg_blk_intraday.setDeal_id_app_id("400");
//	    dg_blk_intraday.setPsx_batch_id("500");
//		dg_blk_intraday.setDuiflag("I_OR_U");   
//		dg_blk_intraday.setEventtype("Intraday");
//		dg_blk_intraday.setCibil_score(customerRequest.getCibil_score());
//		dg_blk_intraday.setCustomercategory(customerRequest.getCustomercategory());
//		dg_blk_intraday.setCustomertypecode(customerRequest.getCustomertypecode());
//		dg_blk_intraday.setDinno(customerRequest.getDinno());
//		dg_blk_intraday.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDob()));
//		dg_blk_intraday.setDoi(new SimpleDateFormat("dd-MM-yyyy").parse(customerRequest.getDoi()));
//		dg_blk_intraday.setDrivinglicense(customerRequest.getDrivinglicense());
//		dg_blk_intraday.setFathername(customerRequest.getFathername());
//		dg_blk_intraday.setFirstname(customerRequest.getFirstname());
//		dg_blk_intraday.setGender(customerRequest.getGender());
//		dg_blk_intraday.setGstin(customerRequest.getGstin());
//		dg_blk_intraday.setHighesteducation(customerRequest.getHighesteducation());
//		dg_blk_intraday.setInserttime(timestamp);
//		dg_blk_intraday.setLan(customerRequest.getLan());
//		dg_blk_intraday.setLastname(customerRequest.getLastname());
//		dg_blk_intraday.setLchgtime(timestamp);
//		dg_blk_intraday.setMartialstatus(customerRequest.getMartialstatus());
//		dg_blk_intraday.setMatchingruleprofile(customerRequest.getMatchingruleprofile());
//		dg_blk_intraday.setMiddlename(customerRequest.getMiddlename());
////		dg_blk_intraday.setMothername(customerRequest.getMothername());
//		dg_blk_intraday.setOld_psx_batch_id("");
//		dg_blk_intraday.setPanno(customerRequest.getPanno());
//		dg_blk_intraday.setPassportno(customerRequest.getPassportno());
//		dg_blk_intraday.setPrimaryoccupation(customerRequest.getPrimaryoccupation());
//		dg_blk_intraday.setProduct(customerRequest.getProduct());
//		dg_blk_intraday.setRegistration_no(customerRequest.getRegistration_no());
//		dg_blk_intraday.setReligion(customerRequest.getReligion());
//		dg_blk_intraday.setResidencestatus(customerRequest.getResidencestatus());
//		dg_blk_intraday.setSourceapplicationid(customerRequest.getSourceapplicationid());
//		dg_blk_intraday.setSourceapplicationno(customerRequest.getSourceapplicationno());
//		dg_blk_intraday.setSourcecustomerid(customerRequest.getSourcecustomerid());
//		dg_blk_intraday.setSourceauthenticationtoken(customerRequest.getSourceauthenticationtoken());
//		dg_blk_intraday.setSourcesystemname(customerRequest.getSourcesystemname());
//		dg_blk_intraday.setSpousename(customerRequest.getSpousename());
//		dg_blk_intraday.setTanno(customerRequest.getTanno());
//		dg_blk_intraday.setTitle(customerRequest.getTitle());
//		dg_blk_intraday.setUid(customerRequest.getUid());
//		dg_blk_intraday.setVoterid(customerRequest.getVoterid());
//		dg_blk_intraday.setRequestId(requestId);
//		dg_blk_intraday.setUcic(ucicId);
//		dg_blk_intraday.setUcicType(ucicType);
//		dg_blk_intraday.setCycNo("");
//		dg_blk_intraday.setGstin("");
//		return dg_blk_intraday;
//	}
//
//	public List<MatchedCustomerDetailsBean> buildingMatchedCustomerDetails(List<CGCL_REPORT_INPUT_OUTPUT> reportedMatches) {
//		List<MatchedCustomerDetailsBean> matchedCustomerDetailsBeanList=new ArrayList<>();
//		try {
//			reportedMatches.stream().forEach(c->{
//				MatchedCustomerDetailsBean matchedCustomerDetailsBean=new MatchedCustomerDetailsBean();
//			//	matchedCustomerDetailsBean.setAccount_status("C");
////				matchedCustomerDetailsBean.setAddress(c.getAddressid0());
////				matchedCustomerDetailsBean.setAddressid(c.getAddressid1());
////				matchedCustomerDetailsBean.setAddresstype("");
//				matchedCustomerDetailsBean.setCibil_score("");
//				matchedCustomerDetailsBean.setCity("");
//				matchedCustomerDetailsBean.setCustomertypecode(c.getCustomertypecode());
//				matchedCustomerDetailsBean.setDinno("");
//				matchedCustomerDetailsBean.setDob(c.getDob());
//				matchedCustomerDetailsBean.setDoi(new Date());
//				matchedCustomerDetailsBean.setDrivinglicense(c.getDrivinglicno());
//		//		matchedCustomerDetailsBean.setEmail(c.getEmail0());
//				matchedCustomerDetailsBean.setFathername(c.getFathername());
//				matchedCustomerDetailsBean.setFirstname(c.getFirstname());
//				matchedCustomerDetailsBean.setGender(c.getGender());
//				matchedCustomerDetailsBean.setGstin("");
//				matchedCustomerDetailsBean.setHighesteducation(c.getHighesteducation());
//		//		matchedCustomerDetailsBean.setLandlinenumber(c.getCustomerlandline0());
//				matchedCustomerDetailsBean.setLastname(c.getLastname());
//				matchedCustomerDetailsBean.setMartialstatus(c.getMaritalstatus());
//				matchedCustomerDetailsBean.setMiddlename(c.getMiddlename());
//		//		matchedCustomerDetailsBean.setMobilenumber(c.getCustomercontact0());
//				matchedCustomerDetailsBean.setMothername("");
//				matchedCustomerDetailsBean.setPanno(c.getPanno());
//				matchedCustomerDetailsBean.setPassportno(c.getPassportno());
////				matchedCustomerDetailsBean.setPincode(0);
//				matchedCustomerDetailsBean.setPrimaryoccupation(c.getPrimaryoccupation());
//				matchedCustomerDetailsBean.setRegistrationorcinno(c.getCin());
//				matchedCustomerDetailsBean.setResidencestatus(c.getResidentstatus());
//				matchedCustomerDetailsBean.setSpousename("");
////				matchedCustomerDetailsBean.setState("");
//				matchedCustomerDetailsBean.setTanno(c.getTan());
//				matchedCustomerDetailsBean.setUcic("");
//				matchedCustomerDetailsBean.setUid(c.getUid()+"");
//				matchedCustomerDetailsBean.setVoterid(c.getVoteridno());
//				matchedCustomerDetailsBeanList.add(matchedCustomerDetailsBean);
//			}
//			);
//		}catch(Exception e) {
//			logger.error(e,e);
//		}
//		
//		return matchedCustomerDetailsBeanList;
//	}
//
//	public List<MatchedCustomerLoanDetailsBean> buildingMatchedLoanDetails(List<CGCL_LOAN_DETAILS> loanMatches) {
//		List<MatchedCustomerLoanDetailsBean> matchedCustomerLoanDetailsBeanList=new ArrayList<>();
//		try {
//			loanMatches.stream().forEach(l->{
//				MatchedCustomerLoanDetailsBean matchedCustomerLoanDetailsBean=new MatchedCustomerLoanDetailsBean();
//				matchedCustomerLoanDetailsBean.setApplicationno(l.getApplication_no());
//				matchedCustomerLoanDetailsBean.setBasicloanamount(l.getBasic_loan_amount());
//				matchedCustomerLoanDetailsBean.setCurrentpos(l.getCurrent_pos());
//				matchedCustomerLoanDetailsBean.setCustomerno(l.getCustomer_no()+"");
//				matchedCustomerLoanDetailsBean.setDpd(l.getDpd());
//				matchedCustomerLoanDetailsBean.setFinterestrate(l.getF_interest_rate());
//				matchedCustomerLoanDetailsBean.setIncome(l.getIncome());
//				matchedCustomerLoanDetailsBean.setLoanamount(l.getLoan_amount());
//				matchedCustomerLoanDetailsBean.setLoanamountno(l.getLoan_account_no());
//				matchedCustomerLoanDetailsBean.setLoanstatus(l.getStatus());
//				matchedCustomerLoanDetailsBean.setStatus(l.getStatus());
//				matchedCustomerLoanDetailsBean.setTenor(l.getTenor());
//				matchedCustomerLoanDetailsBeanList.add(matchedCustomerLoanDetailsBean);
//			});
//		}catch(Exception e) {
//			logger.error(e,e);
//		}
//		
//		return matchedCustomerLoanDetailsBeanList;
//	}
//
//	public PSX_REQUEST buildingPsxRequestInfo(long reqPsxid, Date timestamp, String custUnqId,String matchingruleprofile, String requestStatus) {
//	
//		PSX_REQUEST psxRequest =new PSX_REQUEST();
//		psxRequest.setRequestId(reqPsxid);
//		psxRequest.setInserttime(timestamp);
//		psxRequest.setLchgtime(timestamp);
//		psxRequest.setCustunqid(custUnqId);
//		psxRequest.setProfileid(matchingruleprofile);
//		psxRequest.setRequeststatus(requestStatus);
//		return psxRequest;
//	}
//
//	public CustomerRequestBean buildingCustomerObject(CGCL_DG_BLK_REQUEST custDgBlkRequest,CGCL_ACE_BLK_REQUEST custAceBlkRequest) {
//		CustomerRequestBean customerRequestBean=new CustomerRequestBean();
//		customerRequestBean.setAadhar_no(custDgBlkRequest.getAadharNo());
//		customerRequestBean.setAccount_status(custDgBlkRequest.getAccount_status());
//		customerRequestBean.setAddress(custAceBlkRequest.getAddress());
//		customerRequestBean.setAddressid(custAceBlkRequest.getAddressid());
//		customerRequestBean.setAddresstype(custAceBlkRequest.getAddresstype());
//	//	customerRequestBean.setAssignUcic(custDgBlkRequest.getAssignUcic());
//		customerRequestBean.setCaste(custDgBlkRequest.getCaste());
//		customerRequestBean.setCibil_score(custDgBlkRequest.getCibil_score());
//		customerRequestBean.setCity(custAceBlkRequest.getCity());
//		customerRequestBean.setCustomercategory(custDgBlkRequest.getCustomercategory());
//		customerRequestBean.setCustomertypecode(custDgBlkRequest.getCustomertypecode());
//		customerRequestBean.setDinno(custDgBlkRequest.getDinno());
//		customerRequestBean.setDob(custDgBlkRequest.getDob()+"");
//		customerRequestBean.setDoi(custDgBlkRequest.getDoi()+"");
//		customerRequestBean.setDrivinglicense(custDgBlkRequest.getDrivinglicense());
//		customerRequestBean.setEmail(custDgBlkRequest.getDuiflag());
//		customerRequestBean.setFathername(custDgBlkRequest.getFathername());
//		customerRequestBean.setFirstname(custDgBlkRequest.getFirstname());
//		customerRequestBean.setGender(custDgBlkRequest.getGender());
//		customerRequestBean.setGstin(custDgBlkRequest.getGstin());
//		customerRequestBean.setHighesteducation(custDgBlkRequest.getHighesteducation());
//		customerRequestBean.setLan(custDgBlkRequest.getLan());
//		customerRequestBean.setLandlinenumber(custAceBlkRequest.getLandlinenumber());
//		customerRequestBean.setLastname(custDgBlkRequest.getLastname());
//		customerRequestBean.setMartialstatus(custDgBlkRequest.getMartialstatus());
//		customerRequestBean.setMatchingruleprofile(custDgBlkRequest.getMatchingruleprofile());
//		customerRequestBean.setMiddlename(custDgBlkRequest.getMiddlename());
//		customerRequestBean.setMobilenumber(custAceBlkRequest.getMobilenumber());
//		customerRequestBean.setMothername(custDgBlkRequest.getMotherName());
//		customerRequestBean.setMobilenumber(custAceBlkRequest.getMobilenumber());
//		return customerRequestBean;
//	}
//}
