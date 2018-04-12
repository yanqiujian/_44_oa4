package com.qianfeng.oa.dao;

import com.qianfeng.oa.dto.UserDTO;

import java.util.List;

public interface IUserDAO {

    UserDTO checkUserByName(String username);

    List<String> queryRoleByName(String username);

    List<String> queryPermissionByName(String username);
}
