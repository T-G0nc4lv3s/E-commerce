package com.ecommerce.store.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Address;
import com.ecommerce.store.projections.AddressMinProjection;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {

	private Long id;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 200, message = "Length should be between 5 to 200")
	private String street;
	
	@NotBlank(message = "Required field")
	private String number;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 200, message = "Length should be between 5 to 200")
	private String district;
	
	private String complement;
	@NotBlank(message = "Riquired field")
	@Length(min = 8, max = 8, message = "Length should be 8")
	private String zipCode;
	
	@NotBlank(message = "Riquired field")
	private String clientId;
	
	@NotBlank(message = "Riquired field")
	private String cityId;
	
	public AddressDTO(Address entity) {
		BeanUtils.copyProperties(entity, this);
		this.clientId = String.valueOf(entity.getClient().getId());
		this.cityId = String.valueOf(entity.getCity().getId());
	}
	
	public AddressDTO(AddressMinProjection projection) {
		this.id = projection.getId();
		this.street = projection.getStreet();
		this.complement = projection.getComplement();
		this.number = projection.getNumber();
		this.district = projection.getDistrict();
		this.zipCode = projection.getZip_Code();
		this.cityId = String.valueOf(projection.getCity_Id());
		this.clientId = String.valueOf(projection.getClient_Id());
	}
}
