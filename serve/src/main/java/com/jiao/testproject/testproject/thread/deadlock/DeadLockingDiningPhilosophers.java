package com.jiao.testproject.testproject.thread.deadlock;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadLockingDiningPhilosophers {

    public static void main(String[] args) {
        int ponder = 5 ;
        if (args.length > 0) ponder =  Integer.parseInt(args[0]);
        int size = 5 ;
        if (args.length > 1) size = Integer.parseInt(args[1]);
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建一个筷子的数组
        ChopStick[] sticks =  new ChopStick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new ChopStick();
        }
        for (int i = 0; i < size; i++) {
            executorService.execute(new Philosopher(sticks[i],sticks[(i+1) % size] ,i,ponder));
        }
        if (args.length == 3 && args[2].equals("timeout")) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Press 'Enter ' to quit ");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
