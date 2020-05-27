package com.example.demo.business.test.pojo;

import java.util.Date;

/**
 * @description 测试类实体对象
 * @author zhushj3
 * @date 2020/04/28
 */
public class Test {
    private String username;
    private String userId;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                '}';
    }
}
