package com.trumpia.shortcodetester;

import java.util.Date;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.mail.internet.MimeMessage;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.SchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.trumpia.shortcodetester.data.ScheduledataRepoitory;
import com.trumpia.shortcodetester.model.Mail;
import com.trumpia.shortcodetester.model.MailService;
import com.trumpia.shortcodetester.model.MessageContext;

import com.trumpia.shortcodetester.model.Schedule;
import com.trumpia.shortcodetester.mq.model.Consumer;
import com.trumpia.shortcodetester.mq.model.Sender;
import com.trumpia.shortcodetester.views.ScheduleController;

@SpringBootApplication
@EnableJms
@ComponentScan({"com.trumpia"})
@EnableJpaRepositories("com.trumpia")
@EntityScan("com.trumpia")
public class ShortcodeTesterApplication {
	public static SimpMessagingTemplate simpTemplate;
	public static Sender sender;
	public static Consumer consumer;
	@Autowired
	private static JavaMailSender mailSender;

	public static void main(String[] args)  throws Exception {
		SpringApplication.run(ShortcodeTesterApplication.class, args);
		
		consumer = new Consumer();
		sender=new Sender();
		consumer.create();
		
	
	}


}
