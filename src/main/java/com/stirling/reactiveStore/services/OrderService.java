package com.stirling.reactiveStore.services;

import com.stirling.reactiveStore.entities.Order;
import com.stirling.reactiveStore.entities.Status;
import com.stirling.reactiveStore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Mono<Order> addOrder(Order order) {
        order.setStatus(Status.PLACED);
        return orderRepository.save(order);
    }

    public Mono<Order> getOrder(String orderId) {
        return orderRepository.findById(orderId);
    }

    public Flux<Order> getOrders(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Flux<Order> getOrdersByStatus(Status status) {
        return orderRepository.findByStatus(status);
    }

}
