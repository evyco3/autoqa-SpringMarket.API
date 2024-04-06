package org.example.shopspringapi.repositories;

import org.example.shopspringapi.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,Long> {
}
