package com.common.message.config.redis;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author xhzy
 */
public class RedisEnabled implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String enabled = conditionContext.getEnvironment().getProperty("spring.redis.enabled");
        return "true".equals(enabled) || "1".equals(enabled);
    }
}
