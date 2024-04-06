package org.example.shopspringapi.repositories;

import org.example.shopspringapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,Long> {
}
