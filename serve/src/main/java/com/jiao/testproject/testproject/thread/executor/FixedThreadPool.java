package com.jiao.testproject.testproject.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
            // executorService = Executors.newCachedThreadPool();
        }

    }
}
