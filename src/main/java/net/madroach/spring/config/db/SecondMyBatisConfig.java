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
@MapperScan(basePackages={"net.madroach.spring.dao.madroach2"},
        sqlSessionFactoryRef = "sqlSessionFactory.madroach2")
@EnableTransactionManagement
public class SecondMyBatisConfig {

    @Resource(name= DataSourceCd.MADROACH2)
    DataSource dataSource;

	@Bean(name="sqlSessionFactory.madroach2")
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
        //sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:net/madroach/spring/dao/madroach2/**/*xml"));
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactory.getObject();
	}
	
    @Bean(name="sqlSessionTemplate.madroach2", destroyMethod = "clearCache")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory.madroach2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name= TxManagerCd.MADROACH2)
    @Qualifier("mybatis")
    public PlatformTransactionManager txManager() {
      return new DataSourceTransactionManager(dataSource);
    }
}
