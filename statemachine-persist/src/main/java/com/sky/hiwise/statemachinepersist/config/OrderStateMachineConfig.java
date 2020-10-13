package com.sky.hiwise.statemachinepersist.config;

import com.sky.hiwise.statemachinepersist.enums.OrderEvent;
import com.sky.hiwise.statemachinepersist.enums.OrderStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderEvent> config)
//            throws Exception {
//        config
//                .withConfiguration()
//                .autoStartup(true)
//                .listener(orderListener1());
//    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states)
            throws Exception {
        states
                .withStates()
                .initial(OrderStatus.ORDER_INIT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(OrderStatus.ORDER_INIT)
                .target(OrderStatus.ORDER_START)
                .event(OrderEvent.START)
                .action(orderAction())
                .and()
                .withExternal()
                .source(OrderStatus.ORDER_START)
                .target(OrderStatus.ORDER_END)
                .event(OrderEvent.END)
                .action(orderAction1())
                .and()
                .withExternal()
                .source(OrderStatus.ORDER_INIT)
                .target(OrderStatus.ORDER_END)
                .event(OrderEvent.FINISH);
    }

    @Bean
    public Action<OrderStatus, OrderEvent> orderAction() {
//        return new Action<OrderStatus, OrderEvent>() {
//
//            @Override
//            public void execute(StateContext<OrderStatus, OrderEvent> context) {
//                // do something
//                System.out.println("OrderStatus msg:" + context.getMessage());
//            }
//        };
        return (context)->{
            System.out.println("OrderEvent.START OrderStatus msg:" + context.getMessage());
        };
    }

    @Bean
    public Action<OrderStatus, OrderEvent> orderAction1() {
        return (context)->{
            System.out.println("OrderEvent.END OrderStatus msg:" + context.getMessage());
        };
    }
}
