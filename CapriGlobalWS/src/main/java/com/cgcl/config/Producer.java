package com.cgcl.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	private static Logger logger = Logger.getLogger(Producer.class.getName());
	@Autowired
//	@Qualifier("activeMQ")
	JmsTemplate actjmsTemplate;
	
	
	@Autowired
	Environment environment;
	

//	public void sendMessage(final String queueName,final String textMsg) throws JMSException {
//		long time=System.currentTimeMillis();
//		JmsTemplate template=actjmsTemplate;
//		template.send(queueName, new MessageCreator() {
//				public Message createMessage(Session session) throws JMSException {
//					
//					TextMessage textMessage=session.createTextMessage(textMsg);
//					logger.debug("Sending Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
//					return textMessage;
//				}
//			});
//		logger.debug((System.currentTimeMillis()-time)+"Sent Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
//	}
	
	public void sendMessage(final String queueName,final String clientMessage, String requestId) throws JMSException {
		long time=System.currentTimeMillis();
		try {
		JmsTemplate template=actjmsTemplate;
		template.send(queueName, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					
				//	TextMessage textMessage=session.createTextMessage(textMsg);
					Message message = (session).createTextMessage(clientMessage);
					message.setStringProperty(environment.getProperty("JmsStringPropertyKey"), String.valueOf(requestId));
					logger.debug("Sending Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
					return message;
				}
			});
		logger.debug((System.currentTimeMillis()-time)+" Sent Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
		} catch (Exception e) {
			logger.error("Error in sending message to queue");
			throw new RuntimeException(e.getMessage());
		}
		}
}
