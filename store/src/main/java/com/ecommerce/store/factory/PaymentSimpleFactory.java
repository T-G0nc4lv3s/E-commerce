package com.ecommerce.store.factory;

import java.time.Instant;

import com.ecommerce.store.domain.Payment;
import com.ecommerce.store.domain.PaymentWithCard;
import com.ecommerce.store.domain.PaymentWithTicket;
import com.ecommerce.store.domain.enums.PaymentStatus;
import com.ecommerce.store.service.TicketService;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PaymentSimpleFactory {
	
	private TicketService ticketService;
	
	
	public Payment createPayment(String type, Integer installments) {
		
		Payment payment = null;
		if(installments < 1 || installments == null) {
			installments = 1;
		}
		
		if(type.toLowerCase() == "paymentwithcard") {
			payment = new PaymentWithCard(null, PaymentStatus.PENDING, null, installments);
		}
		else {
			payment = new PaymentWithTicket();
			ticketService.fillTicket( (PaymentWithTicket) payment, Instant.now());
			payment.setStatus(PaymentStatus.PENDING);
		}
		
		return payment;
	}
}
