package com.app.notifyme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.notifyme.models.Usercriteria;


@Repository
public interface UserCriteriaRepository extends CrudRepository<Usercriteria, Integer> {

}
