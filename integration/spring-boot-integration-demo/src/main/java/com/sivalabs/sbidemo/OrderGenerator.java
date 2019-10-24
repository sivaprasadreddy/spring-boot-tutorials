package com.sivalabs.sbidemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class OrderGenerator {

    @Autowired
    OrderRepository orderRepository;

    @Scheduled(fixedDelay = 3000L)
    public void generateOrder() {
        Order order = orderRepository.save(new Order(null, "order-" + new Date(), Order.Status.CREATED, new Date()));
        log.info("Order Id: "+order.getId());
    }
}
