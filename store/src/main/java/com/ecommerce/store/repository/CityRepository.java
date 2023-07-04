package com.ecommerce.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.store.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
