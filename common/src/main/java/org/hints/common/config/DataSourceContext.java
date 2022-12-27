package org.hints.common.config;

/**
 * Created by 180686 on 2021/6/17 14:16
 */

public class DataSourceContext {
    private static final ThreadLocal<String> contextHolder = new InheritableThreadLocal<String>();

    /**
     * 保存租户id
     * @param dbType 租户id
     */
    public static void setDBType(String dbType){
        contextHolder.set(dbType);
    }

    public static String getDBType(){
        return contextHolder.get();
    }

    public static void clearDBType(){
        contextHolder.remove();
    }
}
