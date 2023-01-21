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
		if(cart.getProducts().contains(product))
		{
			if(quantity==0)
			{
				cart.getProducts().remove(product);
				cartRepo.save(cart);
			}
			else 
			{
				product.setQuantity(quantity);
				productRepo.save(product);
			}
		}
		else 
		{
			cart.getProducts().add(product);
			cartRepo.save(cart);
		}
		return true;
	}
}
