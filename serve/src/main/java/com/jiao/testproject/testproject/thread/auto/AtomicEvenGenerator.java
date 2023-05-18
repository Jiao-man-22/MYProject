package com.jiao.testproject.testproject.thread.auto;

import com.jiao.testproject.testproject.thread.lock.EvenChecker;
import com.jiao.testproject.testproject.thread.lock.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEvenGenerator extends IntGenerator {
   AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public int next() {
        return atomicInteger.addAndGet(2);
    }

    public static void main(String[] args ){
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
