package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query("SELECT d FROM Driver d WHERE d.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);


}
