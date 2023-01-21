package com.aster.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;
import com.aster.app.Service.CartServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ecommerce")
public class CartController {
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@PostMapping("/createCart")
	public ResponseEntity<?> newCart(@Valid @RequestBody Cart cart) {
		return new ResponseEntity<Cart>(cartServiceImpl.createCart(cart), HttpStatus.CREATED); 
	}
	@GetMapping("/getCartDetails/{cartId}")
	public ResponseEntity<?> displayAllItems(@Valid @PathVariable int cartId)
	{
		return new ResponseEntity<List<Product>>(cartServiceImpl.getAllItems(cartId), HttpStatus.OK);
	}	
	@PostMapping("/update/{productId}/cart/{cartId}/quantity/{quantity}")
	public ResponseEntity<?> increase(@Valid @PathVariable int productId, @Valid @PathVariable int cartId, @Valid @PathVariable int quantity)
	{
		return new ResponseEntity<Boolean>(cartServiceImpl.modifyCart(productId, cartId, quantity), HttpStatus.OK);
	}
	
	
//	@PostMapping("/update/decreaseQuantity/{productId}/cart/{cartId}")
//	public ResponseEntity<?> decrease(@Valid @PathVariable int productId, @Valid @PathVariable int cartId)
//	{
//		return new ResponseEntity<Boolean>(cartServiceImpl.decreaseQuantity(productId, cartId), HttpStatus.OK);
//	}
//	@DeleteMapping("/delete/{productId}/cart/{cartId}")
//	public ResponseEntity<?> deleteProduct(@Valid @PathVariable int productId, @Valid @PathVariable int cartId)
//	{
//		return new ResponseEntity<Boolean>(cartServiceImpl.removeProduct(productId, cartId), HttpStatus.OK);
//	}
//	@PostMapping("/addProduct/{productId}/cart/{cartId}")
//	public ResponseEntity<?> addNewProduct(@Valid @PathVariable int productId, @Valid @PathVariable int cartId) {
//		return new ResponseEntity<Boolean>(cartServiceImpl.addProduct(productId, cartId), HttpStatus.CREATED); 
//	}
}
