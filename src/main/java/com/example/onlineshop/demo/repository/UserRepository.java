package com.example.onlineshop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshop.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
