package ldg.study.springboot.job.quartz.dynamic.factory;

import ldg.study.springboot.job.quartz.dynamic.model.JobDto;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 任务调度抽象工厂
 *
 * @author foursix
 * @since 2017/11/23
 */
public abstract class AbstractJobFactory {

    private final RestTemplate restTemplate = new RestTemplate();

    public void invoke(JobDto job) {
        if (StringUtils.hasText(job.getUrl())) {
            //此处可调用注册定时任务的url
            //this.restTemplate.postForObject(job.getUrl(), null, String.class);

            System.out.println(System.nanoTime() / 1000 / 1000 + " >>执行....");
        }
        /*if (StringUtils.hasText(job.getJobClassName())) {
            try {
                Class<?> clazz = Class.forName(job.getJobClassName());
                Object obj = SpringBeanUtils.getBeanByType(clazz);//从spring容器获取bean,否则无法注入
                Method method = obj.getClass().getDeclaredMethod(job.getJobMethodName());//反射方法
                method.invoke(obj, job);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }*/
    }
}
