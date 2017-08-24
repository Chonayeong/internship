package com.trumpia.shortcodetester.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


import com.trumpia.shortcodetester.ShortcodeTesterApplication;

import com.trumpia.shortcodetester.data.FullMessageRepository;

import com.trumpia.shortcodetester.inbound.ConnectionSupport;
import com.trumpia.shortcodetester.inbound.RepositorySupport;

import com.trumpia.shortcodetester.model.FullMessage;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Controller
public class WebsocketController { 
	JSONParser jsonParser = new JSONParser();

	@Autowired
	SimpMessagingTemplate simpTemplate;
	
	@Autowired
	FullMessageRepository fullMessageRepository;
	
	ConnectionSupport controller;
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

	
	@RequestMapping("/start")
	public String showIndex() throws JMSException {
		return "websocketExample";
	}

	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public String respond(String message) throws Exception {
		  
		System.out.println(message);
    	long date = new Date().getTime();
    	FullMessage fullMessage;
    	
    	JSONParser parser = new JSONParser();
    	JSONObject jsonObject = (JSONObject) parser.parse(message);
    	jsonObject.remove("message");
  
    	long message_id = Long.parseLong(jsonObject.get("id").toString());
    	
    	
    	fullMessage = fullMessageRepository.findOne(message_id);
    	
    	
    	fullMessage.setAndMessageSendTime(new Date(Long.parseLong(jsonObject.get("and_repsend_time").toString())));
    	fullMessage.setRequestRecieveTime(new Date(Long.parseLong(jsonObject.get("and_request_recieve_time").toString())));
    	fullMessageRepository.save(fullMessage);
    	
    	return jsonObject.toString();
		
	}

	
		

	
	@MessageMapping(value="/update/reciveMessage")
	@SendTo("/topic/chat")
	public String updateMessage(String message) throws JSONException, ParseException, java.text.ParseException {
		long id;
		Object obj = jsonParser.parse(message);
		JSONObject json = (JSONObject) obj;
		System.out.println(json.get("id"));
		id = Long.parseLong(json.get("id").toString());
		Date message_receive_time = new Date(Long.parseLong(json.get("message_recieve_time").toString()));
		Date request_send_time = new Date();
		
		
		FullMessage fullMessage = fullMessageRepository.findOne(id);
	
    	
		fullMessage.setAndMessageRecieveTime(message_receive_time);	
		fullMessage.setRequestSendTime(request_send_time);
		fullMessageRepository.save(fullMessage);
		
		JSONObject object = new JSONObject();
		object.put("phone_number", fullMessage.getPhoneNumber());
		object.put("message", "send command");
		object.put("id", fullMessage.getId());
		return object.toString();
		}
	
	

	
	@MessageMapping("/topic/stock")
	public void sendToQueue(String message) throws JMSException, ParseException {
		
		System.out.println(message);
		JSONParser parser = new JSONParser();
    	JSONObject jsonObject = (JSONObject) parser.parse(message);
    	System.out.println(jsonObject.toString());
    	String phoneNum=jsonObject.get("phone_number").toString();
    	String body=jsonObject.get("message").toString();
    	String short_id=jsonObject.get("short_id").toString();
    	
    	if(jsonObject.get("direction").equals("0")) {
    		ShortcodeTesterApplication.sender.send(phoneNum, body, short_id, false);
		}else {
			ShortcodeTesterApplication.sender.send(phoneNum, body, short_id, true);
		}
	}
	
	

}