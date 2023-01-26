package com.aster.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;
import com.aster.app.Repository.CartRepo;
import com.aster.app.Repository.ProductRepo;

@Service
public class CartServiceImpl implements CartServices{
	@Autowired
	CartRepo cartRepo;
	@Autowired 
	ProductRepo productRepo;
	
	public Cart createCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public List<Product> getAllItems(int cart_id)
	{
		return cartRepo.findAllProducts(cart_id);
	}
	
	public boolean modifyCart(int product_id, int cart_id, int quantity)
	{
		Product product=productRepo.findById(product_id).get();
		Cart cart=cartRepo.findById(cart_id).get();

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
		return true;
	}
}
