package com.ecommerce.store.util;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.domain.Product;
import com.ecommerce.store.domain.State;
import com.ecommerce.store.dto.CategoryDTO;
import com.ecommerce.store.dto.ProductDTO;
import com.ecommerce.store.dto.StateDTO;

public class Formatter {

	
	public static Product dtoToProduct(ProductDTO dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);
		dto.getCategories().forEach(item -> product.getCategories().add(categoryInstance(item)));
		return product;
	}
	
	public static Category categoryInstance(CategoryDTO dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());
		return category;
	}
	
	public static State stateInstance(StateDTO dto) {
		State state = new State();
		BeanUtils.copyProperties(dto, state);
		return state;
	}

}
