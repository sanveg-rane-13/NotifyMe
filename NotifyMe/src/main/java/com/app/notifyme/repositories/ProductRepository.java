package com.app.notifyme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.app.notifyme.models.Product;

@RestResource(path="/product")
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
