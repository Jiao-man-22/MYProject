package com.jiao.testproject.testproject.demo;

import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dto.AjaxResult;
import com.jiao.testproject.testproject.dto.FolderDto;
import com.jiao.testproject.testproject.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
