package com.satish.config;

import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQTopicConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.satish.listener.PrintListener;

@Configuration
public class JmsConfig {
	
	@Value("${servers.mq.host}")
	private String host;
	@Value("${servers.mq.port}")
	private Integer port;
	@Value("${servers.mq.queue-manager}")
	private String queueManager;
	@Value("${servers.mq.channel}")
	private String channel;
	@Value("${servers.mq.queue}")
	private String queue;
	@Value("${servers.mq.topic}")
	private String topic;
	@Value("${servers.mq.timeout}")
	private long timeout;
	
	@Bean
	@Primary
	public MQQueueConnectionFactory mqQueueConnectionFactory() {
		MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
		try {	
			mqQueueConnectionFactory.setHostName(host);
			mqQueueConnectionFactory.setQueueManager(queueManager);
			mqQueueConnectionFactory.setPort(port);
			mqQueueConnectionFactory.setChannel(channel);
			
			System.out.println("user::;;"+System.getProperty("user.name"));
			mqQueueConnectionFactory.setAppName(System.getProperty("user.name"));
			mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
			mqQueueConnectionFactory.setCCSID(1208);					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mqQueueConnectionFactory;
	}
	
	@Bean
	public SimpleMessageListenerContainer queueContainer(MQQueueConnectionFactory mqQueueConnectionFactory) {
		MessageListener listener = new PrintListener();
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(mqQueueConnectionFactory);
		container.setDestinationName(queue);
		container.setMessageListener(listener);
		container.start();
		return container;
	}	
	

	
	@Bean
	public JmsTemplate queueTemplate(MQQueueConnectionFactory mqQueueConnectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(mqQueueConnectionFactory);
		jmsTemplate.setReceiveTimeout(timeout);
		return jmsTemplate;
	}
	

}
