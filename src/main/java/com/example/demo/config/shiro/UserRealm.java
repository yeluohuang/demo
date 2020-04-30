package com.example.demo.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Shiro权限管理—用户认证与权限认证的策略（可以理解为面试题）
 * @author zhushj3
 * @date 2020/04/30
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
    // 添加权限注解时调用，认证用户拥有的角色信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("正在校验用户的权限");
        // 获取当前登录的用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        // 根据用户名去数据库查询用户信息，下述为测试数据
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
        Set<String> roles = new HashSet<>();
        roles.add("zhushj3");
        permissions.add("operate");
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    // 登陆时调用，认证用户的身份
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        logger.info("正在校验用户的身份");
        username = "zhushj3";
        if (username == null) {
            throw new UnknownAccountException("账户不正确");
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, "123456789", getName());
            return simpleAuthenticationInfo;
        }
    }
}
