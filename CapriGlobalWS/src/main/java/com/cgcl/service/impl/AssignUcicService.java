package com.cgcl.service.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cgcl.beans.CustomerRequestInformation;
import com.cgcl.service.CapriGlobalJdbcService;
import com.cgcl.utils.CapriGlobalSender;

@Component
public class AssignUcicService {
	
	private static Logger logger = Logger.getLogger(AssignUcicService.class.getName());

	@Autowired
	private CapriGlobalJdbcService serviceI;

	@Autowired
	private CapriGlobalSender capriGlobalSender;
	
	@Autowired
	private Environment environment;
	
	public String  assignUcicNumber(String ucicId, int totalMatchCount,CustomerRequestInformation customerRequest,Date timestamp,String psxBatchId,String ucicType,boolean isTempUcic,String custUnqId) {
		
		try{
			logger.info("Inside Total Match Count 0 ::: "+totalMatchCount);
			if(totalMatchCount == 0 || ucicType.equalsIgnoreCase("NEW")){
			 ucicId =serviceI.generateSequenceId(environment.getProperty("UcicIdGeneratedSequence"))+"";
			 logger.info("Inside Assign UCIC for UcicType New ::: "+ucicId);
			customerRequest.setUcic(ucicId);
			customerRequest.setUcicType("NEW");
			serviceI.saveDemographicIntraday(customerRequest,timestamp,psxBatchId,isTempUcic,custUnqId);
			serviceI.saveAddressIntraday(customerRequest,timestamp,psxBatchId,custUnqId);
			}
			if(totalMatchCount == 1 || ucicType.equalsIgnoreCase("EXISTING")){
				customerRequest.setUcic(ucicId);
				customerRequest.setUcicType("EXISTING");
				serviceI.saveDemographicIntraday(customerRequest,timestamp,psxBatchId,isTempUcic,custUnqId);
				serviceI.saveAddressIntraday(customerRequest,timestamp,psxBatchId,custUnqId);
				}
		}catch(Exception e){
			logger.error(e,e);
		}
		
		return ucicId;
		
	}
}
