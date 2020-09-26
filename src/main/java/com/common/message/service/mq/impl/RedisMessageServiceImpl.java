package com.common.message.service.mq.impl;

import com.alibaba.fastjson.JSON;
import com.common.message.config.redis.RedisEnabled;
import com.common.message.entity.Message;
import com.common.message.mapper.MessageMapper;
import com.common.message.service.mq.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xhzy
 */
@Service
@Slf4j
@Conditional(RedisEnabled.class)
public class RedisMessageServiceImpl implements MessageService {

    @Value("${mq.message.topic}")
    private String channel;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void send(Message message) {
        //消息持久化，即使发送失败，消息也不会丢失
        if(messageMapper.insert(message) > 0){
            stringRedisTemplate.convertAndSend(channel,JSON.toJSONString(message));
            log.info("消息发送成功！");
        }
    }

}
