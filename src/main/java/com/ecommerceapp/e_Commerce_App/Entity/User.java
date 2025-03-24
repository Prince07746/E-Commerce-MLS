package com.ecommerceapp.e_Commerce_App.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private int loyaltyPoints = 0;
}
