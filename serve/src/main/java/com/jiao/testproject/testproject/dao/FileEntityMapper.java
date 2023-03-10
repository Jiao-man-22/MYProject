package com.jiao.testproject.testproject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.entity.FileEntityExample;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileEntityMapper extends BaseMapper<FileEntity> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int countByExample(FileEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int deleteByExample(FileEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer fileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int insert(FileEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int insertSelective(FileEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    List<FileEntity> selectByExample(FileEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    FileEntity selectByPrimaryKey(Integer fileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") FileEntity record, @Param("example") FileEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") FileEntity record, @Param("example") FileEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FileEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table filelist
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FileEntity record);

    int batchDelFileByFileName(List<FileDto> delList);

    int avoidRepeatInsert(FileEntity record);

    IPage<FileEntity> selectPageVo(IPage<?> fileEntity);

    @MapKey("id")
    Map getMapList();
}