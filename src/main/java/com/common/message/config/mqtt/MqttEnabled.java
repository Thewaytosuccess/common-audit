package com.common.message.config.mqtt;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xhzy
 */
public class MqttEnabled implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String enabled = conditionContext.getEnvironment().getProperty("spring.mqtt.enabled");
        return "true".equals(enabled) || "1".equals(enabled);
    }
}
