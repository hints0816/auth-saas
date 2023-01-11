package org.hints.tenant.controller;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasOracle;
import org.hints.common.pojo.TablePageData;
import org.hints.tenant.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/entity")
public class SaasEntityController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/list")
    public ReturnVo<TablePageData<SaasOracle>> list(SaasOracle saasOracle)
    {
        return ReturnVo.success(entityService.querySaasOracleOnUser(saasOracle));
    }

    @GetMapping(value = "/{tenant_id}")
    public ReturnVo fetchSaasOracle(@PathVariable("tenant_id") String tenantId)
    {
        return ReturnVo.success(entityService.fetchSaasOracle(tenantId));
    }

    @PutMapping
    public ReturnVo edit(@RequestBody SaasOracle saasOracle)
    {
        return ReturnVo.toAjax(entityService.updateSaasOracle(saasOracle));
    }

}
