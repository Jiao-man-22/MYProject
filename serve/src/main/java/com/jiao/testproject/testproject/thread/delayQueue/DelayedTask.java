package com.jiao.testproject.testproject.thread.delayQueue;

import com.zaxxer.hikari.util.ClockSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Runnable , Delayed {

    private static int counter = 0 ;

    private final int id = counter ++ ;

    private final int delta ;

    private final long trigger;

    protected  static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delta) {
        this.delta = delta;
        trigger = System.nanoTime() + delta  ;
        sequence.add(this);
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public void run() {

    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }
}
