package com.example.onlineshop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineshop.demo.entity.UserPurchaseRecord;
import com.example.onlineshop.demo.entity.UserPurchaseRecordCompositeKey;

public interface UserPurchaseRecordRepository extends JpaRepository<UserPurchaseRecord, UserPurchaseRecordCompositeKey> {
	
	List<UserPurchaseRecord> findByUserId(int userId);
	
	List<UserPurchaseRecord> findTop10ByOrderByPriceDesc();

}
