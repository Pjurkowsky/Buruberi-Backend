package com.example.buruberiapp.driver;

import com.example.buruberiapp.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {
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

    @OneToMany(mappedBy = "driver")
    private Set<Order> orders;
}
