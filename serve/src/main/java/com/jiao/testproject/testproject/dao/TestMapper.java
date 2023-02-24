package com.jiao.testproject.testproject.dao;

import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestMapper {
    FileEntity getInfo(@Param("fd") FileDto fileDto);
}
