package com.app.notifyme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.notifyme.models.Productstat;

@Repository
public interface ProductStatRepository extends CrudRepository<Productstat, Integer> {

}
