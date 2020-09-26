package com.common.message.entity;

import lombok.Data;
import org.springframework.util.JdkIdGenerator;

import java.util.Date;

/**
 * @author xhzy
 */
@Data
public class Message {

    public Message(){
        this.setId(new JdkIdGenerator().generateId().toString().replaceAll("-",""));
        this.setSendTime(new Date());
    }

    /**
     * 消息id
     */
    private String id;

    /**
     * 消息内容
     */
    private String body;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 接收人id,多个则用逗号或分号分隔
     */
    private String targetId;

    /**
     * 消息使用的模板
     */
    private int template;

    /**
     * 消息发送状态：0待发送，1已发送
     */
    private int status;
}

