package org.hints.tenant.controller.admin;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasTenant;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/11 9:13
 */
@RestController
@RequestMapping("/admin/tenant")
public class AdminTenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping("/list")
    public ReturnVo<TablePageData<SaasTenant>> list(SaasTenant saasTenant)
    {
        return ReturnVo.success(tenantService.querySaasTenant(saasTenant));
    }

    @GetMapping(value = "/{tenant_id}")
    public ReturnVo fetchSaasTenant(@PathVariable("tenant_id") String tenantId)
    {
        return ReturnVo.success(tenantService.fetchSaasTenant(tenantId));
    }

    @DeleteMapping(value = "/stop/{tenant_ids}")
    public ReturnVo stopTenant(@PathVariable("tenant_ids") Long[] tenantIds)
    {
        return ReturnVo.toAjax(tenantService.stopSaasTenant(tenantIds));
    }

    @DeleteMapping("/{tenant_ids}")
    public ReturnVo remove(@PathVariable("tenant_ids") Long[] tenantIds)
    {
        return ReturnVo.toAjax(tenantService.delSaasTenant(tenantIds));
    }


}
