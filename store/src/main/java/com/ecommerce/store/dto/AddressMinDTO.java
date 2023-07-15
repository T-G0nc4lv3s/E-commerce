package com.ecommerce.store.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Address;
import com.ecommerce.store.projections.AddressMinProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressMinDTO {

	private Long id;
	private String street;
	private String complement;
	private String number;
	private String district;
	private String zipCode;
	private Long cityId;
	private Long clientId;
	
	public AddressMinDTO(AddressMinProjection projection) {
		this.id = projection.getId();
		this.street = projection.getStreet();
		this.complement = projection.getComplement();
		this.number = projection.getNumber();
		this.district = projection.getDistrict();
		this.zipCode = projection.getZip_Code();
		this.cityId = projection.getCity_Id();
		this.clientId = projection.getClient_Id();
	}

	public AddressMinDTO(Address entity) {
		BeanUtils.copyProperties(entity, this);
		cityId = entity.getCity().getId();
		clientId = entity.getClient().getId();
	}
}
