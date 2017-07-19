package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Usercriteria;


@RestResource(path="criteria")
public interface UserCriteriaRepository extends JpaRepository<Usercriteria, Integer> {

}
