package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Productstat;

@RestResource(path="/prodstat")
public interface ProductStatRepository extends JpaRepository<Productstat, Integer> {

}
