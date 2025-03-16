package com.company.inventory.response;

import java.util.List;

import com.company.inventory.entity.CategoryEntity;

import lombok.Data;

@Data
public class CategoryResponse {
	private List<CategoryEntity> category;
}
