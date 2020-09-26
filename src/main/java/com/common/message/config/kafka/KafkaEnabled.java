package com.common.message.config.kafka;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xhzy
 */
public class KafkaEnabled implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String enabled = conditionContext.getEnvironment().getProperty("spring.kafka.enabled");
        return "true".equals(enabled) || "1".equals(enabled);
    }
}
