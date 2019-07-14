package com.lottery.util.Scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledDemo {
    @Scheduled(fixedRate = 3000)
    public void doTime(){
        Date date = new Date();
        System.out.println(date);
    }
}
