package com.projects.order_service;

import com.projects.order_service.entities.Order;
import com.projects.order_service.entities.OrderState;
import com.projects.order_service.entities.ProductItem;
import com.projects.order_service.model.Product;
import com.projects.order_service.repositories.OrderRepository;
import com.projects.order_service.repositories.ProductItemRepository;
import com.projects.order_service.restClients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients // ila madrtich hadi maykhdamch lik openfein wakha t injectih
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository,
                                        ProductItemRepository productItemRepository,
                                        InventoryRestClient inventoryRestClient){
        return args -> {

//            List<Product> allProducts = inventoryRestClient.getAllProducts();
//            hna had inventory kona dayrinha ghir pour test
            List<String> productsIds = List.of("P01","P02","P03"); // list de pId

            for (int i = 0; i < 5; i++) {

                Order order = Order.builder()
                              .id(UUID.randomUUID().toString())
                              .date(LocalDate.now())
                              .state(OrderState.PENDING)
                              .build();

                Order savedOrder = orderRepository.save(order);

//                allProducts.forEach(p -> {
//
//                    ProductItem productItem = ProductItem.builder()
//                                              .productId(p.getId())
//                                              .quantity(new Random().nextInt(10))
//                                              .price(p.getPrice())
//                                              .order(savedOrder)
//                                              .build();
//
//                    productItemRepository.save(productItem);
//                });

                productsIds.forEach(pId -> {
                    ProductItem productItem = ProductItem.builder()
                                              .productId(pId)
                                              .quantity(new Random().nextInt(10))
                                              .price(Math.random() * 4000 + 100)
                                              .order(savedOrder)
                                              .build();

                    productItemRepository.save(productItem);
                });

            }
        };
    }

}
