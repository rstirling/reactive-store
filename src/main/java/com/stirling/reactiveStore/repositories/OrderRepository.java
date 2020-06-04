package com.stirling.reactiveStore.repositories;

import com.stirling.reactiveStore.entities.Order;
import com.stirling.reactiveStore.entities.Status;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

    Flux<Order> findByCustomerId(String customerId);

    Flux<Order> findByStatus(Status status);
}
