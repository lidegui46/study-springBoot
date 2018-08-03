package ldg.study.springboot.orm.mybatis.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author foursix
 * @since 2017.11.15
 */
@Configuration
@MapperScan(basePackages = V3DataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "v3SqlSessionFactory")
public class V3DataSourceConfiguration extends DruidDataSourceConfiguration {

    /**
     * 当前数据库对应的Dao
     */
    static final String PACKAGE = "ldg.study.springboot.orm.mybatis.v3.dao";

    /**
     * 配置xml文件路径,此处是路径
     */
    static final String MAPPER_LOCATION = "classpath*:ldg/study/springboot/orm/mybatis/v3/mapper/*.xml";

    @Value("${spring.datasource.v3.url}")
    private String url;

    @Value("${spring.datasource.v3.username}")
    private String user;

    @Value("${spring.datasource.v3.password}")
    private String password;

    @Value("${spring.datasource.v3.driver-class-name}")
    private String driverClass;

    @Bean(name = "v3DataSource")
    public DataSource dataSource() {
        return super.buildDataSource();
    }

    @Bean(name = "v3TransactionManager")
    public DataSourceTransactionManager v3TransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "v3SqlSessionFactory")
    public SqlSessionFactory v3SqlSessionFactory(@Qualifier("v3DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(V3DataSourceConfiguration.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Override
    public String getDriverClass() {
        return this.driverClass;
    }

    @Override
    public String getDriverUrl() {
        return this.url;
    }

    @Override
    public String getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
