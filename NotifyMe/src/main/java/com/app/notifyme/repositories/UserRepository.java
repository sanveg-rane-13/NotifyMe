package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.User;

@RestResource(path="/user")
public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(@Param(value = "email")String email);
}
