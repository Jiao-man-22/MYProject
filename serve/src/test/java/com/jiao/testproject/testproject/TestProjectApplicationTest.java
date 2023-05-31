package com.jiao.testproject.testproject;

import cn.hutool.core.util.RandomUtil;
import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dao.impl.CustomerCrudRepository;
import com.jiao.testproject.testproject.dto.FileViewVo;
import com.jiao.testproject.testproject.dto.FolderDto;
import com.jiao.testproject.testproject.dto.Node;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.entity.UserEntity;
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
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


@SpringBootTest
class TestProjectApplicationTest {
    @Resource
    CustomerCrudRepository customerCrudRepository;
    @Resource
    UserDao userDao;

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;
    @Test
    void contextLoads() throws InstantiationException, IllegalAccessException {
        setValue();
    }

    //redis 存值
    public void setValue(){
        redisTemplate.opsForValue().set("61儿童节","6-1");
    }
}