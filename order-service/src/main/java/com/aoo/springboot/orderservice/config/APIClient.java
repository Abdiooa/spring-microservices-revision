package com.aoo.springboot.orderservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(value = "INVENTORY-SERVICE", url = "http://localhost:8030")
public interface APIClient {

    @GetMapping(value = "/api/v1/inventory/{skuCode}")
    Boolean checkStock(@PathVariable String skuCode);
}
