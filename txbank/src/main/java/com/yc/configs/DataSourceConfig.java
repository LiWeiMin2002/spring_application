package com.yc.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @program: spring_application
 * @description:
 * @author: lwm
 * @create: 2023-08-03 11:15
 */
@Configuration
@PropertySource("classpath:db.properties")
@Data
@Log4j2
public class DataSourceConfig {
    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driverclass}")
    private String driverclass;

    @Value("#{T(Runtime).getRuntime().availableProcessors()}")
    private int cpuCount;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean
    public DataSource dbcpDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource druidDataSource() {
        DruidDataSource dds = new DruidDataSource();
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setDriverClassName(driverclass);

        log.info("配置druid的池大小：" + cpuCount);
        dds.setInitialSize(cpuCount);
        dds.setMaxActive(cpuCount * 2);
        return dds;
    }


}
