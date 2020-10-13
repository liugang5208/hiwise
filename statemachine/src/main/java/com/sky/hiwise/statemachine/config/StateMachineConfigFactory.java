package com.sky.hiwise.statemachine.config;

import com.sky.hiwise.statemachine.enums.Events;
import com.sky.hiwise.statemachine.enums.OrderEvent;
import com.sky.hiwise.statemachine.enums.OrderStatus;
import com.sky.hiwise.statemachine.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory(name = "stateMachineFactory")
public class StateMachineConfigFactory extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStatus, OrderEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(orderListener());
    }

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
                .action(orderAction());
    }

    @Bean
    public StateMachineListener<OrderStatus, OrderEvent> orderListener() {
        return new StateMachineListenerAdapter<OrderStatus, OrderEvent>() {
            @Override
            public void stateChanged(State<OrderStatus, OrderEvent> from, State<OrderStatus, OrderEvent> to) {
                if (from != null) {
                    System.out.println("OrderStatus change from " + from.getId());
                } else {
                    System.out.println("OrderStatus change from is null");
                }

                System.out.println("OrderStatus change to " + to.getId());
            }
        };
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
            System.out.println("OrderStatus msg:" + context.getMessage());
        };
    }

}
