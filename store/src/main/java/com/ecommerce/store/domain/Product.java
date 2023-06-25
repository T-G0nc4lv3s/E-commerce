package com.ecommerce.store.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_product")
public class Product {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	
	@ManyToMany
	@JoinTable(
			name = "tb_product_category",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
			)
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> itens = new HashSet<>();

	public Product(Long id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
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
	
	public void addCategory(Category category) {
		categories.add(category);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		itens.add(orderItem);
	}
	
	public List<Order> getOrders(){
		List<Order> list = new ArrayList<>();
		itens.forEach(item -> list.add(item.getOrder()));
		return list;
	}

}
