package com.app.notifyme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Productstat;

@RestResource(path = "/prodstat")
public interface ProductStatRepository extends JpaRepository<Productstat, Integer> {

	public static final String ALL_STAT = "select * from productstat where product_id like :id";
	public static final String FIND_MINIMUM_PRICE = "select MIN(price) from productstat where product_id like :id";
	public static final String FIND_MINIMUM_PRICE_STAT = "select * from productstat where price = :min_price LIMIT 1,1";

	@Query(value = FIND_MINIMUM_PRICE, nativeQuery = true)
	public double findProductMinValue(@Param("id") int prodId);
	
	@Query(value = FIND_MINIMUM_PRICE_STAT, nativeQuery = true)
	public Productstat findMinValueStat(@Param("min_price") double minPrice);
	
	@Query(value = ALL_STAT, nativeQuery = true)
	public List<Productstat> getUserData(@Param("id") int prodId);
}
