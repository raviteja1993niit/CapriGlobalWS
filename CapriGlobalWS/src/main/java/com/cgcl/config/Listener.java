package com.cgcl.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.cgcl.beans.MatchedAddressStrengths;
import com.cgcl.exception.PosidexException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.*;


@Component
public class Listener {

	private static Logger logger = Logger.getLogger(Listener.class.getName());
	
	@Autowired
	private Environment environment;
	@Autowired
	private JmsTemplate jmsTemplate;

	private ConcurrentMap<String, Object[]> pendingResponses = new ConcurrentHashMap<>();

//	@JmsListener(destination = "CGCL_PSX_PM_RESP_QUEUE", containerFactory = "jmsListenerContainerFactory")
	@JmsListener(destination = "${spring.activemq.queue.name}", containerFactory = "jmsListenerContainerFactory")
	public synchronized void onMessageInAQJMS(Message message) {
		try {
			logger.info("Inside Listener");
			String messageContent = ((TextMessage) message).getText();
			processMessage(messageContent);
		} catch (JMSException e) {
			logger.error(e, e);
		}

	}

	public Object receiveMessage(String requestId) throws PosidexException {
		Object receivedMessage = null;
		try {
			jmsTemplate.setReceiveTimeout(Integer.parseInt(environment.getProperty("jmsrecievetimeout")));
			receivedMessage = jmsTemplate.receiveSelected(environment.getProperty("primeMatchResponseQ"),environment.getProperty("JmsStringPropertyKey")+"='" + requestId + "'");
		//	receivedMessage = jmsTemplate.receive(environment.getProperty("primeMatchResponseQ"));
		} catch (Exception e) {
			logger.error("Got exception while reciving message from queue" + e.getMessage());
			throw new PosidexException("Got exception while reciving message from queue");
		}
		logger.info("Timestamp After Recieved message::" + new Timestamp(System.currentTimeMillis()) + " for the Request_ID :::  "+requestId);
	//	logger.info(String.format("Timestamp After Recieved message %s for the Request_ID %s" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Timestamp(System.currentTimeMillis())),requestId));
		return receivedMessage;

	}
	
	public List<String> recieveMsgStatus(String requestId) {
		TextMessage receviedMessage = null;
		String messageContent = "";
		String requestStatus ="";
		List<String> recievedMsgStatus=new ArrayList<>();
		try {
				receviedMessage = (TextMessage)receiveMessage(requestId);
				if (receviedMessage != null) {
					messageContent = receviedMessage.getText();
					JsonObject jsonObject = new JsonParser().parse(messageContent).getAsJsonObject();
					requestStatus = jsonObject.get("requestStatus").getAsString();
					recievedMsgStatus.add(requestStatus);
					logger.info("Json Recieved Msg is :: " + messageContent);
					messageContent= messageContent!=null ? messageContent.replace("\\", ""): "";
					recievedMsgStatus.add(messageContent);
					logger.info("Request Status :: "+requestStatus+ " for the Request_Id  ::: "+requestId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error while receiving event message" + e.getMessage());
		}
		return recievedMsgStatus;
	}

		
	private void processMessage(String messageContent) {
		try {
			
		//	String requestId = messageContent.substring(messageContent.indexOf("requestID") + 12,messageContent.indexOf("processType") - 3);
			JsonObject jsonObject = new JsonParser().parse(messageContent).getAsJsonObject();
			String requestId = jsonObject.get("requestID").getAsString();
			logger.info("Inside Recieving the Message for the RequestID " +requestId);
			Object[] c = pendingResponses.get(requestId);
			if (c != null) {
				if (!(messageContent.indexOf("intraDayProcess") >= 0)) {
					((CountDownLatch) c[0]).countDown();
					for (int i = 1; i < c.length; i++) {
						if (c[i] == null) {
							c[i] = System.currentTimeMillis();
							break;
						}
					}
					if (logger.isDebugEnabled()) {
						logger.debug(this + ":" + "countDowned:" + requestId);
					}
					if (((CountDownLatch) c[0]).getCount() == 0) {// bcz we have two nodes
						pendingResponses.remove(requestId);
						if (logger.isDebugEnabled()) {
							logger.debug(this + ":" + "removed:" + requestId);
						}
					}
				}
			} else {
				if (logger.isDebugEnabled()) {
					// logger.debug("There is no entry in the map with the request ID:"+requestId+"
					// and the message is:"+messageContent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e, e);
		}
	}

	public void addPendingRequest(String tokenName, Object[] values) {
		Object obj = this.pendingResponses.put(tokenName, values);
		if (obj != null) {// To check duplicate request ids.
			logger.info(this + ":" + values[0] + " request aleardy exists.");
		}
	}

	public boolean isStillProcessing(String requestID) {
		return pendingResponses.get(requestID) != null;
	}
	

	public String recieveMsgStatus1(String requestId) {
		TextMessage receviedMessage = null;
		String messageContent = "";
		String requestStatus ="";
		
		try {
				receviedMessage = (TextMessage)receiveMessage(requestId);
				if (receviedMessage != null) {
					messageContent = receviedMessage.getText();
					JsonObject jsonObject = new JsonParser().parse(messageContent).getAsJsonObject();
					requestStatus = jsonObject.get("requestStatus").getAsString();
					logger.info("Json Recieved Msg is :: " + messageContent);
					logger.info("Request Status :: "+requestStatus+ " for the Request_Id  ::: "+requestId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error while receiving event message" + e.getMessage());
		}
		return requestStatus;
	}
}