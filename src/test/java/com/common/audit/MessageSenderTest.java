package com.common.audit;

import com.common.audit.entity.Message;
import com.common.audit.service.mq.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class MessageSenderTest {

    @Resource(name="redisMessageSendServiceImpl")
    private MessageService messageService;

    @Test
    public void testSend(){
        Message message = new Message();
        message.setTargetId("1");
        message.setBody("to pony"+3306);
        messageService.send(message);
    }
}

