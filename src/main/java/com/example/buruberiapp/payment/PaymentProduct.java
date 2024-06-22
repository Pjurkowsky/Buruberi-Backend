package com.example.buruberiapp.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProduct {
    private String name;
    private String unitPrice;
    private String quantity;
}
