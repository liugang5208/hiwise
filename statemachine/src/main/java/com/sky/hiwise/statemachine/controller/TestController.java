package com.sky.hiwise.statemachine.controller;

import com.sky.hiwise.statemachine.enums.Events;
import com.sky.hiwise.statemachine.enums.OrderEvent;
import com.sky.hiwise.statemachine.enums.OrderStatus;
import com.sky.hiwise.statemachine.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StateMachineFactory<OrderStatus, OrderEvent> stateMachineFactory;

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @GetMapping("/test")
    public String test(@RequestParam(value = "test") String test) {

        StateMachine stateMachine1 = stateMachineFactory.getStateMachine("123456");
        stateMachine1.start();
        MessageBuilder<OrderEvent> messageBuilder = MessageBuilder
                .withPayload(OrderEvent.START)
                .setHeader("BusinessId", 12345678);
        messageBuilder.setHeader("test", test != null ? test : "hello world");
        Message<OrderEvent> message = messageBuilder.build();

        if (test.equals("hello")) {
            System.out.println("orderEvent Start");
            stateMachine1.sendEvent(message);
        } else {
            System.out.println("orderEvent End");
            stateMachine1.sendEvent(OrderEvent.END);
        }

        stateMachine1.stop();
        return test;
    }

    @GetMapping("/test1")
    public String test1(@RequestParam(value = "test") String test) {

        System.out.println("stateMachine.sendEvent(Events.E1)");
        stateMachine.sendEvent(Events.E1);

        System.out.println("stateMachine.sendEvent(Events.E2);");
        stateMachine.sendEvent(Events.E2);
        return test;
    }
}
