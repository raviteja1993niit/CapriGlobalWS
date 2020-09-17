//package com.cgcpl.service;
//
//import java.util.List;
//
//import com.cgcl.entities.CGCL_ACE_BLK_INTRADAY;
//import com.cgcl.entities.CGCL_ACE_BLK_REQUEST;
//import com.cgcl.entities.CGCL_DG_BLK_INTRADAY;
//import com.cgcl.entities.CGCL_DG_BLK_REQUEST;
//import com.cgcl.entities.CGCL_LOAN_DETAILS;
//import com.cgcl.entities.CGCL_REPORT_INPUT_OUTPUT;
//import com.cgcl.entities.PSX_REQUEST;
//import com.cgcl.entities.Profile;
//
//public interface CapriGlobalService{
//
//	public void saveDemographicRequest(CGCL_DG_BLK_REQUEST customerRequest);
//	
//	public void saveAddressRequest(CGCL_ACE_BLK_REQUEST customerRequest);
//	
//	public void saveDemographicIntraday(CGCL_DG_BLK_INTRADAY customerRequest);
//	
//	public void saveAddressIntraday(CGCL_ACE_BLK_INTRADAY customerRequest);
//	
//	public Profile getProfileById(int profileId);
//	
//	public List<CGCL_REPORT_INPUT_OUTPUT> getAllMatchedCustomerDetails(long requestId);
//	
//	public List<CGCL_LOAN_DETAILS> getMatchedLoanDetails(List<Integer> customer_no);
//	
//	public long generateSequenceId(String seqname);
//	
//	public PSX_REQUEST getRequestInfo(long requestId);
//
//	public List<Profile> getValidProfilesList();
//	
//	public void savePsxRequestInfo(PSX_REQUEST request);
//	
//	public CGCL_DG_BLK_REQUEST getDemographicInfoForUcic(long requestId);
//	
//	public CGCL_ACE_BLK_REQUEST getAceInfoForUcic(long requestId);
//
//}
