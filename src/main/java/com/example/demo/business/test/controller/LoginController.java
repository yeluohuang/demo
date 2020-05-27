package com.example.demo.business.test.controller;

import com.example.demo.business.test.pojo.TestResponse;
import com.example.demo.business.test.pojo.User;
import com.example.demo.exception.ExceptionEnum;
import com.example.demo.exception.TestException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 登录/登出接口
 * @author zhushj3
 * @date 2020/04/30
 */
@RestController
public class LoginController {
    // 登录接口
    @PostMapping("/login")
    public TestResponse Login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            // 已经设置了全局管理器，无需再进行返回
            throw  new TestException(ExceptionEnum.LOGIN_FAIL);
        } catch (AuthorizationException e) {
            throw  new TestException(ExceptionEnum.PERMISSION_DENIED);
        }
        user.setSession(subject.getSession());
        // 后续添加会话管理
        return TestResponse.ok("登录成功",user);
    }
}
