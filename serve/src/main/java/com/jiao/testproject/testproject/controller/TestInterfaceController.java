package com.jiao.testproject.testproject.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.jiao.testproject.testproject.dto.AjaxResult;
import com.jiao.testproject.testproject.dto.FileDto;
import com.jiao.testproject.testproject.dto.SysPermission;
import com.jiao.testproject.testproject.dto.UserDto;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.entity.UserEntity;
import com.jiao.testproject.testproject.services.IPermissionService;
import com.jiao.testproject.testproject.services.ITestService;
import com.jiao.testproject.testproject.services.IUserService;
import com.jiao.testproject.testproject.services.login.PermissionService;
import com.jiao.testproject.testproject.utils.CacheService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestInterfaceController {

    /***
    * @Description: 基于构造注入 和 set注入
    * @Param: []
    * @return:
    * @Author: JRJ
    * @Date: 2022/12/28
    */

    private IPermissionService permissionService;

    private IUserService userService;

    private RedisTemplate redisTemplate;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    ITestService testService;

    @Autowired
    public  TestInterfaceController(IPermissionService ps, IUserService ius
            , @Qualifier("redisTemplate") RedisTemplate rt
            , StringRedisTemplate srt){
        permissionService = ps;
        userService = ius;
        redisTemplate = rt;
        stringRedisTemplate = srt;
    }

    // ================================业务代码
    @GetMapping("/Test/test")
    public AjaxResult Test(){
        List<UserEntity> userEntities = userService.selectUserAll();
        return AjaxResult.success(userEntities);
    }

    @GetMapping("/Test/getRoleInfoDetails")
    public AjaxResult getRoleInfoDetails(){
        List<SysPermission> byUserId = permissionService.findByUserId("1");
        Assert.notEmpty(byUserId,"不为空");
        byUserId.stream().forEach(x->{
            System.out.println(x.getRole_code());
            System.out.println(x.getUser_role());
        });
        UserDto user = new UserDto();
        user.setUserName("jiaorongjin");
        user.setPassword("123456");
        List clientList = redisTemplate.getClientList();
        for (int i = 0; i < clientList.size(); i++) {
            Object o = clientList.get(0);
        }
        redisTemplate.opsForValue().set("user-j", user);
        System.out.println("redis设置成功");
        Object o = redisTemplate.opsForValue().get("user-j");
        System.out.println(((UserDto)o).getUserName());
        Object user1 = redisTemplate.opsForValue().get("user");
        Object test = redisTemplate.opsForValue().get("test");
        System.out.println(user1+"");
        System.out.println(test+"");
        String test1 = stringRedisTemplate.opsForValue().get("test");
        System.out.println(test1);
        return AjaxResult.success(byUserId);
    }
    @GetMapping("/test")
    public void test(){
        List<FileEntity> fileEntities = testService.testMapperParseObject(new FileDto());
    }

}
