package ldg.study.springboot.orm.mybatis.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Tony Mu(tonymu@qq.com)
 * @since 2017-11-13
 */
public abstract class DruidDataSourceConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DruidDataSourceConfiguration.class);

    @Value("${spring.datasource.initialSize}")
    private String initialSize;

    @Value("${spring.datasource.minIdle}")
    private String minIdle;

    @Value("${spring.datasource.maxWait}")
    private String maxWait;

    @Value("${spring.datasource.maxActive}")
    private String maxActive;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;

    @Value("${spring.datasource.filters}")
    private String filters;

    public DataSource buildDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.getDriverUrl());
        datasource.setDriverClassName(this.getDriverClass());
        datasource.setUsername(this.getUser());
        datasource.setPassword(this.getPassword());
        datasource.setInitialSize(Integer.valueOf(this.initialSize));
        datasource.setMinIdle(Integer.valueOf(this.minIdle));
        datasource.setMaxWait(Long.valueOf(this.maxWait));
        datasource.setMaxActive(Integer.valueOf(this.maxActive));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(this.minEvictableIdleTimeMillis));
        try {
            datasource.setFilters(this.filters);
        } catch (SQLException ex) {
            logger.error("设置Druid数据源的监控统计拦截的过滤器时出错", ex);
        }
        return datasource;
    }

    public abstract String getDriverClass();

    public abstract String getDriverUrl();

    public abstract String getUser();

    public abstract String getPassword();

}
