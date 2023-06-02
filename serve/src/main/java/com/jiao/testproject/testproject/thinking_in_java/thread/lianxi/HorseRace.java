//package com.jiao.testproject.testproject.thinking_in_java.thread.lianxi;
//
//import java.sql.Array;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class HorseRace {
//    static final int FINISH_LINE = 75 ;
//    List<Horse> horses = new ArrayList<Horse>();
//    private ExecutorService service =Executors.newCachedThreadPool();
//    private CyclicBarrier barrier ;
//
//    public HorseRace(int nHorses , final int pause) {
//        new CyclicBarrier(nHorses, new Runnable() {
//            @Override
//            public void run() {
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < FINISH_LINE; i++) {
//                    stringBuilder.append(" = ");
//                    System.out.println(stringBuilder);
//                    for (Horse horse: horses) {
//                        System.out.println(horse.tracks());
//                    }
//                    for (Horse horse: horses) {
//                        if (horse.getStrides() >= FINISH_LINE){
//                            System.out.println(horse + "won! ");
//                            service.shutdown();
//                            return;
//                        }
//                    }
//                }
//            }
//        })
//
//    }
//}
