package com.jiao.testproject.testproject.thinking_in_java.thread.localstoren;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalVariableHolder {

  private static ThreadLocal<Integer> value  = new ThreadLocal<Integer>(){
      private Random rand = new Random(47);
      protected synchronized Integer initialValue(){
          return rand.nextInt(10000);
      }
  };

  public static void increment(){
      value.set(value.get() + 1);
  }

  public static Integer get(){
      return value.get();
  }

  public static void main(String[] args) {
      ExecutorService executorService = Executors.newCachedThreadPool();
      for (int i = 0; i < 5; i++) {
          executorService.execute(new Accessor(i));
          try {
              TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
      executorService.shutdown();
  }



}
