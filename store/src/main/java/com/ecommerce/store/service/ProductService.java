package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Product;
import com.ecommerce.store.dto.ProductDTO;
import com.ecommerce.store.dto.ProductMinDTO;
import com.ecommerce.store.projections.ProductMinprojection;
import com.ecommerce.store.record.ProductRecord;
import com.ecommerce.store.repository.ProductRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;
import com.ecommerce.store.util.Formatter;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<ProductMinDTO> findAll(){
		List<ProductMinprojection> result = productRepository.findAllProducts();
		return result.stream().map(item -> new ProductMinDTO(item)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ProductRecord findById(Long productId) {
		Product entity = productRepository.findById(productId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new ProductRecord(entity);
	}
	
	@Transactional
	public ProductRecord saveProduct(ProductDTO dto) {
		Product entity = new Product();
		entity = Formatter.dtoToProduct(entity, dto);
		entity = productRepository.save(entity);
		return new ProductRecord(entity);
	}
	
	@Transactional
	public void deleteById(Long productId) {
		try {
			productRepository.deleteById(productId);
		} catch (IllegalArgumentException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	@Transactional
	public ProductRecord updateProduct(Long productId, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(productId);
			entity = Formatter.dtoToProduct(entity, dto);
			productRepository.save(entity);
			return new ProductRecord(entity);
		} catch (Exception e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
}
