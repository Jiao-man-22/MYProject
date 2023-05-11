package com.jiao.testproject.testproject.demo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dto.AjaxResult;
import com.jiao.testproject.testproject.dto.FolderDto;
import com.jiao.testproject.testproject.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class TestController {

    @Autowired
    private UserDao userDao;

    @PostMapping("/getUserMap")
    public AjaxResult geUserMap(){
        List<Map<String, UserEntity>> userMap = userDao.getUserMap();
        userMap.forEach(map->{
        map.forEach((k,v) -> {
            System.out.println(k + " : " + v);
        });
        });
        return AjaxResult.success(userMap);
    }

    /*测试pageHelper分页*/
    @PostMapping("/getPage")
    public AjaxResult getPage(){
        PageHelper.startPage(1,10);
        List<UserEntity> userEntities = userDao.selectUserAll();
        PageInfo<UserEntity> userEntityPageInfo = new PageInfo<>(userEntities);
        long total = userEntityPageInfo.getTotal();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("count" ,total);
        objectObjectHashMap.put("pageData",userEntityPageInfo);
        objectObjectHashMap.put("commonData",userEntities);
        return AjaxResult.success(objectObjectHashMap);
    }
}
