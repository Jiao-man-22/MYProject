package com.jiao.testproject.testproject;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.entity.FileEntity;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
public class Test_3  implements Runnable {

    @Autowired
    private FileEntityMapper fileEntityMapper;


    @Test
    public void test4(){
        List<FileEntity> fileEntities = fileEntityMapper.selectList(new QueryWrapper<>());

    }

    @Test
    public void test() {
        Test_3 thread1 = new Test_3();
        Test_3 thread2 = new Test_3();
        Test_3 thread3 = new Test_3();
        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();

    }

    @Test
    public void test2() {
        for (int i = 0; i < 500000; i++) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName("武大郎烧饼记——" + i + "部");
            fileEntity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            fileEntity.setFileSize(123 + "");
            fileEntity.setUserId(1);
            fileEntity.setUserId(1);
            fileEntityMapper.insert(fileEntity);
        }
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000; i++) {

            FileEntity fileEntity = new FileEntity();

            fileEntity.setFileName("武大郎烧饼记——" + i + "部");

            fileEntity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

            fileEntity.setFileSize(123 + "");

            fileEntity.setUserId(1);

            fileEntity.setUserId(1);

            fileEntityMapper.insert(fileEntity);
        }
    }

    @Test
    void testThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100000; j++) {
                    //TODO: 线程执行的逻辑
                    FileEntity fileEntity = new FileEntity();
                    fileEntity.setFileName("倚天屠龙记——" + j + "部");
                    fileEntity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                    fileEntity.setFileSize(123 + "");
                    fileEntity.setUserId(1);
                    fileEntity.setUserId(1);
                    fileEntityMapper.insert(fileEntity);
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {

            //等待所有任务完成
            System.out.println("---------1111------2222------3333-----4444---------");

        }
    }

    @Test
    void testMD5() {

        String str = "hello world";

        // 编码
        byte[] encodedBytes = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        String encodedStr = new String(encodedBytes, StandardCharsets.UTF_8);
        System.out.println("Base64 encoded string: " + encodedStr);

        // 解码
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        String decodedStr = new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println("Base64 decoded string: " + decodedStr);
    }


    @Test
    void testComp() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bb");
        list.add("c");
        list.add("ddddd");

        //比较
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };
        Collections.sort(list,comparator);
        System.out.println(list);
    }

}