package com.trumpia.shortcodetester.views;

import java.io.IOException;
import java.util.Date;

import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.trumpia.shortcodetester.data.FullMessageRepository;

import com.trumpia.shortcodetester.inbound.ReadXML;

import com.trumpia.shortcodetester.model.FullMessage;

@Controller
public class InboundController {
	@Autowired 
	FullMessageRepository  fullMessageRepository;

	
	@RequestMapping(path = "/inbound", method = RequestMethod.GET)
	@ResponseBody
	public String parsingMessage(@RequestParam String xml) {
		 try {
			
			Map<String,String> xmlMap = new ReadXML().Read(xml);
			long id = Long.parseLong(xmlMap.get("CONTENTS"));
			 
			FullMessage fullMessage = fullMessageRepository.findOne(id);
		
			fullMessage.setTestEndTime(new Date());
			fullMessageRepository.save(fullMessage);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return xml;
	}
}
