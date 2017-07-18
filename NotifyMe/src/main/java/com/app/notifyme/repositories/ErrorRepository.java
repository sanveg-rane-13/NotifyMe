package com.app.notifyme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.notifyme.models.Error;

@Repository
public interface ErrorRepository extends CrudRepository<Error, Integer> {

}
