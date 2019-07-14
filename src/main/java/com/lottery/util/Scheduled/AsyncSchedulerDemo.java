package com.lottery.util.Scheduled;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
@Async
public class AsyncSchedulerDemo {
    public Future<String> task1() {
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("任务1耗时：" + (endTime - startTime));
        return new AsyncResult<>("任务1");
    }
    public Future<String> task2() {
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("任务2耗时：" + (endTime - startTime));
        return new AsyncResult<>("任务2");
    }
    public Future<String> task3() {
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("任务3耗时：" + (endTime - startTime));
        return new AsyncResult<>("任务3");
    }
}