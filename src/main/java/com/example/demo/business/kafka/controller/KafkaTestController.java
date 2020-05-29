package com.example.demo.business.kafka.controller;

import com.example.demo.annotation.declare.Log;
import com.example.demo.business.kafka.domain.Consumer;
import com.example.demo.business.kafka.domain.Producer;
import com.example.demo.business.system.service.SystemService;
import com.example.demo.business.test.pojo.TestResponse;
import com.example.demo.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhushj3
 * @description 测试Kafka
 * @date 2020/05/22
 **/

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {
    @Autowired
    private Producer producer;
    @Autowired
    private Consumer consumer;

    @PostMapping("sendMsg")
    public TestResponse sendMsg(){
        producer.send("this is a test message");
        return TestResponse.ok("测试成功");
    }

}
