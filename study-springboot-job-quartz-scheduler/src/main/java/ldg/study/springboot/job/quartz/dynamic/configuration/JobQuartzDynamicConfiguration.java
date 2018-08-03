package ldg.study.springboot.job.quartz.dynamic.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author foursix
 * @since 2017-11-23
 */
@Configuration
@EnableScheduling
public class JobQuartzDynamicConfiguration {

    @Value("${quartz.scheduler.instanceName}")
    private String quartzInstanceName;

    @Value("${org.quartz.dataSource.standard.driver}")
    private String standardDriver;

    @Value("${org.quartz.dataSource.standard.URL}")
    private String standardUrl;

    @Value("${org.quartz.dataSource.standard.user}")
    private String standardUser;

    @Value("${org.quartz.dataSource.standard.password}")
    private String standardPassword;

    @Value("${org.quartz.dataSource.standard.maxConnections}")
    private String standardMaxConnections;

    /*
     <!-- 任务管理器 -->
     <bean class="com.quartz.JobManager">
         <property name="scheduler">
         <!-- 将触发器注入任务工程 -->
             <bean id="scheduler" lazy-init="false" scope="singleton"
                   class="org.springframework.scheduling.quartz.SchedulerFactoryBean"/>
         </property>
     </bean>
    */

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean
    //public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setStartupDelay(10);// 延时启动，应用启动1秒后
        bean.setQuartzProperties(quartzProperties());
        bean.setApplicationContextSchedulerContextKey("applicationContext");
        //bean.setDataSource(createDataSource());
        return bean;
    }

    /**
     * 加载quartz数据源配置
     *
     * @return
     * @throws IOException
     */
    private Properties quartzProperties() {
        Properties prop = new Properties();
        prop.put("quartz.scheduler.instanceName", this.quartzInstanceName);
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.scheduler.jmx.export", "true");
/*
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "true");

        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
        prop.put("org.quartz.jobStore.dataSource", "standard");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.misfireThreshold", "120000");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");
*/
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "10");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

        prop.put("org.quartz.plugin.triggHistory.class", "org.quartz.plugins.history.LoggingJobHistoryPlugin");
        prop.put("org.quartz.plugin.shutdownhook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
        prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");
        return prop;
    }

/*
    @Bean(name = "schedulerDataSource")
    public DataSource createDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.standardUrl);
        datasource.setDriverClassName(this.standardDriver);
        datasource.setUsername(this.standardUser);
        datasource.setPassword(this.standardPassword);
        datasource.setMaxActive(Integer.valueOf(this.standardMaxConnections));
        return datasource;
    }
    */
}
