package com.ecommerce.store.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientUpdateDTO {

	private String name;
	private String email;
	
	private Set<String> phones = new HashSet<>();

	public ClientUpdateDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public ClientUpdateDTO(Client entity) {
		BeanUtils.copyProperties(entity, this);
		this.phones = entity.getPhones();
	}
}
