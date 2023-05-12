package com.hlh.order.controller;

import com.hlh.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
public class CloudOrderAPI {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/saveOrder")
    @GlobalTransactional(name = "order-service-demo", rollbackFor = Exception.class)
    public Boolean saveOrder(@RequestParam("cartId") int cartId) {
        return orderService.saveOrder(cartId);
    }
}
