package com.trumpia.shortcodetester.config;
import org.apache.activemq.ActiveMQConnectionFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
@ConfigurationProperties(prefix="spring.activemq")
@ComponentScan(basePackages = "com.trumpia.shortcodetester")
public class JmsConfig {
 
 @Bean
 public ActiveMQConnectionFactory connectionFactory(){
     ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
     return connectionFactory;
 }

 @Bean
 public JmsTemplate jmsTemplate(){
     JmsTemplate template = new JmsTemplate();
     template.setConnectionFactory(connectionFactory());
     return template;
 }

 @Bean
 public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
     DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
     factory.setConnectionFactory(connectionFactory());
     factory.setConcurrency("1-1");
     return factory;
 }

}