package com.example.buruberiapp.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "firstName field is required")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "lastName field is required")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "phoneNumber field is required")
    private String phoneNumber;

    @Column(nullable = false)
    @Email(message = "email field is required")
    private String emailAddress;
}
