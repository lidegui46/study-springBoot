package ldg.study.springboot.job.quartz.dynamic.support;

import ldg.study.springboot.job.quartz.dynamic.factory.DefaultJobFactory;
import ldg.study.springboot.job.quartz.dynamic.factory.DisallowConcurrentJobFactory;
import ldg.study.springboot.job.quartz.dynamic.model.JobConstants;
import ldg.study.springboot.job.quartz.dynamic.model.JobDto;
import ldg.study.springboot.tools.utils.json.FastJsonUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 任务管理器
 *
 * @author foursix
 * @since 2017/11/23
 */
@Component
@EnableScheduling
public class JobManager {
    private final Logger logger = LoggerFactory.getLogger(JobManager.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /*
    //调度管理器注入工厂bean
    private StdScheduler scheduler;
    public StdScheduler getScheduler() { return scheduler; }

    public void setScheduler(StdScheduler scheduler) { this.scheduler = scheduler; }
    */

    /**
     * 开始任务
     */
    public void start() {
        try {
            if (!schedulerFactoryBean.getScheduler().isStarted()) {
                schedulerFactoryBean.getScheduler().start();
            }
        } catch (SchedulerException ex) {
            logger.error("方法：start,异常：message:{},stackTrace:{}", ex.getMessage(), ex.getStackTrace());
        }
    }

    /**
     * 关闭任务
     */
    public void shutdown() {
        try {
            if (schedulerFactoryBean.getScheduler().isStarted()) {
                schedulerFactoryBean.getScheduler().shutdown(true);
            }
        } catch (SchedulerException ex) {
            logger.error("方法：shutdown,异常：message:{},stackTrace:{}", ex.getMessage(), ex.getStackTrace());
        }
    }

    /**
     * 添加任务
     *
     * @param jobDto
     * @throws SchedulerException
     */
    public void addJob(JobDto jobDto) throws SchedulerException, ClassNotFoundException {
        if (jobDto == null || StringUtils.isEmpty(jobDto.getJobId())) {return;}
        if (StringUtils.isEmpty(jobDto.getCronExpression()) && null == jobDto.getSimpleExpression()) {return;}
        //if (StringUtils.isEmpty(jobDto.getJobClassName())) return;
        if (StringUtils.isEmpty(jobDto.getUrl())) {return;}
        if (StringUtils.isEmpty(jobDto.getJobName())) {
            jobDto.setJobName(jobDto.getJobName());
        }
        if (null == jobDto.getSimpleExpression()) {
            addCronJob(jobDto);
        } else {
            addSimpleJob(jobDto);
        }
    }

    /**
     * 添加 cron 表达式任务
     *
     * @param jobDto
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    private void addCronJob(JobDto jobDto) throws SchedulerException, ClassNotFoundException {
        //根据任务id和任务组Id创建触发器key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getJobId(), jobDto.getJobGroupName());
        //获取触发器对象
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);//(CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder
                    .newJob(jobDto.isConcurrent() ? DefaultJobFactory.class : DisallowConcurrentJobFactory.class)
                    .withIdentity(jobDto.getJobId(), jobDto.getJobGroupName())
                    .build();

            jobDetail.getJobDataMap().put(JobConstants.Scheduler.DEFAULT_NAME, FastJsonUtils.objToJson(jobDto));

            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobDto.getCronExpression()))
                    .build();
            schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);//scheduler.scheduleJob(jobDetail, trigger);
        } else {
            updateJobCron(jobDto);
        }
    }

    /**
     * 添加 简单时间 表达式任务
     *
     * @param jobDto
     * @throws SchedulerException
     * @throws ClassNotFoundException
     */
    private void addSimpleJob(JobDto jobDto) throws SchedulerException {
        //根据任务id和任务组Id创建触发器key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getJobId(), jobDto.getJobGroupName());
        //获取触发器对象
        SimpleTrigger trigger = (SimpleTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder
                    .newJob(jobDto.isConcurrent() ? DefaultJobFactory.class : DisallowConcurrentJobFactory.class)
                    .withIdentity(jobDto.getJobId(), jobDto.getJobGroupName())
                    .build();

            jobDetail.getJobDataMap().put(JobConstants.Scheduler.DEFAULT_NAME, FastJsonUtils.objToJson(jobDto));

            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                    .startAt(jobDto.getSimpleExpression()).build();
            schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
        } else {
            updateJobSimple(jobDto);
        }
    }

    /**
     * 更新job时间表达式
     *
     * @param jobDto
     * @throws SchedulerException
     */
    public void updateJobCron(JobDto jobDto) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getJobId(), jobDto.getJobGroupName());
        CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(jobDto.getCronExpression())).build();
        schedulerFactoryBean.getScheduler().rescheduleJob(triggerKey, trigger);
    }

    /**
     * 更新job时间表达式
     *
     * @param jobDto
     * @throws SchedulerException
     */
    public void updateJobSimple(JobDto jobDto) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobDto.getJobId(), jobDto.getJobGroupName());
        SimpleTrigger trigger = (SimpleTrigger) schedulerFactoryBean.getScheduler().getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(jobDto.getSimpleExpression()).build();
        schedulerFactoryBean.getScheduler().rescheduleJob(triggerKey, trigger);
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     * @throws SchedulerException
     **/
    public List<JobDto> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = schedulerFactoryBean.getScheduler().getJobKeys(matcher);
        List<JobDto> jobList = new ArrayList<JobDto>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = schedulerFactoryBean.getScheduler().getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                JobDto jobDto = new JobDto();
                jobDto.setJobId(jobKey.getName());
                jobDto.setJobGroupName(jobKey.getGroup());
                Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());
                jobDto.setJobStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    jobDto.setCronExpression(cronExpression);
                }
                jobList.add(jobDto);
            }
        }
        return jobList;
    }

    /**
     * 获取正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public List<JobDto> getRunningJob() throws SchedulerException {
        List<JobExecutionContext> executingJobs = schedulerFactoryBean.getScheduler().getCurrentlyExecutingJobs();
        List<JobDto> jobList = new ArrayList<JobDto>(executingJobs.size());
        for (JobExecutionContext executingJob : executingJobs) {
            JobDetail jobDetail = executingJob.getJobDetail();
            JobKey jobKey = jobDetail.getKey();
            Trigger trigger = executingJob.getTrigger();
            Trigger.TriggerState triggerState = schedulerFactoryBean.getScheduler().getTriggerState(trigger.getKey());

            JobDto job = new JobDto();
            job.setJobName(jobKey.getName());
            job.setJobGroupName(jobKey.getGroup());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
            jobList.add(job);
        }
        return jobList;
    }

    /**
     * 暂停一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void pauseJob(JobDto scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobId(), scheduleJob.getJobGroupName());
        schedulerFactoryBean.getScheduler().pauseJob(jobKey);
    }

    /**
     * 恢复一个job
     *
     * @param job
     * @throws SchedulerException
     */
    public void resumeJob(JobDto job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroupName());
        schedulerFactoryBean.getScheduler().resumeJob(jobKey);
    }

    /**
     * 删除一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void deleteJob(JobDto scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobId(), scheduleJob.getJobGroupName());
        schedulerFactoryBean.getScheduler().deleteJob(jobKey);

    }

    /**
     * 立即执行job
     *
     * @param job
     * @throws SchedulerException
     */
    public void triggerJob(JobDto job) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(job.getJobId(), job.getJobGroupName());
        schedulerFactoryBean.getScheduler().triggerJob(jobKey);
    }
}
