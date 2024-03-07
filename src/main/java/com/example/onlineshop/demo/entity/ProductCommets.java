package com.example.onlineshop.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
//@IdClass(ProductCommetsCompositeKey.class)
public class ProductCommets implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commetId;
	private int userId;
	private int productId;
	private String comments;
	private String commentsDate;
	private boolean isEnabled;

}
