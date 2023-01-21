package com.aster.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aster.app.Entity.Product;
import com.aster.app.Service.ProductServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@PostMapping("/createProduct")
	public ResponseEntity<?> newProduct(@Valid @RequestBody Product product) {
		return new ResponseEntity<Product>(productServiceImpl.createProduct(product), HttpStatus.CREATED); 
	}	
	@GetMapping("/getProduct/{product_id}")
	public ResponseEntity<?> getProduct(@Valid @PathVariable int product_id)
	{
		return new ResponseEntity<Product>(productServiceImpl.getProduct(product_id),HttpStatus.OK);
	}
	@GetMapping("/getProducts")
	public ResponseEntity<?> displayAllProduct()
	{
		return new ResponseEntity<List<Product>>(productServiceImpl.getAllProducts(),HttpStatus.OK);
	}
	@PostMapping("/updateProduct/{product_id}/quantity/{quantity}")
	public ResponseEntity<?> updateProductQuantity(@Valid @RequestBody int product_id, @Valid @RequestBody int quantity) {
		return new ResponseEntity<Boolean>(productServiceImpl.updateProductQuantity(product_id, quantity), HttpStatus.CREATED); 
	}	
}
