package com.ecommerce.store.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.store.domain.Order;
import com.ecommerce.store.dto.OrderDTO;
import com.ecommerce.store.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> response = orderService.findOrders();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Order> insertCategory(@RequestBody OrderDTO orderDTO){
		Order result = orderService.insertOrder(orderDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/categoryId}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(uri).body(result);	
	}
}
