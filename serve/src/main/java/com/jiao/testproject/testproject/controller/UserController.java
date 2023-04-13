package com.jiao.testproject.testproject.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dto.AjaxResult;
import com.jiao.testproject.testproject.dto.UserDto;
import com.jiao.testproject.testproject.dto.pojo.Department;
import com.jiao.testproject.testproject.dto.pojo.UserRole;
import com.jiao.testproject.testproject.entity.UserEntity;
import com.jiao.testproject.testproject.services.IRegisterService;
import com.jiao.testproject.testproject.services.IUserService;
import com.jiao.testproject.testproject.services.email.MSGService;
import com.jiao.testproject.testproject.utils.RandomUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Log4j2
public class UserController {
    @Autowired
    private IUserService UserService;

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private MSGService msgService;

    private static final Integer integer_0 = 0;
    private static final Integer integer_1 = 1;

    @Resource
    private UserDao userDao;

    /*查询所有用户*/
    @GetMapping("/getUserAll")
    public AjaxResult getAllUser(){
        List<UserEntity> userEntities = UserService.selectUserAll();
        if (userEntities!= null && userEntities.size()>0){
            return AjaxResult.success("200",userEntities);
        }
        return AjaxResult.error("userEntities == null");
    }
    /*删除 user*/
    @PostMapping("/delUser")
    public AjaxResult deleteUserById(@RequestParam(required = false) Integer userid){
        if (userid != null){
            UserDto userDto = new UserDto();
            userDto.setUuid(userid);
            //Integer integer = UserService.deleteUserById(userDto);
            int jpaFlag=0;
            Integer integer = UserService.deleteUserById(userDto, jpaFlag);
            if (integer>0){
                return AjaxResult.success("操作成功",integer);
            }
        }
        return AjaxResult.error("500");
    };
    @PostMapping("/loginOutUser")
    public AjaxResult loginOutUser(@RequestParam String username,@RequestParam Integer userid){
        if (userid != null){
            UserDto userDto = new UserDto();
            userDto.setUserName(username);
            userDto.setUuid(userid);
            UserEntity userEntity = UserService.selectUserById(userDto);

            userDto.setStatus(userEntity.getStatus());
            if (userEntity != null && !(userEntity.getStatus().equals(integer_1))){
                Integer integer = UserService.logOutUser(userDto);
                if (integer>0){
                    return AjaxResult.success("注销登录","1");
                }
            }
        }
        return AjaxResult.error("1");
    }


    /*修改密码*/
    @PostMapping("/updateUserPassword")
    public AjaxResult updateUserPassword(@RequestParam String password,@RequestParam String username ,@RequestParam Integer userid){
        if (password != null && username != null && userid != null){
            UserDto userDto = new UserDto();
            userDto.setPassword(password);
            userDto.setUuid(userid);
            Integer integer = UserService.updatePasswordById(userDto);
            if (integer>0){
                return AjaxResult.success("200");
            }
        }
        return AjaxResult.error();
    }
    /*删除 User file*/
    @PostMapping("/delUserAndFileById")
    public AjaxResult delUserAndFileById(String userid){

        if (userid ==null || userid.equals("''")){
            return AjaxResult.error("userid 不能为空");
        }
        int i = UserService.delUserAndFileByUid(Integer.valueOf(userid));
        if (i==0){
            return AjaxResult.error("NG",i);
        }
        return AjaxResult.success("Ok",i);
    }

    @GetMapping("/getUserPermission")
    public AjaxResult getUserPermission(String role){
        if (role ==null || role.equals("''")){
            return AjaxResult.error("userid 不能为空");
        }
        List<String> strings = UserService.selectFunctions(Integer.valueOf(role));
        return AjaxResult.success("成功",strings);
    }


    @GetMapping("/getUserRole")
    public AjaxResult getUserRole(){
        UserRole userRoleInfo = UserService.getUserRoleInfo();
        return AjaxResult.success("成功",userRoleInfo);
    }

    @GetMapping("/getUserByDepId")
    public AjaxResult getUserByDepId(){
        Department departmentInfoAndEmpee = userDao.getDepartmentInfoAndEmpee();
        return AjaxResult.success("成功",departmentInfoAndEmpee);
    }

    /**
    * @Description:  查询不同角色的用户
    * @Param:
    * @return:
    * @Author: JRJ
    * @Date: 2023/4/10
    */
    @GetMapping("/getUserDetails")
    public AjaxResult getUserDetails(){
        List<Map<Object, Object>> userDetails = userDao.getUserDetails();

        // 存放 list map中 所有的 key 为 rolecode 的 值
        Set<String> queryPattern = new HashSet<>();
        //存放要返回的数据
        List<Map<Object, Object>> webData = new ArrayList<>();

        userDetails.stream().forEach(map -> {
            String roleCode = (String) map.get("roleCode");
            queryPattern.add(roleCode);
        });

        AtomicInteger count = new AtomicInteger();
        queryPattern.stream().forEach( condition ->{
            System.out.println("--内部循环次数 -- " + count.incrementAndGet() + "匹配条件" + condition );
            List<Map<Object, Object>> collect = userDetails.stream()
                    .filter(x -> {
                        if (condition.equals(x.get("roleCode"))){
                            return true;
                        }else {
                            return false;
                        }

                    })
                    .collect(Collectors.toList());
            System.out.println("打印每次 过滤过 后的 list " );
            collect.stream().forEach(System.out::println);
            webData.addAll(collect);
        });
        System.out.println("分组后的数据 " );
        webData.stream().forEach(System.out::println);

        return AjaxResult.success("成功",webData);
    }

    @GetMapping("/getUserDetailsCount")
    public AjaxResult getUserDetailsCount(){
        List<Map<Object, Object>> userRoleCount = userDao.getUserRoleCount();
        return AjaxResult.success("成功",userRoleCount);
    }
    //新建用户
    @PostMapping("/registerAccount")
    public AjaxResult registerAccount(UserDto ud){

        boolean flag;
        int uuid;
       do{
           uuid = RandomUtil.randomInt(100, 200);
           Serializable userId = Integer.valueOf(uuid);
           UserEntity user2 = registerService.getById(userId);
           if (user2 == null) flag =  false;else flag = true;
       } while(flag);

        UserEntity ue = new UserEntity();
//        LocalDateTime now = LocalDateTime.now();
//        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        ue.setCreate_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        ue.setUser_id(uuid);
//        BeanUtil.copyProperties(ud,ue);
        ue.setUser_name(ud.getUserName());
        ue.setUser_password(ud.getPassword());
        ue.setStatus(ud.getStatus());
        ue.setRole(ud.getRole());
        boolean save = registerService.save(ue);
        return AjaxResult.success("成功",save);
    }

    @GetMapping("send/{phone}/interAspect")
    public void sendMsm(@PathVariable String phone){
        //生成随机数
        String code = RandomUtils.getFourBitRandom();
        Map map = new HashMap();
        map.put("code",code);
        boolean b = msgService.send(map,phone);
    }

}
