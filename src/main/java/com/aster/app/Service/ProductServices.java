package com.aster.app.Service;

import java.util.List;

import com.aster.app.Entity.Product;

public interface ProductServices 
{
	public Product createProduct(Product product);
	public Product getProduct(int id);
	public List<Product> getAllProducts();
	public boolean updateProductQuantity(int product_id,int quantity);
}
/* Four Functionalities:
 *1. Creating new products
 *2. Finding product by ID
 *3. Listing all the available products in the shop
 *4. Updating the quantity of a particular product
 */
