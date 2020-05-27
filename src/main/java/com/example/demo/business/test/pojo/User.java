package com.example.demo.business.test.pojo;

import org.apache.shiro.session.Session;

/**
 * @description 测试Shiro功能的User对象
 * @author zhushj3
 * @date 2020/04/29
 */
public class User {
    private String username;
    private String password;
    private Session session;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", session=" + session +
                '}';
    }
}
