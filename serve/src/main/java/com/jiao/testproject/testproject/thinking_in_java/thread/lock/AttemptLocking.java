package com.jiao.testproject.testproject.thinking_in_java.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

///sync 关键字不会尝试获取锁
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    public void untimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock() : " + captured);
        } finally {
            if (captured){
                lock.unlock();
            }
        }
    }
    public void timed(){
        boolean captured = false ;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock （2,timeUnit.seconds ）: " + captured);
        } finally {
            if (captured) lock.unlock();
        }
    }

    public static void main(String[] args) {

      final  AttemptLocking attemptLocking = new AttemptLocking();

      Thread t = new Thread(new Runnable() {
          @Override
          public void run() {
              //获取锁
            attemptLocking.lock.lock();
            System.out.println("   acquired   ");
          }
      });
      t.setDaemon(true);
      t.start();
      Thread.yield();
      attemptLocking.untimed();
      attemptLocking.timed();



    }
}
