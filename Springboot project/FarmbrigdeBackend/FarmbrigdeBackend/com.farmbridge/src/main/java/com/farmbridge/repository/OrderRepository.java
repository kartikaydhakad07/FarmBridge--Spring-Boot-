package com.farmbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbridge.entities.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
