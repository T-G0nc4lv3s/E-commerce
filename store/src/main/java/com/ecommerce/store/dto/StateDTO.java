package com.ecommerce.store.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateDTO {

	private Long id;
	private String name;
	private String acronym;
	
	public StateDTO(State entity) {
		BeanUtils.copyProperties(entity, this);
	}
	
}
