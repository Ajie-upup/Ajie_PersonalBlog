package com.ajie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author: ajie
 * @Date: 2022/12/2
 */
@SpringBootApplication
@MapperScan("com.ajie.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
