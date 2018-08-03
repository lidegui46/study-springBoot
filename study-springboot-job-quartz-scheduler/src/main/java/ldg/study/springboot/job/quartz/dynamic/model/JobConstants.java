package ldg.study.springboot.job.quartz.dynamic.model;

/**
 * 任务常量
 *
 * @author foursix
 * @since 2017/11/23
 */
public final class JobConstants {
    /**
     * 调度
     */
    public static class Scheduler {

        /**
         * 默认调度名称
         */
        public static final String DEFAULT_NAME = "default_scheduler";
    }

    /**
     * Job状态
     */
    public static class Status {
        /**
         * 未处理
         */
        public static final String UNPROCESSED = "0";

        /**
         * 执行成功
         */
        public static final String SUCCESSED = "1";

        /**
         * 执行失败
         */
        public static final String JFAILED = "-1";

        /**
         * 过期
         */
        public static final String EXPIRED = "-2";
    }

    /**
     * 任务组
     */
    public static final class Gruop {
        /**
         * 默认的任务组Id
         */
        public static final String DEFAULT_GROUP_ID = "job_default_group_id";

        /**
         * 默认的任务组名称
         */
        public static final String DEFAULT_GROUP_NAME = "job_default_group_name";
    }

    /**
     * 任务名称
     */
    public static final class Name {
        /**
         * 默认的任务名
         */
        public static final String DEFAULT_TASK_ID = "job_default_task_id";

        /**
         * 默认的任务名
         */
        public static final String DEFAULT_TASK_NAME = "job_default_task_name";
    }
}
