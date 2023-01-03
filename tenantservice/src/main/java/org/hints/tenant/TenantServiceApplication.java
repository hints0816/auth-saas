package org.hints.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Description TODO
 * @Author hints
 * @Date 2022/7/7 9:04
 */
@EnableResourceServer
@SpringBootApplication
@ComponentScan({"org.hints.*"})
public class TenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantServiceApplication.class, args);
    }


}
