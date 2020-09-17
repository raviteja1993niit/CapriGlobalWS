package com.cgcl.controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import posidex.core.prime360adapters.jdbcadapter.RequestBean;

import com.cgcl.beans.CgclAceBlkBean;
import com.cgcl.beans.CgclDgBlkBean;
import com.cgcl.beans.CgclLoanDetailsBean;
import com.cgcl.beans.CgclReportInputOutputBean;
import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.beans.CustomerResultsBean;
import com.cgcl.beans.GetMatchesRequestBean;
import com.cgcl.beans.MatchedAddressStrengths;
import com.cgcl.beans.MatchedCustomerDetailsBean;
import com.cgcl.beans.MatchedCustomerLoanDetailsBean;
import com.cgcl.beans.NegativeMatchedCustomerDetailsBean;
import com.cgcl.beans.PsxProfileBean;
import com.cgcl.beans.PsxRequestBean;
import com.cgcl.beans.UcicRequestInfoBean;
import com.cgcl.beans.UcicResponseInfoBean;
import com.cgcl.config.Listener;
import com.cgcl.config.Producer;
import com.cgcl.exception.PosidexException;
import com.cgcl.service.CapriGlobalJdbcService;
import com.cgcl.service.impl.AssignUcicService;
import com.cgcl.utils.BeanUtility;
import com.cgcl.utils.CapriGlobalHashMapBuilder;
import com.cgcl.utils.CapriGlobalJdbcEntitiesBuilder;
import com.cgcl.utils.CapriGlobalSender;
import com.google.gson.Gson;

@RestController
@PropertySource("classpath:validation.properties")
public class CapriGlobalJdbcRestController{

	private static Logger logger = Logger.getLogger(CapriGlobalJdbcRestController.class.getName());

	@Autowired
	private CapriGlobalJdbcService serviceI;

	@Autowired
	private CapriGlobalJdbcEntitiesBuilder buildingJdbcEntities;

	@Autowired
	private CapriGlobalHashMapBuilder buildingHashMap;

	@Autowired
	private Environment environment;

	@Autowired
	private Producer producer;

	@Autowired
	private Listener listener;

	@Autowired
	private CapriGlobalSender capriGlobalSender;

	@Autowired
	private AssignUcicService assignUcicService;

	BeanUtility beanUtils = new BeanUtility();

	static final String recordType = "INPUT";
	
	String errorMessage = "";
	String msgStatus="";
	
	long reqPsxid = 0L;
	
	String rankingOrders = "";
	
	String profileId;
	
	String productOrg;
	
	String reqStatus = "";
	
	int totalMatchCount = 0;
	
	long processedTime = 0;

	String parameterString = "";
	
	String[] ucicValues = { "Y", "N" };

	@PostMapping(value = "searchCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerResultsBean searchCustomer(@RequestBody CustomerRequestInformation customerRequest,HttpServletRequest request) {
		
		String sourceSystemName1 = environment.getProperty("SOURCE_SYSTEM_NAME1");
		String custUnqId = "";
	// String sourceSystemName2 = environment.getProperty("SOURCE_SYSTEM_NAME2");
		
		LinkedHashMap<String, String> performanceLogs = new LinkedHashMap<>();
		List<String> recievedMsgStatus=new ArrayList<>();
		
		long methodStartTime = System.currentTimeMillis();
		
		logger.info("CustomerRequest Inside Search Customer API ::: "+ customerRequest);
		CustomerResultsBean customerResultsBean = new CustomerResultsBean();
		customerResultsBean.setAccount_status(customerRequest.getDemographicInformation().getAccount_status());
		customerResultsBean.setSourcesystemname(customerRequest.getSourcesystemname());
		customerResultsBean.setSourcecustomerid(customerRequest.getSourcecustomerid());
		customerResultsBean.setSourceauthenticationtoken(customerRequest.getSourceauthenticationtoken());
		PsxProfileBean profile;
		PsxRequestBean psxRequest = new PsxRequestBean();
		
		//Date timestamp = new Date();
		Date timestamp = Date.from(Instant.now().truncatedTo( ChronoUnit.SECONDS));
		String psxBatchId = new SimpleDateFormat("ddMMyyyyHHmmss").format(timestamp);
		try {
			long perfTime = System.currentTimeMillis();
			beanUtils.trimAllStrings(customerRequest);
			beanUtils.trimAllStrings(customerRequest.getDemographicInformation());
			
			beanUtils.trimAllStrings(customerRequest.getAddressInformation());
			beanUtils.trimAllStrings(customerRequest.getContactInformation());
		//	beanUtils.trimAllStrings(customerRequest.getEmailInformation());
			
			if (("").equalsIgnoreCase(customerRequest.getAssignUcic()) || customerRequest.getAssignUcic() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("ASSIGN_UCIC_V_MSG");
				throw new PosidexException(environment.getProperty("ASSIGN_UCIC_V_MSG"));
			}

			if (!Arrays.asList(ucicValues).contains(customerRequest.getAssignUcic())) {
				errorMessage = "";
				errorMessage += environment.getProperty("ASSIGN_UCIC_YN_V_MSG");
				throw new PosidexException(environment.getProperty("ASSIGN_UCIC_YN_V_MSG"));
			}
			
			if (("").equalsIgnoreCase(customerRequest.getCustomercategory()) || ("string").equalsIgnoreCase(customerRequest.getCustomercategory()) || customerRequest.getCustomercategory() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("CUSTOMER_CATEGORY_V_MSG");
				throw new PosidexException(environment.getProperty("CUSTOMER_CATEGORY_V_MSG"));
			}
			else{
				if(!("I").equalsIgnoreCase(customerRequest.getCustomercategory())){
					if(!("C").equalsIgnoreCase(customerRequest.getCustomercategory())){
						errorMessage = "";
						errorMessage += environment.getProperty("CUSTOMER_CATEGORY_IC_V_MSG");
						throw new PosidexException(environment.getProperty("CUSTOMER_CATEGORY_IC_V_MSG"));
					}
				}
				if(("I").equalsIgnoreCase(customerRequest.getCustomercategory())){
					customerRequest.setCustomercategory("INDIVIDUAL");
				}
				if(("C").equalsIgnoreCase(customerRequest.getCustomercategory())){
					customerRequest.setCustomercategory("ORGANIZATION");
				}
			}
			
			if (("").equalsIgnoreCase(customerRequest.getMatchingruleprofile()) || ("string").equalsIgnoreCase(customerRequest.getMatchingruleprofile()) || customerRequest.getMatchingruleprofile() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("MATCHING_RULE_PROFILE_V_MSG");
				throw new PosidexException(environment.getProperty("MATCHING_RULE_PROFILE_V_MSG"));
			}
			else {
				List<String> validProfileId = serviceI.getValidProfilesList();
				if (!validProfileId.contains(customerRequest.getMatchingruleprofile())) {
					throw new PosidexException(environment.getProperty("INVALID_MATCHING_RULE_PROFILE_V_MSG"));
				} else {
					profile = serviceI.getProfileById(Integer.parseInt(customerRequest.getMatchingruleprofile()));
					logger.info("Profile Information ::: " + profile);
				}
			}
		
			if (("").equalsIgnoreCase(customerRequest.getSourceapplicationid()) || ("string").equalsIgnoreCase(customerRequest.getSourceapplicationid()) || customerRequest.getSourceapplicationid() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_APPLICATION_ID_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_APPLICATION_ID_V_MSG"));
			}
			
//			if (("").equalsIgnoreCase(customerRequest.getSourceapplicationno()) || ("string").equalsIgnoreCase(customerRequest.getSourceapplicationno()) || customerRequest.getSourceapplicationno() == null) {
//				errorMessage = "";
//				errorMessage += environment.getProperty("SOURCE_APPLICATION_NO_V_MSG");
//				throw new PosidexException(environment.getProperty("SOURCE_APPLICATION_NO_V_MSG"));
//			}
			
			if (("").equalsIgnoreCase(customerRequest.getSourceauthenticationtoken()) || ("string").equalsIgnoreCase(customerRequest.getSourceauthenticationtoken()) || customerRequest.getSourceauthenticationtoken() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_AUTHENTICATION_TOKEN_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_AUTHENTICATION_TOKEN_V_MSG"));
			}
			

			if (("").equalsIgnoreCase(customerRequest.getSourcecustomerid()) || ("string").equalsIgnoreCase(customerRequest.getSourcecustomerid()) || customerRequest.getSourcecustomerid() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_CUSTOMER_ID_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_CUSTOMER_ID_V_MSG"));
			}
			
			
			if (("").equalsIgnoreCase(customerRequest.getSourcesystemname()) || ("string").equalsIgnoreCase(customerRequest.getSourcesystemname()) || customerRequest.getSourcesystemname() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_SYSTEM_NAME_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_SYSTEM_NAME_V_MSG"));
			}
			
			String source_system = customerRequest.getSourcesystemname();
			List<String> sourceSystemCodes = serviceI.getSourceSystemAuthentication(source_system);
			
			if (sourceSystemCodes == null || sourceSystemCodes.isEmpty()) {
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG");
				throw new PosidexException(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG"));
			}
			
		//	customerRequest.setSourcesystemname(sourceSystemCodes.get(1));
			String sourceSystemSCode=sourceSystemCodes.get(0);
			
			if (!sourceSystemCodes.get(0).equalsIgnoreCase(customerRequest.getSourceauthenticationtoken())) {
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG");
				throw new PosidexException(environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG"));
			}

			if (("").equalsIgnoreCase(customerRequest.getDemographicInformation().getFirstname()) || ("string").equalsIgnoreCase(customerRequest.getDemographicInformation().getFirstname()) || customerRequest.getDemographicInformation().getFirstname() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("FIRST_NAME_V_MSG");
				throw new PosidexException(environment.getProperty("FIRST_NAME_V_MSG"));
			}

			if (customerRequest.getDemographicInformation().getDob() != null) {
				if (customerRequest.getDemographicInformation().getDob().equalsIgnoreCase("string")) {
					customerRequest.getDemographicInformation().setDob("");
				}
			}

			if (customerRequest.getDemographicInformation().getDoi() != null) {
				if (customerRequest.getDemographicInformation().getDoi().equalsIgnoreCase("string")) {
					customerRequest.getDemographicInformation().setDoi("");
				}
			}


			performanceLogs.put("Given Customer No",customerRequest.getSourcecustomerid());
			reqPsxid = serviceI.generateSequenceId(environment .getProperty("RequestIdGeneratedSequence"));
			performanceLogs.put("Request ID",String.valueOf(reqPsxid));
			buildingJdbcEntities.checkingLengths(customerRequest);
			customerRequest.getDemographicInformation().setFathername(customerRequest.getDemographicInformation().getFatherFirstName()+" "+customerRequest.getDemographicInformation().getFatherLastName());
			performanceLogs.put( "Time Taken for Validations and Getting Active Profile Id", "" + (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			
			performanceLogs.put("Time Taken for Generating RequestId from Sequence ", "" + (System.currentTimeMillis() - perfTime));
			customerRequest.setRequestId(reqPsxid);
			
			psxRequest.setRequestId(reqPsxid + "");
			psxRequest.setInserttime(timestamp);
			psxRequest.setProfileid(customerRequest.getMatchingruleprofile());
			psxRequest.setPsxbatchid(psxBatchId);
			psxRequest.setRequeststatus("P");
			psxRequest.setLchgtime(timestamp);
			
			if(customerRequest.getSourceapplicationno() !=null && customerRequest.getSourceapplicationno()!="")
				custUnqId = customerRequest.getSourcecustomerid() + "_" +sourceSystemSCode + "_" + customerRequest.getSourceapplicationno();
			else
				custUnqId = customerRequest.getSourcecustomerid() + "_" +sourceSystemSCode;
			final String cust_unq_id=custUnqId;
			psxRequest.setCustunqid(custUnqId);
			psxRequest.setAssignUcic(customerRequest.getAssignUcic());		
			perfTime = System.currentTimeMillis();
			serviceI.savePsxRequestInfo(psxRequest);
			serviceI.saveDemographicRequest(customerRequest, timestamp,psxBatchId,custUnqId);
			if(Boolean.parseBoolean(environment.getProperty("isFixedAddressTypesEnable"))){
				if(customerRequest.getAddressInformation().getAddress0()!=null && !customerRequest.getAddressInformation().getAddress0().equals("") || !customerRequest.getAddressInformation().getAddress0().equalsIgnoreCase("string"))
				{
					int index=environment.getProperty("addressType0").trim().indexOf("_");
					customerRequest.getAddressInformation().setAddresstype0(environment.getProperty("addressType0").trim().substring(index+1));
					
				}
				if(customerRequest.getAddressInformation().getAddress1()!=null && !customerRequest.getAddressInformation().getAddress1().equals("") || !customerRequest.getAddressInformation().getAddress1().equalsIgnoreCase("string"))
				{
					int index=environment.getProperty("addressType1").trim().indexOf("_");
					customerRequest.getAddressInformation().setAddresstype1(environment.getProperty("addressType1").trim().substring(index+1));
					
				}
				if(customerRequest.getAddressInformation().getAddress2()!=null && !customerRequest.getAddressInformation().getAddress2().equals("") || !customerRequest.getAddressInformation().getAddress2().equalsIgnoreCase("string"))
				{
					int index=environment.getProperty("addressType2").trim().indexOf("_");
					customerRequest.getAddressInformation().setAddresstype2(environment.getProperty("addressType2").trim().substring(index+1));
					
				}
				if(customerRequest.getAddressInformation().getAddress3()!=null && !customerRequest.getAddressInformation().getAddress3().equals("") || !customerRequest.getAddressInformation().getAddress3().equalsIgnoreCase("string"))
				{
					int index=environment.getProperty("addressType3").trim().indexOf("_");
					customerRequest.getAddressInformation().setAddresstype3(environment.getProperty("addressType3").trim().substring(index+1));
					
				}
			}
			serviceI.saveAddressRequest(customerRequest, timestamp, psxBatchId,custUnqId);
			performanceLogs.put("Time Taken for Saving  Request DG & ACE Information",(System.currentTimeMillis() - perfTime)+ "");
			perfTime = System.currentTimeMillis();
			HashMap<String, Object> dg_row1 = buildingHashMap.buildDGHashMap(customerRequest, timestamp, psxBatchId,custUnqId);
			ArrayList<HashMap<String, Object>> dgAl = new ArrayList<HashMap<String, Object>>();
		//	HashMap<String, Object> dg_row2 = buildingHashMap.buildAceHashMap(customerRequest, timestamp, psxBatchId);
			ArrayList<HashMap<String, Object>> aceAl  =buildingHashMap.buildAceObjectHashMap(customerRequest, timestamp, psxBatchId,custUnqId);
			dgAl.add(dg_row1);
		//	aceAl.add(dg_row2);
			HashMap<String, ArrayList<HashMap<String, Object>>> entry = new HashMap<String, ArrayList<HashMap<String, Object>>>();
			entry.put("DG", dgAl);
			entry.put("ACE", aceAl);
			HashMap<String, String> paramMap = new HashMap<>();
			
			// paramMap.put("requestID", Long.toString(reqPsxid));
			
			paramMap.put("inputPsxBatchId", psxBatchId);
			
		//	paramMap.put("onlineIgnoreResKey", custUnqId);
			paramMap.put("paramMapQueueName",environment.getProperty("primeMatchResponseQ"));
			logger.info("Matching Rule ::: " + profile.getMatchingRuleCSV());
			String matching_rule = profile.getMatchingRuleCSV();
			String residual_parameters = profile.getResidualsCSV() != null ? profile.getResidualsCSV() : "";
			String weightages = profile.getWeightagesCSV() != null ? profile.getWeightagesCSV() : "";
			String scale_stringent = profile.getScaleStringentCSV() != null ? profile.getScaleStringentCSV() : "";
			String rankingOrders = profile.getRankingCSV() != null ? profile.getRankingCSV() : "";
			RequestBean requestBean = new RequestBean(weightages, entry,matching_rule, scale_stringent, "matchingProcess",paramMap, "P", Long.toString(reqPsxid), sourceSystemName1,rankingOrders);

		
			if (requestBean.getParamMap() == null) {
				requestBean.setParamMap(new HashMap<String, String>());
			}
			requestBean.getRequestInformation().get("DG").forEach(x -> {x.remove("LCHGTIMEGiven");});
			requestBean.getRequestInformation().get("ACE").forEach(x -> {x.remove("LCHGTIMEGiven");});
			requestBean.getRequestInformation().get("DG").forEach(x -> {x.put("LCHGTIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));});
			requestBean.getRequestInformation().get("ACE").forEach(x -> {x.put("LCHGTIME", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(timestamp));});
			if (residual_parameters.length() > 0)
				requestBean.setResidualParams(residual_parameters);
			
			String requestJson = new Gson().toJson(requestBean,RequestBean.class);
	
			logger.info("PRIME_MATCH_QUEUE message ::: " + requestJson);
			producer.sendMessage(environment.getProperty("PRIME_MATCH_QUEUE"),requestJson,String.valueOf(reqPsxid));
			performanceLogs.put("Time Taken for Building  Request DG & ACE Information and Sending Message to Engine", (System.currentTimeMillis() - perfTime)+ "");
			
			
			
			if(Boolean.parseBoolean(environment.getProperty("isCountDownLatchEnable"))){
				logger.info("Using Count Down Latch Approach for the RequestID "+reqPsxid);
				CountDownLatch countDownLatch = new CountDownLatch(Integer.parseInt(environment.getProperty("NODES_COUNT")));
				int expectedResponsesCount = Integer.parseInt(environment.getProperty("NODES_COUNT"));
				Object[] values = new Object[] { countDownLatch };
				values = Arrays.copyOf(values, expectedResponsesCount + 1);
				logger.info("Adding Pending Request::");
				listener.addPendingRequest(customerRequest.getRequestId() + "",values);
				boolean normalTermination = false;
				while (listener.isStillProcessing(customerRequest.getRequestId()+ "")) {
				try {
					normalTermination = countDownLatch.await(Integer.parseInt(environment.getProperty("countDownAwaitTimeInMillis")),TimeUnit.MILLISECONDS);
				} catch (Exception e) {
					throw new PosidexException("Exception occured while performing CountDown Latch Operation ..... ");
				}
				if (!normalTermination) {
					break;
				}
				}
				logger.info(String.format("Count Down Latch Await Time completed for the request:%d and the count down latch counter value is:%d",reqPsxid, countDownLatch.getCount()));
				performanceLogs.put("countDownLatch Count","" + countDownLatch.getCount());
				for (int i = 1; i < values.length; i++) {
					performanceLogs.put("customerResultsBean" + i + " Received At:",values[i] == null ? null : new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date((Long) values[i])));
				}
				psxRequest = serviceI.getRequestInfo(reqPsxid);
				if (psxRequest.getRequeststatus() != null) {
					reqStatus = psxRequest.getRequeststatus();
				}
			}else{
				logger.info("Using Normal Listener Recieve Timeout Approach for the RequestID "+reqPsxid);
				if(Boolean.parseBoolean(environment.getProperty("isGetAddressStrengthByResponseMsg"))){
				recievedMsgStatus=listener.recieveMsgStatus(String.valueOf(reqPsxid));
				msgStatus=recievedMsgStatus!=null && !recievedMsgStatus.isEmpty() ? recievedMsgStatus.get(0):null;
				logger.info("Recieved message status "+msgStatus);
				}else{
					msgStatus=listener.recieveMsgStatus1(String.valueOf(reqPsxid));
				}
				
				
				if(msgStatus!=null && !msgStatus.equals("")){
					reqStatus=msgStatus.trim();
				}else{
					psxRequest = serviceI.getRequestInfo(reqPsxid);
					if (psxRequest.getRequeststatus() != null) {
						reqStatus = psxRequest.getRequeststatus();
					}
				}
					
			}
			long startWhile = System.currentTimeMillis();
			int hitCount = 0;
			
			perfTime = System.currentTimeMillis();
			long startTime = System.currentTimeMillis();
			logger.info("Before While Loop "+reqStatus);
			while ("P".equalsIgnoreCase(reqStatus)) {
				hitCount++;
				psxRequest = serviceI.getRequestInfo(reqPsxid);
				if (psxRequest != null) {
					if (psxRequest.getRequeststatus() != null) {
						reqStatus = psxRequest.getRequeststatus();
					}
				}
				logger.info("Inside While Loop "+reqStatus + "hitCount "+hitCount);
				processedTime = System.currentTimeMillis() - startTime;
				if (processedTime > Integer.parseInt(environment.getProperty("requestTimeoutInMillis"))) {
					logger.warn("Process Time Limit Exceeded ... " + reqPsxid);
					break;
				}

				if ("E".equalsIgnoreCase(reqStatus)) {
					reqStatus = "E";
					break;
				}
			}

			if (processedTime >= Integer.parseInt(environment.getProperty("requestTimeoutInMillis"))) {
				reqStatus = "P";
				customerResultsBean.setRequestid(reqPsxid);
				customerResultsBean.setStatus("P");
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setDescription(environment.getProperty("TIME_LIMIT_EXCEEDS_ERROR_MSG"));
			}
			if ("E".equalsIgnoreCase(reqStatus)) {
				reqStatus = "E";
				customerResultsBean.setRequestid(reqPsxid);
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus("E");
				customerResultsBean.setDescription(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			}

			logger.debug(String.format("Time Taken to reach Counter %d, for request Id %d ",(System.currentTimeMillis() - methodStartTime), reqPsxid));
			performanceLogs.put("pollingTime", ""+ (System.currentTimeMillis() - perfTime));
			performanceLogs.put("Number of Times Polled", hitCount + "");
			
			
			perfTime = System.currentTimeMillis();
			logger.info(String.format("RequestID: %d Polling time is %d and the number of hits are:%d",reqPsxid,(System.currentTimeMillis() - startWhile), hitCount));
			
			if (reqStatus.equalsIgnoreCase(("C"))) {
				List<MatchedCustomerDetailsBean> matchedCustomers = new ArrayList<>();
				List<MatchedCustomerLoanDetailsBean> matchedLoanDetailsList = new ArrayList<>();
				List<CgclReportInputOutputBean> reportMatches = new ArrayList<>();
				List<NegativeMatchedCustomerDetailsBean> negMatches=new ArrayList<>();
				List<CgclLoanDetailsBean> loanMatches = new ArrayList<CgclLoanDetailsBean>();
				try {
					reqStatus = "C";
					long mc_start_time = System.currentTimeMillis();
					psxRequest = serviceI.getRequestInfo(reqPsxid);
					
					if (psxRequest.getMatchcount() != null) {
						totalMatchCount = psxRequest.getMatchcount();
						logger.info("Total Match Count in Search Customer API :: "+totalMatchCount);
						if(totalMatchCount == 0){
							totalMatchCount=psxRequest.getOfflinematchcount()+psxRequest.getOnlinematchcount();
							psxRequest.setMatchcount(totalMatchCount);
						}
					}
					perfTime = System.currentTimeMillis();
					reportMatches = serviceI.getAllMatchedCustomerDetails(customerRequest.getRequestId());
					logger.info("Matched Customer Details List Size :::"+ reportMatches.size() + " Time Taken "+ (System.currentTimeMillis() - perfTime));
					performanceLogs.put("timeForGettingMatchedCustomers", ""+ (System.currentTimeMillis() - perfTime));
					
					if (reportMatches != null || !reportMatches.isEmpty()) {
						Map<String, List<CgclReportInputOutputBean>> resultsByFcb = new HashMap<>();
						resultsByFcb = reportMatches.stream().collect(Collectors.groupingBy(CgclReportInputOutputBean::getPsxtargetfcb));
						
						logger.info("Total Report Matches ::: " + reportMatches.size());
						
						negMatches=buildingJdbcEntities.buildingNegMatchedCustomerDetails(resultsByFcb.get("NEGATIVECUSTBASE"));
						
						if(Boolean.parseBoolean(environment.getProperty("isGetAddressStrengthByResponseMsg"))){
						List<MatchedAddressStrengths> matchedAddressStrengthsList=buildingHashMap.findingAddressStrengths(recievedMsgStatus.get(1));
						List<CgclReportInputOutputBean> reportedMatches1=buildingJdbcEntities.appendingAddressStrengths(resultsByFcb.get("Customer"),matchedAddressStrengthsList);
						matchedCustomers = buildingJdbcEntities.buildingMatchedCustomerDetails(reportedMatches1);
						}else{
						matchedCustomers = buildingJdbcEntities.buildingMatchedCustomerDetails(resultsByFcb.get("Customer"));
						}
						
						logger.info(String.format("NEGATIVE CUSTOMER MATCHES :: %d ",negMatches !=null ?negMatches.size():0));
						logger.info(String.format("CUSTOMER MATCHES :: %d ", matchedCustomers !=null ?matchedCustomers.size():0));
					}
					
		//			if ((customerRequest.getAssignUcic().equalsIgnoreCase("Y")) && (totalMatchCount == 0 || totalMatchCount == 1)) {
						if ((customerRequest.getAssignUcic().equalsIgnoreCase("Y")) &&  totalMatchCount == 0) {

							String ucicId = assignUcicService.assignUcicNumber("", totalMatchCount, customerRequest,timestamp, psxBatchId, "NEW",false,custUnqId);
							dg_row1.put("UCIC", ucicId);
							customerResultsBean.setSystemAssignedUcic(ucicId);
						//	customerResultsBean.setUcicDescription("Posidex System Generated the UCIC");
							dgAl = new ArrayList<HashMap<String, Object>>();
							dgAl.add(dg_row1);
							entry = new HashMap<String, ArrayList<HashMap<String, Object>>>();
							entry.put("DG", dgAl);
							entry.put("ACE", aceAl);

							capriGlobalSender.sendMessageForUcicIntraday(reqPsxid, entry);
							serviceI.updateUcicAssignStatus(reqPsxid, ucicId,customerRequest.getUcicType(), "SC0");
						}
						
						if(matchedCustomers !=null && Boolean.parseBoolean(environment.getProperty("isSelfMatchFilterEnable"))){
						
						List<String> selfMatchedUcic=matchedCustomers.stream().map(MatchedCustomerDetailsBean::getMatchId).collect(Collectors.toList());
						logger.info("Given Cust_unq_id :: "+cust_unq_id + " Related Matched Ids "+selfMatchedUcic);
							List<MatchedCustomerDetailsBean> selfMatches=matchedCustomers.stream().filter(mc->mc.getMatchId().equalsIgnoreCase(cust_unq_id)).collect(Collectors.toList());
							logger.info(String.format("No of Self Matches Reported %d ", selfMatches!=null ? selfMatches.size() : 0));
							if(selfMatches!=null && selfMatches.size() <= matchedCustomers.size()){
								int selfMatchCount= selfMatches.size();
								//List<String> selfMatchedUcic=selfMatches.stream().map(MatchedCustomerDetailsBean::getMatchId).collect(Collectors.toList());
								MatchedCustomerDetailsBean self = matchedCustomers.stream().filter(p -> p.getMatchId().equalsIgnoreCase(cust_unq_id) && p.getRecordType().equalsIgnoreCase(environment.getProperty("recordType1"))).findFirst().orElse(null);
								if(self == null){
									self = matchedCustomers.stream().filter(p -> p.getMatchId().equalsIgnoreCase(cust_unq_id) && p.getRecordType().equalsIgnoreCase(environment.getProperty("recordType2"))).findFirst().orElse(null);
								}
								
								if(self !=null){
								
								
								
								logger.info("Removing the self matches ");
								matchedCustomers.removeIf(mc->selfMatches.contains(mc));
								totalMatchCount=totalMatchCount-selfMatchCount;
								psxRequest.setMatchcount(totalMatchCount);
								//for(MatchedCustomerDetailsBean self:selfMatches){
									customerRequest.setUcic(self.getUcic());
									customerRequest.setUcicType("EXISTING");
									
									serviceI.saveDemographicIntraday(customerRequest,timestamp,psxBatchId,false,cust_unq_id);
									serviceI.saveAddressIntraday(customerRequest,timestamp,psxBatchId,cust_unq_id);
									dg_row1.put("UCIC", self.getUcic());
									
									dgAl = new ArrayList<HashMap<String, Object>>();
									dgAl.add(dg_row1);
									entry = new HashMap<String, ArrayList<HashMap<String, Object>>>();
									entry.put("DG", dgAl);
									entry.put("ACE", aceAl);
									capriGlobalSender.sendMessageForUcicIntraday(reqPsxid, entry);
									serviceI.updateUcicAssignStatus(reqPsxid,self.getUcic(),customerRequest.getUcicType(), "SELF"+selfMatchCount);
								
								//}
								customerResultsBean.setSystemAssignedUcic(self.getUcic());
								customerResultsBean.setSelfmatchcount(selfMatchCount);
								}
							}
						}
						
						if ((customerRequest.getAssignUcic().equalsIgnoreCase("Y")) && totalMatchCount == 1 && matchedCustomers !=null && matchedCustomers.size() == 1 && environment.getProperty("scaleType").equalsIgnoreCase(matchedCustomers.get(0).getScaleType())) {
							logger.info("Inside Total Match Count 1 ::: "+ totalMatchCount +"Scale_Type :: "+ matchedCustomers.get(0).getScaleType());
							List<String> ucicId = matchedCustomers.stream().map(e -> e.getUcic()).collect(Collectors.toList());
							if (ucicId.size() == 1) {
								logger.info("Existing Customer UCIC Information ::: "+ ucicId.get(0));
								String ucic = assignUcicService.assignUcicNumber(ucicId.get(0),totalMatchCount,customerRequest, timestamp,psxBatchId, "EXISTING",Boolean.parseBoolean(environment.getProperty("isTempUcic")),custUnqId);
								dg_row1.put("UCIC", ucicId.get(0));
								if(Boolean.parseBoolean(environment.getProperty("isTempUcic")))
								customerResultsBean.setSystemAssignedUcic(ucicId.get(0).replaceAll("TEMP_", ""));
								else{
									if(ucicId.get(0) !=null)
										customerResultsBean.setSystemAssignedUcic(ucicId.get(0));
									else
										customerResultsBean.setSystemAssignedUcic("0");
								}
								
									
						//		customerResultsBean.setUcicDescription("Posidex System Generated the UCIC");
								dgAl = new ArrayList<HashMap<String, Object>>();
								dgAl.add(dg_row1);
								entry = new HashMap<String, ArrayList<HashMap<String, Object>>>();
								entry.put("DG", dgAl);
								entry.put("ACE", aceAl);
								capriGlobalSender.sendMessageForUcicIntraday(reqPsxid, entry);
								serviceI.updateUcicAssignStatus(reqPsxid,ucicId.get(0),customerRequest.getUcicType(), "SC1");
								//List<Long> customerId = reportMatches.stream().map(e -> e.getCustomerno() != null ? Long.parseLong(e.getCustomerno()): 0).collect(Collectors.toList());
								List<String> customerId = reportMatches.stream().map(e -> e.getCustomerno() != null ? e.getCustomerno(): "0").collect(Collectors.toList());
								perfTime = System.currentTimeMillis();
								//loanMatches = serviceI.getMatchedLoanDetails(customerId);
								loanMatches = serviceI.getMatchedLoanDetails1(customerId);
								logger.info("Matched Loan Details List Size :::"+ loanMatches.size() + " Time Taken "+ (System.currentTimeMillis() - perfTime));
								performanceLogs.put("timeForGettingMatchedLoan Details", ""+ (System.currentTimeMillis() - perfTime));
								if (loanMatches != null)
									matchedLoanDetailsList = buildingJdbcEntities.buildingMatchedLoanDetails(loanMatches);
							}
						}

					//}
					if (totalMatchCount > 1) {
						
							//List<Long> customerId = reportMatches.stream().map(e -> e.getCustomerno() != null ? Long.parseLong(e.getCustomerno()): 0).collect(Collectors.toList());
							List<String> customerId = reportMatches.stream().map(e -> e.getCustomerno() != null ? e.getCustomerno(): "0").collect(Collectors.toList());
							perfTime = System.currentTimeMillis();
							//loanMatches = serviceI.getMatchedLoanDetails(customerId);
							loanMatches = serviceI.getMatchedLoanDetails1(customerId);
							logger.info("Matched Loan Details List Size :::"+ loanMatches.size() + " Time Taken "+ (System.currentTimeMillis() - perfTime));
							performanceLogs.put("timeForGettingMatchedLoan Details", ""+ (System.currentTimeMillis() - perfTime));
							customerResultsBean.setSystemAssignedUcic("0");
							
						if (loanMatches != null)
							matchedLoanDetailsList = buildingJdbcEntities.buildingMatchedLoanDetails(loanMatches);
					}

					
					

					
					
				} catch (Exception e) {
					logger.error("Exception has Caught inside MatchedCustomers Details");
					logger.error(e,e);
					reqStatus = "E";
					throw new PosidexException("Exception occured while inside MatchedCustomers Details..... ");
				}
				perfTime = System.currentTimeMillis();
				customerResultsBean.setRequestid(reqPsxid);
				
				if ("C".equalsIgnoreCase(reqStatus)) {
					customerResultsBean.setStatus(environment.getProperty("SUCCESS_STATUS"));
					customerResultsBean.setResponsecode(environment.getProperty("SUCCESS_CODE"));
					customerResultsBean.setDescription(environment.getProperty("REQUEST_SUCCESS_MSG"));
					customerResultsBean.setCustmatchcount(psxRequest.getMatchcount());
					customerResultsBean.setNegativematchcount(psxRequest.getNegativeMatchCount());
					customerResultsBean.setNegativeMatchedCustomerDetails(negMatches);
					customerResultsBean.setMatchedCustomerDetails(matchedCustomers);
					customerResultsBean.setMatchedCustomerLoanDetails(matchedLoanDetailsList);
				}
			}

		} catch (Exception e) {
			
			logger.error("Exception occured in findCustomer " + e.getMessage());
			logger.error(e, e);
			customerResultsBean.setRequestid(reqPsxid);
			customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
			reqStatus = "E";
			customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
			customerResultsBean.setDescription(e.getMessage());
			
			if (e.getMessage().contains("You have an error in your SQL syntax")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			}
			if (e.getMessage().contains(" is Mandatory") || e.getMessage().contains("must be in dd-MM-yyyy format")) {
				customerResultsBean.setResponsecode(environment.getProperty("MISSING_FIELDS_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(e.getMessage());
			}
			if (e.getMessage().contains("Could not connect to broker URL:")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("ACTIVE_ERROR_MSG"));
			}

			if (e.getMessage().contains("Communications link failure:")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("DB_ERROR_MSG"));
			}
			if (e.getMessage().contains(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG")) || e.getMessage().contains(environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG")) || e.getMessage().contains("CUSTOMER_CATEGORY must be either I or C ")) {
				customerResultsBean.setResponsecode(environment.getProperty("AUTHENTICATION_FAILURE_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
			//	customerResultsBean.setDescription(environment.getProperty("AUTHENTICATION_ERROR_MSG"));
				customerResultsBean.setDescription(e.getMessage());
			}
			
			if (e.getMessage().contains("Exceeds Length")) {
				customerResultsBean.setResponsecode(environment.getProperty("EXCEEDS_FIELD_LENGTH_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(e.getMessage());
		    }
			
			return customerResultsBean;
		} finally {
			long methodEndTime = System.currentTimeMillis();
			long logCompletionTime;
			if (customerRequest == null) {
				logger.info("customerRequest object is null");
			} else {
				logger.info(String.format("Request ID: %d ,Capri Global Customer No:%s Time Taken in millis:%d. Request StartTime is:%s. RequestEndTime is:%s",reqPsxid,customerRequest.getSourcecustomerid(),(methodEndTime - methodStartTime),new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(methodStartTime)),new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(methodEndTime))));
				performanceLogs.put("requestReceivedAt", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(methodStartTime)));
				performanceLogs.put("requestCompletedAt", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(methodEndTime)));
				performanceLogs.put("totalTimeForRequestProcessing is:",""+ (logCompletionTime = (methodEndTime - methodStartTime)));
				String reqPsxidVal = reqPsxid + "";
				// webserviceRequestProcessingTime(!reqPsxidVal.equalsIgnoreCase("0") ? reqPsxidVal :(( customerRequest.getBflRequestId() != null) ? customerRequest.getBflRequestId(): "0"),logCompletionTime,customerRequest.getMatchProfile(),reqStatus);
				if (!reqPsxidVal.equalsIgnoreCase("0")) {
					// webserviceRequestProcessingTime(reqPsxidVal,logCompletionTime,customerRequest.getMatchingruleprofile(),reqStatus);
				} else if (customerRequest.getSourcecustomerid() != null) {
					if (!customerRequest.getSourcecustomerid().isEmpty() && !customerRequest.getSourcecustomerid().equals("")) {
						// webserviceRequestProcessingTime(customerRequest.getSourcecustomerid(),logCompletionTime,customerRequest.getMatchingruleprofile(),reqStatus);
					} else {
						// webserviceRequestProcessingTime("0",logCompletionTime,customerRequest.getMatchingruleprofile(),reqStatus);
					}
				} else {
					// webserviceRequestProcessingTime("0",logCompletionTime,customerRequest.getMatchingruleprofile(),reqStatus);
				}
				logger.info(performanceLogs.toString());
			}
		}
		return customerResultsBean;
	}

	@PostMapping(value = "assignUcic", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UcicResponseInfoBean assignUcic(@RequestBody UcicRequestInfoBean ucicRequestInfo,HttpServletRequest request) throws Exception {
		
		long m_start_time = System.currentTimeMillis();
		logger.info("Given Reuqest Details inside assignUcic API :: "+ ucicRequestInfo);
		UcicResponseInfoBean ucicResponseInfoBean = new UcicResponseInfoBean();
		LinkedHashMap<String, String> performanceLogs = new LinkedHashMap<>();
		long perfTime = System.currentTimeMillis();
		//Date timestamp = new Date();
		Date timestamp = Date.from(Instant.now().truncatedTo( ChronoUnit.SECONDS));
		String ucicId = ucicRequestInfo.getUcic() + "";
		String ucicType = ucicRequestInfo.getUcictype();
		String psxBatchId = new SimpleDateFormat("ddMMyyyyHHmmss").format(timestamp);
		CustomerRequestInformation customerRequest = new CustomerRequestInformation();
		CustomerRequestInformation customerAce = new CustomerRequestInformation();
		ArrayList<HashMap<String, Object>> dgAl = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> dg_row2 = new HashMap<>();
		HashMap<String, Object> dg_row1 = new HashMap<>();
		boolean existing=false;
		String custUnqId="";
		try {
			beanUtils.trimAllStrings(ucicRequestInfo);
			if (("").equalsIgnoreCase(ucicRequestInfo.getUcictype()) || ("string").equalsIgnoreCase(ucicRequestInfo.getUcictype()) || ucicRequestInfo.getUcictype() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("UCIC_TYPE_V_MSG");
				throw new PosidexException(environment.getProperty("UCIC_TYPE_V_MSG"));
			}
			
//			if ( 0 == (ucicRequestInfo.getUcic())) {
//				errorMessage = "";
//				errorMessage += environment.getProperty("UCIC_V_MSG");
//				throw new PosidexException(environment.getProperty("UCIC_V_MSG"));
//			}
			
			if (0 == ucicRequestInfo.getRequestid())
			 {
				errorMessage = "";
				errorMessage += environment.getProperty("REQUEST_ID_V_MSG");
				throw new PosidexException(environment.getProperty("REQUEST_ID_V_MSG"));
			}
			
			if (("").equalsIgnoreCase(ucicRequestInfo.getSourcesystemname()) || ("string").equalsIgnoreCase(ucicRequestInfo.getSourcesystemname()) || ucicRequestInfo.getSourcesystemname() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_SYSTEM_NAME_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_SYSTEM_NAME_V_MSG"));
			}
			
			if (("").equalsIgnoreCase(ucicRequestInfo.getSourceauthenticationtoken()) || ("string").equalsIgnoreCase(ucicRequestInfo.getSourceauthenticationtoken()) || ucicRequestInfo.getSourceauthenticationtoken() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_AUTHENTICATION_TOKEN_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_AUTHENTICATION_TOKEN_V_MSG"));
			}
			
			if (ucicRequestInfo.getRequestid() != 0 && buildingJdbcEntities.noOfDigits(ucicRequestInfo.getRequestid()) > Integer.parseInt(environment.getProperty("requestIdSize")))
				throw new PosidexException("REQUEST_ID Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("requestIdSize"))+ " Actual Length :: "+ buildingJdbcEntities.noOfDigits(ucicRequestInfo.getRequestid()) );
			
			if (ucicRequestInfo.getSourcesystemname() != null && ucicRequestInfo.getSourcesystemname().length() > Integer.parseInt(environment.getProperty("sourceSystemNameSize")))
				throw new PosidexException("SOURCE_SYSTEM_NAME Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceSystemNameSize"))+ " Actual Length :: "+ ucicRequestInfo.getSourcesystemname().length());
			
			if (ucicRequestInfo.getSourceauthenticationtoken() != null && ucicRequestInfo.getSourceauthenticationtoken().length() > Integer.parseInt(environment.getProperty("sourceAuthenticationTokenSize")))
				throw new PosidexException("SOURCE_AUTHENTICATION_TOKEN Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceAuthenticationTokenSize"))+ " Actual Length :: "+ ucicRequestInfo.getSourceauthenticationtoken().length());
			//  ucicRequestInfo.getUcictype()  ucicRequestInfo.getUcic()
			
				if (ucicRequestInfo.getUcic() != 0 && buildingJdbcEntities.noOfDigits(ucicRequestInfo.getUcic()) > Integer.parseInt(environment.getProperty("ucicSize")))
				throw new PosidexException("UCIC Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("ucicSize"))+ " Actual Length :: "+ buildingJdbcEntities.noOfDigits(ucicRequestInfo.getUcic()) );
				
				if (ucicRequestInfo.getUcictype() != null && ucicRequestInfo.getUcictype().length() > Integer.parseInt(environment.getProperty("ucicTypeSize")))
				throw new PosidexException("UCIC_TYPE Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("ucicTypeSize"))+ " Actual Length :: "+ ucicRequestInfo.getUcictype().length());
			
			String [] ucicTypeList=environment.getProperty("ucicTypeList").trim().toUpperCase().split(",");
			
			if(!(Arrays.asList(ucicTypeList).contains(ucicType.toUpperCase()))){
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_UCIC_TYPE_V_MSG");
				throw new PosidexException(environment.getProperty("INVALID_UCIC_TYPE_V_MSG"));
			}
			
			
			List<String> requestIdList = serviceI.getValidRequestId();
			if (("").equalsIgnoreCase(ucicRequestInfo.getRequestid() + "") || ("string").equalsIgnoreCase(ucicRequestInfo.getRequestid() + "") || ucicRequestInfo.getRequestid() + "" == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("REQUEST_ID_V_MSG");
				throw new PosidexException(environment.getProperty("REQUEST_ID_V_MSG"));
			} else {
				if (requestIdList != null) {
					
					if (!requestIdList.contains(ucicRequestInfo.getRequestid()+ "")) {
						errorMessage = "";
						errorMessage += environment.getProperty("INVALID_REQUEST_ID_V_MSG");
						throw new PosidexException(environment.getProperty("INVALID_REQUEST_ID_V_MSG"));
					}
				}
			}
			
			String source_system = ucicRequestInfo.getSourcesystemname();
			List<String> sourceSystemCodes = serviceI.getSourceSystemAuthentication(source_system);
			CgclDgBlkBean custDgBlkRequest = serviceI.getDemographicInfoForUcic(ucicRequestInfo.getRequestid());
			if(Boolean.parseBoolean(environment.getProperty("crossCheckSourceSystem"))){
			if (sourceSystemCodes == null || sourceSystemCodes.isEmpty() || !custDgBlkRequest.getSourcesystemname().equalsIgnoreCase(source_system)) {
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG");   
				throw new PosidexException(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG"));
			}
			}else{
				if (sourceSystemCodes == null || sourceSystemCodes.isEmpty()) {
					errorMessage = "";
					errorMessage += environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG");   
					throw new PosidexException(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG"));
				}
			}
			
			customerRequest.setSourcesystemname(sourceSystemCodes.get(0));
		
			if (!sourceSystemCodes.get(0).equalsIgnoreCase(ucicRequestInfo.getSourceauthenticationtoken())) {
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG");
				throw new PosidexException(environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG"));
			}
			

			List<String> ucicInfo=serviceI.getUcicInfo(ucicRequestInfo.getRequestid());
			logger.info("Assigned Ucic Information ::: "+ucicInfo);
				if(ucicInfo.get(1)!=null && ucicInfo.get(2)!=null &&ucicInfo.get(3)!=null && "SC1".equalsIgnoreCase(ucicInfo.get(2))){
				errorMessage = "";
				errorMessage += environment.getProperty("ALEARDY_ASSIGNED_UCIC_V_MSG");
				//errorMessage += "UCIC is Aleardy assigned for this Request";
				existing=true;
				ucicResponseInfoBean.setUcic(ucicInfo.get(1));
				ucicResponseInfoBean.setUcictype(ucicInfo.get(3));
				throw new PosidexException(environment.getProperty("ALEARDY_ASSIGNED_UCIC_V_MSG"));
			}
				
				if(ucicInfo.get(4)!=null && ucicInfo.get(4).equalsIgnoreCase("P")){
					throw new PosidexException(environment.getProperty("PENDING_UCIC_V_MSG"));
				}
				if(ucicInfo.get(4)!=null && ucicInfo.get(4).equalsIgnoreCase("E")){
					throw new PosidexException(environment.getProperty("ERROR_UCIC_V_MSG"));
				}
				
				
		
			ucicResponseInfoBean.setRequestid(ucicRequestInfo.getRequestid()+ "");
			ucicResponseInfoBean.setSourceauthenticationtoken(ucicRequestInfo.getSourceauthenticationtoken());
			ucicResponseInfoBean.setSourcecustomerid(ucicRequestInfo.getSourcecustomerid());
			ucicResponseInfoBean.setSourcesystemname(ucicRequestInfo.getSourcesystemname());
			ucicResponseInfoBean.setUcic(ucicRequestInfo.getUcic() + "");
			ucicResponseInfoBean.setUcictype(ucicRequestInfo.getUcictype());
			reqPsxid = ucicRequestInfo.getRequestid();
		//	CgclDgBlkBean custDgBlkRequest = serviceI.getDemographicInfoForUcic(ucicRequestInfo.getRequestid());
			performanceLogs.put("Time Taken for Fetching The DG Information for Given RequestId ::: "+ ucicRequestInfo.getRequestid()+ "and UCIC ID ::: " + ucicRequestInfo.getUcic()+ "and UCIC TYPE ::: "+ ucicRequestInfo.getUcictype(),perfTime - System.currentTimeMillis() + "");
			perfTime = System.currentTimeMillis();
			List<CgclAceBlkBean> custAceBlkRequest = serviceI.getAceInfoForUcic(ucicRequestInfo.getRequestid());
			performanceLogs.put("Time Taken for Fetching The ACE Information for Given RequestId ::: "+ ucicRequestInfo.getRequestid()+ "and UCIC ID ::: " + ucicRequestInfo.getUcic()+ "and UCIC TYPE ::: "+ ucicRequestInfo.getUcictype(),perfTime - System.currentTimeMillis() + "");
			perfTime = System.currentTimeMillis();
			
			if(ucicType.equalsIgnoreCase("NEW")){
				 ucicId =serviceI.generateSequenceId(environment.getProperty("UcicIdGeneratedSequence"))+"";
				 ucicResponseInfoBean.setUcic(ucicId);
				 logger.info("Inside Assign UCIC for UcicType New ::: "+ucicId);
			}
			
			if (custDgBlkRequest != null) {
				customerRequest = buildingJdbcEntities.buildingCustomerDgObject(custDgBlkRequest);
				customerRequest.setRequestId(ucicRequestInfo.getRequestid());
				customerRequest.setUcic(ucicId);
				customerRequest.setUcicType(ucicType);
				customerRequest.setSourcecustomerid(custDgBlkRequest.getSourcecustomerid());
				customerRequest.setSourcesystemname(custDgBlkRequest.getSourcesystemname());
				if(customerRequest.getSourceapplicationno() !=null && customerRequest.getSourceapplicationno()!="")
					custUnqId = customerRequest.getSourcecustomerid() + "_" +customerRequest.getSourcesystemname() + "_" + customerRequest.getSourceapplicationno();
				else
					custUnqId = customerRequest.getSourcecustomerid() + "_" +customerRequest.getSourcesystemname();
				
				serviceI.saveDemographicIntraday(customerRequest, timestamp,psxBatchId,false,custUnqId);
				dg_row1 = buildingHashMap.buildDGHashMap(customerRequest,timestamp, psxBatchId,custUnqId);
				dg_row1.put("UCIC", ucicId);
				logger.info("Customer DG HashMap "+dg_row1);
			}

			if (custAceBlkRequest != null) {
				
				customerAce = buildingJdbcEntities.buildingCustomerAceObject(custAceBlkRequest);
				customerRequest.setUcic(ucicId);
				customerRequest.setUcicType(ucicType);
				customerAce.setUcic(ucicId);
				customerAce.setUcicType(ucicType);
				customerAce.setRequestId(reqPsxid);
				customerAce.setSourcecustomerid(custDgBlkRequest.getSourcecustomerid());
				customerAce.setSourcesystemname(custDgBlkRequest.getSourcesystemname());
				
		//		dg_row2 = buildingHashMap.buildAceHashMap(customerAce,timestamp, psxBatchId);
				serviceI.saveAddressIntraday(customerAce, timestamp, psxBatchId,custUnqId);
				logger.info("Builded ACE HashMap "+dg_row2);
			}
			
			
			performanceLogs.put("Time Taken for Buiding Request Bean for UCIC::: "+ ucicRequestInfo.getRequestid()+ "and UCIC ID ::: " + ucicRequestInfo.getUcic()+ "and UCIC TYPE ::: "+ ucicRequestInfo.getUcictype(),perfTime - System.currentTimeMillis() + "");
			perfTime = System.currentTimeMillis();

			
			// HashMap<String, Object> dg_row2 = buildingHashMap.buildAceHashMap(customerRequest,timestamp,psxBatchId);
//			HashMap<String, Object> dg_row2 = buildingHashMap.buildAceHashMap(customerRequest, timestamp, psxBatchId);
			
			ArrayList<HashMap<String, Object>> aceAl  =buildingHashMap.buildAceObjectHashMap(customerAce, timestamp, psxBatchId,custUnqId);
			
		//	ArrayList<HashMap<String, Object>> aceAl = new ArrayList<HashMap<String, Object>>();
			dgAl.add(dg_row1);
			aceAl.add(dg_row2);
			HashMap<String, ArrayList<HashMap<String, Object>>> entry = new HashMap<String, ArrayList<HashMap<String, Object>>>();
			entry.put("DG", dgAl);
			entry.put("ACE", aceAl);
			capriGlobalSender.sendMessageForUcicIntraday(reqPsxid, entry);
			serviceI.updateUcicAssignStatus(reqPsxid, ucicId,customerRequest.getUcicType(), "AC");
			ucicResponseInfoBean.setStatusinfo("C");
			ucicResponseInfoBean.setDescription(environment.getProperty("SUCCESS_STATUS"));
			ucicResponseInfoBean.setResponseCode(environment.getProperty("SUCCESS_CODE"));
			performanceLogs.put("Time Taken for Assiging UCIC and Saving Information into Intraday tables  ::: "+ ucicRequestInfo.getRequestid()+ "and UCIC ID ::: "+ ucicRequestInfo.getUcic()+ "and UCIC TYPE ::: "+ ucicRequestInfo.getUcictype(),perfTime - System.currentTimeMillis() + "");
		
		} catch (Exception e) {
			e.printStackTrace();
			ucicResponseInfoBean.setRequestid(ucicRequestInfo.getRequestid()+ "");
			ucicResponseInfoBean.setSourceauthenticationtoken(ucicRequestInfo.getSourceauthenticationtoken());
			ucicResponseInfoBean.setSourcecustomerid(ucicRequestInfo.getSourcecustomerid());
			ucicResponseInfoBean.setSourcesystemname(ucicRequestInfo.getSourcesystemname());
			ucicResponseInfoBean.setResponseCode(environment.getProperty("INTERNAL_SERVER_CODE"));
			ucicResponseInfoBean.setStatusinfo(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			if(!existing) {
				ucicResponseInfoBean.setUcic(ucicRequestInfo.getUcic() + "");
				ucicResponseInfoBean.setUcictype(ucicRequestInfo.getUcictype());
			}
			
			ucicResponseInfoBean.setStatusinfo("E");
			ucicResponseInfoBean.setDescription(e.getMessage());
			
			if (e != null && e.getMessage().contains("Exception occured")) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("INTERNAL_SERVER_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			}
			
			if (e.getMessage().contains("You have an error in your SQL syntax")) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("INTERNAL_SERVER_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			}
			if (e.getMessage().contains(environment.getProperty("INVALID_REQUEST_ID_V_MSG"))) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("INVALID_REQUEST_ID"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(e.getMessage());
			}
			
			if (e.getMessage().contains(" is Mandatory")) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("MISSING_FIELDS_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(e.getMessage());
			}
			if (e.getMessage().contains("Could not connect to broker URL:")) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("INTERNAL_SERVER_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(environment.getProperty("ACTIVE_ERROR_MSG"));
			}

			if (e.getMessage().contains("Communications link failure:")) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("INTERNAL_SERVER_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(environment.getProperty("DB_ERROR_MSG"));
			}

			if (e.getMessage().contains("Exceeds Length")) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("EXCEEDS_FIELD_LENGTH_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(e.getMessage());
		    }
			
			if (e.getMessage().contains(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG")) || e.getMessage().contains(environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG"))) {
				ucicResponseInfoBean.setResponseCode(environment.getProperty("AUTHENTICATION_FAILURE_CODE"));
				ucicResponseInfoBean.setStatusinfo(environment.getProperty("FAILURE_STATUS"));
				ucicResponseInfoBean.setDescription(e.getMessage());
			}
			
			logger.error("Error while assiging the UCIC Information "+ e.getMessage());
		} finally {
			performanceLogs.put("Total Time Taken for RequestId"+ ucicRequestInfo.getRequestid(), System.currentTimeMillis()-m_start_time  + "");
			logger.info("Performance HashMap inside getMatches API ::: "+ performanceLogs);
		}
		logger.info("UCIC Response ::: "+ucicResponseInfoBean);
		return ucicResponseInfoBean;

	}

	@PostMapping(value = "getMatches", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerResultsBean getMatches(@RequestBody GetMatchesRequestBean customerRequest,HttpServletRequest request) throws Exception {
		
		long m_start_time = System.currentTimeMillis();
		logger.info("Given Reuqest Details inside getMatches API :: "+ customerRequest);
		
		List<MatchedCustomerDetailsBean> matchedCustomers = new ArrayList<>();
		List<MatchedCustomerLoanDetailsBean> matchedLoanDetailsList = new ArrayList<>();
		List<CgclReportInputOutputBean> reportMatches = new ArrayList<>();
		List<NegativeMatchedCustomerDetailsBean> negMatches=new ArrayList<>();
		List<CgclLoanDetailsBean> loanMatches = new ArrayList<CgclLoanDetailsBean>();
		LinkedHashMap<String, String> performanceLogs = new LinkedHashMap<>();
		
		CustomerResultsBean customerResultsBean = new CustomerResultsBean();
		customerResultsBean.setRequestid(customerRequest.getRequestid());
		customerResultsBean.setSourcesystemname(customerRequest.getSourcesystemname().toUpperCase());
		customerResultsBean.setSourceauthenticationtoken(customerRequest.getSourceauthenticationtoken());
	
		List<String> requestIdList = serviceI.getValidRequestId();
		PsxRequestBean psxRequest = new PsxRequestBean();
		
		try {
			beanUtils.trimAllStrings(customerRequest);
			CgclDgBlkBean custDgBlkRequest = serviceI.getDemographicInfoForUcic(customerRequest.getRequestid());
			customerResultsBean.setAccount_status(custDgBlkRequest.getAccount_status());
			customerResultsBean.setSourcecustomerid(custDgBlkRequest.getSourcecustomerid());
			if (("").equalsIgnoreCase(customerRequest.getRequestid() + "") || ("string").equalsIgnoreCase(customerRequest.getRequestid() + "") || customerRequest.getRequestid() + "" == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("REQUEST_ID_V_MSG");
				throw new PosidexException(environment.getProperty("REQUEST_ID_V_MSG"));
			} else {
				if (requestIdList != null) {
					List<String> validIds = requestIdList.stream().filter(id -> id.equalsIgnoreCase(customerRequest.getRequestid() + "")).collect(Collectors.toList());
					if (!requestIdList.contains(customerRequest.getRequestid()+ "")) {
						errorMessage = "";
						errorMessage += environment.getProperty("INVALID_REQUEST_ID_V_MSG");
						throw new PosidexException(environment.getProperty("INVALID_REQUEST_ID_V_MSG"));
					}
				}
			}
			
			if (("").equalsIgnoreCase(customerRequest.getSourcesystemname()) || ("string").equalsIgnoreCase(customerRequest.getSourcesystemname()) || customerRequest.getSourcesystemname() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_SYSTEM_NAME_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_SYSTEM_NAME_V_MSG"));
			}
			
			if (("").equalsIgnoreCase(customerRequest.getSourceauthenticationtoken()) || ("string").equalsIgnoreCase(customerRequest.getSourceauthenticationtoken()) || customerRequest.getSourceauthenticationtoken() == null) {
				errorMessage = "";
				errorMessage += environment.getProperty("SOURCE_AUTHENTICATION_TOKEN_V_MSG");
				throw new PosidexException(environment.getProperty("SOURCE_AUTHENTICATION_TOKEN_V_MSG"));
			}

			if (customerRequest.getRequestid() != 0 && buildingJdbcEntities.noOfDigits(customerRequest.getRequestid()) > Integer.parseInt(environment.getProperty("requestIdSize")))
				throw new PosidexException("REQUEST_ID Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("requestIdSize"))+ " Actual Length :: "+ buildingJdbcEntities.noOfDigits(customerRequest.getRequestid()) );
			
			if (customerRequest.getSourcesystemname() != null && customerRequest.getSourcesystemname().length() > Integer.parseInt(environment.getProperty("sourceSystemNameSize")))
				throw new PosidexException("SOURCE_SYSTEM_NAME Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceSystemNameSize"))+ " Actual Length :: "+ customerRequest.getSourcesystemname().length());
			
			if (customerRequest.getSourceauthenticationtoken() != null && customerRequest.getSourceauthenticationtoken().length() > Integer.parseInt(environment.getProperty("sourceAuthenticationTokenSize")))
				throw new PosidexException("SOURCE_AUTHENTICATION_TOKEN Exceeds Length,  Expected Length :: "+ Integer.parseInt(environment.getProperty("sourceAuthenticationTokenSize"))+ " Actual Length :: "+ customerRequest.getSourceauthenticationtoken().length());
			
			
			String source_system = customerRequest.getSourcesystemname();
			List<String> sourceSystemCodes = serviceI.getSourceSystemAuthentication(source_system);
			
			
			if(Boolean.parseBoolean(environment.getProperty("crossCheckSourceSystem"))){
			if (sourceSystemCodes == null || sourceSystemCodes.isEmpty() || !custDgBlkRequest.getSourcesystemname().equalsIgnoreCase(source_system)) {
				logger.info("Expected Source System Name in getMatches API :: "+custDgBlkRequest.getSourcesystemname());
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG");   
				throw new PosidexException(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG"));
			}
			}else{
				if (sourceSystemCodes == null || sourceSystemCodes.isEmpty()) {
					errorMessage = "";
					errorMessage += environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG");   
					throw new PosidexException(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG"));
				}
			}
			
		//	customerRequest.setSourcesystemname(sourceSystemCodes.get(1));
			if (!sourceSystemCodes.get(0).equalsIgnoreCase(customerRequest.getSourceauthenticationtoken())) {
				errorMessage = "";
				errorMessage += environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG");
				throw new PosidexException(environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG"));
			}

			long perfTime = System.currentTimeMillis();
			performanceLogs.put("RequestID", customerRequest.getRequestid()+ "");
			performanceLogs.put("SourceSystemName",customerRequest.getSourcesystemname());
			performanceLogs.put("SourceAuthenticationToken",customerRequest.getSourceauthenticationtoken());
			beanUtils.trimAllStrings(customerRequest);
			psxRequest = serviceI.getRequestInfo(customerRequest.getRequestid());

			performanceLogs.put("timeForFetchingGivenRequestInfo","" + (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			reqStatus = psxRequest.getRequeststatus();
			if (psxRequest.getMatchcount() != null) {
				totalMatchCount = psxRequest.getMatchcount();
				if(totalMatchCount == 0){
					totalMatchCount=psxRequest.getOfflinematchcount()+psxRequest.getOnlinematchcount();
					psxRequest.setMatchcount(totalMatchCount);
				}
			}
			
			reportMatches = serviceI.getAllMatchedCustomerDetails(customerRequest.getRequestid());
			if (reportMatches != null || !reportMatches.isEmpty()) {
				Map<String, List<CgclReportInputOutputBean>> resultsByFcb = new HashMap<>();
				resultsByFcb = reportMatches.stream().collect(Collectors.groupingBy(CgclReportInputOutputBean::getPsxtargetfcb));
				
				logger.info("Total Report Matches ::: " + reportMatches.size());
				
				negMatches=buildingJdbcEntities.buildingNegMatchedCustomerDetails(resultsByFcb.get("NEGATIVECUSTBASE"));
				matchedCustomers = buildingJdbcEntities.buildingMatchedCustomerDetails(resultsByFcb.get("Customer"));
				
				if(matchedCustomers.size() == 0 || (matchedCustomers.size() == 1 && environment.getProperty("scaleType").equalsIgnoreCase(matchedCustomers.get(0).getScaleType()))){
					if(Boolean.parseBoolean(environment.getProperty("isTempUcic"))){
					customerResultsBean.setSystemAssignedUcic(psxRequest.getAssignedUcicNo().replaceAll("TEMP_", ""));
					}else{
						customerResultsBean.setSystemAssignedUcic(psxRequest.getAssignedUcicNo());	
					}
				}
				else{
					customerResultsBean.setSystemAssignedUcic("0");
				}
				
				logger.info(String.format("NEGATIVE CUSTOMER MATCHES :: %d ",negMatches !=null ?negMatches.size():0));
				logger.info(String.format("CUSTOMER MATCHES :: %d ", matchedCustomers !=null ?matchedCustomers.size():0));
			}
			
			logger.info("Matched Customer Details List Size :::"+ reportMatches.size() + " Time Taken "+ (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			//List<Long> customerId = reportMatches.stream().map(e -> e.getCustomerno() != null ? Long.parseLong(e.getCustomerno()): 0).collect(Collectors.toList());
			List<String> customerId = reportMatches.stream().map(e -> e.getCustomerno() != null ? e.getCustomerno(): "0").collect(Collectors.toList());
			
			//loanMatches = serviceI.getMatchedLoanDetails(customerId);
			loanMatches = serviceI.getMatchedLoanDetails1(customerId);
			
			if (loanMatches != null) {
				matchedLoanDetailsList = buildingJdbcEntities.buildingMatchedLoanDetails(loanMatches);
			}
			logger.info("Matched Loan Details List Size :::"+ loanMatches.size() + " Time Taken "+ (System.currentTimeMillis() - perfTime));
			customerResultsBean.setRequestid(customerRequest.getRequestid());
			customerResultsBean.setStatus(reqStatus);
			customerResultsBean.setResponsecode(environment.getProperty("SUCCESS_CODE"));
			customerResultsBean.setDescription(environment.getProperty("SUCCESS_STATUS"));
			customerResultsBean.setCustmatchcount(psxRequest.getMatchcount());
			customerResultsBean.setNegativematchcount(psxRequest.getNegativeMatchCount());
			customerResultsBean.setNegativeMatchedCustomerDetails(negMatches);
			customerResultsBean.setMatchedCustomerDetails(matchedCustomers);
			customerResultsBean.setMatchedCustomerLoanDetails(matchedLoanDetailsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception has Caught inside getMatches API ");
			reqStatus = "E";
			customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
			customerResultsBean.setRequestid(customerRequest.getRequestid());
			customerResultsBean.setStatus(reqStatus);
			customerResultsBean.setDescription(e.getMessage());
			
			if (e.getMessage().contains("You have an error in your SQL syntax")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			}
			
			if (e.getMessage().contains(" is Mandatory")) {
				customerResultsBean.setResponsecode(environment.getProperty("MISSING_FIELDS_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(e.getMessage());
			}
			
			if (e.getMessage().contains(environment.getProperty("INVALID_REQUEST_ID_V_MSG"))) {
				customerResultsBean.setResponsecode(environment.getProperty("INVALID_REQUEST_ID"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(e.getMessage());
			}
			
			
			if (e.getMessage().contains("Could not connect to broker URL:")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("ACTIVE_ERROR_MSG"));
			}

			if (e.getMessage().contains("Communications link failure:")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("DB_ERROR_MSG"));
			}
			if (e.getMessage().contains("Exception occured")) {
				customerResultsBean.setResponsecode(environment.getProperty("INTERNAL_SERVER_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(environment.getProperty("INTERNAL_EXCEPTON_MSG"));
			}
			if (e.getMessage().contains("Exceeds Length")) {
				customerResultsBean.setResponsecode(environment.getProperty("EXCEEDS_FIELD_LENGTH_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(e.getMessage());
		    }
			
			if (e.getMessage().contains(environment.getProperty("INVALID_SOURCE_SYSTEM_NAME_V_MSG")) || e.getMessage().contains(environment.getProperty("INVALID_SOURCE_AUTHENTICATION_TOKEN_V_MSG"))) {
				customerResultsBean.setResponsecode(environment.getProperty("AUTHENTICATION_FAILURE_CODE"));
				customerResultsBean.setStatus(environment.getProperty("FAILURE_STATUS"));
				customerResultsBean.setDescription(e.getMessage());
			}
			customerResultsBean.setCustmatchcount(psxRequest.getMatchcount());
			customerResultsBean.setMatchedCustomerDetails(matchedCustomers);
			customerResultsBean.setMatchedCustomerLoanDetails(matchedLoanDetailsList);
			
		} finally {
			performanceLogs.put("Total Time Taken for RequestId"+ customerRequest.getRequestid(), System.currentTimeMillis()-m_start_time + "");
			logger.info("Performance HashMap inside getMatches API ::: "+ performanceLogs);
		}
		return customerResultsBean;

	}
}
