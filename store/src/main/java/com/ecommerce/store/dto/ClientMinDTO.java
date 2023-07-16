package com.ecommerce.store.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Client;
import com.ecommerce.store.domain.enums.ClientType;
import com.ecommerce.store.projections.ClientMinProjection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientMinDTO {

	@Include
	private Long id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private ClientType type;
	
	public ClientMinDTO(Long id, String name, String email, String cpfOrCnpj, ClientType type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.type = type;
	}

	public ClientMinDTO(Client entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public ClientMinDTO(ClientMinProjection projection) {
		this.id = projection.getId();
		this.name = projection.getName();
		this.email = projection.getEmail();
		this.cpfOrCnpj = projection.getCpf_Or_Cnpj();
		this.type = projection.getType();
	}
}
