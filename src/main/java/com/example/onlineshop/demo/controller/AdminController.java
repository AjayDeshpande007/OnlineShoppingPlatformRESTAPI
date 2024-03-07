package com.example.onlineshop.demo.controller;

import java.util.List;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineshop.demo.entity.Category;
import com.example.onlineshop.demo.entity.Offers;
import com.example.onlineshop.demo.entity.Product;
import com.example.onlineshop.demo.entity.ProductCommets;
import com.example.onlineshop.demo.entity.ProductOfferDetails;
import com.example.onlineshop.demo.entity.User;
import com.example.onlineshop.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/login")
	public void login(@RequestParam String userId,@RequestParam String password) {
		
	}
	
	@PostMapping("/addNewProduct")
	public void addNewProduct(@RequestBody Product product) {
		adminService.addNewProduct(product);
	}
	
	@GetMapping("/updateProductPrice")
	public void updateProductPrice(@RequestParam Integer productId,@RequestParam Integer productPrice) {
		adminService.updateProductPrice(productId,productPrice);	
	}

	@GetMapping("/updateProductQuantity")
	public void updateProductQuantity(@RequestParam Integer productId,@RequestParam Integer productQuantity) {
		adminService.updateProductQuantity(productId,productQuantity);	
	}

	
	@GetMapping("/deleteProduct")
	public void deleteProduct(@RequestParam Integer productId) {
		adminService.deleteProduct(productId);
	}
	
	@GetMapping("/enableDisableProduct")
	public void enableDisableProduct(@RequestParam Integer productId,@RequestParam boolean productStatus) {
		adminService.enableDisableProduct(productId,productStatus);
	}
	
	@GetMapping("/seeAllUserList")
	public List<User> seeAllUserList() {
		return adminService.seeAllUserList();
	}
	
	@GetMapping("/getCategoryList")
	public List<Category> getCategoryList() {
		  return adminService.getCategoryList();
	  }
	
	@GetMapping("/getProductListByCategoryId")
	public List<Product>  getProductListByCategoryId(@RequestParam String categoryId) {
		    return adminService.getProductListByCategoryId(categoryId);
	}

	
	@GetMapping("/topTenUserByQuantity")
	public List<User> topTenUserByQuantity() {
		return adminService.topTenUserByQuantity();
	}
	
	@GetMapping("/topTenUserByPurchase")
	public List<User> topTenUserByPurchase() {
		return adminService.topTenUserByPurchase();
	}
	
	@GetMapping("/topTenCategoryByQuantity")
	public List<Category> topTenCategoryByQuantity() {
		return adminService.topTenCategoryByQuantity();
	}
	
	@GetMapping("/topTenCategoryByPurchase")
	public List<Category> topTenCategoryByPurchase() {
		
		return adminService.topTenCategoryByPurchase();
	}
	
	@GetMapping("/getAllCommentsByProductId")
	public List<ProductCommets> getAllCommentsByProductId(@RequestParam int productId) {
		return adminService.getAllCommentsByProductId(productId);
	}

	@GetMapping("/getAllCommentsByCategory")
	public List<ProductCommets> getAllCommentsByCategory() {
		return adminService.getAllCommentsByCategory();
	}
	
	@GetMapping("/updateCommets")
	public void updateCommets(@RequestParam int commentId,@RequestParam String updatedComment) {
		adminService.updateCommets(commentId, updatedComment);
	}
	
	
	@GetMapping("/enableDisableCommets")
	public void enableDisableCommets(@RequestParam int commentId, @RequestParam boolean isEnabled) {
		adminService.enableDisableCommets(commentId,isEnabled);
	}
	
	
	@GetMapping("/deleteComments")
	public void deleteComments(@RequestParam int commentId) {
		adminService.deleteComments(commentId);
	}
	
	@PostMapping("/addProductOffer")
	public void addProductOffer(@RequestBody Offers offers) {
		this.adminService.addProductOffer(offers);
	}
	
	@GetMapping("/updateOffers")
	public void updateOffers(@RequestParam int offerId,String updatedOffer) {
		adminService.updateOffers(offerId, updatedOffer);
	}
	
	
	@GetMapping("/enableDisableOffers")
	public void enableDisableOffers(@RequestParam int offerId, @RequestParam boolean isEnabled) {
		adminService.enableDisableOffers(offerId,isEnabled);
	}
	
	
	@GetMapping("/deleteOffers")
	public void deleteOffers(@RequestParam int offerId) {
		adminService.deleteOffers(offerId);
	}
	
	@GetMapping("/viewOffers")
	public List<ProductOfferDetails> viewOffers(@RequestParam int productId) {
		return this.adminService.viewOffers(productId);
	}


	
	
}
