package com.jiao.testproject.testproject.thinking_in_java.thread.aqs;

public class Main {

    public static void main(String[] args) {

        Counter counter = new Counter(0);
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }

        }).start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.decrement();
            }
        }).start();
    }
}
