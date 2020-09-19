package com.common.audit.service.mq;

import com.common.audit.entity.Message;

/**
 * 消息服务
 * @author xhzy
 */
public interface MessageService {

    /**
     * 消息发送
     * @param message 消息
     */
    void send(Message message);
}
