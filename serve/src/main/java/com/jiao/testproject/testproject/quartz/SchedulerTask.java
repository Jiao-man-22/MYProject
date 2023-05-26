package com.jiao.testproject.testproject.quartz;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SchedulerTask {

//    @Scheduled(cron = "0/10 * * * * ? ")
   public void schedulerPrint(){
        System.out.println("=====定时打印===== ");
    }
}
