package com.aoospringboot.apigatewayservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallbackController {
    @GetMapping("/fallback/productsServiceFallBack")
    public ResponseEntity<String> productsServiceFallBackMethod(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Product service is not available now please try it later");
    }
    @GetMapping("/api/v1/demo")
    public ResponseEntity<String> demoTest(){
        return ResponseEntity.status(HttpStatus.OK).body("Demo");
    }
    @GetMapping("/fallback/orderServiceFallBack")
    public ResponseEntity<String> orderServiceFallBackMethod(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Order service is not available for the moment please try it later");
    }

    @GetMapping("/fallback/inventoryServiceFallBack")
    public ResponseEntity<String> inventoryServiceFallBack(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Inventory service is not available for the moment please try it later");
    }

    @GetMapping("/fallback/notificationServiceFallBack")
    public ResponseEntity<String> notificationServiceFallBack(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Inventory service is not available for the moment please try it later");
    }
}
