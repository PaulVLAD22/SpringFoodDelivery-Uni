package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.local.Menu;
import com.fooddelivery.uniproject.entity.local.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.name = :name and p.price= :price")
    Optional<Product> getProductByNamePrice(@Param("name")String name,@Param("price")double price);
}
