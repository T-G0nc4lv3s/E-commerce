package com.ecommerce.store.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.State;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateDTO {

	private Long id;
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 80, message = "Length should be between 5 to 80")
	private String name;
	@NotBlank(message = "Required field")
	private String acronym;
	
	public StateDTO(State entity) {
		BeanUtils.copyProperties(entity, this);
	}
	
}
