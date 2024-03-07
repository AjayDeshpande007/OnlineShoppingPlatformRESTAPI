package com.example.onlineshop.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineshop.demo.entity.Product;
import com.example.onlineshop.demo.entity.ProductCommets;
import com.example.onlineshop.demo.entity.ProductOfferDetails;
import com.example.onlineshop.demo.entity.UserCart;
import com.example.onlineshop.demo.entity.UserCartDetails;
import com.example.onlineshop.demo.entity.UserPurchaseRecordDetails;
import com.example.onlineshop.demo.service.AdminService;
import com.example.onlineshop.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public void login(@RequestParam String userId,@RequestParam String password) {
		
	}

	@GetMapping("/getProductListByCategoryIdForUser")
	public List<Product>  getProductListByCategoryIdForUser(@RequestParam String categoryId) {
		    return userService.getProductListByCategoryIdForUser(categoryId);
	}

	@GetMapping("/getAllItemFromCart")
	public List<UserCartDetails> getAllItemFromCart(@RequestParam int userId) {
		return userService.getAllItemFromCart(userId);
	}

	@PostMapping("/addNewItemIntoCart")
	public void addNewItemIntoCart(@RequestBody UserCart userCart) {
		userService.addNewItemIntoCart(userCart);
	}
	
	@GetMapping("/updateProductQuantityFromCart")
	public void updateProductQuantityFromCart(@RequestParam int userId,@RequestParam int productId,@RequestParam int quantity) {
		userService.updateProductQuantityFromCart(userId, productId,quantity);
	}

	
	@GetMapping("/deleteItemFromCart")
	public void deleteItemFromCart(@RequestParam int userId,@RequestParam int productId) {
		userService.deleteItemFromCart(userId, productId);
	}
	
	@PostMapping("/buyItemFromCart")
	public void buyItemFromCart(@RequestBody UserCart userCart) {
		userService.buyItemFromCart(userCart);
	}
	
	@PostMapping("/viewItemByCategory")
	public void viewItemByCategory(@RequestBody Product product) {
		
	}
	
	@PostMapping("/viewItemByPrice")
	public void viewItemByPrice(@RequestBody Product product) {
		
	}
	
	@GetMapping("/viewAllPreviouslyPurchasedItem")
	public List<UserPurchaseRecordDetails> viewAllPreviouslyPurchasedItem(@RequestParam int userId) {
		return this.userService.viewAllPreviouslyPurchasedItem(userId);
	}
	
	@GetMapping("/topTenSellingItemByPrice")
	public List<UserPurchaseRecordDetails> topTenSellingItemByPrice() {
		return this.userService.topTenSellingItemByPrice();
	}

	@PostMapping("/topTenSellingItemByCategory")
	public void topTenSellingItemByCategory(@RequestBody Product product) {
		
	}
	
	@GetMapping("/viewOffersForUser")
	public List<ProductOfferDetails> viewOffersForUser() {
		return this.userService.viewOffersForUser();
	}
	
	@PostMapping("/buyProducts")
	public void buyProducts(@RequestBody Product product) {
		
	}
	
	@GetMapping("/getAllCommentsByProductIdForUser")
	public List<ProductCommets> getAllCommentsByProductIdForUser(@RequestParam int productId) {
		return userService.getAllCommentsByProductIdForUser(productId);
	}

	@PostMapping("/addCommentsForProducts")
	public void addCommentsForProducts(@RequestBody ProductCommets productCommets) {
		this.userService.addCommentsForProducts(productCommets);
	}
}
