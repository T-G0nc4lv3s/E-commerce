package com.ecommerce.store.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class OrderItemPK {

	@Include
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Include
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

}
