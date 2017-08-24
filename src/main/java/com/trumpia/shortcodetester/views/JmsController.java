package com.trumpia.shortcodetester.views;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.trumpia.shortcodetester.data.FullMessageRepository;
import com.trumpia.shortcodetester.inbound.ConnectionSupport;
import com.trumpia.shortcodetester.model.FullMessage;
import com.trumpia.shortcodetester.model.APIMessagePayload;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Controller
public class JmsController {

	@Autowired
	SimpMessagingTemplate simpTemplate;

	@Autowired
	FullMessageRepository fullMessageRepository;

	@Autowired
	ConnectionSupport controller;

	@JmsListener(destination = "customQueue")
	public void sendCommand(String message) throws Exception{

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(message);
		System.out.println("direction = "+jsonObject.get("direction"));

		if((boolean) jsonObject.get("direction")) {

			com.trumpia.shortcodetester.model.APIMessagePayload subscription = new com.trumpia.shortcodetester.model.APIMessagePayload();

			Date message_send_time = new Date();
			FullMessage fullMessage = new FullMessage();
			fullMessageRepository.save(fullMessage);
			long message_id = fullMessage.getId();
			
			fullMessage.setMessage(subscription.getMessage());
			fullMessage.setPhoneNumber(subscription.getMobileNumber());
			fullMessage.setTestStartTime(message_send_time);
			fullMessageRepository.save(fullMessage);
			
			subscription.setUniqueID(message_id);
			Date time = controller.SetConnection(subscription);
			
			while(time == null) {
				time = controller.SetConnection(subscription);
					continue;
			}
			
			
			fullMessage.setWebMessageSendTime(time);			
			fullMessageRepository.save(fullMessage);


		}else {
			Date test_start_time=new Date();
			FullMessage fullMessage=new FullMessage();
			fullMessageRepository.save(fullMessage);
			long id=fullMessage.getId();
			fullMessage.setTestStartTime(test_start_time);
			jsonObject.put("id", id);
			fullMessage.setRequestSendTime(new Date());
			fullMessageRepository.save(fullMessage);

			simpTemplate.convertAndSend("/topic/chat", jsonObject.toString());
		}
	}
}

