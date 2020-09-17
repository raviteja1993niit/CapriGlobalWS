package com.cgcl.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
@PropertySource("classpath:activeMq.properties")
public class JMSConfigurator {
		
	@Autowired
	private Environment env;

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(env.getProperty("spring.activemq.broker.url")); 
	    connectionFactory.setPassword(env.getProperty("spring.activemq.user"));
	    connectionFactory.setUserName(env.getProperty("spring.activemq.password"));	
	    connectionFactory.setUseAsyncSend(true);
		connectionFactory.setTrustAllPackages(true);
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(connectionFactory);
		pooledConnectionFactory.setMaxConnections(Integer.parseInt(env.getProperty("spring.activemq.maxConnections")));
		pooledConnectionFactory.setIdleTimeout(Integer.parseInt(env.getProperty("spring.activemq.idleTimeout")));
		return pooledConnectionFactory;
	}
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setSessionAcknowledgeMode(Integer.parseInt(env.getProperty("spring.activemq.sessionAcknowledgeMode")));
		template.setDeliveryMode(Integer.parseInt(env.getProperty("spring.activemq.deliverymode")));
		return template;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory());
	    factory.setAutoStartup(Boolean.parseBoolean(env.getProperty("isListenerAutoStartEnable")));
	    factory.setConcurrency(env.getProperty("spring.activemq.concurrent"));
	    return factory;
	}
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
