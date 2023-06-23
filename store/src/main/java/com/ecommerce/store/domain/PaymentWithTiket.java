package com.ecommerce.store.domain;

import com.ecommerce.store.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentWithTiket extends Payment {

	private Integer installment;

	public PaymentWithTiket() {
		super();
	}

	public PaymentWithTiket(Long id, PaymentStatus status, Order order, Integer installments) {
		super(id, status, order);
		this.installment = installments;
	}

}
