package com.company.inventory.response;

import java.util.List;

import com.company.inventory.entity.ProductEntity;

import lombok.Data;

@Data
public class ProductResponse {
	List<ProductEntity> products;
}
