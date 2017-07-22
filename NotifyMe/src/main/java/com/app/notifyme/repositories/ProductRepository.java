package com.app.notifyme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Product;

@RestResource(path = "/product")
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public static final String FIND_EXISTING_PRODUCT = "select * from products where url like :product_url";

	public static final String GET_PRODUCT_NAME = "select product_name from products where product_id like :id";

	public static final String FIND_PRODUCT_NAMES = "select distinct(product_name) from products";

	public static final String FIND_PRODUCT_IDS = "select product_id from products where product_name like :product_name";

	Product findByProductId(@Param(value = "product_id") int product_id);

	@Query(value = FIND_EXISTING_PRODUCT, nativeQuery = true)
	public Product findProduct(@Param("product_url") String productUrl);

	@Query(value = GET_PRODUCT_NAME, nativeQuery = true)
	public String getProductName(@Param("id") int productId);

	@Query(value = FIND_PRODUCT_NAMES, nativeQuery = true)
	public List<String> findProductNames();

	@Query(value = FIND_PRODUCT_IDS, nativeQuery = true)
	public List<Integer> findProductIds(@Param("product_name") String productName);
}
