package com.projects.order_service.entities;

import com.projects.order_service.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
@Table(name = "orders")
public class Order {

    @Id
    private String id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToMany(mappedBy = "order") // c'est une Relation Bidirectionnelle alors on utilise mappedBy
    private List<ProductItem> productItems;
}
