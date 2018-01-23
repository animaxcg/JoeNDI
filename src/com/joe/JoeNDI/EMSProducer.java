package com.joe.JoeNDI;

import java.util.Vector;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
 
import com.tibco.tibjms.TibjmsQueueConnectionFactory;
 
public class EMSProducer {
 
	public static void main(String args[]) {
 
		String serverUrl = "tcp://172.16.223.133:7222";
		String userName = "";
		String password = "";
 
		String queueName = "JOE.TEST";
 
		try {
 
			Vector<Object> data = new Vector<Object>();
			data.add("Hello World!");
 
			System.out.println("Sending JMS message to server " + serverUrl + "...");
 
			QueueConnectionFactory factory = new TibjmsQueueConnectionFactory(serverUrl);
			QueueConnection connection = factory.createQueueConnection(userName, password);
			QueueSession session = connection.createQueueSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
 
			// Use createQueue() to enable sending into dynamic queues.
			Queue senderQueue = session.createQueue(queueName);
			QueueSender sender = session.createSender(senderQueue);
 
			/* publish messages */
			for (int i = 0; i < data.size(); i++) {
				TextMessage jmsMessage = session.createTextMessage();
				String text = (String) data.elementAt(i);
				jmsMessage.setText(text);
				sender.send(jmsMessage);
				System.out.println("Sent message: " + text);
			}
 
			connection.close();
 
		} catch (JMSException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
 
}