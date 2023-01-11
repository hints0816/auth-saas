package org.hints.tenant.service;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasSite;
import org.hints.common.pojo.TablePageData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/11 16:52
 */
public interface SiteService {

    ReturnVo createSite(SaasSite saasSite);

    int updateSite(SaasSite saasSite);

    int updateStatus(SaasSite saasSite);

    int deleteSiteByIdsOnUser(String id, String[] siteId);

    int closeSite(String id, String siteId);

    TablePageData<SaasSite> selectSiteListOnUser(SaasSite saasSite);

    SaasSite selectSiteById(String siteId);

}
