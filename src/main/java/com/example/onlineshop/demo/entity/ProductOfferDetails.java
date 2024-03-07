package com.example.onlineshop.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductOfferDetails {
	private int offerId;
	private int productId;
	private String productName;
	private int productPrice;
	private String productOffer;
	private boolean isEnabled;
}
