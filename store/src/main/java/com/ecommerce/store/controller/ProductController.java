package com.ecommerce.store.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.store.dto.ProductDTO;
import com.ecommerce.store.dto.ProductMinDTO;
import com.ecommerce.store.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping(value = "/{productId}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long productId){
		ProductDTO response = productService.findById(productId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAllPaged(Pageable pageable,
			@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
			@RequestParam(value = "name", defaultValue = "") String name
			){
		
		Page<ProductDTO> page = productService.findAllPaged(pageable, categoryId, name.trim());
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	public ResponseEntity<ProductDTO> insertProduct(@RequestBody ProductDTO dto){
		ProductDTO response = productService.saveProduct(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{productId}").buildAndExpand(response.getId()).toUri();
		return ResponseEntity.created(uri).body(response);
	}
	
	@DeleteMapping(value = "/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
		productService.deleteById(productId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{productId}")
	public ResponseEntity<ProductMinDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO dto){
		ProductMinDTO response = productService.updateProduct(productId, dto);
		return ResponseEntity.ok(response);
	}
}
