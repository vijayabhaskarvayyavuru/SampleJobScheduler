package com.octanner;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Execution {

public static void main(String[] args) {

		

		JobDetail job = JobBuilder.newJob(SampleJob.class)
			.withIdentity("My Job", "group1").build();

		Trigger trigger = TriggerBuilder
			.newTrigger()
			.withIdentity("trigger1", "group1")
			.withSchedule(
				SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInSeconds(10).repeatForever())
			.build();

		// schedule it
		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			
			e.printStackTrace();
		}
		

	}
}
