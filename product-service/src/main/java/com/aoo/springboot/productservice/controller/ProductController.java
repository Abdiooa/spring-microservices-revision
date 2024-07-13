package com.aoo.springboot.productservice.controller;

import com.aoo.springboot.productservice.model.Product;
import com.aoo.springboot.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RefreshScope
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        URI uri = productService.saveProduct(product);
        log.info("URI created Product : {} ", uri);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        try {
            List<Product> products = productService.getAllProducts();
            log.info("Products : {}",products);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            log.info(" Error: {}",e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findProduct(@PathVariable("id") long id){
        try {
            Product product = productService.findProductById(id);
            log.info("product : {}",product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (Exception e){
            log.info(" Error: {}",e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
