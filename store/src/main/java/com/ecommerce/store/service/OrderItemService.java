package com.ecommerce.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.OrderItem;
import com.ecommerce.store.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Transactional(readOnly = false)
	public void saveOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}
}
