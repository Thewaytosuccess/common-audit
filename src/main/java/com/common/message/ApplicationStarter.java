package com.common.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 后台启动kafka：nohup bin/kafka-server-start.sh config/server.properties &
 * 开启生产者：bin/kafka-console-producer.sh --broker-list localhost:9092 --topic message-push
 * 开启消费者：bin/kafka-console-consumer.sh --bootstrap-servers localhost:9092 --topic message-push --from-beginning
 * @author xhzy
 */
@SpringBootApplication
@ComponentScan("com.common.message")
@MapperScan("com.common.message.mapper")
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class,args);
    }


}

