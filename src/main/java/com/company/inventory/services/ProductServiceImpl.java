package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.Repository.CategoryRepository;
import com.company.inventory.Repository.ProductRepository;
import com.company.inventory.entity.CategoryEntity;
import com.company.inventory.entity.ProductEntity;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.util.Util;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository; 

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(ProductEntity product, Long categoryId) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<ProductEntity> list = new ArrayList<>();
		
		try {
			//search category to set in the product object
			Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
			if(category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Categoria no encontrada asociada");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//save product
			ProductEntity productSaved = productRepository.save(product);
			if(productSaved != null) {
				list.add(productSaved);
				
				response.getProductResponse().setProducts(list);
				response.setMetadata("Respuesta NOK", "00", "Producto guardado");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Producto no guardado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta NOK", "-1", "Error al guardar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true) 
	public ResponseEntity<ProductResponseRest> searchById(Long id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<ProductEntity> list = new ArrayList<>();
		
		try {
			//search product by id
			Optional<ProductEntity> product = productRepository.findById(id);
			
			if(product.isPresent()) {
				byte[] imageDescompressed = Util.decompressZLib(product.get().getPicture());
				product.get().setPicture(imageDescompressed);
				list.add(product.get());
				
				response.getProductResponse().setProducts(list);
				response.setMetadata("Respuesta OK", "00", "Producto encontrado");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Producto no encontrado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> searchByName(String name) {

		ProductResponseRest response = new ProductResponseRest();
		List<ProductEntity> list = new ArrayList<>();
		List<ProductEntity> listAux = new ArrayList<>();
		
		try {
			//search product by name
			listAux = productRepository.findByNameContainingIgnoreCase(name);
			
			if(listAux.size() > 0) {
				listAux.stream().forEach((p) -> {
					byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
					p.setPicture(imageDescompressed);
					list.add(p);
				});
				
				response.getProductResponse().setProducts(list);
				response.setMetadata("Respuesta OK", "00", "Productos encontrados");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Productos no encontrados");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar productos por nombre");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> deleteById(Long id) {
		
		ProductResponseRest response = new ProductResponseRest();
		
		try {
			//delete product by id
			productRepository.deleteById(id);
			
			response.setMetadata("Respuesta OK", "00", "Producto eliminado");	
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta NOK", "-1", "Error al eliminar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ProductResponseRest> search() {
		
		ProductResponseRest response = new ProductResponseRest();
		List<ProductEntity> list = new ArrayList<>();
		List<ProductEntity> listAux = new ArrayList<>();
		
		try {
			//search product
			listAux = (List<ProductEntity>)productRepository.findAll();
			
			if(listAux.size() > 0) {
				listAux.stream().forEach((p) -> {
					byte[] imageDescompressed = Util.decompressZLib(p.getPicture());
					p.setPicture(imageDescompressed);
					list.add(p);
				});
				
				response.getProductResponse().setProducts(list);
				response.setMetadata("Respuesta OK", "00", "Productos encontrados");
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Productos no encontrados");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta NOK", "-1", "Error al buscar productos");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> update(ProductEntity product, Long categoryId, Long id) {
		
		ProductResponseRest response = new ProductResponseRest();
		List<ProductEntity> list = new ArrayList<>();
		
		try {
			//search product to set in the product object
			Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
			if(category.isPresent()) {
				product.setCategory(category.get());
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Categoria no encontrada asociada");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			//search product to update
			Optional<ProductEntity> productSearch = productRepository.findById(id);
			if(productSearch.isPresent()) {
				
				//update product
				productSearch.get().setName(product.getName());
				productSearch.get().setPrice(product.getPrice());
				productSearch.get().setAccount(product.getAccount());
				productSearch.get().setCategory(product.getCategory());
				productSearch.get().setPicture(product.getPicture());
				
				//save product in db
				ProductEntity productToUpdate = productRepository.save(productSearch.get());
				if(productToUpdate != null) {
					list.add(productToUpdate);
					
					response.getProductResponse().setProducts(list);
					response.setMetadata("Respuesta NOK", "00", "Producto actualizado");
				} else {
					response.setMetadata("Respuesta NOK", "-1", "Error al actualizar producto");
					return new ResponseEntity<ProductResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			} else {
				response.setMetadata("Respuesta NOK", "-1", "Producto no guardado");
				return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta NOK", "-1", "Error al actualizar producto");
			return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
	}
}
