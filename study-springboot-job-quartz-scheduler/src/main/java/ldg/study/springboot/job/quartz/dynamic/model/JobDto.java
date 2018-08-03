package ldg.study.springboot.job.quartz.dynamic.model;

import java.util.Date;

/**
 * 任务 实体
 * <p>
 *     jobId + jobGroupId组成唯一的Job任务
 *
 * </p>
 *
 * @author foursix
 * @since 2017/11/23
 */
public class JobDto implements java.io.Serializable  {
    /** 任务ID */
    private String jobId;
    /** 任务名称 */
    private String jobName;
    /** 任务组id */
    private String jobGroupId;
    /** 任务分组名称 */
    private String jobGroupName;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 任务状态 */
    private String jobStatus;
    /** 任务调度时间表达式 */
    private String cronExpression;
    /** 简单时间表达式 */
    private Date simpleExpression;
    /** 定时任务执行调用的URL */
    private String url;
    /** 任务执行时调用哪个类的方法 包名+类名 */
    //private String jobClassName;
    /** 任务调用的方法名 */
    //private String jobMethodName;
    /**
     *  是否允许并发执行同一个任务
     *  <p>
     *      如果任务在下一个执行时间开始的时候，当前执行还没有结束，是否要继续新的任务执行。
     *          true：允许多个线程同时执行一个任务
     *          false：必须当前任务结束后，到下一个执行时间时才允许再次执行
     *  </p>
     */
    private boolean isConcurrent;
    /** 任务描述 */
    private String description;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupId() {
        return jobGroupId;
    }

    public void setJobGroupId(String jobGroupId) {
        this.jobGroupId = jobGroupId;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Date getSimpleExpression() {
        return simpleExpression;
    }

    public void setSimpleExpression(Date simpleExpression) {
        this.simpleExpression = simpleExpression;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getJobMethodName() {
        return jobMethodName;
    }

    public void setJobMethodName(String jobMethodName) {
        this.jobMethodName = jobMethodName;
    }*/

    public boolean isConcurrent() {
        return isConcurrent;
    }

    public void setConcurrent(boolean concurrent) {
        isConcurrent = concurrent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
