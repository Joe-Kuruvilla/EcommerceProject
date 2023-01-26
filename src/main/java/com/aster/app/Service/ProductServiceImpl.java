package com.aster.app.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;
import com.aster.app.Repository.CartRepo;
import com.aster.app.Repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductServices
{
	@Autowired
	ProductRepo productRepo;
	@Autowired
	CartRepo cartRepo;
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	public Product getProduct(int id)
	{
		return productRepo.findById(id).get();
	}
	public List<Product> getAllProducts()
	{
		return (List<Product>) productRepo.findAll();
	}
	public boolean updateProductQuantity(int product_id,int quantity)
	{
		if(quantity>0)
		{
			Product product=productRepo.findById(product_id).get();
			product.setQuantity(quantity);
			productRepo.save(product);
			return true;
		}
		else
		{
			throw new IllegalArgumentException("Quantity cannot be less than 1");
		}
	}
}
