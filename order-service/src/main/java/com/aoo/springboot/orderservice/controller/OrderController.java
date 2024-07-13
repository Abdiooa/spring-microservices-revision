package com.aoo.springboot.orderservice.controller;

import com.aoo.springboot.orderservice.config.APIClient;
import com.aoo.springboot.orderservice.dto.OrderDto;
import com.aoo.springboot.orderservice.model.Order;
import com.aoo.springboot.orderservice.repository.OrderRepository;
import com.aoo.springboot.orderservice.service.RabbitMqSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@RefreshScope
@Slf4j
public class OrderController {
    private final OrderRepository orderRepository;
    private final APIClient inventoryClient;
    private final RabbitMqSender rabbitMqSender;
    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto){
        boolean isAllProductsOnStock = orderDto.getOrderItems().stream()
                .allMatch(
                        orderItem -> inventoryClient.checkStock(orderItem.getSkuCode())
                );
        if(isAllProductsOnStock){
            Order order = new Order();

            order.setOrderItems(orderDto.getOrderItems());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);
            log.info("Sending Order Details with Order Id {} to Notification Service", order.getId());
            rabbitMqSender.send("Order" + order.getId()+" Placed Successfully!");
            return "Order Placed Successfully!";
        }else{
            return "Order Failed - One of the Products in your Order is out of stock";
        }
    }
}
