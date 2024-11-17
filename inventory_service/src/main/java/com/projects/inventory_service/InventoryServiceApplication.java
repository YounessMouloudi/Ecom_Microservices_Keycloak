package com.projects.inventory_service;

import com.projects.inventory_service.entities.Product;
import com.projects.inventory_service.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

/* pour un projet spring voila les dépendances utilisés :
    - Spring Web, Spring Data JPA, Lombok, H2 Database => ça c'est la base
    si on a architecture microservices on ajoute :
    - Eureka Discovery Client,Config Client,OpenFeign => ça c'est la base mais il y a d'autres dépencies ....
    pour la sécurité : OAuth2 Resource Server. => si on utilise front thymeleaf on ajoute OAuth2 Client
*/

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
//            productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Computer").price(2300).quantity(8).build());
//            productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Printer").price(1300).quantity(10).build());
//            productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Phone").price(2500).quantity(12).build());

            /* hna kona dayrin li lfo9 mais bach bghina n ajoutiw productItems ay orderDetails alors khasso yjib
            les products mn inventoryRestClient li drna mais had l'api est sécurisé alors ghadi nbadlo id d product
            w n7aydo l'inventoryRest bach ndiro une liste de pId w diksa3at n9adro n ajoutiw des productItems
            hit la madrnach haka OrderserviceApp maghadich ydémari lina mn lewal */
            productRepository.save(Product.builder().id("P01").name("Computer").price(2300).quantity(8).build());
            productRepository.save(Product.builder().id("P02").name("Printer").price(1300).quantity(10).build());
            productRepository.save(Product.builder().id("P03").name("Phone").price(2500).quantity(12).build());
        };
    };

}
