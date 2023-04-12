package com.jiao.testproject.testproject.services.login;

import com.jiao.testproject.testproject.dao.UserDao;
import com.jiao.testproject.testproject.dto.SysPermission;
import com.jiao.testproject.testproject.services.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

//    @Qualifier("userServicPermiss")
//    IUserService userService;

    @Autowired
    UserDao userDao;

    @Transactional
    public List<SysPermission> findByUserId(String userId){
        List<SysPermission> userPermission = userDao.getUserPermission(userId);
        if (userPermission == null ){
            log.info(" Class : PermissionService userPermission == null \t "   );
        }
        return userPermission;
    }
}
