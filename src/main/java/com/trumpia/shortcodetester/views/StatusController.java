package com.trumpia.shortcodetester.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trumpia.shortcodetester.data.FullMessageRepository;

@Controller
public class StatusController {

	@Autowired
	FullMessageRepository fullMessageRepository;
	
	@RequestMapping(value ="/data", method = RequestMethod.GET)
	public String showData(Model model) {
		
		model.addAttribute("messages",fullMessageRepository.findAll());
		return "webRepository";
	}
	
	@RequestMapping(value="/messages", method=RequestMethod.GET) 
	@ResponseBody
	public String getMessages() {
		System.out.println("messages");
		

		return String.format("There are %d%n messages in the database.",fullMessageRepository.count());
	}
}
