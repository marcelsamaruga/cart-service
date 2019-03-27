package com.wipro.assessment.cartservice.converter;

import com.wipro.assessment.cartservice.entity.Cart;
import com.wipro.assessment.cartservice.resource.CartResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartConverter {

    private ProductConverter productConverter;

    public Cart resourceToEntity(CartResource cartResource) {
        Cart cart = new Cart();

        if (cartResource.getProducts() != null && !cartResource.getProducts().isEmpty()) {
            cart.setProducts(
                    cartResource.getProducts()
                            .stream()
                            .map(productConverter::resourceToEntity)
                            .collect(Collectors.toList())
            );
        }

        return cart;
    }

    public CartResource entityToResource(Cart cart) {
        CartResource cartResource = new CartResource();
        cartResource.setId( cart.getId() );

        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
            cartResource.setProducts(
                    cart.getProducts()
                            .stream()
                            .map(productConverter::entityToResource)
                            .collect(Collectors.toList())
            );
        }

        return cartResource;
    }

}
