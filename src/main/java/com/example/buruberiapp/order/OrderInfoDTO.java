package com.example.buruberiapp.order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInfoDTO {
    private int amount;
    private int numberOfOrders;
}
