package net.madroach.spring.config.db;

import net.madroach.spring.code.DataSourceCd;
import net.madroach.spring.code.TxManagerCd;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author sam.park
 */

@Configuration
@MapperScan(basePackages={"net.madroach.spring.dao.madroach1"},
        sqlSessionFactoryRef = "sqlSessionFactory.madroach1")
@EnableTransactionManagement
public class DefaultMyBatisConfig {

    @Resource(name= DataSourceCd.MADROACH1)
    DataSource dataSource;

    @Primary
	@Bean(name="sqlSessionFactory.madroach1")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setTypeAliasesPackage("net.madroach.spring.model");

        Properties mybatisConfig = new Properties();
        mybatisConfig.put("cacheEnabled", false);
        mybatisConfig.put("lazyLoadingEnabled", false);
        mybatisConfig.put("aggressiveLazyLoading", false);

        sessionFactory.setConfigurationProperties(mybatisConfig);
        //xml path
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:net/madroach/spring/dao/madroach1/**/*xml"));
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactory.getObject();
	}
	
    @Bean(name="sqlSessionTemplate.madroach1", destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory.madroach1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name= TxManagerCd.MADROACH1)
    @Qualifier("mybatis")
    public PlatformTransactionManager txManager() {
      return new DataSourceTransactionManager(dataSource);
    }
}
