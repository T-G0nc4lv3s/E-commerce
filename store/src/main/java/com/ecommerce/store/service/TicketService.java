package com.ecommerce.store.service;

import java.time.Instant;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.ecommerce.store.domain.PaymentWithTicket;

@Service
public class TicketService {

	public void fillTicket(PaymentWithTicket payment, Instant instant) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(instant.toEpochMilli());
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setPayday(Instant.ofEpochMilli(cal.getTimeInMillis()));
	}

	
}
