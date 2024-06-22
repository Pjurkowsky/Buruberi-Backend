package com.example.buruberiapp.payment;

import com.example.buruberiapp.order.Order;
import com.example.buruberiapp.order.OrderService;
import com.example.buruberiapp.price.PriceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PriceService priceService;
    @Autowired
    OrderService orderService;
    @Autowired
    PaymentRepository paymentRepository;
    WebClient webClient = WebClient.create("https://secure.snd.payu.com");

    public String paymentAuth() {
        return webClient.post().uri(uriBuilder -> uriBuilder.path("/pl/standard/user/oauth/authorize")
                        .queryParam("grant_type", "client_credentials")
                        .queryParam("client_id", "476723")
                        .queryParam("client_secret", "7155e4f5b59486a569007d13ce41dad1").build())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).retrieve().bodyToMono(String.class).block();
    }

    public String pay(String accessToken, PaymentOrderDetailsDTO paymentOrderDetailsDTO) throws JsonProcessingException {


        System.out.println("PAYMENTORDERDETAILS: " + paymentOrderDetailsDTO.getDataBaseOrderId());

        Optional<Order> optOrder = orderService.getById(paymentOrderDetailsDTO.getDataBaseOrderId());
        Order order = optOrder.get();

        List<PaymentProduct> productList = new ArrayList<>();
        productList.add(new PaymentProduct("Borowka",
                priceService.getLastPrice().toString(),
                String.valueOf(order.getAmount())));

        PaymentDTO paymentDTO = new PaymentDTO(paymentOrderDetailsDTO.getClientIpAddress(),
                "476723",
                "Cena za kg borowki", String.valueOf(productList.stream()
                .mapToInt(product -> Integer.parseInt(product.getQuantity()))
                .sum()), productList, "http://localhost:5173/order-confirmations");

        System.out.println("PaymentDTO: " + paymentDTO);

        String response = webClient.post().uri(uriBuilder -> uriBuilder.path("/api/v2_1/orders").build()).headers(h -> {
                    h.setContentType(MediaType.APPLICATION_JSON);
                    h.setBearerAuth(accessToken);
                })
                .body(Mono.just(paymentDTO), PaymentDTO.class)
                .retrieve().bodyToMono(String.class).block();


        System.out.println("Response: " + response);

        Payment payment = new Payment();
        payment.setOrderIdExternalService(new ObjectMapper().readTree(response).get("orderId").asText());
        payment.setOrder(order);

        savePayment(payment);

        return response;
    }

    public Optional<Payment> savePayment(Payment payment) {
        paymentRepository.save(payment);
        return Optional.of(payment);
    }

}
