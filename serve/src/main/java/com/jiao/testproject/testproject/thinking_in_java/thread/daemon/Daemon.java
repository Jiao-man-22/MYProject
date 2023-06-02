package com.jiao.testproject.testproject.thinking_in_java.thread.daemon;

import java.util.concurrent.TimeUnit;

public class Daemon implements  Runnable{
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this );
              //  print(Thread.currentThread() + " " + this );
            } catch (InterruptedException e) {
              // print("sleep() 终断") ;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new Daemon());
            daemon.setDaemon(true); // 在运行前 必须调用 将其设置为守护线程
            daemon.start();
            TimeUnit.MILLISECONDS.sleep(175);
        }
    }
}
