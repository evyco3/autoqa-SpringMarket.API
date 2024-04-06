package org.example.shopspringapi.repositories;

import org.example.shopspringapi.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    // Define additional query methods if needed
}