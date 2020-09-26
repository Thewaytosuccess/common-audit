package com.common.message.service.mq.impl;

import com.common.message.config.mqtt.MqttConfig;
import com.common.message.config.mqtt.MqttEnabled;
import com.common.message.config.mqtt.MqttServerConnector;
import com.common.message.entity.Message;
import com.common.message.service.mq.MessageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @author xhzy
 */
@Service
@Slf4j
@AutoConfigureAfter(MqttServerConnector.class)
@Conditional(MqttEnabled.class)
public class MqttMessageServiceImpl implements MessageService {

    @Value("${mq.message.topic}")
    private String topic;

    @Autowired
    private MqttClient client;

    @Autowired
    private MqttConfig mqttConfig;

    @SneakyThrows
    @Override
    public void send(Message message) {
        client.publish(topic,message.getBody().getBytes(),mqttConfig.getQos(),mqttConfig.isRetained());
    }
}
