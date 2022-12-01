package com.ajie.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 测试CommandLineRunner  ----  应用启动时执行一段代码
 *
 * @Author: ajie
 * @Date: 2022/12/1
 */
@Component
@EnableScheduling
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化");
    }
}
