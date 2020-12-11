package com.southernartsoap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.southernartsoap.model.Image;
import com.southernartsoap.model.Product;
import com.southernartsoap.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	private int pageSize = 12;
	private Pageable pageable;

	public Product findById(long id) {
		return productRepository.findById(id);
	}

	public Page<Product> findAll(int pageNum) {
		pageable = PageRequest.of(pageNum - 1, pageSize);
		return productRepository.findAll(pageable);
	}

	public List<String> findDistinctCategories() {
		return productRepository.findDistinctCategories();
	}

	public Page<Product> findByCategory(String category, int pageNum) {
		pageable = PageRequest.of(pageNum - 1, pageSize);
		if (category == null)
			return productRepository.findAll(pageable);
		else
			return productRepository.findByCategory(category, pageable);
	}

	public List<Image> findAllProductImagesByProductId(long id) {
		Product product = productRepository.findById(id);
		return product.getImage();
	}
}