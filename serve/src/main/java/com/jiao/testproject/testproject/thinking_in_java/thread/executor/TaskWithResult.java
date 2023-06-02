package com.jiao.testproject.testproject.thinking_in_java.thread.executor;

import java.util.concurrent.Callable;

public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id ;
    }

    @Override
    public String call() throws Exception {
        return " result of TaskWithResult " + id;
    }
}
