package com.ecommerce.store.domain;

import java.time.Instant;

import com.ecommerce.store.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_payment_with_ticket")
@JsonTypeName("paymentWithTicket")
public class PaymentWithTicket extends Payment {


	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dueDate;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant payday;

	public PaymentWithTicket() {
		super();
	}

	public PaymentWithTicket(Long id, PaymentStatus status, Order order, Instant dueDate, Instant payday) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.payday = payday;
	}

	

}
