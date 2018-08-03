package ldg.study.springboot.job.quartz.dynamic.factory;

import ldg.study.springboot.job.quartz.dynamic.model.JobConstants;
import ldg.study.springboot.job.quartz.dynamic.model.JobDto;
import ldg.study.springboot.tools.utils.FastJsonUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 任务调度工厂
 *
 * @author foursix
 * @since 2017/11/23
 */
@Component
public class DefaultJobFactory extends AbstractJobFactory implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String scheduleJob = (String) context.getMergedJobDataMap().get(JobConstants.Scheduler.DEFAULT_NAME);
        if (StringUtils.hasText(scheduleJob)) {
            JobDto jobDto = FastJsonUtils.jsonToObj(scheduleJob, JobDto.class);
            invoke(jobDto);
        }
    }
}
