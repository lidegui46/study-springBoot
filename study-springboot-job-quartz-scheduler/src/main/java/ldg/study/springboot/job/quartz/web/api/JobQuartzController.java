package ldg.study.springboot.job.quartz.web.api;

import ldg.study.springboot.job.quartz.dynamic.model.JobDto;
import ldg.study.springboot.job.quartz.dynamic.support.JobManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * quartz 定时任务Controller
 *
 * @author foursix
 * @since 2017/11/24
 */
@RestController
@RequestMapping(value = "/job/quartz")
public class JobQuartzController {
    private final Logger logger = LoggerFactory.getLogger(JobQuartzController.class);
    @Autowired
    private JobManager jobManager;

    /**
     * 开始执行任务
     * http://localhost:8084/job/quartz/start
     *
     * @return
     */
    @RequestMapping(value = "/start")
    public String start() {
        try {
            jobManager.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return "Ok";
    }

    /**
     * 关闭任务
     * http://localhost:8084/job/quartz/shutdown
     *
     * @return
     */
    @RequestMapping(value = "/shutdown")
    public String shutdown() {
        try {
            jobManager.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return "Ok";
    }

    /**
     * 新增定时任务
     * http://localhost:8084/job/quartz/add
     *
     * @return
     */
    @RequestMapping(value = "/add")
    public String addJob() {
        JobDto jobDto = new JobDto();
        jobDto.setJobId("1");
        jobDto.setJobName("关闭订单");
        jobDto.setJobGroupId("order");
        jobDto.setJobGroupName("订单");
        jobDto.setCreateTime(new Date());
        jobDto.setUpdateTime(new Date());
        jobDto.setUrl("http://123.com");
        jobDto.setJobStatus("1");
        jobDto.setCronExpression("1/2 * * * * ?");
        jobDto.setConcurrent(false);
        jobDto.setDescription("系统定时任务关闭订单");

        try {
            //Add前验证是否有此定时任务
            jobManager.addJob(jobDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return "Ok";
    }

    /**
     * 暂停定时任务
     * http://localhost:8084/job/quartz/pause
     *
     * @return
     */
    @RequestMapping(value = "/pause")
    public String pauseJob() {
        JobDto jobDto = new JobDto();
        jobDto.setJobId("1");
        jobDto.setJobName("关闭订单");
        jobDto.setJobGroupId("order");
        jobDto.setJobGroupName("订单");
        jobDto.setCreateTime(new Date());
        jobDto.setUpdateTime(new Date());
        jobDto.setUrl("http://123.com");
        jobDto.setJobStatus("1");
        jobDto.setCronExpression("1/2 * * * * ?");
        jobDto.setConcurrent(false);
        jobDto.setDescription("系统定时任务关闭订单");

        try {
            //Add前验证是否有此定时任务
            jobManager.pauseJob(jobDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return "Ok";
    }

    /**
     * 删除定时任务
     * http://localhost:8084/job/quartz/delete
     *
     * @return
     */
    @RequestMapping(value = "/delete")
    public String deleteJob() {
        JobDto jobDto = new JobDto();
        jobDto.setJobId("1");
        jobDto.setJobName("关闭订单");
        jobDto.setJobGroupId("order");
        jobDto.setJobGroupName("订单");
        jobDto.setCreateTime(new Date());
        jobDto.setUpdateTime(new Date());
        jobDto.setUrl("http://123.com");
        jobDto.setJobStatus("1");
        jobDto.setCronExpression("1/2 * * * * ?");
        jobDto.setConcurrent(false);
        jobDto.setDescription("系统定时任务关闭订单");

        try {
            //Add前验证是否有此定时任务
            jobManager.deleteJob(jobDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return "Ok";
    }

    /**
     * 立即执行定时任务
     * http://localhost:8084/job/quartz/trigger
     *
     * @return
     */
    @RequestMapping(value = "/trigger")
    public String triggerJob() {
        JobDto jobDto = new JobDto();
        jobDto.setJobId("1");
        jobDto.setJobName("关闭订单");
        jobDto.setJobGroupId("order");
        jobDto.setJobGroupName("订单");
        jobDto.setCreateTime(new Date());
        jobDto.setUpdateTime(new Date());
        jobDto.setUrl("http://123.com");
        jobDto.setJobStatus("1");
        jobDto.setCronExpression("1/2 * * * * ?");
        jobDto.setConcurrent(false);
        jobDto.setDescription("系统定时任务关闭订单");

        try {
            //Add前验证是否有此定时任务
            jobManager.triggerJob(jobDto);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
        }
        return "Ok";
    }
}
