package com.ecommerceapp.e_Commerce_App;

import com.ecommerceapp.e_Commerce_App.Entity.Order;
import com.ecommerceapp.e_Commerce_App.Entity.Product;
import com.ecommerceapp.e_Commerce_App.Entity.User;
import com.ecommerceapp.e_Commerce_App.Repository.OrderRepository;
import com.ecommerceapp.e_Commerce_App.Repository.ProductRepository;
import com.ecommerceapp.e_Commerce_App.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(ProductRepository productRepo,
                               UserRepository userRepo,
                               OrderRepository orderRepo) {
        return args -> {
            if (productRepo.count() == 0) {
                List<Product> products = Arrays.asList(
                        new Product(null, "Laptop", 1200, 10),
                        new Product(null, "Phone", 800, 15),
                        new Product(null, "Tablet", 600, 8),
                        new Product(null, "Smartwatch", 200, 12),
                        new Product(null, "Headphones", 150, 20)
                );
                productRepo.saveAll(products);
            }

            if (userRepo.count() == 0) {
                List<User> users = Arrays.asList(
                        new User(null, "Alice", "Smith", "alice@example.com", LocalDate.of(1990, 5, 10), 0),
                        new User(null, "Bob", "Johnson", "bob@example.com", LocalDate.of(1985, 8, 21), 0),
                        new User(null, "Charlie", "Brown", "charlie@example.com", LocalDate.of(1995, 2, 14), 0)
                );
                userRepo.saveAll(users);
            }

            if (orderRepo.count() == 0) {
                List<Product> products = productRepo.findAll();
                List<User> users = userRepo.findAll();

                if (!products.isEmpty() && !users.isEmpty()) {
                    Order order1 = new Order(null, users.get(0).getId(),
                            Arrays.asList(products.get(0).getId(), products.get(2).getId()),
                            1800, "PENDING", LocalDate.now());

                    Order order2 = new Order(null, users.get(1).getId(),
                            Arrays.asList(products.get(1).getId(), products.get(4).getId()),
                            950, "PENDING", LocalDate.now());

                    Order order3 = new Order(null, users.get(2).getId(),
                            Arrays.asList(products.get(3).getId()),
                            200, "PENDING", LocalDate.now());

                    orderRepo.saveAll(Arrays.asList(order1, order2, order3));
                }
            }
        };
    }
}
