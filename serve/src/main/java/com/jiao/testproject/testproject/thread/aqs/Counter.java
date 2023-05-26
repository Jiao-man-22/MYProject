package com.jiao.testproject.testproject.thread.aqs;

public class Counter {

    private final AQS aqsSync;

    public Counter(int initCount) {
        this.aqsSync = new AQS(initCount);
    }

    void increment(){
    aqsSync.acquireShared(1);
    System.out.println("Incremented: " + aqsSync.getCount());
    }
    void decrement(){
        aqsSync.releaseShared(1);
        System.out.println("Decremented： " + aqsSync.getCount());
    }
}
