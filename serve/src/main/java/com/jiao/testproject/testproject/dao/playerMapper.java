package com.jiao.testproject.testproject.dao;

import com.jiao.testproject.testproject.entity.player;
import com.jiao.testproject.testproject.entity.playerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface playerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int countByExample(playerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int deleteByExample(playerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int insert(player record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int insertSelective(player record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    List<player> selectByExample(playerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    player selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") player record, @Param("example") playerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") player record, @Param("example") playerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(player record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table player
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(player record);
}