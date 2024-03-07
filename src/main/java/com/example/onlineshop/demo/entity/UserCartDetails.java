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
public class UserCartDetails {
	
	private int userId;
	private int productId;
	private String productName;
	private int productPrice;
	private int quantity;
	private String addDate;

	
}
