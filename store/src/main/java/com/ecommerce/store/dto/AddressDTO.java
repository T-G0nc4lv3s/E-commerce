package com.ecommerce.store.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {

	private Long id;
	private String street;
	private String number;
	private String district;
	private String complement;
	private String zipCode;
	private String clientId;
	private String cityId;
	
	public AddressDTO(Address entity) {
		BeanUtils.copyProperties(entity, this);
		this.clientId = String.valueOf(entity.getClient().getId());
		this.cityId = String.valueOf(entity.getCity().getId());
	}
	
	
}
