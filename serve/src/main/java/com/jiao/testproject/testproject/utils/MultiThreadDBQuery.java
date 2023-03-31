package com.jiao.testproject.testproject.utils;

import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiThreadDBQuery implements Runnable{

    @Autowired
    private FileEntityMapper fileEntityMapper;


    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName("武大郎烧饼记——" + i + "部");
            fileEntity.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            fileEntity.setFileSize(123+"");
            fileEntity.setUserId(1);
            fileEntity.setUserId(1);
            fileEntityMapper.insert(fileEntity);
        }

    }
}
