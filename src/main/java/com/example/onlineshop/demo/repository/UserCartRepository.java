package com.example.onlineshop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshop.demo.entity.UserCart;
import com.example.onlineshop.demo.entity.UserCartCompositeKey;

public interface UserCartRepository extends JpaRepository<UserCart, UserCartCompositeKey> {

	List<UserCart> findByUserId(int userId);
}
