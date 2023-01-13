package org.hints.tenant.controller.user;

import org.hints.common.pojo.ReturnVo;
import org.hints.tenant.service.UserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserBaseController {

    @Autowired
    private UserBaseService userBaseService;

    @GetMapping("/comp/list")
    public ReturnVo comoList() {
        return userBaseService.compList();
    }

}
