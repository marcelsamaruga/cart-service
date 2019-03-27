package com.wipro.assessment.cartservice.service;

import com.wipro.assessment.cartservice.entity.Cart;
import com.wipro.assessment.cartservice.entity.Product;
import com.wipro.assessment.cartservice.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository repository;
    private ProductService productService;

    @Transactional
    public Cart saveCart(Cart cart) {
        return this.repository.save(cart);
    }

    public List<Cart> findAll() {
        return this.repository.findAll();
    }

    public Cart findById(Long cartId) {
        Optional<Cart> cart = Optional.ofNullable( this.repository.findOne(cartId) );
        return cart.orElseThrow( () -> new ResourceNotFoundException("Cart not found", null));
    }

    @Transactional
    public Cart addProduct(Long cartId, Long productId) {
        Cart cart = Optional.ofNullable( this.repository.findOne(cartId) )
                            .orElseThrow( () -> new ResourceNotFoundException("Cart not found", null) );

        Product product = this.productService.findRemoteProduct( productId );
        product.setCart( cart );

        cart.getProducts().add( product );
        this.productService.save( product );

        return cart;
    }

}
