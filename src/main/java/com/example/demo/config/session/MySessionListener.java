package com.example.demo.config.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description 会话监听：监听会话的生命周期变化
 * @author zhushj3
 * @date 2020/04/29
 */
@Component
public class MySessionListener implements SessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger(0);

    // 会话创建
    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
        System.out.println("登陆+1=="+sessionCount.get());
    }
    // 会话销毁
    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
        System.out.println("登陆-1=="+sessionCount.get());
    }
    // 会话过期
    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
        System.out.println("登陆过期-1=="+sessionCount.get());
    }
}
