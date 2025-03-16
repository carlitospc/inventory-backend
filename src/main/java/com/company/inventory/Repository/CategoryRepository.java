package com.company.inventory.Repository;

import org.springframework.data.repository.CrudRepository;

import com.company.inventory.entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

}
