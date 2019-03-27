package com.wipro.assessment.cartservice.service;

import com.wipro.assessment.cartservice.entity.Product;
import com.wipro.assessment.cartservice.repository.ProductRepository;
import com.wipro.assessment.cartservice.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by e068636 on 12/13/2018.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    @Qualifier("restClientProduct")
    private RestClient<Product> productRestClient;

    @Value("${application.products.uri}")
    String productURL;

    public List<Product> findAll() {
        return this.repository.findAll();
    }

    public Product findRemoteProduct(Long productId) {
        Optional<Product> productRemote = this.productRestClient.getResource(productURL + productId);
        return productRemote.orElseThrow( () -> new ResourceNotFoundException("Product not found", null));
    }

    public void save(Product product) {
        this.repository.save( product );
    }
}
