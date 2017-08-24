package com.trumpia.shortcodetester.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="full_message")
public class FullMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//message = unique_id + date
	private String message;
	
	private Date testStartTime;
	private Date webMessageSendTime;
	private String messageType="";
	private String phoneNumber="";
	
	private Date andMessageRecieveTime;
	private Date requestSendTime;
	private Date requestRecieveTime;
	private Date andMessageSendTime;
	private Date testEndTime;
	
	public FullMessage() {

		setMessageType("sms");
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTestStartTime() {
		return testStartTime;
	}
	public void setTestStartTime(Date testStartTime) {
		this.testStartTime = testStartTime;
	}
	public Date getWebMessageSendTime() {
		return webMessageSendTime;
	}
	public void setWebMessageSendTime(Date webMessageSendTime) {
		this.webMessageSendTime = webMessageSendTime;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getAndMessageRecieveTime() {
		return andMessageRecieveTime;
	}
	public void setAndMessageRecieveTime(Date andMessageRecieveTime) {
		this.andMessageRecieveTime = andMessageRecieveTime;
	}
	public Date getRequestSendTime() {
		return requestSendTime;
	}
	public void setRequestSendTime(Date requestSendTime) {
		this.requestSendTime = requestSendTime;
	}
	public Date getRequestRecieveTime() {
		return requestRecieveTime;
	}
	public void setRequestRecieveTime(Date requestRecieveTime) {
		this.requestRecieveTime = requestRecieveTime;
	}
	public Date getAndMessageSendTime() {
		return andMessageSendTime;
	}
	public void setAndMessageSendTime(Date andMessageSendTime) {
		this.andMessageSendTime = andMessageSendTime;
	}
	public Date getTestEndTime() {
		return testEndTime;
	}
	public void setTestEndTime(Date testEndTime) {
		this.testEndTime = testEndTime;
	}
	
	
}
