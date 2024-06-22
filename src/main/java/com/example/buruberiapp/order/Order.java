package com.example.buruberiapp.order;

import com.example.buruberiapp.customer.Customer;
import com.example.buruberiapp.driver.Driver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"Order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @NotNull(message = "customer field is required")
    private Customer customer;

    @Column(nullable = false)
    @NotBlank(message = "street field is required")
    private String street;

    @Column(nullable = false)
    @NotBlank(message = "apartmentNumber field is required")
    private String apartmentNumber;

    @Column(nullable = false)
    @NotBlank(message = "zipCode field is required")
    private String zipCode;

    @Column(nullable = false)
    @NotBlank(message = "city field is required")
    private String city;

    @Column(nullable = false)
    @NotNull(message = "amount field is required")
    @Min(value = 0, message = "amount field must be greater than or equal to 0")
    private int amount;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @NotNull(message = "deliveryDate field is required")
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String description = "";

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
