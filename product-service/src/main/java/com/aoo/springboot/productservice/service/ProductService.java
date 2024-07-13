package com.aoo.springboot.productservice.service;

import com.aoo.springboot.productservice.model.Product;

import java.net.URI;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product findProductById(long id);

    URI saveProduct(Product product);
}
