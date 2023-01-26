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
/* Here I have implemented 3 functions:
 * 1. Creating a new cart
 * 2. Modifying a cart: 
 * 					  -- Adding a new item to cart
 * 					  -- Removing an item from the cart completely
 * 					  -- Updating a particular item's quantity
 * 3. Displaying all items from a particular cart
 */
