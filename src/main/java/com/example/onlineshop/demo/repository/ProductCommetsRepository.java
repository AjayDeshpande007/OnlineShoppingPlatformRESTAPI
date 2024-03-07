package com.example.onlineshop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshop.demo.entity.ProductCommets;
import com.example.onlineshop.demo.entity.ProductCommetsCompositeKey;

public interface ProductCommetsRepository extends JpaRepository<ProductCommets, Integer> {
	
	
	List<ProductCommets> findByProductId(int pId);
	List<ProductCommets> findByProductIdAndIsEnabled(int pId,boolean isEnabled);
}
