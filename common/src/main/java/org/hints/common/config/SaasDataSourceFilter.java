//package org.hints.common.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.gree.framework.dao.saas.SaasDbInfoDao;
//import com.gree.framework.dao.saas.SaasOracleDao;
//import com.gree.framework.dao.saas.SaasTenantDao;
//import com.gree.framework.entity.SaasOracle;
//import org.nutz.dao.entity.Record;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.LinkedHashMap;
//import java.util.List;
//
///**
// * Created by 180686 on 2021/6/17 16:30
// */
//@Component
//@WebFilter(urlPatterns = {"/**"})
//public class SaasDataSourceFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private SaasDbInfoDao saasDbInfoDao;
//
//    @Autowired
//    private SaasTenantDao saasTenantDao;
//
//    @Autowired
//    private SaasOracleDao saasOracleDao;
//
//    @Autowired
//    private MultiRouteDataSource dynamicDataSource;
//
//    @PostConstruct
//    public void init()
//    {
//        List<Record> records = saasDbInfoDao.getDbInfoWithSaas();
//        for(Record record:records){
//            DruidDataSource druidDataSource = new DruidDataSource();
//            druidDataSource.setUrl("jdbc:oracle:thin:@10.2.25.123/ggrdattest");
//            druidDataSource.setUsername(record.getString("client_id"));
//            druidDataSource.setPassword("123456");
//            dynamicDataSource.addDataSource(druidDataSource);
//        }
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String clientId = "gscm_client";
//        OAuth2Authentication oAuth2Authentication = null;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication instanceof OAuth2Authentication){
//            oAuth2Authentication = (OAuth2Authentication)authentication;
//            LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) oAuth2Authentication.getUserAuthentication().getDetails();
//            clientId = map.get("client_id")==null?"":map.get("client_id").toString();
//        }
//        DataSourceContext.setDBType("gscm_client");
//        SaasOracle saasOracle = saasOracleDao.selectSaasOracleByClientId(clientId);
//        /* 供應鏈租戶下的用戶登錄時會自動切換數據源，其餘的是要手動切換 */
//        if(!clientId.equals("gscm_client") && !clientId.equals("saas_client")){
//            if(saasOracle != null) {
//                DataSourceContext.setDBType(clientId);
//            }
//        }
//        filterChain.doFilter(request, response);
//
//    }
//}