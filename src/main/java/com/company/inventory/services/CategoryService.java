package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.entity.CategoryEntity;
import com.company.inventory.response.CategoryResponseRest;

public interface CategoryService {

	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id); 
	public ResponseEntity<CategoryResponseRest> save(CategoryEntity category); 
	public ResponseEntity<CategoryResponseRest> update(CategoryEntity category, long id); 
	public ResponseEntity<CategoryResponseRest> deleteById(Long id); 
}
