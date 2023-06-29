package com.ecommerce.store.record;

import java.util.Set;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.domain.Product;

public record ProductRecord(Long id, String name, Double price, Set<Category> categories) {
	
	public ProductRecord(Product product) {
		this(product.getId(), product.getName(), product.getPrice(), product.getCategories());
	}


}
