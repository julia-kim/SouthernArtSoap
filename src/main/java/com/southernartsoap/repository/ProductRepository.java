package com.southernartsoap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.southernartsoap.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAll();

	Product findById(long id);

	List<Product> findByCategory(String category);

	@Query("SELECT DISTINCT p.category FROM Product p")
	List<String> findDistinctCategories();
}