package com.jiao.testproject.testproject.services.impl;




import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.entity.UserEntity;
import com.jiao.testproject.testproject.services.IRegisterService;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl extends ServiceImpl<UserDao, UserEntity> implements IRegisterService {



}
