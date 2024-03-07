package com.example.onlineshop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshop.demo.entity.Category;
import com.example.onlineshop.demo.entity.Offers;


public interface OffersRepository extends JpaRepository<Offers, Integer> {
	
	List<Offers> findByProductId(int productId);
}
