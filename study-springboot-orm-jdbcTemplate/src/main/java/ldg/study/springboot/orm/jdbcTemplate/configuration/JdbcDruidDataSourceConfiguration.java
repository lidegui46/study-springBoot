package ldg.study.springboot.orm.jdbcTemplate.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * @author Tony Mu(tonymu@qq.com)
 * @since 2017-11-13
 */
@Configuration
public class JdbcDruidDataSourceConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.v3.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.v3.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.v3.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.v3.driver-class-name"));
        dataSource.setInitialSize(Integer.valueOf(env.getProperty("spring.datasource.initialSize")));
        dataSource.setMaxActive(Integer.valueOf(env.getProperty("spring.datasource.maxActive")));
        dataSource.setMinIdle(Integer.valueOf(env.getProperty("spring.datasource.minIdle")));
        dataSource.setMaxWait(Integer.valueOf(env.getProperty("spring.datasource.maxWait")));
        dataSource.setValidationQuery(env.getProperty("spring.datasource.validationQuery"));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("spring.datasource.testOnBorrow")));
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("spring.datasource.testWhileIdle")));
        dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("spring.datasource.poolPreparedStatements")));
        return dataSource;
    }
}
