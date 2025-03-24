package com.ecommerceapp.e_Commerce_App.Service;

import com.ecommerceapp.e_Commerce_App.Entity.Order;
import com.ecommerceapp.e_Commerce_App.Entity.Payment;
import com.ecommerceapp.e_Commerce_App.Repository.OrderRepository;
import com.ecommerceapp.e_Commerce_App.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    public Payment processPayment(Payment payment) {
        Order order = orderRepository.findById(payment.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if ("COMPLETED".equalsIgnoreCase(payment.getPaymentStatus())) {
            order.setStatus("CONFIRMED");
        } else {
            order.setStatus("CANCELLED");
        }

        orderRepository.save(order);
        return paymentRepository.save(payment);
    }
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public void cancelPayment(String id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Order order = orderRepository.findById(payment.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus("CANCELLED");
        orderRepository.save(order);
        paymentRepository.deleteById(id);
    }
}
