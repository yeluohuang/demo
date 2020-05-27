package com.example.demo.config.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import java.io.Serializable;
import java.util.Collection;

/**
 * @description session的持久化类，可以存储在本地内存/redis/数据库等;默认存在内存中
 * @author zhushj3
 * @date 2020/04/30
 */
@Component
public class SessionDao extends AbstractSessionDAO {
    private final String SHIRO_SESSIOM_PREFIX = "shiro-session"; // 会话的key值

    private byte[] getKey(String key){
        return (SHIRO_SESSIOM_PREFIX+key).getBytes();
    }
    // 创建会话
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    // 读取会话
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            return null;
        }
        return (Session) SerializationUtils.deserialize(null);
    }

    // 更新会话
    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    // 保存会话
    private void saveSession(Session session){
        if(session !=null&& session.getId()!=null) {
            byte[] key = getKey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
        }
    }

    // 删除会话
    @Override
    public void delete(Session session) {
        if(session == null || session.getId() ==null){
            return;
        }
        byte[] key = getKey(session.getId().toString());
    }

    // 获取当前处于连接中的session
    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
