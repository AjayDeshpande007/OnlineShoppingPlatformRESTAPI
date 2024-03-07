package com.example.onlineshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshop.demo.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByCategoryId(String categoryId);
	List<Product> findByCategoryIdAndIsProductEnabled(String categoryId,boolean isProductEnabled);
}
