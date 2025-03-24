package com.ecommerceapp.e_Commerce_App.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Payment {

    @Id
    @Schema (description = "The unique identifier for the payment.")
    private String id;
    @Schema(description = "The unique identifier for the order associated with the payment.")
    private String orderId;
    @Schema(description = "The unique identifier for the user associated with the payment.")
    private String userId;
    @Schema(description = "The amount of the payment.")
    private double amount;
    @Schema(description = "The payment status of the payment.")
    private String paymentStatus;
    @Schema(description = "The date of the payment.")
    private LocalDate paymentDate;
}

