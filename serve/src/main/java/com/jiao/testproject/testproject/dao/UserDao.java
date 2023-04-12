package com.jiao.testproject.testproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiao.testproject.testproject.dto.SysPermission;
import com.jiao.testproject.testproject.dto.pojo.Department;
import com.jiao.testproject.testproject.dto.pojo.UserRole;
import com.jiao.testproject.testproject.dto.pojo.user;

import com.jiao.testproject.testproject.entity.UserEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    /*update password*/
    int updatePasswordById(UserEntity userEntity);

    /*注销登录*/
    int logOutUserById(UserEntity userEntity);

    /*delete account*/
    int deleteAccountById(@Param("user_id") Integer user_id);

    /*查询用户 */
    UserEntity selectUserById(@Param("user_id") Integer user_id);

    /*查询所有的用户*/
    List<UserEntity>selectUserAll();

    int deleteUserAndFileById(Integer userid);

    List<String> selectFunctions(@Param("role") Integer role);

    /* 获取用户的角色*/
     UserRole getUserRole();

    user getUserByDepId(@Param("id") Integer departmentId);

    Department getDepartmentInfoAndEmpee();


    /****
    * @Description:
    * @Param: [user]
    * @return: com.jiao.testproject.testproject.entity.UserEntity
    * @Author: JRJ
    * @Date: 2022/12/21
    */
    UserEntity getUserByCondition(UserEntity user);

    /****
    * @Description:
    * @Param: [userId]
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: JRJ
    * @Date: 2022/12/21
    */

    List<SysPermission> getUserPermission(String userId);

    @MapKey("userId")
    List<Map<Object, Object> >getUserDetails();

    @MapKey("rodeCode")
    List<Map<Object, Object> >getUserRoleCount();

}
