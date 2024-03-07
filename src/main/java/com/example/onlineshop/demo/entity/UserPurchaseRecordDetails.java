package com.example.onlineshop.demo.entity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPurchaseRecordDetails {
	
	private int userId;
	private int productId;
	private String productName;
	private int quantity;
	private int price;
	private String purchaseDate;

	
}
