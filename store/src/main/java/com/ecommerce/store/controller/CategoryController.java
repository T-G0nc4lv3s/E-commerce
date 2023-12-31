package com.ecommerce.store.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.store.dto.CategoryDTO;
import com.ecommerce.store.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> result = categoryService.searchAll();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryDTO> finById(@PathVariable Long categoryId){
		CategoryDTO result = categoryService.findById(categoryId);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insertCategory(@Valid @RequestBody CategoryDTO dto){
		CategoryDTO result = categoryService.insertCategory(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{/categoryId}").buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(uri).body(result);	
	}
	
	@PutMapping(value = "/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDTO dto){
		CategoryDTO result = categoryService.updateCategory(categoryId, dto);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(value = "/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteById(categoryId);
		return ResponseEntity.noContent().build();
	}
}
