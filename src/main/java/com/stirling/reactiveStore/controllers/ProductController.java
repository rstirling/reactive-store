package com.stirling.reactiveStore.controllers;

import com.stirling.reactiveStore.entities.Product;
import com.stirling.reactiveStore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public Flux<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Mono<Product> get(@PathVariable String id) {
        return productRepository.findById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> modifyProduct(@PathVariable String id, @RequestBody Product product) {
        return productRepository.findById(id)
                .flatMap(current -> {
                    current.setDescription(product.getDescription());
                    current.setName(product.getName());
                    current.setPrice(product.getPrice());
                    return productRepository.save(current);
                })
                .map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/products/{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id) {
        return productRepository.deleteById(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/products")
    public Mono<Void> deleteAll() {
        return productRepository.deleteAll();
    }

}
