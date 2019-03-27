package com.wipro.assessment.cartservice.repository;

import com.wipro.assessment.cartservice.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends MongoRepository<Cart, Long> {

}
