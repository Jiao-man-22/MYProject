package com.jiao.testproject.testproject.thread.executor;

import java.util.ArrayList;
import java.util.concurrent.*;
// 带返回值的 任务执行
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        //submit 方法 会产生Future 对象
        for (int i = 0; i < 10; i++) results.add(executorService.submit(new TaskWithResult(i)));
        for (Future<String> fs: results) {
            System.out.println(fs.get());
        }

    }
}
