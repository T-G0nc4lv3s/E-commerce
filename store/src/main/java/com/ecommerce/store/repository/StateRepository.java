package com.ecommerce.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.store.domain.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
