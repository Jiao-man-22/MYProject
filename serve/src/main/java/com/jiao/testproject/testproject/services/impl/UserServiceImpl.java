package com.jiao.testproject.testproject.services.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiao.testproject.testproject.dao.FileEntityMapper;
import com.jiao.testproject.testproject.dao.FileEntityRepository;
import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dao.UserRepository;
import com.jiao.testproject.testproject.dto.UserDto;
import com.jiao.testproject.testproject.dto.pojo.UserRole;
import com.jiao.testproject.testproject.entity.*;
import com.jiao.testproject.testproject.entity.QFileEntity;
import com.jiao.testproject.testproject.entity.QPermissionEntity;
import com.jiao.testproject.testproject.entity.QUserEntity;
import com.jiao.testproject.testproject.services.IUserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/*
* 用户的crud*/
@EnableTransactionManagement
//@Service("myUserService")
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final Integer del_flag=1;

    @Resource
    private UserDao userDao;

    @Resource
    private FileEntityMapper fileMapper;

    @Resource
    private FileEntityRepository fileEntityRepository;
    
    @Resource
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //查询工厂实体
    private JPAQueryFactory queryFactory;


    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory()
    {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    //存储用户信息
    private final Map userMap=new ConcurrentHashMap();

    @Override
    public Integer insertUser(UserDto userDto) {
        return null;
    }

    @Override
    @Transactional (propagation = Propagation.REQUIRED,timeout=30)
    public UserEntity selectUserById(UserDto userDto) {
        if (userDto!=null && userDto.getUuid()!= null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userDto.getUuid());
            UserEntity userEntity_result = userDao.selectUserById(userEntity.getUser_id());
            if (!userMap.isEmpty()) {
                userMap.clear();
            } else {
                userMap.put("userinfo",userEntity_result);
            }
            return userEntity_result;
        }
         return null;
    }
    /*重载*/
    @Override
    @Transactional (propagation = Propagation.REQUIRED,timeout=30)
    public UserEntity selectUserById(UserDto userDto , int overloadFlag) {
        if (userDto!=null && userDto.getUserName()!= null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_name(userDto.getUserName());
            Example<UserEntity> of = Example.of(userEntity);
            Optional<UserEntity> result_User = userRepository.findOne(of);
            if (!result_User.isPresent() ){
                return null;
            }
            UserEntity userEntity_result_temp = result_User.get();
            Optional<UserEntity> byId = userRepository.findById(userEntity_result_temp.getUser_id());
            UserEntity userEntity_result = byId.get();
            //获取最新的用户信息
            userMap.clear();
            userMap.put("userinfo",userEntity_result);
            return userEntity_result;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer deleteUserById(UserDto userDto) {
        if (userDto!=null && userDto.getUuid()!= null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userDto.getUuid());
            int delFlag=0;
            try{
                //创建 optional容器对象
                Optional<UserEntity> userEntityNotNull = Optional.of(userEntity);
                Optional<UserEntity> resultUser_option = userRepository.findById(Integer.valueOf(userEntityNotNull.map(s -> s.getUser_name()).orElse("")));
                UserEntity resultUser = resultUser_option.get();
                //userDao.deleteAccountById(userEntity.getUser_id());
                userRepository.deleteById(Optional.of(resultUser).map(r -> r.getUser_id()).get());
                FileEntityExample fileEntityExample = new FileEntityExample();
                FileEntityExample.Criteria criteria = fileEntityExample.createCriteria();
                criteria.andUserIdEqualTo(userDto.getUuid());
                delFlag = fileMapper.deleteByExample(fileEntityExample);
            }catch (Exception e){
                e.printStackTrace();
            }
            return Integer.valueOf(delFlag);
        }
        return null;
    }
    /*
    * 使用jpa 自定sql查询实现*/
    public Integer deleteUserById(UserDto userDto , int overloadFlag) {
        //QUserEntity quserEntity=QUserEntity.userEntity;
        QFileEntity qfileEntity = QFileEntity.fileEntity;

        // 定于获取条件
        //BooleanBuilder booleanBuilder = new BooleanBuilder();
        // 要查询的条件
        if (userDto!=null && userDto.getUuid()!= null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userDto.getUuid());
          //  booleanBuilder.and(quserEntity.user_id.eq(userEntity.getUser_id()));
            int delFlag=0;
            try{
                //创建 optional容器对象
                Optional<UserEntity> userEntityNotNull = Optional.of(userEntity);
                Optional<UserEntity> resultUser_option = userRepository.findById(Integer.valueOf(userEntityNotNull.map(s -> s.getUser_name()).orElse("")));
                UserEntity resultUser = resultUser_option.get();
                //userDao.deleteAccountById(userEntity.getUser_id());
                userRepository.deleteById(Optional.of(resultUser).map(r -> r.getUser_id()).get());
                // 定于获取条件
                BooleanBuilder where = new BooleanBuilder();
                where.and(qfileEntity.userId.eq(resultUser.getUser_id()));
                queryFactory.delete(qfileEntity).where(where);
            }catch (Exception e){
              log.error("jpa 自定义查询 " + e);
            }
            return Integer.valueOf(delFlag);
        }
        return null;
    }

    @Override
    public Map<String, UserEntity> getUserInfoMap() {
        return getUserMap();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer logOutUser(UserDto userDto) {
        if (userDto!=null && userDto.getUuid()!= null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userDto.getUuid());
            if (userDto.getStatus().equals(Integer.valueOf(1))){
                /*表示登录逻辑*/
                userEntity.setStatus(0);
                userRepository.save(userEntity);
                //userDao.logOutUserById(userEntity);
                return 0;
            }
            userEntity.setStatus(del_flag);
            UserEntity saveResult = userRepository.save(userEntity);
            return  Optional.of(saveResult).map(x -> x.getUser_id()).orElse(0);//userDao.logOutUserById(userEntity);
        }
        return null;
    }

    /*
    * @param UserDto
    * return 0 NG: 1 OK
    * */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Integer updatePasswordById(UserDto userDto) {
        if (userDto!=null && userDto.getUuid()!= null && userDto.getPassword()!=null ) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userDto.getUuid());
            // 当前时间戳
            LocalDate today=LocalDate.now();
            /*表示更新密码*/
            Optional<UserEntity> userEntity_result = userRepository.findById(userEntity.getUser_id());
            //UserEntity userEntity_result = userDao.selectUserById(userDto.getUuid());
            if (userEntity_result ==null){
                log.error(" way of updatePasswordById   var userEntity_result = null ");
             return 0;
            }
            UserEntity userEntity_result_element = userEntity_result.get();
            userEntity_result_element.setUser_password(userDto.getPassword());
            UserEntity saveResult = userRepository.save(userEntity_result_element);
            return saveResult.getUser_id(); //userDao.updatePasswordById(userEntity);
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> selectUserAll() {
        //List<UserEntity> userEntities = userDao.selectUserAll();
        List<UserEntity> userEntities = userRepository.findAll();
        if (userEntities!=null && userEntities.size()>0){
           return  userEntities;
        }
        return null;
    }

    @Override
    public int delUserAndFileByUid(Integer userid) {
        int delFlag=0;
        try{
            //delFlag = userDao.deleteUserAndFileById(userid);
            UserEntity userEntity = new UserEntity();
            userEntity.setUser_id(userid);
            userRepository.delete(userEntity);
        }catch (Exception e){
            log.info("自定义sql error" + e);
        }
        return delFlag;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> selectFunctions(Integer role) {
        List<String> functionButton=null;
            QUserEntity quserEntity = QUserEntity.userEntity;
            QPermissionEntity qpermissionEntity = QPermissionEntity.permissionEntity;
            functionButton = queryFactory.select(qpermissionEntity.function)
                    .from(quserEntity, qpermissionEntity)
                    .where(quserEntity.role.eq(qpermissionEntity.role))
                    .fetch().stream().distinct().collect(Collectors.toList());
            //反序
            Collections.reverse(functionButton);
        return functionButton;
    }

    //返回用户信息
    public Map<String,UserEntity> getUserMap(){
        return userMap;
    }

    public UserRole getUserRoleInfo(){
        UserRole userRole = userDao.getUserRole();

        return userRole;
    }

    /****
    * @Description:
     * 查询用户信息 根据提供的条件
    * @Param: [username]
    * @return: com.jiao.testproject.testproject.entity.UserEntity
    * @Author: JRJ
    * @Date: 2022/12/21
    */

    @Override
    public UserEntity getByUsername(String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_name(username);
        UserEntity userByCondition = null;
        try {
             userByCondition = userDao.getUserByCondition(userEntity);
        } catch (Exception e) {
           log.info("line 305 ：error ====   "+ e );
        }
        return userByCondition;
    }

    @Override
    public List<UserEntity> exportExcel() {
        UserEntity userEntity = new UserEntity();
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>(userEntity);
        return  userDao.selectUserAll();
    }




}
