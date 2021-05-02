package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.local.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
