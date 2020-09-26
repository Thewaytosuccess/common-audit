package com.common.message.config.mqtt;

import lombok.Getter;
import lombok.SneakyThrows;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author xhzy
 */
@Configuration
@ConfigurationProperties(prefix = "mqtt.server")
@Getter
@Conditional(MqttEnabled.class)
public class MqttConfig {

    @Value("${mqtt.server.uri}")
    private String uri;

    @Value("${mqtt.server.username}")
    private String username;

    @Value("${mqtt.server.password}")
    private String password;

    @Value("${mqtt.server.clientId}")
    private String clientId;

    @Value("${mqtt.server.timeout}")
    private int timeout;

    @Value("${mqtt.server.cleanSession}")
    private boolean cleanSession;

    @Value("${mqtt.server.keepAliveInterval}")
    private int keepAliveInterval;

    @Value("${mqtt.server.qos}")
    private int qos;

    @Value("${mqtt.server.retained}")
    private boolean retained;

    @SneakyThrows
    @Bean
    public MqttClient client(){
        return new MqttClient(uri,clientId);
    }

}
