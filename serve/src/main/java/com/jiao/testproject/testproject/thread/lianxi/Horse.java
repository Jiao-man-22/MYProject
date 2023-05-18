package com.jiao.testproject.testproject.thread.lianxi;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Horse implements Runnable{
    private static int counter = 0 ;
    private final int id = counter ++ ;
    private int strides = 0 ;
    Random random = new Random(47);
    private static CyclicBarrier cyclicBarrier;

    public Horse(CyclicBarrier cyclicBarrier) {
        cyclicBarrier = cyclicBarrier;
    }

    public synchronized int getStrides(){return strides;}

    @Override
    public void run() {
        while (!Thread.interrupted()){
            synchronized (this){
                strides += random.nextInt(3);
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + id +
                ", strides=" + strides +
                ", random=" + random +
                '}';
    }
    public String tracks(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            stringBuilder.append("*");
            stringBuilder.append(id);

        }
        return stringBuilder.toString();
    }

}
