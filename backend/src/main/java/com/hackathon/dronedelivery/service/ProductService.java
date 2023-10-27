package com.hackathon.dronedelivery.service;

import com.hackathon.dronedelivery.model.Product;
import com.hackathon.dronedelivery.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    public final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
