package com.app.notifyme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.notifyme.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
