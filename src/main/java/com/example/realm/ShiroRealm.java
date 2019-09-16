package com.example.realm;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.services.IUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 * @date 2019/9/9 9:39
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUser userServices;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles();
        simpleAuthorizationInfo.setStringPermissions();
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        if(username == null || "".equals(username)){
            throw new NullPointerException("用户名为空");
        }

        User user = userServices.getUserByName(username);
        if(user == null){
            throw new UnknownAccountException("用户名或密码错误");
        }
        if(!password.equals(user.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误1");
        }
        if(user.isLocked()){
            throw new LockedAccountException("用户被锁定");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,password,getName());
        return simpleAuthenticationInfo;
    }
}
