package com.aster.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aster.app.Entity.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
//For Product table