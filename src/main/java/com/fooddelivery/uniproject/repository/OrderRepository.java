package com.fooddelivery.uniproject.repository;

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
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o from Order o where o.status = 1 and o.user = :user")
    Optional<Order> findActiveOrderByUser(@Param("user") User user);
    @Transactional
    @Modifying
    @Query("Update Order o set o.status='0' where o.id = :orderId")
    void setOrderInactive(@Param("orderId") Long orderId);

}
