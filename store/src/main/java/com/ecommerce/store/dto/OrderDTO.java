package com.ecommerce.store.dto;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.store.domain.OrderItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderDTO {

	@NotBlank(message = "Required field")
	private String clientId;
	
	@NotBlank(message = "Required field")
	private String addressId;
	
	@NotBlank(message = "Required field")
	private String type;
	
	@Positive
	private Integer installments;
	
	private Set<OrderItem> itens = new HashSet<>();
	
	public OrderDTO(String clientId, String addressId, String type, Integer installments) {
		
		this.clientId = clientId;
		this.addressId = addressId;
		this.type = type;
		this.installments = installments;
	}
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
}
