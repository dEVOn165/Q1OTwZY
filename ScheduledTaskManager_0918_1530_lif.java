// 代码生成时间: 2025-09-18 15:30:10
import org.apache.ibatis.session.SqlSession;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ScheduledTaskManager {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskManager.class);
    private static final String SCHEDULER_PROPERTIES = "scheduler.properties";
    private Scheduler scheduler;
    private Properties schedulerProperties;

    public ScheduledTaskManager() {
        try {
            schedulerProperties = new Properties();
            schedulerProperties.load(getClass().getClassLoader().getResourceAsStream(SCHEDULER_PROPERTIES));
        } catch (IOException e) {
            logger.error("Failed to load scheduler properties", e);
        }
        initScheduler();
    }

    private void initScheduler() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("Failed to start scheduler", e);
        }
    }

    public void scheduleTask(Trigger trigger, JobDetail jobDetail) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("Task scheduled successfully");
        } catch (SchedulerException e) {
            logger.error("Failed to schedule task", e);
        }
    }

    public void shutdownScheduler() {
        try {
            scheduler.shutdown();
            logger.info("Scheduler shut down successfully");
        } catch (SchedulerException e) {
            logger.error("Failed to shut down scheduler", e);
        }
    }

    public static void main(String[] args) {
        ScheduledTaskManager manager = new ScheduledTaskManager();
        // Define job and trigger
        JobDetail job = JobBuilder.newJob(MyBatisJob.class)
                .withIdentity("myBatisJob", "group1")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .build();

        // Schedule job
        manager.scheduleTask(trigger, job);

        // Shutdown scheduler when done
        Runtime.getRuntime().addShutdownHook(new Thread(manager::shutdownScheduler));
    }
}

// Define a job that will be executed
public class MyBatisJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            // Perform database operations using MyBatis
            logger.info("Executing MyBatis job");
        } catch (Exception e) {
            logger.error("Error executing MyBatis job", e);
        }
    }
}