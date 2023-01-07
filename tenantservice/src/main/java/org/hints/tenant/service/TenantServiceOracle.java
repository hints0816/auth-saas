package org.hints.tenant.service;

import io.jsonwebtoken.Claims;
import org.hints.common.config.MultiRouteDataSource;
import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOracle;
import org.hints.common.pojo.SaasTenant;
import org.hints.common.pojo.SysClient;
import org.hints.tenant.dao.SaasTenantDao;
import org.hints.tenant.utils.SecurityUtil;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/3 15:02
 */
@Service
public class TenantServiceOracle implements TenantService{

    @Autowired
    private Dao dao;

    @Autowired
    private SaasTenantDao saasTenantDao;

    @Autowired
    private MultiRouteDataSource dataSource;

    @Override
    public void create(String tenantid, String groupId, LocalDateTime plus, Long maxSiteNum, String addon) {

        /*生成随机ClientId = oracle数据库用户 (dba对greescmdb2创建的saas开头的20位用户名处理)*/
        long randomtime=System.currentTimeMillis();
        String randomtimestr = Long.toString(randomtime);
        int rannum = (int) (Math.random()*900 + 100);
        String rannumstr = Integer.toString(rannum);
        String clientid = "SAAS"+randomtimestr+rannumstr;

        /*Auth2客户端基本信息*/
        String client_secret = "123456";
        String authorized_grant_types = "gscmmode,refresh_token";
        String redirect_uri = "";
        Double accesstokenvalidityseconds = 1800D;
        Double refreshtokenvalidityseconds = 1800D;
        String scopes = "server";

        /*检查Oracle是否有存在user*/
        Sql sql2 = Sqls.create("select * from all_users where username=@user");
        sql2.setParam("user",clientid);
        sql2.setCallback(Sqls.callback.entities());
        sql2.setEntity(dao.getEntity(Record.class));
        List<Record> alltabledatas = dao.execute(sql2).getList(Record.class);
        if(alltabledatas.size()>0){
            return;
        }
        Dao dao2 = new NutDao(dataSource,new FileSqlManager("sql/createuser.sqls"));
        String[] keys = dao2.sqls().keys();
        for(String key : keys) {
            Sql sql = dao2.sqls().create(key);
            sql.setVar("user",clientid);
            dao2.execute(sql);
        }
        Dao dao3 = new NutDao(dataSource,new FileSqlManager("sql/smcom.sqls"));
        String[] keys3 = dao3.sqls().keys();
        for(String key : keys3) {
            Sql sql = dao3.sqls().create(key);
            sql.setVar("user",clientid);
            dao3.execute(sql);
        }

        Dao dao4 = new NutDao(dataSource,new FileSqlManager("sql/sys.sqls"));
        String[] keys4 = dao4.sqls().keys();
        for(String key : keys4) {
            Sql sql = dao4.sqls().create(key);
            sql.setVar("user",clientid);
            dao4.execute(sql);
        }

        String finalSecret = new BCryptPasswordEncoder().encode(client_secret);

        /*插入权限认证模块*/
        SysClient sysClient = new SysClient();
        sysClient.setClientId(clientid);
        sysClient.setClientSecret("{bcrypt}"+finalSecret);
        sysClient.setAuthorizedGrantTypes(authorized_grant_types);
        sysClient.setAccessTokenValiditySeconds(accesstokenvalidityseconds);
        sysClient.setRedirectUri(redirect_uri);
        sysClient.setRefreshTokenValiditySeconds(refreshtokenvalidityseconds);
        sysClient.setScopes(scopes);
        dao.insert(sysClient);

        /*插入租户信息表*/
        SaasOracle saasOracle = new SaasOracle();
        long randomtime1=System.currentTimeMillis();
        String randomtimestr1 = Long.toString(randomtime1);
        int rannum1 = (int) (Math.random()*900 + 100);
        String rannumstr1 = Integer.toString(rannum1);
        String id = randomtimestr1+rannumstr1;
        saasOracle.setId(id);
        saasOracle.setClientId(clientid);
        saasOracle.setCreate_time(LocalDateTime.now());
        saasOracle.setExpireTime(plus);
        saasOracle.setGroupId(groupId);
        saasOracle.setMaxSiteNum(maxSiteNum);
        saasOracle.setSiteNum(0L);
        saasOracle.setTenant_id(tenantid);
        saasOracle.setStatus(1L);
        saasOracle.setPassword(finalSecret);
        dao.insert(saasOracle);
    }

    @Override
    public ReturnVo register(SaasTenant saasTenant) {
        SaasTenant saasTenant1 = saasTenantDao.selectSaasSysTenantByMobile(saasTenant.getMobile());
        if(saasTenant1 != null) {
            return ReturnVo.error("account is existed");
        }
        //生成随机的用户ID
        long randomtime=System.currentTimeMillis();
        String randomtimestr = Long.toString(randomtime);
        int rannum = (int) (Math.random()*90 + 10);
        String rannumstr = Integer.toString(rannum);
        String sysuid = randomtimestr+rannumstr;
        //用户ID和租户ID一样，设置状态(1:正常)
        saasTenant.setSysUid(sysuid);
        saasTenant.setCreate_time(LocalDateTime.now());
        saasTenant.setTenant_id(sysuid);
        saasTenant.setStatus(1L);
        //BCrypt密码加密
        String finalSecret = new BCryptPasswordEncoder().encode(saasTenant.getPassword());
        saasTenant.setPassword(finalSecret);
        SaasTenant result = dao.insert(saasTenant);
        return ReturnVo.success();
    }

    @Override
    public ReturnVo reset(SaasTenant saasTenant) {
        try {
            String mobile = SecurityUtil.getJwtInfo().get("user_name").toString();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String finalSecret = encoder.encode(saasTenant.getPassword());
            saasTenantDao.updateSaasTenantPassword(mobile, finalSecret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ReturnVo.success();
    }

    @Override
    public SaasTenant fetchTenantInfo() throws UnsupportedEncodingException {
        String mobile = SecurityUtil.getJwtInfo().get("user_name").toString();
        SaasTenant saasTenant = saasTenantDao.selectSaasSysTenantByMobile(mobile);
        saasTenant.setPassword(null);
        return saasTenant;
    }
}
