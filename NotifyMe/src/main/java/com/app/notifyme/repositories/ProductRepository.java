package com.app.notifyme.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.notifyme.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
