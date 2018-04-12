package com.qianfeng.oa.dao;

import com.qianfeng.oa.dto.UserDTO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends SqlSessionDaoSupport implements IUserDAO {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public UserDTO checkUserByName(String username) {
        UserDTO user = getSqlSession().selectOne("com.qianfeng.oa.dto.UserMapper.checkUser", username);
        return user;
    }

    public List<String> queryRoleByName(String username) {
        List<String> rpDTO = getSqlSession().selectList("com.qianfeng.oa.dto.RolePermissionMapper.queryRole", username);
        return rpDTO;
    }

    public List<String> queryPermissionByName(String rolename) {
        List<String> rpDTO = getSqlSession().selectList("com.qianfeng.oa.dto.RolePermissionMapper.queryPermission", rolename);
        return rpDTO;
    }
}
