package com.ecommerce.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.store.domain.Client;
import com.ecommerce.store.projections.ClientMinProjection;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query(nativeQuery = true,
			value = "SELECT DISTINCT (TB_CLIENT.ID), (TB_CLIENT.NAME), (TB_CLIENT.EMAIL), (TB_CLIENT.CPF_OR_CNPJ), (TB_CLIENT.TYPE) "
					+ "FROM TB_CLIENT "
					+ "INNER JOIN TB_PHONES ON TB_PHONES.CLIENT_ID = TB_CLIENT.ID "
					+ "INNER JOIN TB_ADDRESS ON TB_ADDRESS.CLIENT_ID = TB_CLIENT.ID")
	public List<ClientMinProjection> findAllClients();
}
