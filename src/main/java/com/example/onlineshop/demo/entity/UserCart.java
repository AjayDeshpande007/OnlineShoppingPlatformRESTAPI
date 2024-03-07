package com.example.onlineshop.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(UserCartCompositeKey.class)
public class UserCart implements Serializable  {
	
	@Id
	private int userId;
	@Id
	private int productId;
	private int quantity;
	private String addDate;
}
