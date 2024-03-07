package com.example.onlineshop.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlineshop.demo.entity.Category;
import com.example.onlineshop.demo.entity.Offers;
import com.example.onlineshop.demo.entity.Product;
import com.example.onlineshop.demo.entity.ProductCommets;
import com.example.onlineshop.demo.entity.ProductOfferDetails;
import com.example.onlineshop.demo.entity.User;
import com.example.onlineshop.demo.repository.*;

@Service
public class AdminService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductCommetsRepository productCommetsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserCartRepository userCartRepository;
	
	@Autowired
	private UserPurchaseRecordRepository userPurchaseRecordRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OffersRepository offersRepository;

	
	public void addNewProduct(Product product) {
		product.setProductEnabled(true);
		productRepository.save(product);
	}
	
	public void updateProductPrice(Integer productId,Integer productPrice) {
	 	Optional<Product> productFromDB = productRepository.findById(productId);
	 	if(productFromDB.isPresent()) {
	 		productFromDB.get().setProductPrice(productPrice);
			productRepository.save(productFromDB.get());
	 	}
	}

	public void updateProductQuantity(Integer productId,Integer productQuantity) {
	 	Optional<Product> productFromDB = productRepository.findById(productId);
	 	if(productFromDB.isPresent()) {
	 		productFromDB.get().setProductQuantity(productQuantity);
			productRepository.save(productFromDB.get());
	 	}
	}

	
	public void updateProduct(Product product) {
	 	Optional<Product> productFromDB = productRepository.findById(product.getProductId());
	 	if(productFromDB.isPresent()) {
	 		productFromDB.get().setProductName(product.getProductName());
	 		productFromDB.get().setCategoryId(product.getCategoryId());
	 		productFromDB.get().setPaymentmode(product.getPaymentmode());
	 		productFromDB.get().setProductPrice(product.getProductPrice());
	 		productFromDB.get().setProductQuantity(product.getProductQuantity());
			productRepository.save(productFromDB.get());
	 	}
	}
	
	public void deleteProduct(Integer productId) {
		productRepository.deleteById(productId);
	}
	
	public void enableDisableProduct(Integer productId, boolean productStatus) {
	 	Optional<Product> productFromDB = productRepository.findById(productId);
	 	if(productFromDB.isPresent()) {
	 		if(productStatus) {
	 			productFromDB.get().setProductEnabled(false);
	 		} else {
	 			productFromDB.get().setProductEnabled(true);
	 		}
			productRepository.save(productFromDB.get());
	 	}	
	}
	
	public List<User> seeAllUserList() {
		return userRepository.findAll();
	}
	
	public List<Category> getCategoryList() {
	    return categoryRepository.findAll();
	}

	public List<Product> getProductListByCategoryId(String categoryId) {
	    return productRepository.findByCategoryId(categoryId);
	}

	
	public List<User> topTenUserByQuantity() {
		return null;
	}
	
	public List<User> topTenUserByPurchase() {
		return null;
	}

	public List<Category> topTenCategoryByQuantity() {
		return null;
	}
	
	public List<Category> topTenCategoryByPurchase() {
		return null;
	}
	
	public List<ProductCommets> getAllCommentsByProductId(int productId) {
		return productCommetsRepository.findByProductId(productId);
	}

	public List<ProductCommets> getAllCommentsByCategory() {
		return null;
	}
	
	public void updateCommets(int commentId,String updatedComment) {
		Optional<ProductCommets> productCommets=productCommetsRepository.findById(commentId);
		if(productCommets.isPresent()) {
			productCommets.get().setComments(updatedComment);
			productCommetsRepository.save(productCommets.get());
		}
		
	}
	
	public void enableDisableCommets(int commentId,boolean isEnabled) {
		Optional<ProductCommets> productCommets=productCommetsRepository.findById(commentId);
		if(productCommets.isPresent()) {
			if(isEnabled) {
			productCommets.get().setEnabled(false);
			} else {
				productCommets.get().setEnabled(true);
			}
			productCommetsRepository.save(productCommets.get());	
		}
	}
	
	public void deleteComments(int commentId) {
		productCommetsRepository.deleteById(commentId);
	}	
	
	public void addProductOffer(Offers offers) {
		
		offers.setEnabled(true);
		offersRepository.save(offers);
	}	
	
	public void updateOffers(int offerId,String updatedOffers) {
		Optional<Offers> productOffers=offersRepository.findById(offerId);
		if(productOffers.isPresent()) {
			productOffers.get().setOffer(updatedOffers);
			offersRepository.save(productOffers.get());
		}
		
	}
	
	public void enableDisableOffers(int offerId,boolean isEnabled) {
		Optional<Offers> productOffers=offersRepository.findById(offerId);
		if(productOffers.isPresent()) {
		if(isEnabled) {
			productOffers.get().setEnabled(false);
			} else {
				productOffers.get().setEnabled(true);
			}
			offersRepository.save(productOffers.get());	
		}
	}
	
	public void deleteOffers(int offerId) {
		offersRepository.deleteById(offerId);
	}	
	
	public List<ProductOfferDetails> viewOffers(int productId) {
		List<ProductOfferDetails> productOfferDetailsList=new ArrayList<>();
		List<Offers> offersList=offersRepository.findByProductId(productId);
		for(Offers offer:offersList) {
			Optional<Product> product = productRepository.findById(offer.getProductId());
			if(product.isPresent()) {
				ProductOfferDetails productOfferDetails=new ProductOfferDetails();
				productOfferDetails.setOfferId(offer.getOfferId());
				productOfferDetails.setProductId(product.get().getProductId());
				productOfferDetails.setProductName(product.get().getProductName());
				productOfferDetails.setProductOffer(offer.getOffer());
				productOfferDetails.setProductPrice(product.get().getProductPrice());
				productOfferDetails.setEnabled(offer.isEnabled());
				productOfferDetailsList.add(productOfferDetails);
			}	
		}
		return productOfferDetailsList;
	}
	

	
}
