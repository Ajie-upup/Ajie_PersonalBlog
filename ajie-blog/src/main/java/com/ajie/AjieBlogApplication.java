package com.ajie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: ajie
 * @Date: 2022/11/11
 */
@SpringBootApplication
@MapperScan("com.ajie.mapper")
@EnableScheduling
public class AjieBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AjieBlogApplication.class, args);
    }
}
