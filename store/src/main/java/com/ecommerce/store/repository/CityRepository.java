package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.City;
import com.ecommerce.store.projections.CityMinProjection;

public interface CityRepository extends JpaRepository<City, Long> {

	
	@Query(nativeQuery = true, 
			value = "SELECT * "
			+ "FROM tb_city "
			+ "INNER JOIN tb_state "
			+ "WHERE tb_city.state_id = tb_state.id")
	public List<CityMinProjection> findAllCities();
}
