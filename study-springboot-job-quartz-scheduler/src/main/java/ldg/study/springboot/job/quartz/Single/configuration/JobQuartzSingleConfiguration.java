package ldg.study.springboot.job.quartz.Single.configuration;

import ldg.study.springboot.job.quartz.Single.model.ScheduleTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author foursix
 * @since 2017/11/23
 */
//@Configuration
public class JobQuartzSingleConfiguration {
    /**
     * attention:
     * Details：配置定时任务
     */
   // @Bean(name = "singleJobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {// ScheduleTask为需要执行的任务
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         *  是否并发执行
         *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);

        jobDetail.setName("srd-chhliu");// 设置任务的名字
        jobDetail.setGroup("srd");// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

        /*
         * 为需要执行的实体类对应的对象
         */
        jobDetail.setTargetObject(task);

        /*
         * sayHello为需要执行的方法
         * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
         */
        jobDetail.setTargetMethod("sayHello");
        return jobDetail;
    }

    /**
     * attention:
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    //@Bean(name = "singleJobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean singleJobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(singleJobDetail.getObject());
        tigger.setCronExpression("0 30 20 * * ?");// 初始时的cron表达式
        tigger.setName("srd-chhliu");// trigger的name
        return tigger;

    }

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    //@Bean(name = "singleScheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }
}
