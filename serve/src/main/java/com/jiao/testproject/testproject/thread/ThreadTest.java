package com.jiao.testproject.testproject.thread;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @AUTHOR jiaorongjin
 * @Date 2022/9/19 12:37
 * @Version 1.0
 **/


public class ThreadTest {

    private static volatile int count = 0;


    public static void main(String[] args){
        Lock lock=new ReentrantLock();
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId() + "\t" +count);
        Thread thread = new Thread(new Runnable() {
           @SneakyThrows
           @Override
           public void run() {
               lock.lock();
               for (int i =0 ;i<99;i++){
                   count++;
                   System.out.println(Thread.currentThread().getName() + "\t"+ Thread.currentThread().getId() + "\t" + count);
                   Thread.sleep(100);
                   lock.unlock();
               }

           }
       });

       thread.start();
       Thread thread2 = new Thread(new Runnable() {
           @SneakyThrows
           @Override
           public void run() {
               lock.lock();
               for (int i =0 ;i<99;i++){
                   count++;
                   System.out.println(Thread.currentThread().getName() + "\t"+ Thread.currentThread().getId() + "\t" + count);
                   Thread.sleep(100);
                   lock.unlock();
               }

           }
       });
       thread2.start();
       //System.out.println(Thread.currentThread().getName() + "\t" + Thread.currentThread().getId() + "\t" +count);
   }
}
