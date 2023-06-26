package com.ecommerce.store.record;

import com.ecommerce.store.domain.Category;

public record CategoryRecord(Long id, String name) {
	
	public CategoryRecord(Category category) {
		this(category.getId(), category.getName());
	}

}
