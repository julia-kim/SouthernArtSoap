package com.southernartsoap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southernartsoap.model.Image;
import com.southernartsoap.model.Product;
import com.southernartsoap.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(long id) {
		return productRepository.findById(id);
	}

	public List<String> findDistinctCategories() {
		return productRepository.findDistinctCategories();
	}

	public List<Product> findByCategory(String category) {
		if (category == null)
			return productRepository.findAll();
		else
			return productRepository.findByCategory(category);
	}
	
	public List<Image> findAllProductImagesByProductId(long id) {
		Product product = productRepository.findById(id);
		return product.getImage();
	}
	
	public List<Product> findByNameIgnoreCaseContaining(String query) {
        return productRepository.findByNameIgnoreCaseContaining(query);
    }
}