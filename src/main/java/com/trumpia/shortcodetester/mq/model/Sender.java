package com.trumpia.shortcodetester.mq.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

public class Sender {


	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
	//private Queue queue;
	//BrokerService BROKER = null;
	private Connection connection = null;
	//private Connection connection;
    private Session session;
    private MessageProducer producer;
    
	public Sender() throws  URISyntaxException, Exception {
		
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("customQueue");
			//String payload = "Important Task";
			//Message message = session.createTextMessage(payload);
			producer = session.createProducer(destination);
			//System.out.println("Sending text '" + payload + "'");
			//producer.send(message);
		
	}
	
 public void send(String phoneNum, String body, String short_id, boolean direction) throws JMSException {
		 
		 JsonObject value = Json.createObjectBuilder()
				 	.add("phone_number", phoneNum)
					.add("message",body)
					.add("short_id",short_id)
					.add("direction", direction)
					.build();
	       // String text = phoneNum + " " + body;
	    	//String text=new String("send command");
	        // create a JMS TextMessage
	        TextMessage textMessage = session.createTextMessage(value.toString());
	      
	        // send the message to the topic destination
	        producer.send(textMessage);
	      
	        System.out.println( ": sent message with text= " + value.toString());
	        //LOGGER.debug(clientid + ": sent message with text='{}'", text);
	    }
	 
	
 public void send(String phoneNum, String body) throws JMSException {
		 
		 JsonObject value = Json.createObjectBuilder()
				 	.add("phone_number", phoneNum)
					.add("message", "send command")
					.build();
	       // String text = phoneNum + " " + body;
	    	//String text=new String("send command");
	        // create a JMS TextMessage
	        TextMessage textMessage = session.createTextMessage(value.toString());
	       
	        // send the message to the topic destination
	        producer.send(textMessage);
	      
	        System.out.println( ": sent message with text= " + value.toString());
	        //LOGGER.debug(clientid + ": sent message with text='{}'", text);
	    }
	 

}
