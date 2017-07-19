package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Error;

@RestResource(path="/error")
public interface ErrorRepository extends JpaRepository<Error, Integer> {

}
