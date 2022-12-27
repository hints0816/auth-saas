package org.hints.common.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 180686 on 2021/6/17 14:18
 */

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DataSourceConfig {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public DataSource initGscmDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(getDriverClassName());
        druidDataSource.setUrl(getUrl());
        druidDataSource.setUsername(getUsername());
        druidDataSource.setPassword(getPassword());
        return  druidDataSource;
    }

    @Primary
    @Bean
    public Dao initNutzDao(@Qualifier("multiDataSource") DataSource dataSource){
        return new NutDao(dataSource);
    }



    /**
     * 注册动态数据源
     *
     * @return
     */
    @Bean(name = "multiDataSource")
    @Primary
    public MultiRouteDataSource multiDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", initGscmDataSource());
        return  new MultiRouteDataSource(initGscmDataSource(), targetDataSources);
    }


}
