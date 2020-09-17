package com.cgcl.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cgcl.beans.CgclAceBlkBean;
import com.cgcl.beans.CgclDgBlkBean;
import com.cgcl.beans.CgclLoanDetailsBean;
import com.cgcl.beans.CgclReportInputOutputBean;
import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.beans.PsxProfileBean;
import com.cgcl.beans.PsxRequestBean;
import com.cgcl.dao.CapriGlobalJdbcDao;
import com.cgcl.exception.DAOException;
import com.cgcl.service.CapriGlobalJdbcService;
import com.cgcl.utils.CapriGlobalHashMapBuilder;

@Repository
@PropertySource("classpath:dbQueries.properties")
public class CapriGlobalJdbcServiceImpl implements CapriGlobalJdbcService {

	private static Logger logger = Logger.getLogger(CapriGlobalJdbcServiceImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Environment environment;
	
	@Autowired
	private CapriGlobalJdbcDao capriGlobalJdbcDao;
	
	@Autowired
	private CapriGlobalHashMapBuilder capriGlobalHashMapBuilder;
	
	@Override
	public void saveDemographicRequest(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String custUnqId)throws DAOException {
			capriGlobalJdbcDao.saveDemographicRequest(customerRequest,timestamp,psxBatchId,custUnqId);
	}

	@Override
	public void saveAddressRequest(CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String custUnqId)throws DAOException {
			capriGlobalJdbcDao.saveAddressRequest(customerRequest,timestamp,psxBatchId,custUnqId);
	}

	@Override
	public void saveDemographicIntraday(CustomerRequestInformation customerRequest,Date date,String psxBatchId,boolean isTempUcic,String custUnqId)throws DAOException {
			capriGlobalJdbcDao.saveDemographicIntraday(customerRequest,date,psxBatchId,isTempUcic,custUnqId);
	}
    
	@Override
	public void saveAddressIntraday(CustomerRequestInformation customerRequest,Date date,String psxBatchId,String custUnqId)throws DAOException {
			capriGlobalJdbcDao.saveAddressIntraday(customerRequest,date,psxBatchId,custUnqId);
	}

	@Override
	public PsxProfileBean getProfileById(int profileId) throws DAOException {
			return capriGlobalJdbcDao.getProfileById(profileId);
	}

	@Override
	public List<CgclReportInputOutputBean> getAllMatchedCustomerDetails(long requestId) throws DAOException {
		return capriGlobalJdbcDao.getAllMatchedCustomerDetails(requestId);
	}

	@Override
	public List<CgclLoanDetailsBean> getMatchedLoanDetails(List<Long> customer_no) throws DAOException {
		return capriGlobalJdbcDao.getMatchedLoanDetails(customer_no);
	}

	@Override
	public long generateSequenceId(String seqname) throws DAOException {
		return capriGlobalJdbcDao.generateSequenceId(seqname);
	}

	@Override
	public PsxRequestBean getRequestInfo(long requestId) throws DAOException {
		return capriGlobalJdbcDao.getRequestInfo(requestId);
	}
	
	@Override
	public void savePsxRequestInfo(PsxRequestBean request) throws DAOException {
		capriGlobalJdbcDao.savePsxRequestInfo(request);
	}

	
	@Override
	public List<String> getValidProfilesList() throws DAOException {
		return capriGlobalJdbcDao.getValidProfilesList();
	}

	@Override
	public CgclDgBlkBean getDemographicInfoForUcic(long requestId) throws DAOException {
		return capriGlobalJdbcDao.getDemographicInfoForUcic(requestId);
	}

	@Override
	public List<CgclAceBlkBean> getAceInfoForUcic(long requestId) throws DAOException {
		return capriGlobalJdbcDao.getAceInfoForUcic(requestId);
	}

	@Override
	public void updateUcicAssignStatus(long reqPsxid, String ucicId,String ucicType, String status)  throws DAOException {
		capriGlobalJdbcDao.updateUcicAssignStatus(reqPsxid,ucicId,ucicType,status);
	}

	@Override
	public List<String> getValidRequestId() throws DAOException {
		return capriGlobalJdbcDao.getValidRequestId();
	}

	@Override
	public List<String> getSourceSystemAuthentication(String source_system) throws DAOException {
		return capriGlobalJdbcDao.getSourceSystemAuthentication(source_system);
	}

	@Override
	public List<String> getUcicInfo(long requestid) throws DAOException {
		return capriGlobalJdbcDao.getUcicInfo(requestid);
	}

	@Override
	public List<CgclLoanDetailsBean> getMatchedLoanDetails1(List<String> customer_no) throws DAOException {
		return capriGlobalJdbcDao.getMatchedLoanDetails1(customer_no);
	}

}
