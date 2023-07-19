package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Product;
import com.ecommerce.store.dto.ProductDTO;
import com.ecommerce.store.dto.ProductMinDTO;
import com.ecommerce.store.repository.ProductRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;
import com.ecommerce.store.util.Formatter;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		List<Product> result = productRepository.findAllProducts();
		return result.stream().map(item -> new ProductDTO(item, item.getCategories()))
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long productId) {
		try {
			Product obj = productRepository.findByProductId(productId);
			return new ProductDTO(obj, obj.getCategories());
		} catch (Exception e) {
			throw new EntityNotFoundException("Entity not found");
		}
	}
	
	@Transactional
	public ProductMinDTO saveProduct(ProductDTO dto) {
		Product entity = new Product();
		entity = Formatter.dtoToProduct(entity, dto);
		entity = productRepository.save(entity);
		return new ProductMinDTO(entity);
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
	public ProductMinDTO updateProduct(Long productId, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(productId);
			entity = Formatter.dtoToProduct(entity, dto);
			productRepository.save(entity);
			return new ProductMinDTO(entity);
		} catch (Exception e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
}
