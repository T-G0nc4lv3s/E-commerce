package com.ecommerce.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.dto.CategoryDTO;
import com.ecommerce.store.repository.CategoryRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> searchAll() {
		List<Category> list = categoryRepository.findAll();
		List<CategoryDTO> result = new ArrayList<>();
		list.forEach(x -> result.add(new CategoryDTO(x)));
		return result;
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long categoryId) {
		Category result = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(result);
	}

	@Transactional(readOnly = false)
	public CategoryDTO insertCategory(CategoryDTO dto) {
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
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

	@Transactional
	public void deleteById(Long categoryId) {
		
		try {
			categoryRepository.deleteById(categoryId);
			
		} catch (Error e) {
			throw new EntityNotFoundException(e.getMessage());
		}
		
	}
}
