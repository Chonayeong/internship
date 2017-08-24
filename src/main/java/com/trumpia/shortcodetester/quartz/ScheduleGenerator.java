package com.trumpia.shortcodetester.quartz;

public class ScheduleGenerator {
	

	
	public ScheduleGenerator() {
		
	}
	
	public String getSchedule(String second, String minute, String hour, String dayOfMonth, String month,
			String dayOfWeek, String year) {
		// TODO Auto-generated method stub
		System.out.println("Schedule Generator");
		//String schedule=new String(second+" "+minute+" "+hour+" "+dayOfMonth+" "+month+" "+dayOfWeek+" "+year);
		String schedule = String.format("%s %s %s %s %s %s %s", second, minute, hour, dayOfMonth, month, dayOfWeek, year);
	
		System.out.println(schedule);
		return schedule;
	}
}
