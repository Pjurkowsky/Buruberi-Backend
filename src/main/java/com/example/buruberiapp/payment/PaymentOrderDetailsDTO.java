package com.example.buruberiapp.payment;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderDetailsDTO {
    private String clientIpAddress;
    private int dataBaseOrderId;
}
