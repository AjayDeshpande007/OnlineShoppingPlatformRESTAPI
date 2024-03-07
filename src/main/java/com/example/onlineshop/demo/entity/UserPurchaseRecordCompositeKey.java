package com.example.onlineshop.demo.entity;

import java.io.Serializable;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPurchaseRecordCompositeKey implements Serializable {

	private int userId;
	private int productId;
	private int quantity;

}
