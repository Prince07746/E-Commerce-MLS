package com.ecommerceapp.e_Commerce_App.Repository;

import com.ecommerceapp.e_Commerce_App.Entity.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
}
