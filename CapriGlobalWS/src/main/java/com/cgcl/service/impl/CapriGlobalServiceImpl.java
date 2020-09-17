//package com.cgcl.service.impl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.stereotype.Service;
//
//import com.cgcl.entities.CGCL_ACE_BLK_INTRADAY;
//import com.cgcl.entities.CGCL_ACE_BLK_REQUEST;
//import com.cgcl.entities.CGCL_DG_BLK_INTRADAY;
//import com.cgcl.entities.CGCL_DG_BLK_REQUEST;
//import com.cgcl.entities.CGCL_LOAN_DETAILS;
//import com.cgcl.entities.CGCL_REPORT_INPUT_OUTPUT;
//import com.cgcl.entities.PSX_REQUEST;
//import com.cgcl.entities.Profile;
//import com.cgcl.repository.AddressIntradayRepository;
//import com.cgcl.repository.AddressRequestRepository;
//import com.cgcl.repository.DemographicIntradayRepository;
//import com.cgcl.repository.DemographicRequestRepository;
//import com.cgcl.repository.LoanDetailsRepository;
//import com.cgcl.repository.ProfileRepository;
//import com.cgcl.repository.PsxRequestRepository;
//import com.cgcl.repository.ReportInputOutputRepository;
//import com.cgcl.repository.RequestRepository;
//import com.cgcpl.service.CapriGlobalService;
//
//@Service
//public class CapriGlobalServiceImpl implements CapriGlobalService{
//
//	private static Logger logger = Logger.getLogger(CapriGlobalServiceImpl.class.getName());
//	
//	@Autowired
//	private DemographicRequestRepository customerRequestRepository;
//	
//	@Autowired
//	private AddressRequestRepository addressRequestRepository;
//	
//	@Autowired
//	private AddressIntradayRepository addressIntradayRepository;
//	
//	@Autowired
//	private DemographicIntradayRepository demographicIntradayRepository;
//	
//	@Autowired
//	private ProfileRepository profileRepository;
//	
//	@Autowired
//	private LoanDetailsRepository loanDetailsRepository;
//	
//	@Autowired
//	private ReportInputOutputRepository reportInputOutputRepository;
//	
//	@Autowired
//	private RequestRepository requestRepository;
//	
//	@Autowired
//	private PsxRequestRepository psxRequestRepository;
//	
//	@Autowired
//	Environment environment;
//	
//	@Autowired
//	DataSource dataSource;
//	
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//
//	@Override
//	public void saveDemographicRequest(CGCL_DG_BLK_REQUEST customerRequest) {
//		
//		logger.info("Saving Customer Demographic Request Information :: ");
//		customerRequestRepository.save(customerRequest);
//	}
//
//	@Override
//	public Profile getProfileById(int profileId) {
//		
//		return profileRepository.getProfileById(profileId);
//	}
//
//	@Override
//	public void saveAddressRequest(CGCL_ACE_BLK_REQUEST customerRequest) {
//		logger.info("Saving Customer ACE Request Information :: ");
//		addressRequestRepository.save(customerRequest);
//	}
//
//	@Override
//	public void saveDemographicIntraday(CGCL_DG_BLK_INTRADAY customerRequest) {
//		logger.info("Saving Customer Demographic Intraday Information :: ");
//		demographicIntradayRepository.save(customerRequest);
//	}
//
//	@Override
//	public void saveAddressIntraday(CGCL_ACE_BLK_INTRADAY customerRequest) {
//		logger.info("Saving Customer ACE Intraday Information :: ");
//		addressIntradayRepository.save(customerRequest);
//	}
//
//	@Override
//	public List<CGCL_LOAN_DETAILS> getMatchedLoanDetails(List<Integer> customer_no) {
//		
//		logger.info("Getting Mathced Loan Details fot the Customer :: "+customer_no);
//		return loanDetailsRepository.findAllById(customer_no);
//	}
//
//	public List<CGCL_REPORT_INPUT_OUTPUT> getAllMatchedCustomerDetails(long requestId){
//		String recordtype="INPUT";
//		logger.info("Getting Mathced Customer Details fot the Customer :: "+requestId);
//		return reportInputOutputRepository.findAllByRequestId(requestId,recordtype);
//	}
//
//	@Override
//	public long generateSequenceId(String seqname) {
//		logger.info("Getting Mathced Customer Details fot the Customer :: ");
//		long sum = jdbcTemplate.queryForObject("SELECT nextval(?) as next_sequence", Long.class,new Object[] {seqname});
//		return sum;
//	}
//
//	@Override
//	public PSX_REQUEST getRequestInfo(long requestId) {		
//		//return requestRepository.findByRequestId(requestId);
//		return jdbcTemplate.query("SELECT CUST_UNQ_ID,MATCH_COUNT,REQUEST_ID,REQUEST_STATUS FROM PSX_REQUEST WHERE REQUEST_ID=?", new ResultSetExtractor<PSX_REQUEST>() {
//
//			@Override
//			public PSX_REQUEST extractData(ResultSet rs) throws SQLException,
//					DataAccessException {
//				PSX_REQUEST request=new PSX_REQUEST();
//				while(rs.next()){
//				
//				request.setCustunqid(rs.getString("CUST_UNQ_ID"));
//				request.setMatchcount(rs.getInt("MATCH_COUNT"));
//				request.setRequestId(rs.getInt("REQUEST_ID"));
//				request.setRequeststatus(rs.getString("REQUEST_STATUS"));
//				}
//				System.out.println("request inside dao :: "+request);
//				return request;
//			}
//		},requestId);
//	}
//
//	@Override
//	public List<Profile> getValidProfilesList() {
//		return profileRepository.getAllActiveProfiles();
//	}
//
//	@Override
//	public void savePsxRequestInfo(PSX_REQUEST request) {
//		psxRequestRepository.save(request);
//		
//	}
//
//	@Override
//	public CGCL_DG_BLK_REQUEST getDemographicInfoForUcic(long requestId) {
//		return customerRequestRepository.findByRequestId(requestId);
//	}
//
//	@Override
//	public CGCL_ACE_BLK_REQUEST getAceInfoForUcic(long requestId) {
//		return addressRequestRepository.findByRequestId(requestId);
//	}
//}
