package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyTask {

    // @Scheduled(cron = "*/10 * * * * *")
    public void executeTask(){
        log.info("任务开始: {}", new Date());
    }
}
