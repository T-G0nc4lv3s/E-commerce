package com.ecommerce.store.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_order")
public class Order {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant date;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "client_id") 
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> itens = new HashSet<>();

	public Order(Long id, Instant date, Client client, Address address) {
		this.id = id;
		this.date = date;
		this.client = client;
		this.address = address;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public void setPayment(Payment payment) {
		payment.setOrder(this);
		this.payment = payment;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void setOrderItem(OrderItem orderItem) {
		itens.add(orderItem);
	}
	
	@JsonIgnore
	public List<Product> getProducts(){
		List<Product> list = new ArrayList<>();
		itens.forEach(item -> list.add(item.getProduct()));
		return list;
	}

}
