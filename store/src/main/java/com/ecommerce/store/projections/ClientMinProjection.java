package com.ecommerce.store.projections;

import com.ecommerce.store.domain.enums.ClientType;

public interface ClientMinProjection {

	Long getId();
	String getName();
	String getEmail();
	String getCpf_Or_Cnpj();
	ClientType getType();
}
