package common;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Recordset_Arg1;
import job.DaemonCheckJob;

public class CommonData {

		Logger log = LoggerFactory.getLogger(CommonData.class);
		
		public void startJobSchedule(final Scheduler scheduler) {
			try {
				//JobDetail jobDetail = JobBuilder.newJob(DaemonCheckJob.class).withIdentity("","").build();
				JobDetial jobDetail = new JobDetail("messaging job",					//잡의 이름
																		"MESSAGING",					//잡의 그룹이름
																		GetMessageJob.class);		//특정 잡 구현
				CronTrigger trigger = new CronTrigger("MESSAGING","MESSANGING",StaticValues.cronExpression);
				scheduler.scheduleJob(jobDetail, trigger);

			}catch (SchedulerException e) {
				// TODO: handle exception
				Util.printErr(e.getMessage());
				e.printStackTrace();
			}catch (ParseException e2) {
				// TODO: handle exception
				log.error("{}",e2.getMessage());
			}
		}
		
		public void getArgs(String key, Recordset_Arg1 item, HashMap<String, ArrayList<Recordset_Arg1>> map) {
			if(map.get(key) != null) {
				ArrayList<Recordset_Arg1> list = map.get(key);
				list.add(item);
				map.remove(key);
				map.put(key, list);				
			}else {
				ArrayList<Recordset_Arg1> list = new ArrayList<Recordset_Arg1>();
				list.add(item);
				map.put(key, list);
			}
		}
		
		public void getReceiverList(String key, String value, HashMap<String, StringBuffer> map) {
			if(map.get(key) != null) {
				StringBuffer list = map.get(key);
				if(list.indexOf(value)==-1) {
					list.append(value).append(",");
					map.remove(key);
					map.put(key, list);
				}
			}else {
				StringBuffer list = new StringBuffer();
				list.append(value).append(",");
				map.put(key, list);
			}
		}
		
		public void dropScheduler(String groupName) throws SchedulerException {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			String jobNames[] = scheduler.getJobNames(groupName);
			for(int k=0; k<jobNames.length; k++) {
				scheduler.deleteJob(jobNames[k],groupName);
			}
		}
		
		public void startCheckDaemonManager(final Scheduler scheduler) {
			try {
				JobDetail jobDetail = new JobDetail("Daemon Check Job","DMCHECK",DaemonCheckJob.class);
				CronTrigger trigger = new CronTrigger("DM Check","DMCHECK",StaticValues.cronExpression);
				scheduler.rescheduleJob(jobDetail, trigger);
			}catch (SchedulerException e) {
				// TODO: handle exception
				log.error("{}",e.getMessage());
			}catch (ParseException e2) {
				// TODO: handle exception
				log.error("{}",e2.getMessage());
			}
		}
		
		
		
}
