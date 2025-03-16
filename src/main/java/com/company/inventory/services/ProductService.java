package com.company.inventory.services;

import org.springframework.http.ResponseEntity;

import com.company.inventory.entity.ProductEntity;
import com.company.inventory.response.ProductResponseRest;

public interface ProductService {

	public ResponseEntity<ProductResponseRest> save(ProductEntity product, Long categoryId);
	public ResponseEntity<ProductResponseRest> searchById(Long id);
	public ResponseEntity<ProductResponseRest> searchByName(String name);
	public ResponseEntity<ProductResponseRest> deleteById(Long id);
	public ResponseEntity<ProductResponseRest> search();
	public ResponseEntity<ProductResponseRest> update(ProductEntity product, Long categoryId, Long id);
}
