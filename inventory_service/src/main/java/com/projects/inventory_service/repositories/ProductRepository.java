package com.projects.inventory_service.repositories;

import com.projects.inventory_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
