package com.example.onlineshop.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlineshop.demo.entity.Offers;
import com.example.onlineshop.demo.entity.Product;
import com.example.onlineshop.demo.entity.ProductCommets;
import com.example.onlineshop.demo.entity.ProductOfferDetails;
import com.example.onlineshop.demo.entity.UserCart;
import com.example.onlineshop.demo.entity.UserCartCompositeKey;
import com.example.onlineshop.demo.entity.UserCartDetails;
import com.example.onlineshop.demo.entity.UserPurchaseRecord;
import com.example.onlineshop.demo.entity.UserPurchaseRecordDetails;
import com.example.onlineshop.demo.repository.CategoryRepository;
import com.example.onlineshop.demo.repository.OffersRepository;
import com.example.onlineshop.demo.repository.ProductCommetsRepository;
import com.example.onlineshop.demo.repository.ProductRepository;
import com.example.onlineshop.demo.repository.UserCartRepository;
import com.example.onlineshop.demo.repository.UserPurchaseRecordRepository;
import com.example.onlineshop.demo.repository.UserRepository;

@Service
public class UserService {

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
	
	public List<Product> getProductListByCategoryIdForUser(String categoryId) {
	    return productRepository.findByCategoryIdAndIsProductEnabled(categoryId,true);
	}

	
	public List<UserCartDetails> getAllItemFromCart(int userId) {
		//return userCartRepository.findyUserId(userId);
		List<UserCartDetails> userCartDetailsList=new ArrayList();
		List<UserCart> userCartList=new ArrayList<>();
		userCartList=userCartRepository.findByUserId(userId);
		for(UserCart userCart:userCartList) {
			Optional<Product> product=this.productRepository.findById(userCart.getProductId());
			if(product.isPresent()) {
				UserCartDetails userCartDetails=new UserCartDetails();
				userCartDetails.setUserId(userCart.getUserId());
				userCartDetails.setProductId(userCart.getProductId());
				userCartDetails.setProductName(product.get().getProductName());
				userCartDetails.setProductPrice(product.get().getProductPrice());
				userCartDetails.setAddDate(userCart.getAddDate());
				userCartDetails.setQuantity(userCart.getQuantity());
				userCartDetailsList.add(userCartDetails);
			}
		}	
		return userCartDetailsList;
	}
	
	public void addNewItemIntoCart(UserCart userCart) {
		userCart.setAddDate(LocalDateTime.now().toString());
		userCartRepository.save(userCart);
	}
	
	public void updateProductQuantityFromCart(int userId,int productId,int quantity) {
		Optional<UserCart> userCart=userCartRepository.findById(new UserCartCompositeKey(userId, productId));
		if(userCart.isPresent()) {
			userCart.get().setQuantity(quantity);
			userCartRepository.save(userCart.get());	
		}
	}

	
	public void deleteItemFromCart(int userId,int productId) {
		userCartRepository.deleteById(new UserCartCompositeKey(userId, productId));
	}
	
	public void buyItemFromCart(UserCart userCart) {
		
		Optional<Product> product = productRepository.findById(userCart.getProductId());
		if(product.isPresent()) {
			product.get().setProductQuantity(product.get().getProductQuantity()-userCart.getQuantity());
			productRepository.save(product.get());
			UserPurchaseRecord userPurchaseRecord =new UserPurchaseRecord();
			userPurchaseRecord.setPrice(product.get().getProductPrice());
			userPurchaseRecord.setProductId(userCart.getProductId());
			userPurchaseRecord.setPurchaseDate(LocalDateTime.now().toString());
			userPurchaseRecord.setQuantity(userCart.getQuantity());
			userPurchaseRecord.setUserId(userCart.getUserId());
			userPurchaseRecordRepository.save(userPurchaseRecord);
			
			userCartRepository.deleteById(new UserCartCompositeKey(userCart.getUserId(), userCart.getProductId()));
			
		}
	}
	
	public List<UserPurchaseRecordDetails> viewAllPreviouslyPurchasedItem(int userId) {
		List<UserPurchaseRecordDetails> userPurchaseRecordDetailsList=new ArrayList<>();
		List<UserPurchaseRecord> userPurchaseRecordList =this.userPurchaseRecordRepository.findByUserId(userId);
		for(UserPurchaseRecord userPurchaseRecord:userPurchaseRecordList) {
			Optional<Product> product = productRepository.findById(userPurchaseRecord.getProductId());
			if(product.isPresent()) {
				UserPurchaseRecordDetails userPurchaseRecordDetails=new UserPurchaseRecordDetails();
				userPurchaseRecordDetails.setPrice(product.get().getProductPrice());
				userPurchaseRecordDetails.setProductId(product.get().getProductId());
				userPurchaseRecordDetails.setProductName(product.get().getProductName());
				userPurchaseRecordDetails.setPurchaseDate(userPurchaseRecord.getPurchaseDate());
				userPurchaseRecordDetails.setQuantity(userPurchaseRecord.getQuantity());
				userPurchaseRecordDetails.setUserId(userId);
				userPurchaseRecordDetailsList.add(userPurchaseRecordDetails);
			}
		}
		return userPurchaseRecordDetailsList;
	}
	
	public List<UserPurchaseRecordDetails> topTenSellingItemByPrice() {
		List<UserPurchaseRecordDetails> userPurchaseRecordDetailsList=new ArrayList<>();
		List<UserPurchaseRecord> userPurchaseRecordList =this.userPurchaseRecordRepository.findTop10ByOrderByPriceDesc();
		for(UserPurchaseRecord userPurchaseRecord:userPurchaseRecordList) {
			Optional<Product> product = productRepository.findById(userPurchaseRecord.getProductId());
			if(product.isPresent()) {
				UserPurchaseRecordDetails userPurchaseRecordDetails=new UserPurchaseRecordDetails();
				userPurchaseRecordDetails.setPrice(product.get().getProductPrice());
				userPurchaseRecordDetails.setProductId(product.get().getProductId());
				userPurchaseRecordDetails.setProductName(product.get().getProductName());
				userPurchaseRecordDetails.setPurchaseDate(userPurchaseRecord.getPurchaseDate());
				userPurchaseRecordDetails.setQuantity(userPurchaseRecord.getQuantity());
				userPurchaseRecordDetails.setUserId(userPurchaseRecord.getUserId());
				userPurchaseRecordDetailsList.add(userPurchaseRecordDetails);
			}
		}
		return userPurchaseRecordDetailsList;
	}
	
	public List<ProductOfferDetails> viewOffersForUser() {
		List<ProductOfferDetails> productOfferDetailsList=new ArrayList<>();
		List<Offers> offersList=offersRepository.findAll();
		for(Offers offer:offersList) {
			Optional<Product> product = productRepository.findById(offer.getProductId());
			if(product.isPresent() && offer.isEnabled()) {
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

	public List<ProductCommets> getAllCommentsByProductIdForUser(int productId) {
		return productCommetsRepository.findByProductIdAndIsEnabled(productId, true);
	}	
	
	public void addCommentsForProducts(ProductCommets productCommets) {
		/*ProductCommets productCommets=new ProductCommets();
		productCommets.setComments(comment);
		productCommets.setProductId(productId);
		productCommets.setUserId(userId);*/
		productCommets.setCommentsDate(LocalDateTime.now().toString());
		productCommets.setEnabled(true);
		this.productCommetsRepository.save(productCommets);
		
	}
	
}
