package com.jiao.testproject.testproject.services;

import com.jiao.testproject.testproject.dto.SysPermission;

import java.util.List;

public interface IPermissionService {

     List<SysPermission> findByUserId(String userId);
}
