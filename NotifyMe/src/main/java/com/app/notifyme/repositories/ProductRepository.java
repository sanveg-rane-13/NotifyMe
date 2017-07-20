package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Product;

@RestResource(path = "/product")
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public static final String FIND_EXISTING_PRODUCT = "select * from products where url like :product_url";
	
	Product findByProductId(@Param(value = "product_id") int product_id);
	
	@Query(value=FIND_EXISTING_PRODUCT,nativeQuery=true)
    public Product findProduct(@Param("product_url") String productUrl);
	
}
