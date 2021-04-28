package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.account.Admin;
import com.fooddelivery.uniproject.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
}
