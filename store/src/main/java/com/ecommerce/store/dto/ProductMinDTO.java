package com.ecommerce.store.dto;

import org.springframework.beans.BeanUtils;

import com.ecommerce.store.domain.Product;
import com.ecommerce.store.projections.ProductMinprojection;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductMinDTO {

	@Include
	private Long id;
	private String name;
	private double price;
	
	public ProductMinDTO(Product product) {
		BeanUtils.copyProperties(product, this);
	}

	public ProductMinDTO(ProductMinprojection projection) {
		this.id = projection.getId();
		this.name = projection.getName();
		this.price = projection.getPrice();
	}
}
