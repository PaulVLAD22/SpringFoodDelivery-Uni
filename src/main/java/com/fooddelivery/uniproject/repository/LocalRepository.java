package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.local.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalRepository extends JpaRepository<Local,Long> {
    @Query("Select l from Local l where l.name = :name")
    Optional<Local> findLocalByName(@Param("name") String name);
}
