package com.training.demo.dao;

import com.training.demo.model.Prodct;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Prodct, Integer>{

}
