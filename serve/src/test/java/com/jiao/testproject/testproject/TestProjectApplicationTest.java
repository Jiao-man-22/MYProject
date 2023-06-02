package com.jiao.testproject.testproject;

import cn.hutool.core.util.RandomUtil;
import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dao.impl.CustomerCrudRepository;
import com.jiao.testproject.testproject.dto.FileViewVo;
import com.jiao.testproject.testproject.dto.FolderDto;
import com.jiao.testproject.testproject.dto.Node;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Log4j2
@SpringBootTest
class TestProjectApplicationTest {
    @Resource
    CustomerCrudRepository customerCrudRepository;
    @Resource
    UserDao userDao;

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;
    CountDownLatch countDownLatch = new CountDownLatch(10);
    @Test
    void contextLoads() throws InstantiationException, IllegalAccessException {
//        Long clickQuantity = redisTemplate.opsForValue().increment("clickQuantity");
//        log.info(clickQuantity);
        duoXianCheng();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("mian : 执行结束");
        log.info("线程名称 ： " + Thread.currentThread().getName());

    }

    //redis 存值
    public void setValue(){
        redisTemplate.opsForValue().set("clickQuantity",0);
    }

    public void duoXianCheng(){

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int start = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                   // Long clickQuantity = redisTemplate.opsForValue().increment("clickQuantity");
                    try {
                        log.info("线程名称 ： " + Thread.currentThread().getName() + ", hash值： " + "点击量 ：" + start);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        log.info("线程名称 ： " + Thread.currentThread().getName() + ", hash值： " + "innner " );

        executorService.shutdown();
    }
}