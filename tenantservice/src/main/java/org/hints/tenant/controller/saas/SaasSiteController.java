package org.hints.tenant.controller.saas;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasSite;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/site")
public class SaasSiteController {

    @Autowired
    private SiteService siteService;

    @GetMapping("/list")
    public ReturnVo<TablePageData<SaasSite>> tenantlist(SaasSite saasSite)
    {
        TablePageData<SaasSite> saasSiteTablePageData = siteService.selectSiteListOnUser(saasSite);
        return ReturnVo.success(saasSiteTablePageData);
    }

    @GetMapping(value = "/{siteId}/{oraId}")
    public ReturnVo getInfo(@PathVariable("siteId") String siteId, @PathVariable("oraId") String oraId)
    {
        SaasSite saasSite = siteService.selectSiteById(oraId, siteId);
        return ReturnVo.success(saasSite);
    }

    @PostMapping
    public ReturnVo add(@RequestBody SaasSite saasSite)
    {
        return siteService.createSite(saasSite);
    }

    @PutMapping
    public ReturnVo edit(@RequestBody SaasSite saasSite)
    {
        return ReturnVo.toAjax(siteService.updateSite(saasSite));
    }

    @PutMapping(value = "/status")
    public ReturnVo updateStatus(@RequestBody SaasSite saasSite)
    {
        return ReturnVo.toAjax(siteService.updateStatus(saasSite));
    }

    @DeleteMapping("/{siteIds}/{id}")
    public ReturnVo removeOnUser(@PathVariable String[] siteIds, @PathVariable String id)
    {
        return ReturnVo.toAjax(siteService.deleteSiteByIdsOnUser(id, siteIds));
    }

    @DeleteMapping("/close/{siteId}/{id}")
    public ReturnVo closeOnUser(@PathVariable String siteId, @PathVariable String id)
    {
        return ReturnVo.toAjax(siteService.closeSite(id, siteId));
    }

    @GetMapping("/download")
    public void download(@RequestParam String path, HttpServletResponse response) {
    }
}
