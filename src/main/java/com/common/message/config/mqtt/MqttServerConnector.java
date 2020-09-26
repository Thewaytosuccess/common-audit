package com.common.message.config.mqtt;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xhzy
 */
@Component
@Slf4j
@AutoConfigureAfter(MqttConfig.class)
@Conditional(MqttEnabled.class)
public class MqttServerConnector {

    @Value("${mq.message.topic}")
    private String topic;

    @Autowired
    private MqttConfig mqttConfig;

    @Autowired
    private MqttClient client;

    /**
     * connect to mqtt server
     */
    @SneakyThrows
    @PostConstruct
    public void init(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(mqttConfig.isCleanSession());
        options.setConnectionTimeout(mqttConfig.getTimeout());
        //heart beat detection interval
        options.setKeepAliveInterval(mqttConfig.getKeepAliveInterval());
        options.setUserName(mqttConfig.getUsername());
        options.setPassword(mqttConfig.getPassword().toCharArray());
        options.setWill(topic,mqttConfig.getClientId().getBytes(),mqttConfig.getQos(),mqttConfig.isRetained());

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                log.error(throwable.getMessage());
            }
            @Override
            public void messageArrived(String topic, MqttMessage msg) {
                String message = new String(msg.getPayload());
                log.info("received msg from client = {}",message);

            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
        client.connect(options);
        log.info("【mqtt server startup...】");
    }

    @SneakyThrows
    @PreDestroy
    public void destroy(){
        client.disconnect();
    }
}
