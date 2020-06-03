package com.example.demo.business.webscoket;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.DateUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhushj3
 * @description WebSocket服务器,serverEndpoint表示根据用户定义客户端连接的地址，可以写死
 * @date 2020/06/03
 **/
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    // 当前保持连接的人数
    private static final AtomicInteger onlineCount = new AtomicInteger(0);
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();
    // 当前连接的用户id
    private String userId;

    /**
     * 连接成功建立时调用该方法
     * @param session  当前会话
     * @param userId  用户id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.userId = userId;
        JSONObject succesJson = new JSONObject();
        if(!sessionMap.containsKey(userId)) {
            sessionMap.put(userId, session);
            int currentOnlineCount = onlineCount.incrementAndGet();
            logger.info("连接创建成功，当前用户id为{}, 当前在线人数{}", userId, currentOnlineCount);
            succesJson.put("from", "服务器");
            succesJson.put("to", userId);
            succesJson.put("content", "WebSocket服务器连接成功！");
        } else {
            succesJson.put("from", "服务器");
            succesJson.put("to", userId);
            succesJson.put("content", "WebSocket已经连接");
        }
        sendMessage(session, succesJson.toJSONString());
    }

    /**
     * 双方通信时调用该方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        sendMessage(session, message);
    }

    /**
     * 通信过程中出错调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        logger.error(throwable.toString());
    }

    /**
     * 链接关闭
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(this.userId);
        int currentOnlineCount = onlineCount.decrementAndGet();
        logger.info("用户:{} 退出连接，当前连接数为：{}", this.userId, currentOnlineCount);
    }

    /**
     * 向指定会话发送消息
     * @param session 当前会话session
     * @param message 消息内容
     */
    public void sendMessage(Session session, String message) {
        // 将该消息进行储存，或者转发给其他人；下面为演示示例，实现根据message指定的人发送消息
        if(message.startsWith("client:")) {
            // 客户端向另一客户端发消息
            String[] temp = message.split(":");
            if (!sessionMap.containsKey(temp[1])) {
                try {
                    session.getBasicRemote().sendText("对方未在线");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                sendMessage(temp[1], temp[2]);
            }
        } else {
            // 服务端返回客户端消息
            if (session == null) {
                logger.info("目标用户{} 已退出连接");
                return;
            }
            try {
                // 返回确认信息，或者不返回
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("发送消息失败, 失败原因为：{}", e);
            }
        }
    }

    /**
     * 向指定用户发送消息
     * @param userId 用户id
     * @param message 消息
     */
    public void sendMessage(String userId, String message) {
        Session session = sessionMap.get(userId);
        if (session != null) {
            JSONObject succesJson = new JSONObject();
            succesJson.put("from", "服务器");
            succesJson.put("to", userId);
            succesJson.put("content", message);
            this.sendMessage(session, succesJson.toJSONString());
        } else {
            logger.info("用户{} 已退出连接，无法发送消息", userId);
        }
    }

}
