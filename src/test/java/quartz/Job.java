package quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by yanfeng-mac on 2017/8/20.
 */
public class Job implements org.quartz.Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(jobExecutionContext.getTrigger().getName() + " trigger time is" + (new Date()));
    }
}
