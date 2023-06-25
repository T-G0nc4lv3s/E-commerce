package com.ecommerce.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Order;
import com.ecommerce.store.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional(readOnly = false)
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}
}
