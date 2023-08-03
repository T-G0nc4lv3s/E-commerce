package com.ecommerce.store.dto;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.store.domain.Order;
import com.ecommerce.store.domain.OrderItem;
import com.ecommerce.store.domain.Payment;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderDTO {

	private Payment payment;
	private String clientId;
	private String addressId;
	
	private Set<OrderItem> itens = new HashSet<>();
	
	public OrderDTO(Payment payment, String clientId, String addressId) {
		this.payment = payment;
		this.clientId = clientId;
		this.addressId = addressId;
	}
	
	
	public OrderDTO(Order entity) {
		this.clientId = String.valueOf(entity.getClient().getId());
		this.addressId = String.valueOf(entity.getAddress().getId());
		this.payment = entity.getPayment();
		this.itens = entity.getItens();
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
}
