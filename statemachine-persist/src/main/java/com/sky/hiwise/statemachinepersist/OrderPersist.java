package com.sky.hiwise.statemachinepersist;

import com.sky.hiwise.statemachinepersist.config.OrderPersistStateMachineHandler;
import com.sky.hiwise.statemachinepersist.enums.OrderEvent;
import com.sky.hiwise.statemachinepersist.enums.OrderStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class OrderPersist {

    private final OrderPersistStateMachineHandler handler;

    private final OrderPersistStateMachineHandler.PersistStateChangeListener listener = new OrderPersist.LocalPersistStateChangeListener();

    public OrderPersist(OrderPersistStateMachineHandler handler) {
        this.handler = handler;
        this.handler.addPersistStateChangeListener(listener);
    }

    public void change(int order, OrderEvent orderEvent, OrderStatus orderStatus) {
        handler.handleEventWithState(MessageBuilder
                .withPayload(orderEvent)
                .setHeader("order", order)
                .build(),
                orderStatus);
    }

    private class LocalPersistStateChangeListener implements OrderPersistStateMachineHandler.PersistStateChangeListener {

        @Override
        public void onPersist(State<OrderStatus, OrderEvent> state, Message<OrderEvent> message,
                              Transition<OrderStatus, OrderEvent> transition, StateMachine<OrderStatus, OrderEvent> stateMachine) {
            System.out.println("state:" + state);
            System.out.println("LocalPersistStateChangeListener:" + message);
            System.out.println("transition:" + transition);
            System.out.printf("stateMachine:" + stateMachine);
        }
    }
}
