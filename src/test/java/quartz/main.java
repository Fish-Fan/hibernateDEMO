package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by yanfeng-mac on 2017/8/20.
 */
public class main {
    public static void main(String[] args) {
        JobDetail jobDetail = new JobDetail("job_1","job_group1",Job.class);



        //使用SimpleTrigger触发任务
//        SimpleTrigger simpleTrigger = new SimpleTrigger("job_1","job.group1");
//        simpleTrigger.setStartTime(new Date());
//        simpleTrigger.setRepeatCount(10);
//        simpleTrigger.setRepeatInterval(1000);




        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            //使用CronTrigger触发任务
            CronTrigger cronTrigger = new CronTrigger("job_1","job_group1","0-5 * * * * ?");


            Scheduler scheduler = schedulerFactory.getScheduler();

            scheduler.scheduleJob(jobDetail,cronTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
