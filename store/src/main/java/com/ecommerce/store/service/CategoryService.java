package com.ecommerce.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.dto.CategoryDTO;
import com.ecommerce.store.repository.CategoryRepository;
import com.ecommerce.store.service.exception.DatabaseException;
import com.ecommerce.store.service.exception.ResourceNotFoundException;
import com.ecommerce.store.util.Formatter;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> searchAll() {
		List<Category> list = categoryRepository.findAll();
		return list.stream().map(item -> new CategoryDTO(item)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long categoryId) {
		Category result = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found " + categoryId));
		return new CategoryDTO(result);
	}

	@Transactional(readOnly = false)
	public CategoryDTO insertCategory(CategoryDTO dto) {
		Category entity = Formatter.categoryInstance(dto);
		entity = categoryRepository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO updateCategory(Long categoryId, CategoryDTO dto) {
		Category entity = categoryRepository.getReferenceById(categoryId);
		BeanUtils.copyProperties(dto, entity);
		entity = categoryRepository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleteById(Long categoryId) {
		if(!categoryRepository.existsById(categoryId)) {
			throw new ResourceNotFoundException("Entity not found " + categoryId);
		}
		try {
			categoryRepository.deleteById(categoryId);
			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}
}
