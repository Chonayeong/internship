package com.trumpia.shortcodetester.quartz;


import java.time.LocalDateTime;


import com.trumpia.shortcodetester.model.Schedule;

public class ScheduleVerification {

	
	private final static String secPattern="(?)|(*)|[0-5]?\\d";
	private final static String minPattern="(?)|(*)|[0-5]?\\d";
	private final static String hourPattern="(?)|(*)|[01]?\\d|2[0-3]";
	private final static String dayOfMonthPattern="(?)|(*)|0?[1-9]|[12]\\d|3[01]";
	private final static String monthPattern="(?)|(*)|[1-9]|1[012]";
	private final static String dayOfWeekPattern="(?)|(*)|[1-7]";
	private final static String yearPattern="(?)|(*)||\\d{4}";
	private final static String phonePattern="\\d{10}";
	private final static String shortcodePattern="\\d{5}";
	
	
	public static boolean isValidSchedule(Schedule schedule) {
		
		if(schedule.getInterval()==0) return checkCronSchedule(schedule);
		else if(schedule.getInterval()>0) return checkSimpleSchedule(schedule);
		else return false;
	}
	
	public static boolean isValidMessageInfo(Schedule schedule) {
		
		//Is phone number valid?
		if(!schedule.getPhoneNumber().matches(phonePattern)) return false;
		
		//Is short code valid?
		if(!String.valueOf(schedule.getShortId()).matches(shortcodePattern)) return false;
		
		return true;
	}
	
	@SuppressWarnings("deprecation")
	private static boolean checkCronSchedule(Schedule schedule) {
		
		LocalDateTime today=LocalDateTime.now();
		LocalDateTime scheduleTime = LocalDateTime.of(Integer.parseInt(schedule.getYear()), Integer.parseInt(schedule.getMonth()), 
				Integer.parseInt(schedule.getDayOfMonth()), Integer.parseInt(schedule.getHour()), 
				Integer.parseInt(schedule.getMinute()), Integer.parseInt(schedule.getSecond()));
	
		//Is schedule expression valid?
		if(!schedule.getSecond().matches(secPattern) || !schedule.getMinute().matches(minPattern)
				|| !schedule.getHour().matches(hourPattern) || !schedule.getDayOfMonth().matches(dayOfMonthPattern)
				|| !schedule.getDayOfWeek().matches(dayOfWeekPattern) || !schedule.getMonth().matches(monthPattern)
				|| !schedule.getYear().matches(yearPattern)) return false;
		
		//Is schedule later than current time?
		if(today.isAfter(scheduleTime)) return false;
		
		return true;
	}
	
	private static boolean checkSimpleSchedule(Schedule schedule) {
		//simple schedule uses only repeatCount and interval
		if(schedule.getRepeatCount()>0) return true;
		
		return false;
	}
	

}
