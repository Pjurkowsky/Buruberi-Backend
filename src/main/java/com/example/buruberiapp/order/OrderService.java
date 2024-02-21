package com.example.buruberiapp.order;

import com.example.buruberiapp.price.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Iterable<Order> getAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> getById(int id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> save(Order order) {
        Order savedOrder = orderRepository.save(order);
        return Optional.of(savedOrder);
    }

//    public Optional<OrderInfoDTO> getNumberAndInfoAboutOrdersToDeliveryFromCurrentDay() {
//        LocalDate today = LocalDate.now();
//        LocalDate startOfDay = LocalDate.from(today.atStartOfDay());
//        LocalDate endOfDay = LocalDate.from(today.plusDays(1));
//        System.out.println("startOfDay" + startOfDay);
//        System.out.println("endOfDay" + endOfDay);
//
//        List<Order> orders = orderRepository.findAllByDeliveryDateBetween(startOfDay, endOfDay);
//
//        int amount = 0;
//        for (Order order : orders) {
//            amount += order.getAmount();
//        }
//
//        return Optional.of(new OrderInfoDTO(amount, orders.size()));
//    }

    public Map<LocalDate, OrderInfoDTO> getAmountAndNumberOfOrders() {

        Map<LocalDate, OrderInfoDTO> map = new HashMap<>();
        List<Order> orders = (List<Order>) orderRepository.findAll();

        for (Order order : orders) {
            LocalDate orderDate = order.getDeliveryDate();
            if (!map.containsKey(order.getDeliveryDate())) {
                map.put(orderDate, new OrderInfoDTO(order.getAmount(), 1));
            } else {
                OrderInfoDTO orderInfoDTO = map.get(orderDate);
                map.put(orderDate, new OrderInfoDTO(orderInfoDTO.getAmount() + order.getAmount(), orderInfoDTO.getNumberOfOrders() + 1));
            }

        }

        return map;
    }

}
