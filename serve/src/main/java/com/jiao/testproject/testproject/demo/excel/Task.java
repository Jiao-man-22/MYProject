package com.jiao.testproject.testproject.demo.excel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class Task implements Runnable{

    @Autowired
    private FileEntityMapper fileEntityMapper;
    //存放 返回的 excel 数据  多线程集合
    private static List<FileEntity> mergerList = Collections.synchronizedList(new ArrayList<>());

    private int offset ;

    @Override
    public void run() {
        Page<FileEntity> fileEntityPage = fileEntityMapper
                .selectPage(new Page<>(this.offset , 10000),
                        new QueryWrapper<FileEntity>());
        List<FileEntity> records = fileEntityPage.getRecords();
        mergerList.addAll(records);
    }


    public Task() {
    }

    public Task(int s) {
        this.offset = s * 10000;
    }

    public static List<FileEntity> getMergerList() {
        return mergerList;
    }

    public static void setMergerList(List<FileEntity> mergerList) {
        Task.mergerList = mergerList;
    }

}
