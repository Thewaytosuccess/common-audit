package com.common.audit;

import com.common.audit.util.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class MessageSenderTest {

    @Autowired
    private MessageSender messageSender;

    @Test
    public void testSend(){
        messageSender.send(1,"to pony"+111);
        messageSender.send(2,"to jackMa"+222);
    }
}

