package com.stirling.reactiveStore.repositories;

import com.stirling.reactiveStore.entities.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByName(String name);
    Flux<Product> findByDescriptionIsLike(String partialDescription);
}
