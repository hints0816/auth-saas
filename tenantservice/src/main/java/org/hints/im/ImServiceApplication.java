package org.hints.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description TODO
 * @Author hints
 * @Date 2022/7/7 9:04
 */
@SpringBootApplication
@ComponentScan({"org.hints.*"})
public class ImServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImServiceApplication.class, args);
    }


}
