package org.hints.tenant.dao;

import org.apache.commons.lang3.StringUtils;
import org.hints.common.pojo.SaasSite;
import org.hints.common.pojo.TablePageData;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 180686 on 2021/9/8 17:04
 */
@Repository
public class SaasSiteDao {

    @Autowired
    private Dao dao;

    public TablePageData selectSaasSiteList(SaasSite saasSite){
        Pager pager = dao.createPager(saasSite.getPageNum(), saasSite.getPageSize());
        saasSite.setNull();
        Cnd cnd = Cnd.from(dao,saasSite);
        String site_id = saasSite.getSite_id();
        saasSite.setSite_id(null);
        String title = saasSite.getTitle();
        saasSite.setTitle(null);
        if (cnd == null){
            cnd = Cnd.where("1","=","1");
        }
        cnd = cnd.andNot("status","=",3);
        if(StringUtils.isNotEmpty(site_id)){
            cnd = cnd.and("site_id","like", "%"+site_id+"%");
        }
        if(StringUtils.isNotEmpty(title)){
            cnd = cnd.and("title","like", "%"+title+"%");
        }
        List query = dao.query(SaasSite.class, cnd, pager);
        TablePageData tablePageData = new TablePageData(query, pager);
        return tablePageData;
    }

    public SaasSite insertSaasSite(SaasSite saasSite){
        SaasSite insert = dao.insert(saasSite);
        return insert;
    }

    public SaasSite selectSaasSiteById(String siteId){
        SaasSite fetch = dao.fetch(SaasSite.class, siteId);
        return fetch;
    }

    public List<SaasSite> selectSaasSite(){
        List<SaasSite> query = dao.query(SaasSite.class, null);
        return query;
    }

    public int updateSaasSite(SaasSite saasSite){
        int update = dao.update(saasSite);
        return update;
    }

    public int updateSaasSiteStatus(String site_id, Long status) {
        int update = dao.update(SaasSite.class, Chain.make("status",status),Cnd.where("site_id","=",site_id));
        return update;
    }

    public int deleteSaasSiteByIds(String[] siteIds){
        int delete = dao.clear(SaasSite.class, Cnd.where("site_id", "in", siteIds));
        return delete;
    }

}
