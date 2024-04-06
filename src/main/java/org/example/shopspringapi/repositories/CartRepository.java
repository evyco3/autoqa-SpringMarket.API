package org.example.shopspringapi.repositories;

import org.example.shopspringapi.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,Long> {
}
