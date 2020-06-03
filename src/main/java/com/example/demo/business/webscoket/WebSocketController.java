package com.example.demo.business.webscoket;

import com.example.demo.business.test.pojo.TestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhushj3
 * @description WebSocket 接口
 * @date 2020/06/03
 **/
@RestController
@RequestMapping("/websocket")
public class WebSocketController {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    @Autowired
    private WebSocketServer webSocketServer;

    @RequestMapping("/users/{from}/{to}")
    public ModelAndView index(@PathVariable("from") String from, @PathVariable("to") String to){
        logger.info(from+"||"+to);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("from", from);
        modelAndView.addObject("to", to);
        return modelAndView;
    }

    /**
     * 模拟服务器端向客户端推送消息
     * @param userId
     * @param message
     */
    @RequestMapping("/push/{userId}")
    public void mockPushMessageToClient(@PathVariable String userId, String message) {
        webSocketServer.sendMessage(userId, message);
    }
}
