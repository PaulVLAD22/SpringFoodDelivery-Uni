package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.account.Driver;
import com.fooddelivery.uniproject.entity.account.User;
import com.fooddelivery.uniproject.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query("SELECT d FROM Driver d WHERE d.email = :email or d.username = :username")
    Optional<Driver> findUserByEmailOrUsername(@Param("email") String email,@Param("user")String username);

    @Transactional
    @Modifying
    @Query("update Driver d set d.username = :newName where d.username = :oldName")
    void renameDriver(@Param("newName")String newName,@Param("oldName")String oldName);


    @Transactional
    @Modifying
    @Query("update Driver d set d.currentOrder = :currentOrder where d.id = :driverId")
    void setCurrentOrder(@Param("currentOrder") Order currentOrder, @Param("driverId")Long driverId);

}
