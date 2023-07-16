package com.ecommerce.store.dto;

import com.ecommerce.store.domain.Client;
import com.ecommerce.store.domain.enums.ClientType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientInsertDTO {

	private Long id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private ClientType type;
	
	private String phone1;
	private String phone2;
	private String phone3;
	
	private String street;
	private String number;
	private String district;
	private String complement;
	private String zipCode;
	private String cityId;
	
	public ClientInsertDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpfOrCnpj = entity.getCpfOrCnpj();
		this.type = entity.getType();
	}
}
