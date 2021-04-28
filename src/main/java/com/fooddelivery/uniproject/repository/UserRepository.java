package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
