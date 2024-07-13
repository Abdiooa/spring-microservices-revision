package com.aoo.springboot.inventoryservice.controller;

import com.aoo.springboot.inventoryservice.entity.Inventory;
import com.aoo.springboot.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
@Slf4j
@RefreshScope
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    @GetMapping("/{skuCode}")
    public Boolean isInStock(@PathVariable("skuCode") String skuCode){
        log.info("Checking stock for product with skucode - " + skuCode);

        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Cannot Find Product with this skuCode "+ skuCode));

        return inventory.getQuantityAvailable()>0;
    }

}
