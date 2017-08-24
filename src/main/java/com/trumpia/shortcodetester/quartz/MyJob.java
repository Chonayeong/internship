package com.trumpia.shortcodetester.quartz;

import javax.jms.JMSException;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.trumpia.shortcodetester.ShortcodeTesterApplication;

public class MyJob implements Job {
private static int count;
	
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Job start: " + jobContext.getFireTime());
		count++;
		System.out.println("Job count " + count);		
		System.out.println("Job next scheduled time: " + jobContext.getNextFireTime());
		System.out.println("Job's thread name is: " + Thread.currentThread().getName());
		System.out.println("Job end");
		System.out.println("--------------------------------------------------------------------");


		 JobKey key = jobContext.getJobDetail().getKey();

	     JobDataMap dataMap = jobContext.getJobDetail().getJobDataMap();

	      String phoneNumber = dataMap.getString("phone_number");
	      long shortId=dataMap.getLong("short_id");
	      String messageType=dataMap.getString("message_type");
	      boolean direction=dataMap.getBoolean("direction");
	      System.out.println(direction);
		
			
			 try {
				ShortcodeTesterApplication.sender.send(phoneNumber, "send command",String.valueOf(shortId), direction);
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
         
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
