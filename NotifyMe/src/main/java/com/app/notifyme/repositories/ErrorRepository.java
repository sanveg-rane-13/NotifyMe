package com.app.notifyme.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Error;

@RestResource(path="/error")
public interface ErrorRepository extends JpaRepository<Error, Integer> {

	@Query("SELECT e FROM Error e where e.verdict = :verdict") 
    List<Error> findByVerdict(@Param("verdict") int verdict);
    
    @Query("SELECT e FROM Error e where e.errorId = :id") 
    Error findById(@Param("id") Integer id);	
    
}
