package com.trumpia.shortcodetester.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class QuartzScheduler {
	//private int repeatCount = 3;

public void QuartzSchedulereConfigureExample() {
		
	}
	

	public Scheduler createAndStartScheduler() throws SchedulerException {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler scheduler = schedFact.getScheduler();
		System.out
				.println("Scheduler name is: " + scheduler.getSchedulerName());
		System.out.println("Scheduler instance ID is: "
				+ scheduler.getSchedulerInstanceId());
		System.out.println("Scheduler context's value for key QuartzTopic is "
				+ scheduler.getContext().getString("QuartzTopic"));
		scheduler.start();
		return scheduler;
	}

	public <T extends Job> void jobPeriodically(Scheduler scheduler, Class<T> jobClass, int interval, int repeatCount,  String phone_number, long short_id, String message_type, boolean direction)
			throws SchedulerException, InterruptedException {

		// define the job and tie it to our HelloJob class
		JobBuilder jobBuilder = JobBuilder.newJob(jobClass);
		JobDataMap data = new JobDataMap();
		data.put("phone_number", phone_number);
		data.put("short_id", short_id);
		data.put("message_type", message_type);
		data.put("direction", direction);

		JobDetail jobDetail = jobBuilder
				.usingJobData("example",
						"com.javacodegeeks.quartz.QuartzSchedulerExample")
				.usingJobData(data).build();
		

		// Trigger the job to run now, and then every 40 seconds
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.startNow()
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withRepeatCount(repeatCount)
								.withIntervalInSeconds(interval))
				.withDescription("MyTrigger").build();

		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(jobDetail, trigger);
	}

	public <T extends Job> void jobAtSpecificTime(Scheduler scheduler, Class<T> jobClass, String cronScheduleStr, String phone_number, long short_id, String message_type, boolean direction)
			throws SchedulerException, InterruptedException {

		System.out.println("jobAtSpecificTime "+cronScheduleStr);
		//cronScheduleStr="0 0 9 * * ?";
		// define the job and tie it to our HelloJob class
		JobBuilder jobBuilder = JobBuilder.newJob(jobClass);
		JobDataMap data = new JobDataMap();
		data.put("phone_number", phone_number);
		data.put("short_id", short_id);
		data.put("message_type", message_type);
		data.put("direction", direction);
		
		JobDetail jobDetail = jobBuilder
				.usingJobData("example",
						"com.javacodegeeks.quartz.QuartzSchedulerExample")
				.usingJobData(data).build();

		// Trigger the job to run now, and then every 40 seconds
		Trigger trigger = TriggerBuilder
					.newTrigger()
					.startNow()
				    .withSchedule(CronScheduleBuilder.cronSchedule(cronScheduleStr))
				    .withDescription("MyTrigger")
				    .build();
		
		// Tell quartz to schedule the job using our trigger
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
