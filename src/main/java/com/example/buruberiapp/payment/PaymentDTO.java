package com.example.buruberiapp.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String customerIp;
    private String merchantPosId;
    private String description;
    private final String currencyCode = "PLN";
    private String totalAmount;
    private List<PaymentProduct> paymentProducts;
    private String continueUrl;

}
