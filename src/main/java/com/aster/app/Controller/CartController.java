package com.aster.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;
import com.aster.app.Service.CartServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@PostMapping("/createCart")
	public ResponseEntity<?> newCart(@Valid @RequestBody Cart cart) {
		return new ResponseEntity<Cart>(cartServiceImpl.createCart(cart), HttpStatus.CREATED); 
	}
	@GetMapping("/{cartId}")
	public ResponseEntity<?> displayAllItems(@Valid @PathVariable int cartId)
	{
		return new ResponseEntity<List<Product>>(cartServiceImpl.getAllItems(cartId), HttpStatus.OK);
	}	
	@PutMapping("/{productId}")
	public ResponseEntity<?> modifyCart(@Valid @PathVariable int productId, @Valid @RequestParam("cartId") int cartId, @Valid @RequestParam("quantity") int quantity)
	{
		return new ResponseEntity<Boolean>(cartServiceImpl.modifyCart(productId, cartId, quantity), HttpStatus.OK);
	}
}
