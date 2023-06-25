package com.ecommerce.store.domain;

import com.ecommerce.store.domain.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "tb_payment_with_card")
public class PaymentWithCard extends Payment {

	private Integer installment;	

	public PaymentWithCard() {
		super();
	}

	public PaymentWithCard(Long id, PaymentStatus status, Order order, Integer installment) {
		super(id, status, order);
		this.installment = installment;
	}

}
