package com.ecommerce.store.domain;

import java.time.Instant;

import com.ecommerce.store.domain.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentWithTiket extends Payment {


	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dueDate;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant payday;

	public PaymentWithTiket() {
		super();
	}

	public PaymentWithTiket(Long id, PaymentStatus status, Order order, Instant dueDate, Instant payday) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.payday = payday;
	}

	

}
