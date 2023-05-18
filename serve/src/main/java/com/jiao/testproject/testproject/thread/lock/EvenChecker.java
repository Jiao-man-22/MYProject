package com.jiao.testproject.testproject.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements  Runnable{

    private IntGenerator generator;

    private  final int id ;

    public EvenChecker(IntGenerator generator,int ident) {
        this.generator = generator;
        this.id = ident;
    }

    @Override
    public void run() {
        while(!generator.isCanceled()){
            int val = generator.next();
            if (val % 2 != 0 ){
                System.out.println(val + " 不能被二 整除");
                generator.canceled();
            }else {
                System.out.println(val + " 能被二 整除");
            }
        }
    }

    public static void  test(IntGenerator gp , int count ){
        System.out.println("Press Control-C to exit ");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executorService.execute(new EvenChecker(gp , i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator gp){
        test(gp,10);
    }

}
