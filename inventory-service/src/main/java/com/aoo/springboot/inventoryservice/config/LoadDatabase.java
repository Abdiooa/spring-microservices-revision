package com.aoo.springboot.inventoryservice.config;

import com.aoo.springboot.inventoryservice.entity.Inventory;
import com.aoo.springboot.inventoryservice.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    public CommandLineRunner initDatabase(InventoryRepository inventoryRepository){
        Inventory inventory1 = Inventory.builder()
                .skuCode("IPHONE_12_RED")
                .quantityAvailable(100).build();
        Inventory inventory2 = Inventory.builder()
                .skuCode("IPHONE_12_GREY")
                .quantityAvailable(100).build();
        return args -> {
            log.info("Preloading "+ inventoryRepository.save(inventory1));
            log.info("Preloading "+ inventoryRepository.save(inventory2));
        };
    }
}
