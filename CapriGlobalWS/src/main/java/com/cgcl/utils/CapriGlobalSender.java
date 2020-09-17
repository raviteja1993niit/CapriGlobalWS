package com.cgcl.utils;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cgcl.config.Producer;


@Component
public class CapriGlobalSender {
	
	private static Logger logger = Logger.getLogger(CapriGlobalSender.class.getName());
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private Environment environment;
	
	public void sendMessageForUcicIntraday(long reqPsxid,HashMap<String, ArrayList<HashMap<String, Object>>> entry) throws JSONException,
			JMSException {
		try{
	    String sourceSystemName1 = environment.getProperty("SOURCE_SYSTEM_NAME1");
		logger.info("IntraDay  RequestBean.....");
		JSONObject json = new JSONObject();
		json.put("requestID", reqPsxid);
		json.put("requestInformation", entry);
		// json.put("matchingRule", matching_rule);
		json.put("requestStatus", "P");
		json.put("sourceSystemName", sourceSystemName1);
	//	json.put("rankingOrders", rankingOrders);
		json.put("processType", "intraDayProcess");
		logger.info("Message is " + json.toString());
		String message = json.toString();
	    producer.sendMessage(environment.getProperty("PRIME_MATCH_QUEUE"), message,String.valueOf(reqPsxid));
		}catch(Exception e){
			e.getMessage();
		}

	}
}
