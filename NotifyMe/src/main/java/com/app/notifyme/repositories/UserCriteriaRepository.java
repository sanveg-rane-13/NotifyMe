package com.app.notifyme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Usercriteria;

@RestResource(path = "criteria")
public interface UserCriteriaRepository extends JpaRepository<Usercriteria, Integer> {

	public static final String FIND_PRODUCT = "select * from usercriteria where product_id = :prod_id";

	public static final String LAST_SEARCHED_PRODUCT = "select product_id from usercriteria where email like :email order by date desc, track_id desc LIMIT 1";

	public static final String ALL_TRACKED_PRODUCTS = "select product_id from usercriteria where email like :email";

	public static final String FIND_SUM_OF_PRODUCT = "select sum(counter) as sum from" + " (select count(*) as counter"
			+ " from usercriteria where product_id in :pids"
			+ " and date between date_sub(now(),interval 7 day) and now()"
			+ " group by product_id having counter is not null) as t";

	@Query(value = FIND_PRODUCT, nativeQuery = true)
	public List<Usercriteria> getCriteria(@Param("prod_id") int productId);

	@Query(value = LAST_SEARCHED_PRODUCT, nativeQuery = true)
	public int getLastProductFromUser(@Param("email") String emailId);

	@Query(value = ALL_TRACKED_PRODUCTS, nativeQuery = true)
	public List<Integer> getAllProductsOfUser(@Param("email") String emailId);

	@Query(value = FIND_SUM_OF_PRODUCT, nativeQuery = true)
	Integer getSumOfProductCount(@Param("pids") List<Integer> pids);

	@Query(value = "select c from Usercriteria c where c.trackId=:id")
	public Usercriteria findByTrackId(@Param("id") int trackId);
}
