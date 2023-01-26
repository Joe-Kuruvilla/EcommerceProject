package com.aster.app.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart 
{
	@Id
	private int id;
	@OneToMany
	private List<Product> products;
}

// Cart entity has 2 attributes, one is the cartId and a List of products
// One cart can have many products and so one to many mapping is used.
