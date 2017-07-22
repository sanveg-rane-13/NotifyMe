package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.User;

@RestResource(path = "/user")
public interface UserRepository extends JpaRepository<User, String> {
	public static final String FIND_USER_BY_ID = "select * from user where email = :email_id";
	
	@Query(value = FIND_USER_BY_ID, nativeQuery = true)
	public User getUserById(@Param("email_id") String email);
}
