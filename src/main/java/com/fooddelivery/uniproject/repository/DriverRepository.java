package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query("SELECT d FROM Driver d WHERE d.email = :email")
    Optional<Driver> findUserByEmail(@Param("email") String email);

    @Query("update Driver d set d.currentOrder = :currentOrder where d.id = :driverId")
    void setCurrentOrder(@Param("currentOrder") Order currentOrder, @Param("driverId")Long driverId);

}
