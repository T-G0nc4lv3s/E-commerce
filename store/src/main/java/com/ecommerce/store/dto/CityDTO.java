package com.ecommerce.store.dto;

import com.ecommerce.store.domain.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityDTO {

	private Long id;
	private String name;
	
	private StateDTO stateDTO;

	public CityDTO(City entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stateDTO = new StateDTO(entity.getState());
	}
}
