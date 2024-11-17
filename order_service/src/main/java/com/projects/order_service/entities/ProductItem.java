package com.projects.order_service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projects.order_service.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class ProductItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private double price;
    private int quantity;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // hadi drnaha ghir pour bricolage ay solution temporaire hit mnin tan3ayto l allOrders tay3tina boucle
    // infini a cause de cette relation bidirectionnelle aprés on fait la vrai solution w li hia les DTOs
    private Order order;
    @Transient
    public Product product;
}
