package com.ecommerce.store.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

	private Long id;
	private String name;
	
	public CategoryDTO(Category entity) {
		BeanUtils.copyProperties(entity, this);
	}
}
