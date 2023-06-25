package com.ecommerce.store;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerce.store.domain.Order;
import com.ecommerce.store.domain.Payment;
import com.ecommerce.store.domain.PaymentWithCard;
import com.ecommerce.store.domain.enums.PaymentStatus;
import com.ecommerce.store.service.OrderService;

@SpringBootApplication
public class StoreApplication implements CommandLineRunner{

	@Autowired
	private OrderService orderService;
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Order order1 = new Order(null, Instant.parse("2022-12-03T10:15:30.00Z"), null, null);
		
		Payment payment1 = new PaymentWithCard(null, PaymentStatus.PENDING, order1, 6);
		order1.setPayment(payment1);
		
		orderService.saveOrder(order1);
		
	}

}
