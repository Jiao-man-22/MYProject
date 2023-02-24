package com.jiao.testproject.testproject.services;

import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.entity.FileEntity;

import java.util.List;

public interface ITestService {
    List<FileEntity> testMapperParseObject(FileDto fd);
}
