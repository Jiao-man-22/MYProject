package com.jiao.testproject.testproject.thinking_in_java.thread.deadlock;

import java.util.ArrayList;

class Count{

    private static int count = 0 ;

    static   void  incrementing()  {
        ++ count;
        try {
            Thread.sleep(1);  //  沉睡之前
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "monitor print " +  count );

    }

    public static int getVal(){
        return count;
    }

}

public class Demo {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    int incrementing = Count.incrementing();
//                    System.out.println(incrementing);
//                }
//            });
//        }

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                Count.incrementing();
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread th:threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println( "总  " + Count.getVal());
        }
    }
