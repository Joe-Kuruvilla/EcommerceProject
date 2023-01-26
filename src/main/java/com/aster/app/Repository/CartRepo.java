package com.aster.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aster.app.Entity.Cart;
import com.aster.app.Entity.Product;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	@Query("Select products from Cart where id=?1")
	public List<Product> findAllProducts(int id);
}
//This is the CartRepo layer which basically interacts with the DB to make the cart table and perform
//CRUD operations on it, mapping objects to entities and also executing SQL statements
//Main reason is so that we can focus on the business logic and not worry about the DB connectivity and stuff