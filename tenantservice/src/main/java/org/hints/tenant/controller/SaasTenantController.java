package org.hints.tenant.controller;

import org.hints.common.pojo.ReturnVo;
import org.hints.common.pojo.SaasTenant;
import org.hints.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

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
    public SaasTenant getInfo(Principal principal) throws UnsupportedEncodingException {
        SaasTenant saasTenant = tenantService.fetchTenantInfo();
        return saasTenant;
    }

}
