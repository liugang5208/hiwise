package com.sky.hiwise.autoconfigure.condition;

import com.sky.hiwise.autoconfigure.annotation.ConditionalOnProperty;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnProperty.class.getName());

        String propertyName = String.valueOf(attributes.get("name"));

        return propertyName.equals("test");
    }
}
