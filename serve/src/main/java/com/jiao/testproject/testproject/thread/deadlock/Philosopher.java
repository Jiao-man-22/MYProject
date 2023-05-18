package com.jiao.testproject.testproject.thread.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable{
    private ChopStick left;
    private ChopStick right;
    private final int id ;
    private final int ponderFactory;
    private Random random = new Random(47);

    public void pause(){
        if (ponderFactory == 0)return;
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactory * 250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Philosopher(ChopStick left, ChopStick right, int id, int ponderFactory) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactory = ponderFactory;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            System.out.println(this + " " + "thinking ");
            pause();
            System.out.println(this + "grabbing left");
            left.take();
            System.out.println(this + "grabbing right ");
            right.take();
            System.out.println(this + "eating ");
            pause();
            left.drop();
            right.drop();
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                ", id=" + id +
                '}';
    }
}
