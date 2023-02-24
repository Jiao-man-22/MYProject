package com.jiao.testproject.testproject.thread;

import lombok.SneakyThrows;

public class ThreadTest_1 {


    static int count = 0;

    public static void main(String[] args){

    Thread thread1 = new Thread(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t"+ Thread.currentThread().getId() + "\t" + count ++ );
                Thread.sleep(100);
            }
        }
    });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 10;i++) {
                    count ++;
                    System.out.println(Thread.currentThread().getName() + "\t"+ Thread.currentThread().getId() + "\t" + count  );
                }

            }
        });
        thread2.start();

    }
}
