package com.jiao.testproject.testproject.thinking_in_java.thread.lock;

public abstract class IntGenerator {

    //原子性 可见性
    private volatile  boolean canceled = false ;

    public abstract int next();

    public void canceled() {
        this.canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }
}
