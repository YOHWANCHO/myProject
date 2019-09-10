package job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonCheckJob implements Job{
	
	private static Logger logger = LoggerFactory.getLogger(DaemonCheckJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}

}
