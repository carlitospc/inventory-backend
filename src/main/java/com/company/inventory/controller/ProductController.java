package com.company.inventory.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventory.entity.ProductEntity;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.ProductService;
import com.company.inventory.util.Util;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
				@RequestParam("picture") MultipartFile picture,
				@RequestParam("name") String name,
				@RequestParam("price") int price,
				@RequestParam("account") int account,
				@RequestParam("categoryId") Long categoryId) throws IOException {
		ProductEntity product = new ProductEntity();
		product.setName(name);
		product.setPrice(price);
		product.setAccount(account);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);
		
		return response;
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchById(
			@PathVariable Long id) {
		
		ResponseEntity<ProductResponseRest> response = productService.searchById(id);
		return response;
	}
	
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchByName(
			@PathVariable String name) {
		
		ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
		return response;
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> deleteById(
			@PathVariable Long id) {
		
		ResponseEntity<ProductResponseRest> response = productService.deleteById(id);
		return response;
	}
	
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> search() {
		
		ResponseEntity<ProductResponseRest> response = productService.search();
		return response;
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(
				@RequestParam("picture") MultipartFile picture,
				@RequestParam("name") String name,
				@RequestParam("price") int price,
				@RequestParam("account") int account,
				@RequestParam("categoryId") Long categoryId,
				@PathVariable Long id) throws IOException {
		ProductEntity product = new ProductEntity();
		product.setName(name);
		product.setPrice(price);
		product.setAccount(account);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		ResponseEntity<ProductResponseRest> response = productService.update(product, categoryId, id);
		
		return response;
	}
}
