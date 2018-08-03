package ldg.study.springboot.orm.mybatis.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author foursix
 * @since 2017.11.15
 */
@Configuration
@MapperScan(basePackages = PromotionsDataSourceConfiguration.PACKAGE, sqlSessionFactoryRef = "promotionsSqlSessionFactory")
public class PromotionsDataSourceConfiguration extends DruidDataSourceConfiguration {

    /**
     * 当前数据库对应的Dao
     */
    static final String PACKAGE = "ldg.study.springboot.orm.mybatis.promotions.dao";

    /**
     * 配置xml文件路径，此处是路径
     */
    static final String MAPPER_LOCATION = "classpath*:ldg/study/springboot/orm/mybatis/promotions/mapper/*.xml";

    @Value("${spring.datasource.promotions.url}")
    private String url;

    @Value("${spring.datasource.promotions.username}")
    private String user;

    @Value("${spring.datasource.promotions.password}")
    private String password;

    @Value("${spring.datasource.promotions.driver-class-name}")
    private String driverClass;

    @Bean(name = "promotionsDataSource")
    @Primary
    public DataSource dataSource() {
        return super.buildDataSource();
    }

    @Bean(name = "promotionsTransactionManager")
    @Primary
    public DataSourceTransactionManager promotionsTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "promotionsSqlSessionFactory")
    @Primary
    public SqlSessionFactory promotionsSqlSessionFactory(@Qualifier("promotionsDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PromotionsDataSourceConfiguration.MAPPER_LOCATION));
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
