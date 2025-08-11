// 代码生成时间: 2025-08-11 09:20:01
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import javax.sql.DataSource;

public class ScheduledTaskExecutor {

    private static final String JOB_GROUP_NAME = "default";
    private static final String TRIGGER_GROUP_NAME = "default";

    /**
     * 初始化定时任务调度器
     */
    public void initScheduler(DataSource dataSource, SqlSessionFactory sqlSessionFactory) throws SchedulerException {
        // 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建定时任务
        JobDetail job = JobBuilder.newJob(MyBatisJob.class)
                .withIdentity("mybatisJob", JOB_GROUP_NAME)
                .usingJobData("sqlSessionFactory", sqlSessionFactory)
                .usingJobData("dataSource", dataSource)
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", TRIGGER_GROUP_NAME)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")) // 每10秒执行一次
                .build();

        // 注册定时任务和触发器
        scheduler.scheduleJob(job, trigger);

        // 启动调度器
        scheduler.start();
    }

    /**
     * 定时任务类，实现MyBatis的数据库操作
     */
    public static class MyBatisJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            try (SqlSession sqlSession = ((SqlSessionFactory) context.getJobDetail().getJobDataMap().get("sqlSessionFactory")).openSession()) {
                DataSource dataSource = (DataSource) context.getJobDetail().getJobDataMap().get("dataSource");
                // 这里可以添加你的MyBatis Mapper和数据库操作代码
                // ExampleMapper exampleMapper = sqlSession.getMapper(ExampleMapper.class);
                // exampleMapper.doSomething();
            } catch (Exception e) {
                throw new JobExecutionException(e);
            }
        }
    }
}
