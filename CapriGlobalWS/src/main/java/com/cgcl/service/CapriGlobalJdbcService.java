package com.cgcl.service;

import java.util.Date;
import java.util.List;

import com.cgcl.beans.CgclAceBlkBean;
import com.cgcl.beans.CgclDgBlkBean;
import com.cgcl.beans.CgclLoanDetailsBean;
import com.cgcl.beans.CgclReportInputOutputBean;
import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.beans.PsxProfileBean;
import com.cgcl.beans.PsxRequestBean;
import com.cgcl.exception.DAOException;



public interface CapriGlobalJdbcService {

	public void saveDemographicRequest(CustomerRequestInformation customerRequest, Date timestamp,String psxBatchId, String custUnqId) throws DAOException;
	
	public void saveAddressRequest(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String custUnqId) throws DAOException;
	
	public void saveDemographicIntraday(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId, boolean isTempUcic,String custUnqId) throws DAOException;
	
	public void saveAddressIntraday(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String custUnqId) throws DAOException;
	
	public PsxProfileBean getProfileById(int profileId) throws DAOException;
	
	public List<CgclReportInputOutputBean> getAllMatchedCustomerDetails(long requestId) throws DAOException;
	
	public List<CgclLoanDetailsBean> getMatchedLoanDetails(List<Long> customer_no) throws DAOException;
	
	public List<CgclLoanDetailsBean> getMatchedLoanDetails1(List<String> customer_no) throws DAOException;
	
	public long generateSequenceId(String seqname) throws DAOException;
	
	public PsxRequestBean getRequestInfo(long requestId) throws DAOException;

//	public List<Profile> getValidProfilesList();
	
	public void savePsxRequestInfo(PsxRequestBean request) throws DAOException;
	
    public CgclDgBlkBean getDemographicInfoForUcic(long requestId) throws DAOException;
	
	public List<CgclAceBlkBean>  getAceInfoForUcic(long requestId) throws DAOException;
	
	public List<String> getValidProfilesList() throws DAOException;

	public void updateUcicAssignStatus(long reqPsxid, String ucicId,String ucicType, String status) throws DAOException;

	public List<String> getValidRequestId() throws DAOException;

	public List<String> getSourceSystemAuthentication(String source_system) throws DAOException;

	public List<String> getUcicInfo(long requestid) throws DAOException;
}
