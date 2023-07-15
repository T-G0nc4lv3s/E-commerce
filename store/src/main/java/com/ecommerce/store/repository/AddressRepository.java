package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.Address;
import com.ecommerce.store.projections.AddressMinProjection;

public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query(nativeQuery = true,
			value = "SELECT (tb_address.id), (tb_address.street), (tb_address.number), (tb_address.complement), "
					+ "(tb_address.district), (tb_address.zip_code), (tb_address.city_id), (tb_address.client_id) "
					+ "FROM TB_ADDRESS "
					+ "INNER JOIN tb_client ON tb_client.id = tb_address.client_id "
					+ "INNER JOIN tb_city ON tb_city.id = tb_address.city_id")
	List<AddressMinProjection> findAllAddresses();

}
