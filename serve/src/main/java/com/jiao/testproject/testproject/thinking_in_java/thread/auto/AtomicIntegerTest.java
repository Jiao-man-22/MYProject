package com.jiao.testproject.testproject.thinking_in_java.thread.auto;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {

    private AtomicInteger i = new AtomicInteger();

    public int getValue(){
        return i.get();
    }

    private void evenIncrement(){
        i.addAndGet(2);
    }

    @Override
    public void run() {
        evenIncrement();
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        },5000); // 五秒后中断

    }
}
