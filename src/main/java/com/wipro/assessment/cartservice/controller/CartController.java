package com.wipro.assessment.cartservice.controller;

import com.wipro.assessment.cartservice.converter.CartConverter;
import com.wipro.assessment.cartservice.converter.ProductConverter;
import com.wipro.assessment.cartservice.entity.Cart;
import com.wipro.assessment.cartservice.resource.CartResource;
import com.wipro.assessment.cartservice.resource.ProductResource;
import com.wipro.assessment.cartservice.service.CartService;
import com.wipro.assessment.cartservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private CartService service;
    private CartConverter cartConverter;
    private ProductConverter productConverter;
    private ProductService productService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResource> saveCart() {
        log.info("Calling saveCart");
        Cart cart = this.cartConverter.resourceToEntity( new CartResource() );
        Cart savedCart = this.service.saveCart(cart);
        //
        CartResource cartResource = this.cartConverter.entityToResource( savedCart );
        log.info("saveCart is completed=" + cartResource);
        //
        return new ResponseEntity<CartResource>( cartResource, HttpStatus.OK );
    }

    // *************************************************************************************************

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartResource>> findAll() {
        log.info("Calling findAllCart");
        //
        List<CartResource> cartResourceList = this.service.findAll()
                                                          .stream()
                                                          .map( this.cartConverter::entityToResource)
                                                          .collect( Collectors.toList() );

        //
        log.info("findAllCart has been completed");
        return new ResponseEntity<List<CartResource>>( cartResourceList, HttpStatus.OK );
    }


    // *************************************************************************************************

    @GetMapping(value = "/{cart_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResource> findById(@PathVariable(name = "cart_id") @Valid String cartId) {
        log.info("Calling findById=" + cartId);
        //
        Cart cart = this.service.findById( Long.valueOf(cartId) );
        CartResource cartResource = this.cartConverter.entityToResource( cart );

        //
        log.info("findById/{cart_id} has been completed");
        return new ResponseEntity<CartResource>( cartResource, HttpStatus.OK );
    }


    // *************************************************************************************************

    @PostMapping(value = "/{cart_id}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResource> addProduct(@Valid @PathVariable(name = "cart_id") String cartId, @Valid @RequestBody ProductResource resource) {
        log.info("Calling addProductsToCart. ProductResource=" + resource);
        //

        Cart cart = this.service.addProduct( Long.valueOf(cartId), resource.getId() );
        CartResource cartResource = this.cartConverter.entityToResource( cart );

        //
        log.info("addProductsToCart has been completed. Cart=" + cartResource);
        return new ResponseEntity<CartResource>( cartResource, HttpStatus.OK );
    }

}
