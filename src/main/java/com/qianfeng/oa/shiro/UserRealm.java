package com.qianfeng.oa.shiro;

import com.qianfeng.oa.dao.IUserDAO;
import com.qianfeng.oa.dto.UserDTO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Autowired
    private IUserDAO userDAO;
    /**
     * 权限和角色查询
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = principals.getPrimaryPrincipal().toString();

        List<String> roles = userDAO.queryRoleByName(principal);
        if (roles == null || roles.isEmpty()) {
            throw new AuthorizationException("no role");
        }
        List<String> permissions = userDAO.queryPermissionByName(roles.get(0));

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        char[] passwordchar = usernamePasswordToken.getPassword();
        String password = new String(passwordchar);

        UserDTO userDTO = userDAO.checkUserByName(username);

        if (userDTO == null) {
            throw new UnknownAccountException("username error");
        }

        String dbPassword = userDTO.getPassword();

        SimpleHash simpleHash = new SimpleHash("MD5",password,userDTO.getSalt());
        if (!simpleHash.toString().equalsIgnoreCase(dbPassword)) {
            throw new IncorrectCredentialsException("password error");
        }


        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
