package com.common.audit.service.mq.impl;

import com.alibaba.fastjson.JSON;
import com.common.audit.entity.Message;
import com.common.audit.mapper.MessageMapper;
import com.common.audit.service.mq.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author xhzy
 */
@Service
@Slf4j
public class KafkaMessageServiceImpl implements MessageService {

    @Value("${mq.message.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void send(Message message) {
        //消息持久化，即使发送失败，消息也不会丢失
        if(messageMapper.insert(message) > 0){
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic,
                    JSON.toJSONString(message));
            if(future.isDone()){
                try {
                    log.info("消息发送成功！");
                    log.info("{}",future.get());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }

    }
}

