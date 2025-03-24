package com.ecommerceapp.e_Commerce_App.Repository;
import com.ecommerceapp.e_Commerce_App.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}

