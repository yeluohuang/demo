package com.example.demo.business.system.controller;

import com.example.demo.business.system.service.SystemService;
import com.example.demo.business.test.pojo.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhushj3
 * @description 系统信息controller
 * @date 2020/05/22
 **/
@RestController
@RequestMapping("/system")
public class SystemInfoController {
    @Autowired
    private SystemService systemService;


    @PostMapping("getsysteminfos")
    public TestResponse getSystemInfos(){
        return TestResponse.ok("获取系统参数成功",systemService.listSystemInfos());
    }
}
