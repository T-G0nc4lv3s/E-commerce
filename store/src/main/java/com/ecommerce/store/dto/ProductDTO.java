package com.ecommerce.store.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.domain.Product;
import com.ecommerce.store.projections.ProductMinprojection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDTO {

	@Include
	private Long id;
	
	@NotBlank(message = "Required field")
	@Length(min = 5, max = 80, message = "Length should be between 5 to 80")
	private String name;
	
	@Positive(message = "Price should be positive value")
	private Double price;
	
	private List<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO(Long id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
	}
	
	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
	}
	
	public ProductDTO(ProductMinprojection projection) {
		this.id = projection.getId();
		this.name = projection.getName();
		this.price = projection.getPrice();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
