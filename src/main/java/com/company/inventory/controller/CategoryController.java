package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.entity.CategoryEntity;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories() {
		ResponseEntity<CategoryResponseRest> response = categoryService.search();
		return response;
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoriesById(
			@PathVariable Long id) {
		ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
		return response;
	}
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> save(
			@RequestBody CategoryEntity category) {
		ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
		return response;
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> update(
			@RequestBody CategoryEntity category, 
			@PathVariable Long id) {
		ResponseEntity<CategoryResponseRest> response = categoryService.update(category, id);
		return response;
	}
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> delete(
			@PathVariable Long id) {
		ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
		return response;
	}
}
