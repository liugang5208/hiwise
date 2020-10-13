package com.sky.hiwise.statemachinepersist.config;

import com.sky.hiwise.statemachinepersist.enums.OrderEvent;
import com.sky.hiwise.statemachinepersist.enums.OrderStatus;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrderPersistStateMachineHandler extends LifecycleObjectSupport {

    private final StateMachine<OrderStatus, OrderEvent> stateMachine;
    private final PersistingStateChangeInterceptor interceptor = new PersistingStateChangeInterceptor();
    private final CompositePersistStateChangeListener listeners = new CompositePersistStateChangeListener();

    public OrderPersistStateMachineHandler(StateMachine<OrderStatus, OrderEvent> stateMachine) {
        Assert.notNull(stateMachine, "State machine must be set");
        this.stateMachine = stateMachine;
    }

    protected void onInit() throws Exception {
        this.stateMachine.getStateMachineAccessor().doWithAllRegions(new StateMachineFunction<StateMachineAccess<OrderStatus, OrderEvent>>() {
            public void apply(StateMachineAccess<OrderStatus, OrderEvent> function) {
                function.addStateMachineInterceptor(OrderPersistStateMachineHandler.this.interceptor);
            }
        });
        //获取所有 PersistStateChangeListener的bean注册到CompositePersistStateChangeListener
        Map<String, PersistStateChangeListener> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) this.getBeanFactory(), PersistStateChangeListener.class, true, false);
        if (!matchingBeans.isEmpty()) {
            listeners.setListeners(new ArrayList(matchingBeans.values()));
        }

    }

    public boolean handleEventWithState(Message<OrderEvent> event, OrderStatus state) {
        this.stateMachine.stop();
        List<StateMachineAccess<OrderStatus, OrderEvent>> withAllRegions = this.stateMachine.getStateMachineAccessor().withAllRegions();
        Iterator var4 = withAllRegions.iterator();

        while(var4.hasNext()) {
            StateMachineAccess<OrderStatus, OrderEvent> a = (StateMachineAccess)var4.next();
            a.resetStateMachine(new DefaultStateMachineContext(state, (Object)null, (Map)null, (ExtendedState)null));
        }

        this.stateMachine.start();
        return this.stateMachine.sendEvent(event);
    }

    public void addPersistStateChangeListener(OrderPersistStateMachineHandler.PersistStateChangeListener listener) {
        this.listeners.register(listener);
    }

    private class CompositePersistStateChangeListener extends AbstractCompositeListener<PersistStateChangeListener> implements OrderPersistStateMachineHandler.PersistStateChangeListener {
        private CompositePersistStateChangeListener() {
        }

        public void onPersist(State<OrderStatus, OrderEvent> state, Message<OrderEvent> message, Transition<OrderStatus, OrderEvent> transition, StateMachine<OrderStatus, OrderEvent> stateMachine) {
            Iterator iterator = this.getListeners().reverse();

            while(iterator.hasNext()) {
                OrderPersistStateMachineHandler.PersistStateChangeListener listener = (OrderPersistStateMachineHandler.PersistStateChangeListener)iterator.next();
                listener.onPersist(state, message, transition, stateMachine);
            }

        }
    }

    private class PersistingStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStatus, OrderEvent> {
        private PersistingStateChangeInterceptor() {
        }

        public void preStateChange(State<OrderStatus, OrderEvent> state, Message<OrderEvent> message, Transition<OrderStatus, OrderEvent> transition, StateMachine<OrderStatus, OrderEvent> stateMachine) {
            OrderPersistStateMachineHandler.this.listeners.onPersist(state, message, transition, stateMachine);
        }

        public void preStateChange(State<OrderStatus, OrderEvent> state, Message<OrderEvent> message, Transition<OrderStatus, OrderEvent> transition, StateMachine<OrderStatus, OrderEvent> stateMachine, StateMachine<OrderStatus, OrderEvent> rootStateMachine) {
            OrderPersistStateMachineHandler.this.listeners.onPersist(state, message, transition, stateMachine);
        }
    }

    public interface PersistStateChangeListener {
        void onPersist(State<OrderStatus, OrderEvent> var1, Message<OrderEvent> var2, Transition<OrderStatus, OrderEvent> var3, StateMachine<OrderStatus, OrderEvent> var4);
    }
}
