package com.ecommerce.store.dto;

import com.ecommerce.store.domain.City;
import com.ecommerce.store.projections.CityMinProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityMinDTO {

	private Long id;
	private String name;
	private String stateId;
	
	public CityMinDTO(CityMinProjection projection) {
		this.id = projection.getId();
		this.name = projection.getName();
		this.stateId = String.valueOf(projection.getState_Id());
	}
	
	public CityMinDTO(City entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stateId = String.valueOf(entity.getState().getId());
	}
}
