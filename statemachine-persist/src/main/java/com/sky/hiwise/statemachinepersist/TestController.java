package com.sky.hiwise.statemachinepersist;

import com.sky.hiwise.statemachinepersist.enums.OrderEvent;
import com.sky.hiwise.statemachinepersist.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    OrderPersist orderPersist;

    @GetMapping("/test2")
    public String test2(@RequestParam(value = "event") String event) {
        if (event.equals("1")) {
            orderPersist.change(1, OrderEvent.START, OrderStatus.ORDER_INIT);
        } else if (event.equals(2)) {
            orderPersist.change(2, OrderEvent.END, OrderStatus.ORDER_START);
        } else {
            orderPersist.change(3, OrderEvent.FINISH, OrderStatus.ORDER_INIT);
        }
        return "success";
    }
}
