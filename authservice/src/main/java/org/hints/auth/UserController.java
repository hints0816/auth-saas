package org.hints.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Description TODO
 * @Author 180686
 * @Date 2023/1/6 18:22
 */
@RestController
public class UserController {

    @RequestMapping(value = "/users/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }
}
