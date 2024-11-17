package com.projects.order_service.model;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Product {

    private String id;
    private String name;
    private double price;
    private int quantity;
}
