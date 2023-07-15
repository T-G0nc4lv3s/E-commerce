package com.ecommerce.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.store.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
