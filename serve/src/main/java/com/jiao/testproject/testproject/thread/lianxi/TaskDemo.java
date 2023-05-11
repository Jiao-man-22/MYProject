package com.jiao.testproject.testproject.thread.lianxi;

import cn.hutool.core.util.RandomUtil;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;

public class TaskDemo implements Runnable{
    @SneakyThrows
    @Override
    public void run() {
        int i = RandomUtil.randomInt(0, 10);
        long start = System.currentTimeMillis();
        Thread.sleep(i*1000L);
        long end = System.currentTimeMillis();
        long l = end - start;

    }

    public static void main(String[] args) throws InterruptedException {
        int i = RandomUtil.randomInt(0, 10);
        long start = System.currentTimeMillis();
        Thread.sleep(i*1000L);
        long end = System.currentTimeMillis();
        long l = end - start;
        System.out.println("原始数据"+l);
        System.out.println("时间卓" + new Timestamp(l));
    }
}
