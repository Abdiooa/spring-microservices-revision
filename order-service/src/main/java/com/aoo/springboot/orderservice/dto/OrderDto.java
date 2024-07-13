package com.aoo.springboot.orderservice.dto;

import com.aoo.springboot.orderservice.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Set<OrderItem> orderItems;
}
