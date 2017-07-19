package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Product;

@RestResource(path = "/product")
public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByProductId(@Param(value = "product_id") int product_id);
	
}
