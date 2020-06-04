package com.stirling.reactiveStore.controllers;

import com.stirling.reactiveStore.entities.Order;
import com.stirling.reactiveStore.entities.Status;
import com.stirling.reactiveStore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/orders")
    public Flux<Order> get() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Mono<Order> getOrder(@PathVariable String id) {
        return orderRepository.findById(id);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> addOrder(@RequestBody Order order) {
        order.setStatus(Status.PLACED);
        return orderRepository.save(order);
    }

    @DeleteMapping("/orders/{id}")
    public Mono<ResponseEntity<Void>> deleteOrder(@PathVariable String id) {
        return orderRepository.deleteById(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
