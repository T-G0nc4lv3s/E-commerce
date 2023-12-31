package com.ecommerce.store.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.domain.Product;
import com.ecommerce.store.dto.ProductDTO;
import com.ecommerce.store.dto.ProductMinDTO;
import com.ecommerce.store.repository.CategoryRepository;
import com.ecommerce.store.repository.ProductRepository;
import com.ecommerce.store.service.exception.ResourceNotFoundException;
import com.ecommerce.store.util.Formatter;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long productId) {
		try {
			Product obj = productRepository.findByProductId(productId);
			return new ProductDTO(obj, obj.getCategories());
		} catch (Exception e) {
			throw new ResourceNotFoundException("Entity not found");
		}
	}
	
	@Transactional
	public ProductDTO saveProduct(ProductDTO dto) {
		Product entity = new Product();
		entity = Formatter.dtoToProduct(dto);
		entity = productRepository.save(entity);
		return new ProductDTO(entity, entity.getCategories());
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long productId) {
		if(!productRepository.existsById(productId)) {
			throw new ResourceNotFoundException("Id not found " + productId);
		}
		try {
			productRepository.deleteById(productId);
		} catch (IllegalArgumentException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}

	}
	
	@Transactional
	public ProductMinDTO updateProduct(Long productId, ProductDTO dto) {
		try {
			Product entity = productRepository.getReferenceById(productId);
			entity = Formatter.dtoToProduct(dto);
			productRepository.save(entity);
			return new ProductMinDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(Pageable pageable, Long categoryId, String name) {
		List<Category> cats = (categoryId == 0) ? null : Arrays.asList(categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found")));
		Page<Product> page = productRepository.searchProduct(pageable, cats, name);
		return page.map(x -> new ProductDTO(x));
	}
	
}
