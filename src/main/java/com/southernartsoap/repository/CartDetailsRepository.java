package com.southernartsoap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.southernartsoap.model.CartDetails;

public interface CartDetailsRepository extends CrudRepository<CartDetails, Long> {
	List<CartDetails> findAll();
}
