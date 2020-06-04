package com.stirling.reactiveStore;

import com.stirling.reactiveStore.entities.Product;
import com.stirling.reactiveStore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import reactor.core.publisher.Flux;

@Slf4j
//@Component
@RequiredArgsConstructor
public class ProductInit { //implements ApplicationRunner {

    private final ProductRepository productRepository;

    //@Override
    public void run(ApplicationArguments args) throws Exception {

        var products = Flux.just(
                Product.builder().name("Milk").price(3.5).description("Regular milk").build(),
                Product.builder().name("Coffee").price(4.2).description("Black Coffe").build(),
                Product.builder().name("Sugar").price(2.1).description("Cristal Sugar").build(),
                Product.builder().name("Bread").price(0.5).description("White Box Bread").build(),
                Product.builder().name("Butter").price(2.1).description("Salted Butter").build()
        );

        productRepository.deleteAll()
                .thenMany(products)
                .flatMap(productRepository::save)
                .then()
                .block();
    }
}
