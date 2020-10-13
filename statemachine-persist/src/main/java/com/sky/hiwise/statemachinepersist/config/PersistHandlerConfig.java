package com.sky.hiwise.statemachinepersist.config;

import com.sky.hiwise.statemachinepersist.OrderPersist;
import com.sky.hiwise.statemachinepersist.enums.OrderEvent;
import com.sky.hiwise.statemachinepersist.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

@Configuration
public class PersistHandlerConfig {

    @Autowired
    private StateMachine<OrderStatus, OrderEvent> orderStateMachine;

    @Bean
    public OrderPersist orderPersist() {
        return new OrderPersist(orderPersistStateMachineHandler());
    }

    @Bean
    public OrderPersistStateMachineHandler orderPersistStateMachineHandler() {
        return new OrderPersistStateMachineHandler(orderStateMachine);
    }

}
