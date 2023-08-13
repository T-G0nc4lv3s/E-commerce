package com.ecommerce.store.dto;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.store.domain.City;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityDTO {

	private Long id;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 80, message = "Length should be between 5 to 80")
	private String name;
	
	@NotBlank(message = "Required field")
	private String stateId;

	public CityDTO(City entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stateId = String.valueOf(entity.getState().getId());
	}
}
