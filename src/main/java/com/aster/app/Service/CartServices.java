package com.aster.app.Service;

import java.util.List;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;

public interface CartServices 
{
	public Cart createCart(Cart cart);
	public boolean modifyCart(int product_id, int cart_id, int quantity);
	public List<Product> getAllItems(int cart_id);
}
