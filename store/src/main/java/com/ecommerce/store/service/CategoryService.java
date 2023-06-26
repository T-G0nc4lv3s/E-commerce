package com.ecommerce.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.store.domain.Category;
import com.ecommerce.store.dto.CategoryDTO;
import com.ecommerce.store.record.CategoryRecord;
import com.ecommerce.store.repository.CategoryRepository;
import com.ecommerce.store.service.exception.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<CategoryRecord> searchAll() {
		List<Category> list = categoryRepository.findAll();
		List<CategoryRecord> result = new ArrayList<>();
		list.forEach(x -> result.add(new CategoryRecord(x)));
		return result;
	}

	@Transactional(readOnly = true)
	public CategoryRecord findById(Long categoryId) {
		Category result = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryRecord(result);
	}

	@Transactional(readOnly = false)
	public CategoryRecord insertCategory(CategoryDTO dto) {
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		entity = categoryRepository.save(entity);
		return new CategoryRecord(entity);
	}

	@Transactional
	public CategoryRecord updateCategory(Long categoryId, CategoryDTO dto) {
		Category entity = categoryRepository.getReferenceById(categoryId);
		BeanUtils.copyProperties(dto, entity);
		entity = categoryRepository.save(entity);
		return new CategoryRecord(entity);
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
