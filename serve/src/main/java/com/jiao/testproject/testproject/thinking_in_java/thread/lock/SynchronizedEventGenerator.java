package com.jiao.testproject.testproject.thinking_in_java.thread.lock;

public class SynchronizedEventGenerator extends IntGenerator{
    //当前 偶数值
    private int currentEvenValue = 0 ;

    @Override
    public synchronized  int next() {
        ++ currentEvenValue;
        Thread.yield();
        ++ currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEventGenerator());
    }
}
