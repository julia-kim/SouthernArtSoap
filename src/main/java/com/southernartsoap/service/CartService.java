package com.southernartsoap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.southernartsoap.model.Cart;
import com.southernartsoap.model.CartDetails;
import com.southernartsoap.model.Product;
import com.southernartsoap.model.User;
import com.southernartsoap.repository.CartDetailsRepository;
import com.southernartsoap.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired 
	private CartDetailsRepository cartDetailsRepository;

	public List<CartDetails> findAll() {
		return cartDetailsRepository.findAll();
	}
	
	public Cart findCartByUser(User user) {
		return cartRepository.findCartByUser(user);
	}

}
