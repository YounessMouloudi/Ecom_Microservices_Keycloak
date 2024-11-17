package com.projects.order_service.web;

import com.projects.order_service.entities.Order;
import com.projects.order_service.repositories.OrderRepository;
import com.projects.order_service.restClients.InventoryRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    private OrderRepository orderRepository;
    private InventoryRestClient inventoryRestClient;

    public OrderRestController(OrderRepository orderRepository, InventoryRestClient inventoryRestClient) {
        this.orderRepository = orderRepository;
        this.inventoryRestClient = inventoryRestClient;
    }

    @GetMapping("/orders")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Order> allOrders(){
        List<Order> allOrders = orderRepository.findAll();

        allOrders.forEach(order -> {
            order.getProductItems().forEach(pi -> {
                pi.setProduct(inventoryRestClient.getProductById(pi.getProductId()));
            });
        });

        return allOrders;
    }

    @GetMapping("/orders/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {

        Order order = orderRepository.findById(id).get();

        order.getProductItems().forEach(pi -> {
            pi.setProduct(inventoryRestClient.getProductById(pi.getProductId()));
        });
        return ResponseEntity.ok(order);
    }


}
