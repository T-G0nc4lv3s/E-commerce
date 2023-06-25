package com.ecommerce.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.store.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
