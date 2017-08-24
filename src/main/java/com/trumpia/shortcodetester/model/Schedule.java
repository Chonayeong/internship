package com.trumpia.shortcodetester.model;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="e_schedule")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int interval; 
	private int repeatCount; 
	//start time
	private String second; //0~59
	private String minute; //0~59
	private String hour; //0~23
	private String dayOfMonth; //1~31 
	private String month; //1~12
	private String dayOfWeek; //1~7 ( 1 means monday, 7 means sunday)
	private String year; //1970~2099
	//second, minute, ..., year can be "?" or "*". "?" means don't care, "*" means every.
	
	//if interval==0, then use cronSchedule, else use simpleSchedule
	private String phoneNumber;
	private long shortId;
	private String messageType;
	private boolean Direction;
	
	public Schedule() {
		
		interval = 5;
		repeatCount = 5;
		second="30";
		minute="16";
		hour="12";
		dayOfMonth="14";
		month="8";
		dayOfWeek="0";
		year="2017";
		Direction=false;
		setPhoneNumber(new String("4043243857"));
		shortId=90762;
		messageType=new String("sms");
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		if(interval<0) this.repeatCount=0;
		else this.interval = interval;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		if(repeatCount<0) this.repeatCount=0;
		else this.repeatCount = repeatCount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {

		this.second = second;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {

		this.minute = minute;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {

		this.hour = hour;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {

		this.dayOfMonth = dayOfMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {

		this.month = month;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {

		this.dayOfWeek = dayOfWeek;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		@SuppressWarnings("deprecation")
		int thisYear=new Date().getYear();
		
		
		if(Integer.parseInt(year)<thisYear || Integer.parseInt(year)>2099) year=String.valueOf(thisYear);
		this.year = year;
	}



	public boolean isDirection() {
		return Direction;
	}

	public void setDirection(boolean direction) {
		Direction = direction;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getShortId() {
		return shortId;
	}

	public void setShortId(long shortId) {
		this.shortId = shortId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}


	
}
