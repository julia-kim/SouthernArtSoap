package com.southernartsoap.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.southernartsoap.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findById(long id);
	
	Page<Product> findAll(Pageable pageable);

	Page<Product> findByCategory(String category, Pageable pageable);

	@Query("SELECT DISTINCT p.category FROM Product p")
	List<String> findDistinctCategories();
	
	// @Query("SELECT p FROM Product p WHERE p.name LIKE %:query%")
	List<Product> findByNameIgnoreCaseContaining(String query);
}