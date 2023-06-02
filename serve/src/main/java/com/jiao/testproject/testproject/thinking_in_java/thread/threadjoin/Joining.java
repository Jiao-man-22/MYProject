package com.jiao.testproject.testproject.thinking_in_java.thread.threadjoin;
//
public class Joining {
    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleepy" , 1000);
        Sleeper grumpy = new Sleeper("Grumpy" , 1000);
        Joiner joiner = new Joiner("join1 " , sleepy);
        Joiner doc = new Joiner("join2", grumpy);
        try {
//            Thread printThread = new Thread(() -> {
//                int count = 0;
//                while (count++ < 7)
//                    System.out.println("中断 " + count + " s");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            printThread.setDaemon(true);
//            printThread.start();
            int count = 0 ;
            while( count ++ < 6){
                System.out.println("中断 " + count + " s");
                Thread.sleep(1000);
            }
            System.out.println("中断 grumpy " + count + " s");
            //线程 中断 了 不能再 睡眠 ，但 线程睡眠了 可以中断
            grumpy.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //sleepy.interrupt();
        System.out.println("=== main 处理结束 =====");


    }
}
