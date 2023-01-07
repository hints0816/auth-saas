package org.hints.tenant.dao;

import org.hints.common.pojo.SaasCommodity;
import org.hints.common.pojo.TablePageData;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.pager.Pager;
import org.nutz.trans.Atom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/7 18:28
 */
@Repository
public class SaasCommodityDao {

    @Autowired
    private Dao dao;

    public SaasCommodity insertSaasCommodityDao(SaasCommodity saasCommodity){
        SaasCommodity insert = dao.insert(saasCommodity);
        return insert;
    }

    public int updateSaasCommodityDao(SaasCommodity saasCommodity){
        final int[] a = {0};
        FieldFilter.locked(SaasCommodity.class,"^create_time$").run(new Atom() {
            @Override
            public void run() {
                a[0] = dao.update(saasCommodity);
            }
        });
        return a[0];
    }

    /**
     * 根据商品ID更新图片
     */
    public int updateSaasCommodityImage(String group_id, String image){
        return dao.update(SaasCommodity.class, Chain.make("image", image), Cnd.where("group_id","=",group_id));
    }

    /**
     * 根据商品ID获取商品信息
     * @param groupId
     * @return
     */
    public SaasCommodity getGroupIdCommodity(String groupId){
        SaasCommodity saasCommodity = dao.fetch(SaasCommodity.class, groupId);
        return saasCommodity;
    }

    /**
     * 分页查询商品
     * @return
     */
    public TablePageData<SaasCommodity> selectCommodityPage(SaasCommodity saasCommodity){
        Pager pager = dao.createPager(saasCommodity.getPageNum(), saasCommodity.getPageSize());
        saasCommodity.setGroupId(null);
        saasCommodity.setGroupName(null);
        Cnd cnd = Cnd.from(dao,saasCommodity);
        if (cnd == null){
            cnd = Cnd.where("1","=","1");
        }
        List query = dao.query(SaasCommodity.class, cnd, pager);
        pager.setRecordCount(dao.count(SaasCommodity.class, cnd));
        TablePageData<SaasCommodity> tablePageData = new TablePageData(query, pager);
        return tablePageData;
    }

    /**
     * 批量删除
     * @return
     */
    public int delCommodityIds(String[] ids) {
        int clear = dao.clear(SaasCommodity.class, Cnd.where("GROUP_ID", "in", ids));
        return clear;
    }

}
