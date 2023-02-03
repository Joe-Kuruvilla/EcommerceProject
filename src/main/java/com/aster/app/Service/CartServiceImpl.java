package com.aster.app.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;
import com.aster.app.Entity.User;
import com.aster.app.Repository.CartRepo;
import com.aster.app.Repository.ProductRepo;
import com.aster.app.Repository.UserRepository;

@Service
public class CartServiceImpl implements CartServices{
//	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Autowired
	CartRepo cartRepo;
	@Autowired 
	ProductRepo productRepo;
	@Autowired
	UserRepository userRepo;
	
//	public Cart createCart(Cart cart) {
//		return cartRepo.save(cart);
//		
//	}
	public boolean createCart(Cart cart, int userId) {
		cartRepo.save(cart);
		
		User user=userRepo.findById(userId).get();
		user.setCart(cart);
		userRepo.save(user);
		return true;
	}
	
	public List<Product> getAllItems(int cart_id)
	{
		return cartRepo.findAllProducts(cart_id);
	}
	
	public boolean modifyCart(int product_id, int cart_id, int quantity)
	{
//		LOGGER.trace("Inside the modify cart method");
		
		Product product=productRepo.findById(product_id).get();
//		LOGGER.debug("Checing the product "+product);
		
		Cart cart=cartRepo.findById(cart_id).get();
//		LOGGER.debug("Checing the cart "+cart);
		
		int initialStoreQuantity=product.getQuantity();
		
		//First we check if the product is present in cart or not
		if(cart.getProducts().contains(product))
		{
			/* Now we see if we want to remove the product from the cart, 
			 * for that we pass 0 as quantity 
			 * and the product is removed
			*/
			if(quantity==0)
			{
				int quantityBeforeDeletion=0;
				for(Product prd: cart.getProducts())
				{
					if(prd.equals(product))
						quantityBeforeDeletion=prd.getQuantity();	
				}
				product.setQuantity(initialStoreQuantity+quantityBeforeDeletion);
				productRepo.save(product);
				cart.getProducts().remove(product);
				cartRepo.save(cart);
			}
			else 
			{
				int quantityBeforeUpdation=0;
				for(Product prd: cart.getProducts())
				{
					if(prd.equals(product))
						quantityBeforeUpdation=prd.getQuantity();	
				}
				int quantityDiff=quantityBeforeUpdation-quantity;
				product.setQuantity(quantityDiff+initialStoreQuantity);
				productRepo.save(product);
				
				cart.getProducts().remove(product);
				product.setQuantity(quantity);
				cart.getProducts().add(product);
				cartRepo.save(cart);
			}
		}// If the item doesn't exist in cart itself, then we add the item to the cart
		else 
		{
			product.setQuantity(initialStoreQuantity-quantity);
			productRepo.save(product);
			product.setQuantity(quantity);
			cart.getProducts().add(product);
			cartRepo.save(cart);
		}
		
//		LOGGER.info("Modification Successful");
		return true;
	}
}
