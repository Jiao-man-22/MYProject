package com.jiao.testproject.testproject.thread.localstoren;

// 防止任务 共享线程产生冲突的方式
// 根除 对变量的 共享
public class Accessor implements Runnable {

    private final int id ;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id +
                " : " + ThreadLocalVariableHolder.get();
    }
}
