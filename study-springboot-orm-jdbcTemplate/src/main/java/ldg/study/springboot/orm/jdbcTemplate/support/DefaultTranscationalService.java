package ldg.study.springboot.orm.jdbcTemplate.support;

import ldg.study.springboot.orm.jdbcTemplate.service.TranscationalService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 事务
 *
 * @author： 灿炉
 * @create date： 2019/7/26
 */
@Service
public class DefaultTranscationalService implements TranscationalService {
    private TranscationalService transcationalService;

    @Resource
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        transcationalService = context.getBean(TranscationalService.class);
    }

    /**
     * 通过Aop方式，事务生效
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add() {
        // 执行Sql语句
        // insertSql();


        // 事务的传播 ~ 生效
        // 通过查找 add() 的Aop动态代理，otherMethod()的事务和add()的事务存在传播性
        transcationalService.otherMethod();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void otherMethod() {
        int i = 0;
        double a = 2 / i;
    }
}
