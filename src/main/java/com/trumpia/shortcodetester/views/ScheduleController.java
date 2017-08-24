package com.trumpia.shortcodetester.views;

import java.util.Iterator;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trumpia.shortcodetester.data.ScheduledataRepoitory;
import com.trumpia.shortcodetester.quartz.MyJob;
import com.trumpia.shortcodetester.quartz.QuartzScheduler;
import com.trumpia.shortcodetester.quartz.ScheduleGenerator;

//import scala.annotation.meta.setter;

import com.trumpia.shortcodetester.model.Schedule;

@Controller
public class ScheduleController {
	
	private ScheduleGenerator scheduleGenerator=new ScheduleGenerator();
	@Autowired
	private ScheduledataRepoitory schedulRepoitory;
	
	static QuartzScheduler quartzScheduler = new QuartzScheduler();
	
	public ScheduleController(ScheduledataRepoitory scheduledataRepoitory) {
		//schedulRepoitory.save(new Schedule());
		this.schedulRepoitory = scheduledataRepoitory;
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/create/schedule", method= RequestMethod.GET)
	public String createSchedule(Model model) {
		return "webSchedule";
	}

	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	@ResponseBody
	public String haveJob() throws SchedulerException {
		Iterator<Schedule> schedules;
		Schedule schedule;
		Scheduler scheduler = quartzScheduler.createAndStartScheduler();
		
		if(schedulRepoitory.count() !=0 ) {
			schedules = schedulRepoitory.findAll().iterator();
			while(schedules.hasNext()) {
				//schedule = schedulRepoitory.findAll().iterator().next();
				schedule = schedules.next();
				System.out.println(schedule.getInterval());
				
				try {
					
					if(schedule.getInterval()==0){ 
					// use cronSchedule
						String cronSchedule=scheduleGenerator.getSchedule(schedule.getSecond(),schedule.getMinute(), schedule.getHour(), 
								schedule.getDayOfMonth(), schedule.getMonth(), schedule.getDayOfWeek(), schedule.getYear());
		
								quartzScheduler.jobAtSpecificTime(scheduler, MyJob.class, cronSchedule, schedule.getPhoneNumber(), schedule.getShortId(),schedule.getMessageType(), schedule.isDirection());
						
					}else {
					// use simpleSchedule
								quartzScheduler.jobPeriodically(scheduler, MyJob.class, schedule.getInterval(), schedule.getRepeatCount(),schedule.getPhoneNumber(), schedule.getShortId(),schedule.getMessageType(), schedule.isDirection());
							
							
					}
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			return String.format("There are %d%n messages in the database.",schedulRepoitory.count());
		}
		else {
			schedule = new Schedule();
			schedule.setDirection(true);
			System.out.println(schedule.getId());
			System.out.println(schedulRepoitory);
			schedulRepoitory.save(schedule);
			return String.format("There are %d%n messages in the database.",schedulRepoitory.count());
			
		}
	}
}
