package com.aoo.springboot.productservice.service;

import com.aoo.springboot.productservice.model.Product;
import com.aoo.springboot.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return null;
        }
        return products;
    }

    @Override
    public Product findProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new RuntimeException("product with that id is not found!");
        }
        return product.get();
    }

    @Override
    public URI saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return uri;
    }
}
