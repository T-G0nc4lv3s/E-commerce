package com.ecommerce.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerce.store.service.CategoryService;

@SpringBootApplication
public class StoreApplication implements CommandLineRunner{

	/*
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemService orderItemService;
	*/
	
	@Autowired
	private CategoryService categoryService;
	
	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Order order1 = new Order(null, Instant.parse("2022-12-03T10:15:30.00Z"), null, null);
		
		Payment payment1 = new PaymentWithCard(null, PaymentStatus.PENDING, order1, 6);
		order1.setPayment(payment1);
		
		orderService.saveOrder(order1);
		
		Product product1 = new Product(null, "Xbox one", 4000.00);
		Product product2 = new Product(null, "Playstation 5", 5000.00);
		Product product3 = new Product(null, "Notebook Ryzen 5", 5500.00);
		
		productService.saveProduct(product1);
		productService.saveProduct(product2);
		productService.saveProduct(product3);
		
		OrderItem orderItem1 = new OrderItem(product1, order1, 0.00, 1);
		OrderItem orderItem2 = new OrderItem(product2, order1, 0.00, 1);
		OrderItem orderItem3 = new OrderItem(product3, order1, 0.00, 1);
		
		order1.setOrderItem(orderItem1);
		order1.setOrderItem(orderItem2);
		
		product1.addOrderItem(orderItem1);
		product2.addOrderItem(orderItem2);
		product3.addOrderItem(orderItem3);
		
		
		orderItemService.saveOrderItem(orderItem1);
		
		
		
		System.out.println(categoryService.searchAll());
		
		*/
	}

}
