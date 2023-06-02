package com.jiao.testproject.testproject.thinking_in_java.thread.deadlock;

public class ChopStick {

    // 标记 筷子状态
    private boolean taken = false ;

    // 当筷子 被占用 其它线程等待
    public synchronized void take() {
        while(taken){
            try {
                wait();
                taken = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void  drop(){
        taken = false ;
        notifyAll();
    }
}
