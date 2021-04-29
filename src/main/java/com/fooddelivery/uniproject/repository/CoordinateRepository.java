package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.location.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate,Long> {
}
