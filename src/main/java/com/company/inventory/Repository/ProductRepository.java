package com.company.inventory.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.inventory.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
	@Query("select p from ProductEntity p where p.name like %?1%")
	List<ProductEntity> findByNameLike(String name);
	
	List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
