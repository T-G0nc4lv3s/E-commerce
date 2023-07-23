package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	@Query("SELECT city "
			+"FROM City city "
			+"JOIN FETCH city.state ")
	public List<City> findAllCities();
}
