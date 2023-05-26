package com.jiao.testproject.testproject.demo.util;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author lhy
 * @Date 2022/5/13
 */
@Slf4j
public class ThreadUtil {

    private volatile static ThreadUtil threadUtil;

    private ThreadPoolExecutor executor;

    /**
     * 单例
     */
    private ThreadUtil() {
    }

    public static ThreadUtil getThreadUtilInstance() {
        if (null == threadUtil) {
            synchronized (ThreadUtil.class) {
                if (null == threadUtil) {
                    threadUtil = new ThreadUtil();
                }
            }
        }
        return threadUtil;
    }

    /**
     * 提交任务
     *
     * @param task
     */
    public Future<?> submit(Runnable task) {
        if (executor == null) {
            // 初始化线程池
            executor = initialize();
        }
        // 执行线程
        return executor.submit(task);
    }

    /**
     * 初始化线程池
     *
     * @return
     */
    private synchronized ThreadPoolExecutor initialize() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new ThreadFactoryBuilder()
                        .setNameFormat("task-admin-getlist--%d")
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("===================>ThreadUtil线程池初始化");
        return executor;
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }

}

