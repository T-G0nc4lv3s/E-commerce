package com.ecommerce.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.store.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
