package com.ecommerce.store.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Address;
import com.ecommerce.store.domain.Client;
import com.ecommerce.store.domain.Order;
import com.ecommerce.store.domain.OrderItem;
import com.ecommerce.store.domain.Payment;
import com.ecommerce.store.dto.OrderDTO;
import com.ecommerce.store.factory.PaymentSimpleFactory;
import com.ecommerce.store.repository.AddressRepository;
import com.ecommerce.store.repository.ClientRepository;
import com.ecommerce.store.repository.OrderItemRepository;
import com.ecommerce.store.repository.OrderRepository;
import com.ecommerce.store.repository.PaymentRepository;
import com.ecommerce.store.repository.ProductRepository;
import com.ecommerce.store.service.exception.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	@Autowired
	private TicketService ticketService;
	
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional(readOnly = false)
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}
	
	@Transactional(readOnly = true)
	public List<Order> findOrders(){
		List<Order> result = orderRepository.findAll();
		return result;
	}

	@Transactional
	public Order insertOrder(OrderDTO orderDTO) {
		
		Client client = clientRepository.findById(Long.valueOf(orderDTO.getClientId()))
				.orElseThrow(() -> new ResourceNotFoundException("Client not found, id: "));
		
		Address address = addressRepository.findById(Long.valueOf(orderDTO.getClientId()))
				.orElseThrow(() -> new ResourceNotFoundException("Address not found, id: "));
		
		Order obj = new Order(null, Instant.now(), client, address);
		
		for(OrderItem item: orderDTO.getItens()) {
			obj.getItens().add(item);
		}

		for(OrderItem item: obj.getItens()) {
			item.setDiscount(0.0);
			item.setPrice(productRepository.getReferenceById(item.getProduct().getId()).getPrice());
			item.setOrder(obj);
		}
		
		PaymentSimpleFactory factory = new PaymentSimpleFactory();
		factory.setTicketService(ticketService);
		
		Payment payment = factory.createPayment(orderDTO.getType(), orderDTO.getInstallments());
		
		obj.setPayment(payment);
		
		obj = orderRepository.save(obj);
		
		orderItemRepository.saveAll(obj.getItens());
		
		
		obj = orderRepository.save(obj);
		
		paymentRepository.save(obj.getPayment());
		
		return obj;
	}
	
}
