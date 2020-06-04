package com.stirling.reactiveStore.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String id;
    private String customerId;
    private List<OrderItem> orderItems;
    private Status status;
}
