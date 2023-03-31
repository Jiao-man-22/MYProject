package com.jiao.testproject.testproject.controller.thread;

import com.jiao.testproject.testproject.utils.MultiThreadDBQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/testThread")
public class TestThreadController {

    @RequestMapping
    void test(){
//        MultiThreadDBQuery thread1 = new MultiThreadDBQuery(1, 1000);
//        MultiThreadDBQuery thread2 = new MultiThreadDBQuery(1000, 2000);
//        MultiThreadDBQuery thread3 = new MultiThreadDBQuery(2000, 3000);

//        new Thread(thread1).start();
//        new Thread(thread2).start();
//        new Thread(thread3).start();
    }
}
