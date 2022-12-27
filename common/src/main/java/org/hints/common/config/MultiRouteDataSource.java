package org.hints.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 180686 on 2021/6/17 14:16
 */

public class MultiRouteDataSource extends AbstractRoutingDataSource {

    private static Map<Object, Object> targetDataSources = new HashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        // 通过绑定线程的数据源上下文实现多数据源的动态切换
        return DataSourceContext.getDBType();
    }

    public MultiRouteDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSource1){
        targetDataSources = targetDataSource1;
        super.setDefaultTargetDataSource(defaultDataSource);
        // 存放数据源的map
        super.setTargetDataSources(targetDataSources);
        // afterPropertiesSet 的作用很重要，它负责解析成可用的目标数据源
        super.afterPropertiesSet();
    }

    /**addDataSource
     * 动态增加数据源
     * @return
     */
    public synchronized void addDataSource(DruidDataSource datasource) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        druidDataSource.setUrl(datasource.getUrl());
        druidDataSource.setUsername(datasource.getUsername());
        druidDataSource.setPassword(datasource.getPassword());
        // 将传入的数据源对象放入动态数据源类的静态map中，然后再讲静态map重新保存进动态数据源中
        targetDataSources.put(datasource.getUsername(), druidDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}
