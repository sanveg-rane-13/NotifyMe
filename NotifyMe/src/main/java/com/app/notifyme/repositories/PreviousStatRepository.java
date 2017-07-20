package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Previousstat;

@RestResource(path = "/prevstat")
public interface PreviousStatRepository extends JpaRepository<Previousstat, Integer> {

}
