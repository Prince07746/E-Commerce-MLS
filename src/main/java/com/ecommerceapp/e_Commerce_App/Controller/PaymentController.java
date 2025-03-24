package com.ecommerceapp.e_Commerce_App.Controller;

import com.ecommerceapp.e_Commerce_App.Entity.Payment;
import com.ecommerceapp.e_Commerce_App.Service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    @Operation(summary = "Get all payments", description = "Retrieve all payments in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payments retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Payment.class)))
            })
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID", description = "Retrieve a payment by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            })
    public Payment getPaymentById(@PathVariable String id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new payment", description = "Process a new payment",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Payment processed successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Payment.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid payment data")
            })
    public Payment createPayment(
            @Parameter(description = "Payment details", required = true, schema = @Schema(implementation = Payment.class))
            @RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a payment", description = "Cancel a payment",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Payment cancelled successfully"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            })
    public void deletePayment(@PathVariable String id) {
        paymentService.cancelPayment(id);
    }
}
