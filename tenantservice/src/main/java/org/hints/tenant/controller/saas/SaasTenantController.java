package org.hints.tenant.controller.saas;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasTenant;
import org.hints.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant")
public class SaasTenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/register")
    public ReturnVo register(@Validated @RequestBody SaasTenant saasTenant) {
        return tenantService.register(saasTenant);
    }

    @PutMapping("/reset")
    public ReturnVo updatePasswordOnUser(@RequestBody SaasTenant saasTenant) {
        return tenantService.reset(saasTenant);
    }

    @GetMapping(value = "/me")
    public ReturnVo getInfo() {
        SaasTenant saasTenant = tenantService.fetchTenantInfo();
        return ReturnVo.success(saasTenant);
    }

}
