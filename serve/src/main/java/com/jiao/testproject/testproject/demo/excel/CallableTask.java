package com.jiao.testproject.testproject.demo.excel;

import antlr.collections.impl.LList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.entity.FileEntity;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CallableTask implements Callable<List> {

    private FileEntityMapper fileEntityMapper;
    private int startIndex;
    private int size ;
    private List result;
    private CountDownLatch cd = new CountDownLatch(3);

    public CallableTask(FileEntityMapper fileEntityMapper, int startIndex, int size) {
        this.fileEntityMapper = fileEntityMapper;
        this.startIndex = startIndex;
        this.size = size;
    }

    public CallableTask(FileEntityMapper fileEntityMapper, int startIndex, int size, CountDownLatch cd) {
        this.fileEntityMapper = fileEntityMapper;
        this.startIndex = startIndex;
        this.size = size;
        this.cd = cd;
    }

    @Override
    public List call() throws Exception {
        try {
            Page<FileEntity> fileEntityPage = this.fileEntityMapper
                    .selectPage(new Page<>(startIndex, size), new QueryWrapper<FileEntity>());
            result = fileEntityPage.getRecords();
        } finally {
                cd.countDown();
        }
        return result;
    }
}
