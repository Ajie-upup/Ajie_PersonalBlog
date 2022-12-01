package com.ajie.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 实现定时任务
 *
 * @Author: ajie
 * @Date: 2022/12/1
 */
@Component
public class TestJob {
    //进行测试，代表从0秒开始，每隔5秒执行一次。
    @Scheduled(cron = "0/5 * * * * ?")
    public void testJob() {
        //要执行的代码
//        System.out.println("定时任务执行了");
    }

}
