package com.app.notifyme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Previousstat;

@RestResource(path = "/prevstat")
public interface PreviousStatRepository extends JpaRepository<Previousstat, Integer> {
	public static final String ALL_STAT = "select * from previousstat where product_id = :id";
	
	@Query(value = ALL_STAT, nativeQuery = true)
	public List<Previousstat> getUserData(@Param("id") int prodId);
}
