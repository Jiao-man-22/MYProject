package com.jiao.testproject.testproject.services.impl;

import com.jiao.testproject.testproject.dao.TestMapper;
import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.services.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper tm;

    @Override
    public List<FileEntity> testMapperParseObject(FileDto fd) {
        fd.setFileId(1);
        FileEntity info = tm.getInfo(fd);
        List<FileEntity> fileEntities = new ArrayList<>();
        fileEntities.add(info);
        return fileEntities;
    }
}
