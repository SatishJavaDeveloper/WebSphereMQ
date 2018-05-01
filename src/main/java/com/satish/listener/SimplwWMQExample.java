package com.satish.listener;


import javax.jms.Connection;
import javax.jms.JMSException;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQTopicConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class SimplwWMQExample {

	public static void main(String[] args) throws JMSException {

		/*MQQueueConnectionFactory qcf = new MQQueueConnectionFactory();

		// Host and port settings have their usual meanings
		qcf.setHostName ("localhost");
		qcf.setPort (1414);

		// Queue manager and channel — the W-MQ administrator should
		//  supply these
		qcf.setQueueManager ("test23");
		qcf.setChannel ("SYSTEM.DEF.SVRCONN");

		// Although there are many possible values of transport type,
		//  only 'client' and 'bindings' work in a Java client. Bindings
		//  is a kind of in-memory transport and only works when the client
		//  and the queue manager are on the same physical host. In most
		//  cases we need 'client'. 
		qcf.setTransportType (WMQConstants.RTT_DIRECT_AUTH_DEFAULT);
		qcf.s

QueueConnection qc = qcf.createQueueConnection ();
		qc.start();*/
	  
		MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
		mqQueueConnectionFactory.setHostName("127.0.0.1");
		mqQueueConnectionFactory.setQueueManager("test");
		mqQueueConnectionFactory.setPort(1415);
		mqQueueConnectionFactory.setChannel("test");
		mqQueueConnectionFactory.setAppName("Satish");
		mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
		mqQueueConnectionFactory.setCCSID(1208);
		Connection c=mqQueueConnectionFactory.createConnection("Satish","");
		c.start();
	}

}
