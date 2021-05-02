package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.local.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}