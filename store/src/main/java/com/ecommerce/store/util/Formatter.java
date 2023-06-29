package com.ecommerce.store.util;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.domain.Product;
import com.ecommerce.store.dto.CategoryDTO;
import com.ecommerce.store.dto.ProductDTO;

public class Formatter {

	
	public static Product dtoToEntity(Product product, ProductDTO dto) {
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		dto.getCategories().forEach(x -> product.addCategory(Formatter.categoryInstance(x)));
		return product;
	}
	
	
	public static Category categoryInstance(CategoryDTO dto) {
		Category category = new Category();
		category.setId(dto.getId());
		category.setName(dto.getName());
		return category;
	}
}
