package ldg.study.springboot.job.quartz.dynamic.factory;

import ldg.study.springboot.job.quartz.dynamic.model.JobConstants;
import ldg.study.springboot.job.quartz.dynamic.model.JobDto;
import ldg.study.springboot.tools.utils.json.FastJsonUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.util.StringUtils;

/**
 * @author foursix
 * @since 2017/11/23
 */
@DisallowConcurrentExecution
public class DisallowConcurrentJobFactory extends AbstractJobFactory implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String scheduleJob = (String) context.getMergedJobDataMap().get(JobConstants.Scheduler.DEFAULT_NAME);
        if (!StringUtils.hasText(scheduleJob)) {
            JobDto jobDto = FastJsonUtils.jsonToObj(scheduleJob, JobDto.class);
            invoke(jobDto);
        }
    }
}
