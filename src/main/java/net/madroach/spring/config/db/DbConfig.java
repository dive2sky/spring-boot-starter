package net.madroach.spring.config.db;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import net.madroach.spring.code.DataSourceCd;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by sam.park on 2016. 12. 15..
 */
@Slf4j
@Configuration
public class DbConfig {


    @Primary
    @Bean(name = DataSourceCd.MADROACH1)
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = DataSourceCd.MADROACH2)
    @ConfigurationProperties(prefix = "spring.slaveDatasource.hikari")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
