package com.fooddelivery.uniproject.repository;

import com.fooddelivery.uniproject.entity.audit.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action,Long> {

}
