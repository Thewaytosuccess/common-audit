package com.common.message;

import com.common.message.entity.Message;
import com.common.message.service.mq.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class MessageSenderTest{

    @Autowired
    private MessageService messageService;

    @Test
    public void testSend(){
        Message message = new Message();
        message.setTargetId("1");
        message.setBody("to pony"+3307);
        messageService.send(message);
    }
}

