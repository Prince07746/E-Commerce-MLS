package com.ecommerceapp.e_Commerce_App.Service;

import com.ecommerceapp.e_Commerce_App.Entity.Order;
import com.ecommerceapp.e_Commerce_App.Entity.Product;
import com.ecommerceapp.e_Commerce_App.Entity.User;
import com.ecommerceapp.e_Commerce_App.Repository.OrderRepository;
import com.ecommerceapp.e_Commerce_App.Repository.ProductRepository;
import com.ecommerceapp.e_Commerce_App.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(Order order) {
        //Chck product availability
        for (String productId : order.getProductIds()) {
            Product product = productRepository.findById(productId).orElseThrow();
            if (product.getStockQuantity() < 1) {
                throw new RuntimeException("Product out of stock!");
            }
        }
       //calculat th total pric
        User user = userRepository.findById(order.getUserId()).orElseThrow();
        double total = 0;
        for (String productId : order.getProductIds()) {
            Product product = productRepository.findById(productId).orElseThrow();
            total += product.getPrice();
            product.setStockQuantity(product.getStockQuantity() - 1);
            productRepository.save(product);
        }
       //loyalty points
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        user.setLoyaltyPoints(user.getLoyaltyPoints() + (int) total);
        userRepository.save(user);

        return orderRepository.save(order);
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(String id,Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
